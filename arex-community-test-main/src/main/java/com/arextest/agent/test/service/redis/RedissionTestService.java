package com.arextest.agent.test.service.redis;

import com.arextest.agent.test.config.RedisConfig;
import org.redisson.Redisson;
import org.redisson.api.RAtomicDouble;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RDoubleAdder;
import org.redisson.api.RList;
import org.redisson.api.RLock;
import org.redisson.api.RLongAdder;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author daixq
 * @date 2022/12/07
 */
@Component
public class RedissionTestService {
    @Autowired
    RedisConfig redisConfig;

    RedissonClient redissonClient;

    @PostConstruct
    public void init() {
        Config config = new Config();
        config.useSingleServer().setAddress(String.format("redis://%s:%s", redisConfig.getHost(), redisConfig.getPort()))
                .setDatabase(1);
        redissonClient = Redisson.create(config);
    }

    public List<String> testGetList(String parameterData) {
        RList<String> rList = redissonClient.getList(parameterData);
        rList.add("bing");
        return rList;
    }

    public String testGetLock(String parameterData) {
        RLock rLock = redissonClient.getLock(parameterData);
        return rLock.getName();
    }

    public String testGetId() {
        String id = redissonClient.getId();
        return id;
    }

    public boolean testIsShuttingDown() {
        boolean isShuttingDown = redissonClient.isShuttingDown();
        return isShuttingDown;
    }

    public boolean testIsShutdown() {
        boolean isShutdown = redissonClient.isShutdown();
        return isShutdown;
    }

    public long testGetConfig() {
        Config config = redissonClient.getConfig();
        return config.getLockWatchdogTimeout();
    }

    public long testGetAtomicLong() {
        RAtomicLong rAtomicLong = redissonClient.getAtomicLong("testGetAtomicLong");
        return rAtomicLong.incrementAndGet();
    }

    public double testGetAtomicDouble() {
        RAtomicDouble rAtomicDouble = redissonClient.getAtomicDouble("testGetAtomicDouble");
        return rAtomicDouble.incrementAndGet();
    }

    public String testGetLongAdder() {
        RLongAdder rLongAdder = redissonClient.getLongAdder("testGetLongAdder");
        return rLongAdder.getName();
    }

    public String testGetDoubleAdder() {
        RDoubleAdder rDoubleAdder = redissonClient.getDoubleAdder("testDoubleAdder");
        return rDoubleAdder.getName();
    }

    public boolean testRenameException() {
        // try {
            redissonClient.getKeys().rename("renameKey1", "renameKey2");
            return true;
        // } catch (Exception e) {
        //     return "rename exception, message: " + e.getMessage();
        // }
    }
}
