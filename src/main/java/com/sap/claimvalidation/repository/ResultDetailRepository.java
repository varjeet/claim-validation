package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Notification;
import com.sap.claimvalidation.entities.ResultDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultDetailRepository extends JpaRepository<ResultDetail,String> {


}
