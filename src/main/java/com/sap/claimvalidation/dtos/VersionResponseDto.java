package com.sap.claimvalidation.dtos;

public class VersionResponseDto {

    private String id;


    private String purchasingOrganization;


    private String salesOrganization;


    private String distributionChannel;


    private String versionCategory;


    private String personResponsible;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getVersionCategory() {
        return versionCategory;
    }

    public void setVersionCategory(String versionCategory) {
        this.versionCategory = versionCategory;
    }

    public String getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(String personResponsible) {
        this.personResponsible = personResponsible;
    }
}
