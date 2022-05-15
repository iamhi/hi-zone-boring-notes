package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteCreateMapper;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteCreateRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import reactor.core.publisher.Mono;

public record NoteCreateDish(
    BoredomService boredomService
) implements SheepServingDish {

    private static final String COMMAND = "note-create";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Mono<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteCreateRequest noteCreateRequest = NoteCreateMapper.INSTANCE.map(userInputDataDTO);

        return boredomService.createNote(
            new BoringNoteDTO(
                "",
                sheepDTO.uuid(),
                noteCreateRequest.getTitle(),
                noteCreateRequest.getContent(),
                null,
                null
            )
        ).then(Mono.empty());
    }
}
