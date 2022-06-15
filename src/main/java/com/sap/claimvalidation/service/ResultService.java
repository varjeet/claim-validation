package com.sap.claimvalidation.service;

import com.sap.claimvalidation.dtos.ClaimResponseDto;
import com.sap.claimvalidation.dtos.ResultDetailResponseDto;
import com.sap.claimvalidation.dtos.ResultResponseDto;
import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.entities.Result;
import com.sap.claimvalidation.entities.ResultDetail;
import com.sap.claimvalidation.repository.ResultDetailRepository;
import com.sap.claimvalidation.repository.ResultRepository;
import com.sap.claimvalidation.utils.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ResultService {
    private final ResultRepository resultRepository;
    private final ResultDetailRepository resultDetailRepository;

    public ResultService(ResultRepository resultRepository, ResultDetailRepository resultDetailRepository) {
        this.resultRepository = resultRepository;
        this.resultDetailRepository = resultDetailRepository;
    }

//    public List<Result> addResult(List<Result> results)
//    {
//        List<Result> resultResponse= this.resultRepository.saveAll(results);
//        return resultResponse;
//
//    }

    public ResultResponseDto addResult(Result result)
    {
        List<Result> results=this.resultRepository.findAll();

        if(results.size()!=0){
            Optional<Result> first = results.stream().filter(result1 -> result1.getClaimId().equals(result.getClaimId())).findFirst();
            if(first.isPresent()) {
                Result resultTemp=first.get();
               result.setId(resultTemp.getId());
           }
        }
        Result resultResponse= this.resultRepository.save(result);
        List<ResultDetail> resultDetails=this.resultDetailRepository.findAll();
        if(resultDetails.size()!=0) {
            List<ResultDetail> resultDetails1 = resultDetails.stream().filter(resultDetail -> resultDetail.getResult().getId().equals(result.getId())).collect(Collectors.toList());
        if(resultDetails1.size()!=0){
            this.resultDetailRepository.deleteAll(resultDetails1);
        }
        }
        Set<ResultDetail> resultDetail = result.getResultDetail();
        resultDetail.forEach(resultDetail1 -> {
            resultDetail1.setResult(resultResponse);
        });
        List<ResultDetail> resultDetailResponse=this.resultDetailRepository.saveAll(resultDetail);
        ResultResponseDto resultResponseDto = new ResultResponseDto();
       List<ResultDetailResponseDto> resultDetailResponseDto=  ObjectMapperUtils.mapAll(resultDetailResponse, ResultDetailResponseDto.class);
       resultResponseDto.setId(result.getId());
       resultResponseDto.setClaimId(result.getClaimId());
       resultResponseDto.setResultDetails(resultDetailResponseDto);
   return resultResponseDto;
    }

    public ResultResponseDto getResultByClaimId(String claimId){
        Result result= this.resultRepository.findResultByClaimId(claimId);
        ResultResponseDto resultResponseDto = new ResultResponseDto();
        List<ResultDetailResponseDto> resultDetailResponseDto=  ObjectMapperUtils.mapAll(result.getResultDetail(), ResultDetailResponseDto.class);
        resultResponseDto.setId(result.getId());
        resultResponseDto.setClaimId(result.getClaimId());
        resultResponseDto.setResultDetails(resultDetailResponseDto);
        return resultResponseDto;
    }
}
