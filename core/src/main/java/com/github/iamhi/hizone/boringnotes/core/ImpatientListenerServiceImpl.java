package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public record ImpatientListenerServiceImpl(
    InnocentSupplierService supplierService
) implements ImpatientListenerService {

    @Override
    public Mono<Boolean> handleUserInput(UserInputDTO userInput) {
        System.out.println("Message: " + userInput.message() + " by: " + userInput.sheep().uuid());

        supplierService.addMessage(userInput.message(), userInput.sheep());

        return Mono.just(true);
    }

    @Override
    public Mono<Void> disconnect(SheepDTO sheepDTO) {
        supplierService.cleanMessages(sheepDTO);

        return Mono.empty();
    }
}
