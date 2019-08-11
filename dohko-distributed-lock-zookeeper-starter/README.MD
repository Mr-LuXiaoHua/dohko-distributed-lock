
dohko-distributed-lock-zookeeper-starter
---
将Curator封装成spring-boot-starter,方便在spring-boot项目中使用。

**Curator版本为4.2.0，支持zookeeper版本为3.5.x**

#### 使用方式
1. 使用maven install到本地仓库
2. 引入依赖
```
    <dependency>
        <groupId>com.dohko</groupId>
        <artifactId>dohko-distributed-lock-zookeeper-starter</artifactId>
        <version>0.0.1-SNAPSHOT</version>
    </dependency>
```
3.项目配置
```
    # 多个地址使用,分隔：127.0.0.1:2181,127.0.0.1:3181
    dohko.zookeeper.addrs=127.0.0.1:2181
    dohko.zookeeper.curator-connection-timeout-ms=10000
    dohko.zookeeper.curator-session-timeout-ms=5000
    dohko.zookeeper.curator-retry-count=5
```

3.注入ZookeeperDistributedLock
```
    @Autowired
    private ZookeeperDistributedLock zookeeperDistributedLock;
```

4.使用ZookeeperDistributedLock获取相应的锁
```
    String lockPath = "/xxxxx/yyyyyy";
    InterProcessMutex lock = zookeeperDistributedLock.getInterProcessMutex(lockPath);
    try {
        lock.acquire();
        //do something...
        
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            lock.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
```