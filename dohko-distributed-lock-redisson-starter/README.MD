
dohko-distributed-lock-redisson-starter
---
将redisson封装成spring-boot-starter,方便在spring-boot项目中使用。

#### 使用方式
1. 使用maven install到本地仓库
2. 引入依赖
```
    <dependency>
        <groupId>com.dohko</groupId>
        <artifactId>dohko-distributed-lock-redisson-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```
3.springboot项目的application.properties配置redis
```
# 单个redis服务器
dohko.redisson.cluster-type=single
dohko.redisson.db=0
dohko.redisson.password=
dohko.redisson.address=redis://127.0.0.1:6379
dohko.redisson.connect-timeout=10000
dohko.redisson.connection-pool-size=64


# redis-集群-哨兵模式
#dohko.redisson.cluster-type=sentinal
#dohko.redisson.sentinel-master-name=your-sentinel-name
#dohko.redisson.db=0
dohko.redisson.password=
#dohko.redisson.sentinel-nodes=redis://127.0.0.1:6379,redis://127.0.0.1:6389,redis://127.0.0.1:6399
#dohko.redisson.connect-timeout=10000
#dohko.redisson.connection-pool-size=64

# redis-集群-cluster模式
#dohko.redisson.cluster-type=cluster
dohko.redisson.password=
#dohko.redisson.cluster-nodes=redis://127.0.0.1:6349,redis://127.0.0.1:6359,redis://127.0.0.1:6369,redis://127.0.0.1:6379,redis://127.0.0.1:6389,redis://127.0.0.1:6399
#dohko.redisson.connect-timeout=10000
#dohko.redisson.connection-pool-size=64
```

4.在需要使用的组件中注入RedissonDistributedLock
```
    @Autowired
    private RedissonDistributedLock redissonDistributedLock;
```
5.获取redisson锁
```
    RLock rLock = redissonDistributedLock.getLock(lockName);
    rLock.lock();
    // rLock.lock(10L, TimeUnit.SECONDS);
    // boolean flag = rLock.tryLock();
    // boolean flag = rLock.tryLock(10L, TimeUnit.SECONDS);
    
    // do something...
    
    if (rLock != null && rLock.isLocked()) {
        rLock.unlock();
    }
```

