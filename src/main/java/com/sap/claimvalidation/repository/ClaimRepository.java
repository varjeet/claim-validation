package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Claim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaimRepository extends JpaRepository<Claim,String> {

   public Claim findClaimByVersionIdAndItemId(String versionId,String itemId);
   public Claim findClaimByVersion_id(String versionId);

}
