package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Header;
import com.sap.claimvalidation.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeaderRepository extends JpaRepository<Header,String> {
}
