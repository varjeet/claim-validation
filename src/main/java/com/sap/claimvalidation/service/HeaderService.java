package com.sap.claimvalidation.service;

import com.sap.claimvalidation.dtos.HeaderRequestDto;
import com.sap.claimvalidation.dtos.ItemRequestDto;
import com.sap.claimvalidation.dtos.VersionRequestDto;
import com.sap.claimvalidation.entities.Header;
import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Version;
import com.sap.claimvalidation.repository.HeaderRepository;
import com.sap.claimvalidation.repository.ItemRepository;
import com.sap.claimvalidation.utils.ObjectMapperUtils;
import com.sap.claimvalidation.utils.VersionStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HeaderService {
    private final HeaderRepository headerRepository;

    HeaderService(HeaderRepository headerRepository){
        this.headerRepository=headerRepository;
    }

    public Header addHeader(HeaderRequestDto header)
    {
        Header header1= ObjectMapperUtils.map(header,Header.class);
        Header headerResponse= this.headerRepository.save(header1);
        return headerResponse;

    }

    public Header updateHeader(HeaderRequestDto header1, String headerId)
    {
        Header header= ObjectMapperUtils.map(header1,Header.class);
        Header headerResponse=getHeaderById(headerId);
        headerResponse.setId(headerResponse.getId());
        headerResponse.setCustomer(header.getCustomer());
        headerResponse.setDamage_date(header.getDamage_date());
        headerResponse.setVersion(header.getVersion());
        headerResponse.setObject_number(header.getObject_number());
        headerResponse.setObject_type(header.getObject_type());
        headerResponse.setRepair_end_date(header.getRepair_end_date());
        headerResponse.setRegistartion_date(header.getRegistartion_date());
        headerResponse.setSource(header.getSource());
        headerResponse.setSource_object_number(header.getSource_object_number());
        headerResponse.setRepair_start_Date(header.getRepair_start_Date());
        this.headerRepository.save(headerResponse);
        return headerResponse;
    }
    public Header getHeaderById(String id){
        Optional<Header> optionalHeader=this.headerRepository.findById(id);
        Header header= optionalHeader.get();
        return header;
    }
}
