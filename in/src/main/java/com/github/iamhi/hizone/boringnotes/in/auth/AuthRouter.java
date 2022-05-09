package com.github.iamhi.hizone.boringnotes.in.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AuthRouter {

    private static final String ROUTER_PREFIX = "/auth";

    @Bean
    public RouterFunction<ServerResponse> authRouterCompose(AuthHandler authHandler) {
        return route(POST(ROUTER_PREFIX), authHandler::login);
    }
}
