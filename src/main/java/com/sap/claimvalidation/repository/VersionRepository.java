package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Claim;
import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VersionRepository extends JpaRepository<Version,String> {
     List<Version> findVersionsBySalesOrganization(String salesOrganization);



}
