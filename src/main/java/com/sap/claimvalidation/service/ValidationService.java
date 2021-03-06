package com.sap.claimvalidation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sap.claimvalidation.dtos.ResultResponseDto;
import com.sap.claimvalidation.dtos.VersionRequestDto;
import com.sap.claimvalidation.entities.*;
import com.sap.claimvalidation.repository.ClaimRepository;
import com.sap.claimvalidation.repository.ItemRepository;
import com.sap.claimvalidation.utils.CpbrConnection;
import com.sap.claimvalidation.utils.VersionStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ValidationService {
    private final ClaimRepository claimRepository;
    private final ItemRepository itemRepository;

    private final VersionService versionService;
    private final ResultService resultService;
    private final NotificationService notificationService;


    ValidationService(ClaimRepository claimRepository, ItemRepository itemRepository, VersionService versionService, ResultService resultService, NotificationService notificationService){
        this.claimRepository=claimRepository;
        this.itemRepository=itemRepository;
        this.versionService = versionService;
        this.resultService = resultService;
        this.notificationService = notificationService;
    }
    public ResultResponseDto validateClaim(String claimId) throws JsonProcessingException {
        Claim claim=this.claimRepository.findById(claimId).get();
        Validation validation = new Validation();
        validation.setRuleServiceId("d91d77eedfd94aaab5b427f1261dc036");
        validation.setVocabulary(claim);
        CpbrConnection cpbrConnection=new CpbrConnection();
        List<ResultDetail> resultDetails=  cpbrConnection.setValidationRule(validation);
        Set<ResultDetail> results=new HashSet<>();
        results.addAll(resultDetails);
        Result result=new Result();
        ResultResponseDto resultResponseDto=new ResultResponseDto() ;

          if(results == null ||results.size()==0){
              String versionId=claim.getVersion_id();
              Version version=versionService.getVersionById(versionId);
              VersionRequestDto versionRequestDto=new VersionRequestDto();
              versionRequestDto.setDistributionChannel(version.getDistributionChannel());
              versionRequestDto.setPersonResponsible(version.getPersonResponsible());
              versionRequestDto.setPurchasingOrganization(version.getPurchasingOrganization());
              versionRequestDto.setSalesOrganization(version.getSalesOrganization());
              versionRequestDto.setVersionCategory(VersionStatus.OC);
              versionService.updateVersion(versionRequestDto,versionId);
               // saving result
              ResultDetail resultDetail=new ResultDetail();
              resultDetail.setSeverity("low");
              resultDetail.setMessage("Claim Validated Successfully");
              resultDetail.setMessageClassId("B");
              results.add(resultDetail);
              result.setResultDetail(results);
              result.setClaimId(claimId);
              resultResponseDto=  resultService.addResult(result);

              //raising notification
              Notification notification=new Notification();
              notification.setClaimId(claimId);
              notification.setDescription("A claim has been validated successfully for Customer "+claim.getHeader().getCustomer()+" and for amount "+claim.getItem().getAmount());
              notification.setOrganisation(claim.getVersion().getSalesOrganization());
              notification.setTitle("Claim Validation Successful");
              notification.setViewed(false);
              notification.setCreatedAt(new Date().toString());
              notificationService.addNotification(notification);

          }
          else{

              result.setResultDetail(results);
              result.setClaimId(claimId);

              //add notifictaion
              Notification notification=new Notification();
              notification.setClaimId(claimId);
              notification.setDescription("A claim has been rejected by vendor for Customer "+claim.getHeader().getCustomer()+" and for amount "+claim.getItem().getAmount()+" Check validations for more information");
              notification.setOrganisation(claim.getVersion().getSalesOrganization());
              notification.setTitle("Claim Rejected");
              notification.setViewed(false);
              notification.setCreatedAt(new Date().toString());
              notificationService.addNotification(notification);
              resultResponseDto=  resultService.addResult(result);
          }
         return resultResponseDto;

    }

    public Item getItemByClaimId(String claimId){
        Claim claim=this.claimRepository.findById(claimId).get();
        Item item=claim.getItem();
        return item;
    }
}
