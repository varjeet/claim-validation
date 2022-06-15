package com.sap.claimvalidation.controller;

import com.sap.claimvalidation.dtos.ResultResponseDto;
import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.entities.Result;
import com.sap.claimvalidation.service.ResultService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ResultController {

    private final ResultService resultService;

    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/claim/getResult")
    public ResponseEntity<ResultResponseDto> getResultByClaimId(@RequestParam(value = "claimId") String claimId){
        return new ResponseEntity<>(resultService.getResultByClaimId(claimId), HttpStatus.OK);
    }
}
