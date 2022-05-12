package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
record EchoingDish(
    InnocentSupplierService supplierService
) implements SheepServingDish {

    private static final String COMMAND = "echo";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        supplierService.addMessage(userInputDataDTO.getData().toString(), sheepDTO);

        return Mono.empty();
    }
}
