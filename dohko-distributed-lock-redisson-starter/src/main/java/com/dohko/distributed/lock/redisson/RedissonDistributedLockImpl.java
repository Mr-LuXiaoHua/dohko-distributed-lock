package com.dohko.distributed.lock.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

public class RedissonDistributedLockImpl implements RedissonDistributedLock{

    private RedissonClient redissonClient;

    public RedissonDistributedLockImpl(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    /**
     * 根据名称获取锁
     * @param lockName
     * @return
     */
    public RLock getLock(String lockName) {
        return redissonClient.getLock(lockName);
    }

    @Override
    public RLock getLock(String lockName, boolean isFair) {
        if (isFair) {
            return redissonClient.getFairLock(lockName);
        } else {
            return getLock(lockName);
        }
    }


}
