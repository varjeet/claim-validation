package com.sap.claimvalidation.service;

import com.sap.claimvalidation.dtos.ClaimResponseDto;
import com.sap.claimvalidation.entities.Claim;
import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.repository.ClaimRepository;
import com.sap.claimvalidation.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import java.util.*;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final NotificationService notificationService;


    ClaimService(ClaimRepository claimRepository, NotificationService notificationService){
        this.claimRepository=claimRepository;
        this.notificationService = notificationService;
    }


    public Claim addClaim(Claim claim)
    {
        Random r = new Random();
        int low = 10000;
        int high = 99999;
        int result = r.nextInt(high-low) + low;
        claim.setClaim_number(result);
        Claim claimResponse= this.claimRepository.save(claim);

        return claimResponse;

    }
    public List<ClaimResponseDto> getAllClaims(){
        List<Claim> claims= this.claimRepository.findAll();
        List<ClaimResponseDto> claimResponseDtos = ObjectMapperUtils.mapAll(claims, ClaimResponseDto.class);

       return claimResponseDtos;
    }
    public ClaimResponseDto getClaimById(String id){
        Optional<Claim> Optionalitem=this.claimRepository.findById(id);
        Claim claim= Optionalitem.get();
       ClaimResponseDto claimResponseDto = ObjectMapperUtils.map(claim, ClaimResponseDto.class);
        return claimResponseDto;
    }
//    public ClaimResponseDto getClaimById1(String id){
//        List<Claim> claims= this.claimRepository.findAll();
//        List<ClaimResponseDto> claimResponseDtos = ObjectMapperUtils.mapAll(claims, ClaimResponseDto.class);
//        ClaimResponseDto claimResponseDto=  claimResponseDtos.stream().filter(claimResponseDto1 -> claimResponseDto1.getId().equals(id)).findFirst().get();
//        return claimResponseDto;
//    }


    public Claim getClaimByVersionAndItem(String versionId,String itemId){
        Claim claim=this.claimRepository.findClaimByVersionIdAndItemId(versionId,itemId);
        return claim;
    }

    public void deleteClaim(String claimId)
    {

       this.claimRepository.deleteById(claimId);

    }

    public List<ClaimResponseDto> getClaimsByOrganization(String organization){
        List<Claim> claims= this.claimRepository.findAll();
        List<ClaimResponseDto> claimResponseDtos = ObjectMapperUtils.mapAll(claims, ClaimResponseDto.class);
        return claimResponseDtos;
    }
    public Claim getClaimByVersionId(String id){
        Claim claim=this.claimRepository.findClaimByVersion_id(id);
        return claim;
    }

    public List<ClaimResponseDto> getClaimsByVersionIds(List<String> versionIds){
        List<Claim> claims=new ArrayList<>();
        for(int i=0;i< versionIds.size();i++){
            Claim claim=  getClaimByVersionId(versionIds.get(i));
                 claims.add(claim);
        }
        List<ClaimResponseDto> claimResponseDtos = ObjectMapperUtils.mapAll(claims, ClaimResponseDto.class);
        return claimResponseDtos;

    }

}
