package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import reactor.core.publisher.Mono;

public interface SketchyTokenService {

    Mono<String> createConnectionToken(String sheepPasscode);

    Mono<SheepDTO> connectWithToken(String token);
}
