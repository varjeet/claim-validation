package com.sap.claimvalidation.repository;

import com.sap.claimvalidation.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,String> {
//    List<Course> findCourseByBatch_id(String batchId);
//    Course findCourseById(String courseId);
}