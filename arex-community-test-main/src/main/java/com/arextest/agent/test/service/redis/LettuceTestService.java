package com.arextest.agent.test.service.redis;

import com.arextest.agent.test.config.RedisConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.SetArgs;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import io.lettuce.core.api.sync.RedisCommands;

import javax.annotation.PostConstruct;

import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LettuceTestService {
    RedisCommands<String, String> syncCommands;
    RedisAsyncCommands<String, String> asyncCommands;

    RedisReactiveCommands<String, String> reactiveCommands;

    @Autowired
    RedisConfig redisConfig;

    @PostConstruct
    public void init() {
        RedisURI uri = RedisURI.builder().withHost(redisConfig.getHost()).withPort(redisConfig.getPort()).build();
        RedisClient redisClient = RedisClient.create(uri);
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        syncCommands = connection.sync();
        asyncCommands = connection.async();
        reactiveCommands = connection.reactive();
    }

    /**
     * close lettuce connection in PreDestroy
     */
    @PreDestroy
    public void close() {
        syncCommands.getStatefulConnection().close();
    }

    public String testRenameException(String parameterData) {
        try {
            Flux<String> flux = reactiveCommands.hkeys(parameterData);
            return flux.blockFirst();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public String testGet(String key) {
        syncCommands.set(key, "v-" + key, SetArgs.Builder.ex(3));
        return syncCommands.get(key);
    }
}
