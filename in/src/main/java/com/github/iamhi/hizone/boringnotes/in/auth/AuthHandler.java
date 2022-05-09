package com.github.iamhi.hizone.boringnotes.in.auth;

import com.github.iamhi.hizone.boringnotes.core.SketchyTokenService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public record AuthHandler(
    SketchyTokenService sketchyTokenService
) {

    Mono<ServerResponse> login(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(LoginRequest.class)
            .map(LoginRequest::passcode)
            .flatMap(sketchyTokenService::createConnectionToken)
            .flatMap(response -> ServerResponse.ok().bodyValue(response));
    }
}
