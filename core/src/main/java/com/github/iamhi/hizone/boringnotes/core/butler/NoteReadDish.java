package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteReadMapper;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteReadRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.Gson;
import reactor.core.publisher.Mono;

public record NoteReadDish(
    BoredomService boredomService,
    InnocentSupplierService supplierService,
    Gson gson
) implements SheepServingDish {

    private static final String COMMAND = "note-read";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteReadRequest noteReadRequest = NoteReadMapper.INSTANCE.map(userInputDataDTO);

        return boredomService.readNote(sheepDTO.uuid(), noteReadRequest.getUuid())
            .flatMap(boringNote -> {
                supplierService.addMessage(gson.toJson(boringNote), sheepDTO);

                return Mono.empty();
            });
    }
}
