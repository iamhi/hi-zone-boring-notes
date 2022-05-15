package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteDeleteMapper;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteDeleteRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import reactor.core.publisher.Mono;

public record NoteDeleteDish(
    BoredomService boredomService
) implements SheepServingDish {

    private static final String COMMAND = "note-delete";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteDeleteRequest noteDeleteRequest = NoteDeleteMapper.INSTANCE.map(userInputDataDTO);

        return boredomService.deleteNote(sheepDTO.uuid(), noteDeleteRequest.getUuid());
    }
}
