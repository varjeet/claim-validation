package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Item;
import com.sap.claimvalidation.entities.Result;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<Result,String> {
}
