package com.sap.claimvalidation.controller;

import com.sap.claimvalidation.dtos.*;
import com.sap.claimvalidation.entities.*;
import com.sap.claimvalidation.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClaimController {
    private final ClaimService claimService;
    private final ItemService itemService;
    private final VersionService versionService;
    private final NotificationService notificationService;
    private final HeaderService headerService;

    ClaimController(ClaimService claimService, ItemService itemService, VersionService versionService, NotificationService notificationService, HeaderService headerService){
        this.claimService=claimService;
        this.itemService = itemService;
        this.versionService = versionService;
        this.notificationService = notificationService;
        this.headerService = headerService;
    }

    @PostMapping("v1/claim/saveClaim")
    public ResponseEntity<Claim> addClaim(@RequestBody ClaimRequestDto claim){
        if(claim != null){
            HeaderRequestDto headerRequestDto=claim.getHeader();
            Header headerResponse= headerService.addHeader(headerRequestDto);
            String headerId = headerResponse.getId();
              VersionRequestDto version= claim.getVersion();
              Version versionResponse=versionService.addVersion(version,headerResponse);
             String versionId = versionResponse.getId();
               ItemRequestDto item=claim.getItem();
              Item itemResponse =itemService.addItem(item,versionResponse);
              String itemId=itemResponse.getId();

             Claim claimRequest=new Claim();
             claimRequest.setItem_id(itemId);
             claimRequest.setVersion_id(versionId);
             claimRequest.setHeader_id(headerId);
             Claim claimResponse=claimService.addClaim(claimRequest);

             //saving notofication
            Notification notification=new Notification();
            notification.setClaimId(claimResponse.getId());
            notification.setDescription("A claim has been raised by customer "+claim.getHeader().getCustomer()+" for amount "+claim.getItem().getAmount());
            notification.setOrganisation(claim.getVersion().getPurchasingOrganization());
            notification.setTitle("New Claim raised");
            notification.setViewed(false);
            notification.setCreatedAt(new Date().toString());
            notificationService.addNotification(notification);

            return new ResponseEntity<>(claimResponse, HttpStatus.CREATED);

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/claim/getAllClaims")
    public ResponseEntity<List<ClaimResponseDto>> getAllClaims(){
        return new ResponseEntity<>(claimService.getAllClaims(),HttpStatus.OK);
    }

    @GetMapping("/claim/getClaim/{claimId}")
    public ResponseEntity<ClaimResponseDto> getClaimById(@PathVariable(value = "claimID") String claimId){
        return new ResponseEntity<>(claimService.getClaimById(claimId),HttpStatus.OK);
    }
    @DeleteMapping("/claim/deleteClaim/{claimId}")
    public ResponseEntity<Void> deleteClaim(@PathVariable("claimId") String claimId)
    {
        this.claimService.deleteClaim(claimId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PutMapping("/claim/updateClaim/{claimId}")
    public ResponseEntity<ClaimResponseDto> updateClaim(@RequestBody ClaimRequestDto claim, @PathVariable(value = "claimId") String claimId)
    {
        Notification notification=new Notification();
        notification.setClaimId(claimId);
        notification.setDescription("A claim has been updated and resubmitted by customer "+claim.getHeader().getCustomer()+" for amount "+claim.getItem().getAmount());
        notification.setOrganisation(claim.getVersion().getPurchasingOrganization());
        notification.setTitle("Claim Updated");
        notification.setViewed(false);
        notification.setCreatedAt(new Date().toString());
        notificationService.addNotification(notification);
        ClaimResponseDto claimRes=claimService.getClaimById(claimId);
        String itemId=claimRes.getItem().getId();
        ItemRequestDto item=claim.getItem();
         this.itemService.updateItem(item,itemId);

        String versionId=claimRes.getVersion().getId();
        VersionRequestDto version= claim.getVersion();
        this.versionService.updateVersion(version,versionId);

        String headerId=claimRes.getHeader().getId();
        HeaderRequestDto header=claim.getHeader();
        this.headerService.updateHeader(header,headerId);

        return new ResponseEntity<>(claimRes,HttpStatus.OK);



    }

    @GetMapping("/claim/getClaim")
    public ResponseEntity<List<ClaimResponseDto>> getClaimsByOrganization(@RequestParam(value = "salesOrganisation") String salesOrganisation){
        List<String> versionIds=versionService.getVersionBySalesOrganization(salesOrganisation).stream().map(version -> version.getId()).collect(Collectors.toList());
        return new ResponseEntity<>(claimService.getClaimsByVersionIds(versionIds),HttpStatus.OK);
    }



}
