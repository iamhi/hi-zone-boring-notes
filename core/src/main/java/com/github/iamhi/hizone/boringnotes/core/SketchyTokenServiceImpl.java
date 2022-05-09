package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.out.TokenRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
record SketchyTokenServiceImpl(
    TokenRepository repository,
    SheepService sheepService
) implements SketchyTokenService {

    @Override
    public Mono<String> createConnectionToken(String sheepPasscode) {
        return sheepService.getSheepWithPasscode(sheepPasscode).map(SheepDTO::uuid).flatMap(repository::generateToken);
    }

    @Override
    public Mono<SheepDTO> connectWithToken(String token) {
        return repository.activateToken(token).flatMap(sheepService::readSheep);
    }
}
