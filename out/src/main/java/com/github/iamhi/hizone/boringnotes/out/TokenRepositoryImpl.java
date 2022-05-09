package com.github.iamhi.hizone.boringnotes.out;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
record TokenRepositoryImpl(
    RedisRepository redisRepository
) implements TokenRepository {

    private static final String ACTIVE_TOKEN_STATE = "active";

    private static final long TOKEN_EXPIRATION = 30;

    @Override
    public Mono<String> generateToken(String uuid) {
        String newToken = UUID.randomUUID().toString();

        return redisRepository
            .getReactiveConnection()
            .setex(newToken, TOKEN_EXPIRATION, uuid)
            .map(result -> newToken);
    }

    @Override
    public Mono<Boolean> hasToken(String token) {
        return redisRepository
            .getReactiveConnection()
            .get(token)
            .map(StringUtils::isNotBlank);
    }

    @Override
    public Mono<Boolean> removeToken(String token) {
        return redisRepository
            .getReactiveConnection()
            .getdel(token)
            .map(StringUtils::isEmpty);
    }

    @Override
    public Mono<String> activateToken(String token) {
        return redisRepository
            .getReactiveConnection()
            .get(token)
            .flatMap(tokenState -> {
                if (!ACTIVE_TOKEN_STATE.equals(tokenState) && StringUtils.isNotBlank(tokenState)) {
                    return redisRepository
                        .getReactiveConnection()
                        .set(token, ACTIVE_TOKEN_STATE).map(result -> true)
                        .then(Mono.just(tokenState));
                }

                return Mono.error(new RuntimeException());
            }).switchIfEmpty(Mono.error(new RuntimeException("Used or missing token")));
    }
}
