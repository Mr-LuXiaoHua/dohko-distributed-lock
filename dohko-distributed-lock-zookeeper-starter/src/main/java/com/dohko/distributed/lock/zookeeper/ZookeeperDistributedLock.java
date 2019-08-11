package com.dohko.distributed.lock.zookeeper;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.framework.recipes.locks.InterProcessSemaphoreMutex;

public interface ZookeeperDistributedLock {

    /**
     * 获取共享可重入锁
     * @param lockPath 锁路径
     * @return
     */
    InterProcessMutex getInterProcessMutex(String lockPath);


    /**
     * 获取共享锁
     * @param lockPath 锁路径
     * @return
     */
    InterProcessSemaphoreMutex getInterProcessSemaphoreMutex(String lockPath);


    /**
     * 获取共享可重入读写锁
     * @param lockPath 锁路径
     * @return
     */
    InterProcessReadWriteLock getInterProcessReadWriteLock(String lockPath);
}
