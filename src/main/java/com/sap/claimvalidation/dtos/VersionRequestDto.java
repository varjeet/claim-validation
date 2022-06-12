package com.sap.claimvalidation.dtos;

import com.sap.claimvalidation.utils.VersionStatus;

public class VersionRequestDto {
    private String purchasingOrganization;


    private String salesOrganization;


    private String distributionChannel;


    private String personResponsible;

    private VersionStatus versionCategory;


    public VersionStatus getVersionCategory() {
        return versionCategory;
    }

    public void setVersionCategory(VersionStatus versionCategory) {
        this.versionCategory = versionCategory;
    }

    public String getPurchasingOrganization() {
        return purchasingOrganization;
    }

    public void setPurchasingOrganization(String purchasingOrganization) {
        this.purchasingOrganization = purchasingOrganization;
    }

    public String getSalesOrganization() {
        return salesOrganization;
    }

    public void setSalesOrganization(String salesOrganization) {
        this.salesOrganization = salesOrganization;
    }

    public String getDistributionChannel() {
        return distributionChannel;
    }

    public void setDistributionChannel(String distributionChannel) {
        this.distributionChannel = distributionChannel;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }

}
