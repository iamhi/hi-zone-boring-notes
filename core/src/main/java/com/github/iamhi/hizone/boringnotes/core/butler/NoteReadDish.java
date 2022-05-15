package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteReadRequest;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteReadResponse;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
record NoteReadDish(
    BoredomService boredomService,
    InnocentSupplierService supplierService,
    Gson gson
) implements SheepServingDish {

    private static final String COMMAND = "note-read";
    private static final String UUID_INPUT = "uuid";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteReadRequest noteReadRequest = parseInput(userInputDataDTO);

        return boredomService.readNote(sheepDTO.uuid(), noteReadRequest.getUuid())
            .flatMap(boringNote -> {
                supplierService.addMessage(gson.toJson(new NoteReadResponse(
                    boringNote.uuid(),
                    boringNote.ownerUuid(),
                    boringNote.title(),
                    boringNote.content(),
                    (boringNote.createdAt() != null) ? boringNote.createdAt().toString() : "",
                    (boringNote.updatedAt() != null) ? boringNote.updatedAt().toString() : ""
                )), sheepDTO);

                return Mono.empty();
            })
            .flux().flatMap(obj -> Mono.empty());
    }

    NoteReadRequest parseInput(UserInputDataDTO userInputDataDTO) {
        NoteReadRequest noteReadRequest = new NoteReadRequest();

        if (userInputDataDTO.getData() instanceof LinkedTreeMap<?, ?> inputs) {
            String uuid = inputs.get(UUID_INPUT) != null ? inputs.get(UUID_INPUT).toString() : "";

            noteReadRequest.setUuid(uuid);
        } else if (userInputDataDTO.getData() instanceof String) {
            noteReadRequest.setUuid(userInputDataDTO.getData().toString());
        }

        return noteReadRequest;
    }
}
