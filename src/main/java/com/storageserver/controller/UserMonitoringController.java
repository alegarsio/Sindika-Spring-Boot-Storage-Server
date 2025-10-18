package com.storageserver.controller;

/**
 * DTO
 */
import com.storageserver.dto.UserUsageDto;



import com.storageserver.service.UserMonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/monitoring")
@CrossOrigin(origins = "http://localhost:4200") 
public class UserMonitoringController {

    private final UserMonitoringService userMonitoringService;
    
    /**
     * 
     * @param userMonitoringService
     */
    @Autowired
    public UserMonitoringController(UserMonitoringService userMonitoringService) {
        this.userMonitoringService = userMonitoringService;
    }

    /**
     * 
     * @return
     */
    @GetMapping("/usage-by-user")
    public ResponseEntity<List<UserUsageDto>> getUsageByUser() {
        List<UserUsageDto> usageList = userMonitoringService.getUsageStatsByUser();
        return ResponseEntity.ok(usageList);
    }
    
}
