package com.storageserver.model;

public class Limits {
    
    private Integer userId;
    private Integer bucketId;
    private Integer limits;


    public Limits(Integer userId , Integer bucketId , Integer limits)
    {
        this.userId = userId;
        this.bucketId = bucketId;
        this.limits = limits;
    }


    public Integer getUserId() {
        return userId;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }


    public Integer getBucketId() {
        return bucketId;
    }


    public void setBucketId(Integer bucketId) {
        this.bucketId = bucketId;
    }


    public Integer getLimits() {
        return limits;
    }


    public void setLimits(Integer limits) {
        this.limits = limits;
    }


    



    
}
