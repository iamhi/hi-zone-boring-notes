package com.github.iamhi.hizone.boringnotes.out;

import io.lettuce.core.api.reactive.RedisReactiveCommands;

public interface RedisRepository {

    RedisReactiveCommands<String, String> getReactiveConnection();
}
