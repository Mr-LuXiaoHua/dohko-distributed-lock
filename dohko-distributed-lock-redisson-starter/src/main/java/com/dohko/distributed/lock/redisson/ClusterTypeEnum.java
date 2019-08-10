package com.dohko.distributed.lock.redisson;

/**
 * redis集群枚举
 */
public enum  ClusterTypeEnum {

    SINGLE("single"),
    SENTINEL("sentinel"),
    CLUSTER("cluster");

    private String type;

    ClusterTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }}
