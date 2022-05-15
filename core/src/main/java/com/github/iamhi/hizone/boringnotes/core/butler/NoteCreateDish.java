package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteCreateMapper;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteCreateRequest;
import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.internal.LinkedTreeMap;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
record NoteCreateDish(
    BoredomService boredomService
) implements SheepServingDish {

    private static final String COMMAND = "note-create";
    private static final String TITLE_INPUT = "title";
    private static final String CONTENT_INPUT = "content";

    @Override
    public boolean canServe(String userCommand) {
        return COMMAND.equals(userCommand);
    }

    @Override
    public Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        NoteCreateRequest noteCreateRequest = parseInput(userInputDataDTO);

        return boredomService.createNote(
            new BoringNoteDTO(
                "",
                sheepDTO.uuid(),
                noteCreateRequest.getTitle(),
                noteCreateRequest.getContent(),
                null,
                null
            )
        ).flux().flatMap(obj -> Mono.empty());
    }

    NoteCreateRequest parseInput(UserInputDataDTO userInputDataDTO) {
        NoteCreateRequest noteCreateRequest = new NoteCreateRequest();

        if (userInputDataDTO.getData() instanceof LinkedTreeMap<?,?> inputs) {
            String title = inputs.get(TITLE_INPUT) != null ? inputs.get(TITLE_INPUT).toString() : "";
            String content = inputs.get(CONTENT_INPUT) != null ? inputs.get(CONTENT_INPUT).toString() : "";

            noteCreateRequest.setTitle(title);
            noteCreateRequest.setContent(content);
        } else if (userInputDataDTO.getData() instanceof String) {
            noteCreateRequest.setContent(userInputDataDTO.getData().toString());
        }

        return noteCreateRequest;
    }
}
