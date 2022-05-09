package com.github.iamhi.hizone.boringnotes.out;

import com.github.iamhi.hizone.boringnotes.config.RedisConfig;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.reactive.RedisReactiveCommands;
import org.springframework.stereotype.Service;

@Service
class RedisClientWrapper implements RedisRepository {

    RedisReactiveCommands<String, String> reactiveCommands;

    public RedisClientWrapper(RedisConfig redisConfig) {
        RedisClient redisClient = RedisClient.create("redis://" + redisConfig.getHost() + ":" + redisConfig.getPort());
        StatefulRedisConnection<String, String> connection = redisClient.connect();

        reactiveCommands = connection.reactive();
    }

    @Override
    public RedisReactiveCommands<String, String> getReactiveConnection() {
        return reactiveCommands;
    }
}
