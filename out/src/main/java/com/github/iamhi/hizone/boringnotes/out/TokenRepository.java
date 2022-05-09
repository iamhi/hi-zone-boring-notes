package com.github.iamhi.hizone.boringnotes.out;

import reactor.core.publisher.Mono;

public interface TokenRepository {

    Mono<String> generateToken(String uuid);

    Mono<Boolean> hasToken(String token);

    Mono<Boolean> removeToken(String token);

    Mono<String> activateToken(String token);
}
