package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;

public record NoteListDish(
    BoredomService boredomService,
    InnocentSupplierService innocentSupplierService,
    Gson gson
) implements SheepServingDish {

    private static final String COMMAND = "note-list";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        return boredomService.getNotes(sheepDTO.uuid()).map(gson::toJson).doOnNext(jsonObject -> {
            innocentSupplierService.addMessage(jsonObject, sheepDTO);
        }).single().flatMap(str -> Mono.empty());
    }
}
