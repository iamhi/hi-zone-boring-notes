package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import reactor.core.publisher.Mono;

public interface SheepService {

    Mono<SheepDTO> readSheep(String uuid);

    Mono<SheepDTO> getSheepWithPasscode(String passcode);
}
