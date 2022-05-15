package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteDeleteRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
record NoteDeleteDish(
    BoredomService boredomService
) implements SheepServingDish {

    private static final String COMMAND = "note-delete";
    private static final String UUID_INPUT = "uuid";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteDeleteRequest noteDeleteRequest = parseInput(userInputDataDTO);

        return boredomService.deleteNote(sheepDTO.uuid(), noteDeleteRequest.getUuid())
            .flux().flatMap(obj -> Mono.empty());
    }

    NoteDeleteRequest parseInput(UserInputDataDTO userInputDataDTO) {
        NoteDeleteRequest noteDeleteRequest = new NoteDeleteRequest();

        if (userInputDataDTO.getData() instanceof LinkedTreeMap<?, ?> inputs) {
            String uuid = inputs.get(UUID_INPUT) != null ? inputs.get(UUID_INPUT).toString() : "";

            noteDeleteRequest.setUuid(uuid);
        } else if (userInputDataDTO.getData() instanceof String) {
            noteDeleteRequest.setUuid(userInputDataDTO.getData().toString());
        }

        return noteDeleteRequest;
    }
}
