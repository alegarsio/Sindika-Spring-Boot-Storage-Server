package com.storageserver.service;


import com.storageserver.dto.UserUsageDto;
import com.storageserver.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * usage monitor service is a record that contais all users file 
 * usage in the cloud
 */
@Service
public class UserMonitoringService {
    

    private final DocumentRepository documentRepository;

    /**
     * @param documentRepository
     */
    @Autowired
    public UserMonitoringService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    /**
     * @return
     */
    public List<UserUsageDto> getUsageStatsByUser() {
        return documentRepository.getStorageUsageByUser();
    }
}
