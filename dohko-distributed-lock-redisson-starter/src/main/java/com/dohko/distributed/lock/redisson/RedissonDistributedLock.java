package com.dohko.distributed.lock.redisson;

import org.redisson.api.RLock;

/**
 *  redisson分布式锁接口
 */
public interface RedissonDistributedLock {

    /**
     * 根据lockName获取锁
     * @param lockName
     * @return
     */
    RLock getLock(String lockName);

    /**
     * 可控制是否需要公平锁
     * @param lockName
     * @param isFair    true-公平锁，false-非公平锁
     * @return
     */
    RLock getLock(String lockName, boolean isFair);


}
