package com.storageserver.dto;

import lombok.Data;
import java.util.List;
import java.util.Map;

/**
 * Monitoring Stats DTO 
 * 
 */

@Data
public class MonitoringStatsDto {
    
    private long totalDiskSpace;
    private long usedDiskSpace;
    private long freeDiskSpace;
    private double diskUsagePercentage;

    private long totalDocuments;
    private long totalPengelola;
    private long totalBuckets;

    private Map<String, Long> fileTypeDistribution;
    private List<DocumentSummaryDto> recentUploads;
    


    

}
