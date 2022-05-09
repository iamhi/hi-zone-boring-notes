package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDTO;
import reactor.core.publisher.Mono;

public interface ImpatientListenerService {

    Mono<Boolean> handleUserInput(UserInputDTO userInput);

    Mono<Void> disconnect(SheepDTO sheepDTO);
}
