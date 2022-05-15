package com.github.iamhi.hizone.boringnotes.core.butler;

import com.github.iamhi.hizone.boringnotes.core.BoredomService;
import com.github.iamhi.hizone.boringnotes.core.InnocentSupplierService;
import com.github.iamhi.hizone.boringnotes.core.butler.mappers.NoteListResponse;
import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
record NoteListDish(
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
    public Flux<Void> serve(UserInputDataDTO userInputDataDTO, SheepDTO sheepDTO) {
        return boredomService.getNotes(sheepDTO.uuid())
            .map(this::map)
            .map(gson::toJson)
            .doOnNext(jsonObject -> innocentSupplierService.addMessage(jsonObject, sheepDTO))
            .flatMap(obj -> Mono.empty());
    }

    NoteListResponse map(BoringNoteDTO boringNoteDTO) {
        return new NoteListResponse(
            boringNoteDTO.uuid(),
            boringNoteDTO.title()
        );
    }
}
