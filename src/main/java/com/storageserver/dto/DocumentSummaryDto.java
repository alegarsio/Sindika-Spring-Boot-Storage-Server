package com.storageserver.dto;


import lombok.Data;
import java.time.LocalDateTime;

/**
 * 
 */
@Data
public class DocumentSummaryDto {
    private String userFriendlyName;
    private String fileType;
    private Long fileSize;
    private LocalDateTime createdAt;
}
