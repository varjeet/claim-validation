package com.sap.claimvalidation.service;

import com.sap.claimvalidation.dtos.ItemRequestDto;
import com.sap.claimvalidation.dtos.VersionRequestDto;
import com.sap.claimvalidation.entities.Header;
import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Version;
import com.sap.claimvalidation.repository.ItemRepository;
import com.sap.claimvalidation.repository.VersionRepository;
import com.sap.claimvalidation.utils.ObjectMapperUtils;
import com.sap.claimvalidation.utils.VersionStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VersionService {
    private final VersionRepository versionRepository;

    VersionService(VersionRepository versionRepository){
        this.versionRepository=versionRepository;
    }


    public Version addVersion(VersionRequestDto version, Header header)
    {
        Version version1= ObjectMapperUtils.map(version,Version.class);
        version1.setVersionCategory(VersionStatus.IC);
        version1.setHeader(header);
        Version versionResponse= this.versionRepository.save(version1);
        return versionResponse;

    }

    public List<Version> getAllVersion(){
        return this.versionRepository.findAll();
    }
    public Version getVersionById(String id){
        Optional<Version> optionalVersion=this.versionRepository.findById(id);
        Version version= optionalVersion.get();
        return version;
    }

    public Version updateVersion(VersionRequestDto version1, String versionId)
    {
        Version version= ObjectMapperUtils.map(version1,Version.class);
        Version versionResponse=getVersionById(versionId);
        versionResponse.setId(versionId);
        versionResponse.setVersionCategory(version.getVersionCategory());
        versionResponse.setPersonResponsible(version.getPersonResponsible());
        versionResponse.setPurchasingOrganization(version.getPurchasingOrganization());
        versionResponse.setDistributionChannel(version.getDistributionChannel());
        versionResponse.setSalesOrganization(version.getSalesOrganization());
        this.versionRepository.save(versionResponse);
        return versionResponse;
    }

    public List<Version> getVersionBySalesOrganization(String salesOrganiation){

        return this.versionRepository.findVersionsBySalesOrganization(salesOrganiation);
    }
}
