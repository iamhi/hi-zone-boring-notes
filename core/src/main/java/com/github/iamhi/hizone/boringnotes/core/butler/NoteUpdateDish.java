package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteUpdateRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
record NoteUpdateDish(
    BoredomService boredomService
) implements SheepServingDish {

    private static final String COMMAND = "note-update";
    private static final String UUID_INPUT = "uuid";
    private static final String TITLE_INPUT = "title";
    private static final String CONTENT_INPUT = "content";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteUpdateRequest noteUpdateRequest = parseInput(userInputDataDTO);

        return boredomService.updateNote(
            new BoringNoteDTO(
                noteUpdateRequest.getUuid(),
                sheepDTO.uuid(),
                noteUpdateRequest.getTitle(),
                noteUpdateRequest.getContent(),
                null,
                null
            )
        ).flux().flatMap(obj -> Mono.empty());
    }

    NoteUpdateRequest parseInput(UserInputDataDTO userInputDataDTO) {
        NoteUpdateRequest noteUpdateRequest = new NoteUpdateRequest();

        if (userInputDataDTO.getData() instanceof LinkedTreeMap<?,?> inputs) {
            String uuid = inputs.get(UUID_INPUT) != null ? inputs.get(UUID_INPUT).toString() : "";
            String title = inputs.get(TITLE_INPUT) != null ? inputs.get(TITLE_INPUT).toString() : "";
            String content = inputs.get(CONTENT_INPUT) != null ? inputs.get(CONTENT_INPUT).toString() : "";

            noteUpdateRequest.setUuid(uuid);
            noteUpdateRequest.setTitle(title);
            noteUpdateRequest.setContent(content);
        }

        return noteUpdateRequest;
    }
}
