
dohko-distributed-lock-zookeeper-starter
---
将Curator封装成spring-boot-starter,方便在spring-boot项目中使用。

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