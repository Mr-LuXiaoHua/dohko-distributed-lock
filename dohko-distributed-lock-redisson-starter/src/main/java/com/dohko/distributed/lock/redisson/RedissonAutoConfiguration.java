package com.dohko.distributed.lock.redisson;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(RedissonDistributedLockImpl.class)
@EnableConfigurationProperties(RedissonProperties.class)
@Slf4j
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    @Bean(destroyMethod = "shutdown")
    @ConditionalOnMissingBean(RedissonClient.class)
    public RedissonClient redissonClient() {
        Config config = null;
        String clusterType = redissonProperties.getClusterType();
        if (ClusterTypeEnum.SINGLE.getType().equals(clusterType)) {
            config = new Config();
            config.useSingleServer()
                    .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                    .setConnectTimeout(redissonProperties.getConnectTimeout())
                    .setAddress(redissonProperties.getAddress())
                    .setDatabase(redissonProperties.getDb())
                    .setPassword(redissonProperties.getPassword());

        } else if (ClusterTypeEnum.SENTINEL.getType().equals(clusterType)) {
            config = new Config();
            String[] nodes = redissonProperties.getSentinelNodes();
            config.useSentinelServers()
                    .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                    .setConnectTimeout(redissonProperties.getConnectTimeout())
                    .setMasterName(redissonProperties.getSentinelMasterName())
                    .addSentinelAddress(nodes)
                    .setDatabase(redissonProperties.getDb())
                    .setPassword(redissonProperties.getPassword());

        } else if (ClusterTypeEnum.CLUSTER.getType().equals(clusterType)) {
            String[] nodes = redissonProperties.getClusterNodes();
            config = new Config();
            config.useClusterServers()
                    .setMasterConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                    .setConnectTimeout(redissonProperties.getConnectTimeout())
                    .addNodeAddress(nodes)
                    .setPassword(redissonProperties.getPassword());

        } else {
            throw new IllegalArgumentException("redisson.cluster-type illegal");
        }
        return Redisson.create(config);
    }

    @Bean
    public RedissonDistributedLock redissonService() {
        RedissonDistributedLock redissonService = new RedissonDistributedLockImpl(redissonClient());
        return redissonService;
    }




}
