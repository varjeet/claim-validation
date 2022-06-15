package com.sap.claimvalidation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sap.claimvalidation.dtos.ClaimDto;
import com.sap.claimvalidation.dtos.ResultResponseDto;
import com.sap.claimvalidation.entities.Claim;
import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Result;
import com.sap.claimvalidation.entities.Version;
import com.sap.claimvalidation.service.ClaimService;
import com.sap.claimvalidation.service.ValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ValidationController {

    private final ValidationService validationService;
    private final ClaimService claimService;


    ValidationController(ValidationService validationService,ClaimService claimService){

        this.validationService=validationService;
        this.claimService=claimService;
    }
    @PostMapping("v2/claim/validate")
    public ResponseEntity<ResultResponseDto> validateClaim(@RequestBody ClaimDto claimdto) throws JsonProcessingException {


        return new ResponseEntity<>( validationService.validateClaim(claimdto.getId()),HttpStatus.OK);


       // return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



}
