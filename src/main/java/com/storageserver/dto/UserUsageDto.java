package com.storageserver.dto;

import lombok.NoArgsConstructor;

/**
 * User Usage Data Transfer Object
 * @param userId 
 * @param userName
 * @param fileCount
 * @param totalSize
 */


@NoArgsConstructor
public class UserUsageDto {
    private Integer userId;
    private String userName;
    private Long fileCount;
    private Long totalSize;

    /**
     * Constructor User Usage Dto 
     * @param userId
     * @param userName
     * @param fileCount
     * @param totalSize
     */
    
    public UserUsageDto(Integer userId, String userName, Long fileCount, Long totalSize) {
        this.userId = userId;
        this.userName = userName;
        this.fileCount = fileCount;
        this.totalSize = totalSize;
    }

  
    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Long getFileCount() {
        return fileCount;
    }

    public Long getTotalSize() {
        return totalSize;
    }

    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFileCount(Long fileCount) {
        this.fileCount = fileCount;
    }

    public void setTotalSize(Long totalSize) {
        this.totalSize = totalSize;
    }
}