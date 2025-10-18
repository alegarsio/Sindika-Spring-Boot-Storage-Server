package com.storageserver.repository;


import com.storageserver.dto.UserUsageDto; 
import com.storageserver.model.Document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Document Repository 
 * Autowired injectable
 */
@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer>{

   @Query("SELECT new com.storageserver.dto.UserUsageDto(d.uploadedBy.id, d.uploadedBy.nama, COUNT(d), COALESCE(SUM(d.fileSize), 0)) " +
           "FROM Document d GROUP BY d.uploadedBy.id, d.uploadedBy.nama ORDER BY SUM(d.fileSize) DESC")
    List<UserUsageDto> getStorageUsageByUser();
    
}
