package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.butler.SheepButler;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record ImpatientListenerServiceImpl(
    Gson gson,
    InnocentSupplierService supplierService,
    SheepButler sheepButler
) implements ImpatientListenerService {

    @Override
    public Mono<Boolean> handleUserInput(UserInputDTO userInput) {
        UserInputDataDTO dataDTO = parseUserMessage(userInput.message());

        sheepButler.satisfy(dataDTO, userInput.sheep()).subscribe();

        return Mono.just(true);
    }

    @Override
    public Mono<Void> disconnect(SheepDTO sheepDTO) {
        supplierService.cleanMessages(sheepDTO);

        return Mono.empty();
    }

    UserInputDataDTO parseUserMessage(String message) {
        return gson.fromJson(message, UserInputDataDTO.class);
    }
}
