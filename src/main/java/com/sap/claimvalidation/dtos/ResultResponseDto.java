package com.sap.claimvalidation.dtos;

import com.sap.claimvalidation.entities.ResultDetail;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

public class ResultResponseDto {
    private String id;
    private String claimId;


    private List<ResultDetailResponseDto> resultDetails;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClaimId() {
        return claimId;
    }

    public void setClaimId(String claimId) {
        this.claimId = claimId;
    }

    public List<ResultDetailResponseDto> getResultDetails() {
        return resultDetails;
    }

    public void setResultDetails(List<ResultDetailResponseDto> resultDetails) {
        this.resultDetails = resultDetails;
    }
}
