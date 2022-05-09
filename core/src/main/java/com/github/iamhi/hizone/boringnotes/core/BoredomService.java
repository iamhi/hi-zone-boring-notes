package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BoredomService {

    Flux<BoringNoteDTO> getNotes(String ownerUuid);

    Mono<BoringNoteDTO> createNote(BoringNoteDTO boringNoteDTO);

    Mono<BoringNoteDTO> readNote(String ownerUuid, String noteUuid);

    Mono<BoringNoteDTO> updateNote(BoringNoteDTO boringNoteDTO);

    Mono<BoringNoteDTO> deleteNote(String ownerUuid, String noteUuid);
}
