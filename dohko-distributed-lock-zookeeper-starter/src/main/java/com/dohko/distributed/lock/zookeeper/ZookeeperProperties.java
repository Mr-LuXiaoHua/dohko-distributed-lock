package com.dohko.distributed.lock.zookeeper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "dohko.zookeeper")
public class ZookeeperProperties {

    /**
     * zk地址，集群使用,分割：127.0.0.1:2181,127.0.0.1:3181
     */
    private String addrs;

    /**
     * 重试次数
     */
    private int curatorRetryCount = 5;

    /**
     * session超时时间
     */
    private int curatorSessionTimeoutMs = 60000;

    /**
     * 连接超时时间
     */
    private int curatorConnectionTimeoutMs = 5000;


}
