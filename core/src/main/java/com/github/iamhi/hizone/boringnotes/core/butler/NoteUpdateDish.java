package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import reactor.core.publisher.Mono;

public record NoteUpdateDish() implements SheepServingDish {

    private static final String COMMAND = "note-update";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        return null;
    }
}
