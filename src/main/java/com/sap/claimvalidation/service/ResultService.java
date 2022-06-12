package com.sap.claimvalidation.service;

import com.sap.claimvalidation.entities.Result;
import com.sap.claimvalidation.entities.ResultDetail;
import com.sap.claimvalidation.repository.ResultDetailRepository;
import com.sap.claimvalidation.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

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

    public Result addResult(Result result)
    {
        Result resultResponse= this.resultRepository.save(result);
//        Set<ResultDetail> resultDetail = result.getResultDetail();
//        resultDetail.forEach(resultDetail1 -> {
//            resultDetail1.setResult(resultResponse);
//        });
//        List<ResultDetail> resultDetailResponse=this.resultDetailRepository.saveAll(result.getResultDetail());
        return resultResponse;

    }
}
