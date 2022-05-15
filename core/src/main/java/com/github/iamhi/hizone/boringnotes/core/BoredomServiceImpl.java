package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.BoringNoteDTO;
import com.github.iamhi.hizone.boringnotes.out.BoredomRepository;
import com.github.iamhi.hizone.boringnotes.out.BoringNoteEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.UUID;

@Service
public record BoredomServiceImpl(
    BoredomRepository repository
) implements BoredomService {

    @Override
    public Flux<BoringNoteDTO> getNotes(String ownerUuid) {
        return repository.findByOwnerUuid(ownerUuid).map(BoringNoteDTO::fromEntity);
    }

    @Override
    public Mono<BoringNoteDTO> createNote(BoringNoteDTO boringNoteDTO) {
        return repository.save(
            new BoringNoteEntity(
                UUID.randomUUID().toString(),
                boringNoteDTO.ownerUuid(),
                boringNoteDTO.title(),
                boringNoteDTO.content(),
                Instant.now(),
                Instant.now()
            )
        ).map(BoringNoteDTO::fromEntity);
    }

    @Override
    public Mono<BoringNoteDTO> readNote(String ownerUuid, String noteUuid) {
        return repository
            .findByUuidAndOwnerUuid(noteUuid, ownerUuid)
            .map(BoringNoteDTO::fromEntity)
            .switchIfEmpty(Mono.error(new RuntimeException("Note not found")));
    }

    @Override
    public Mono<BoringNoteDTO> updateNote(BoringNoteDTO boringNoteDTO) {
        return repository
            .findById(boringNoteDTO.uuid())
            .map(entity -> populateEntity(entity, boringNoteDTO))
            .flatMap(repository::save)
            .map(BoringNoteDTO::fromEntity);
    }

    @Override
    public Mono<Void> deleteNote(String ownerUuid, String noteUuid) {
        return repository.deleteByUuidAndOwnerUuid(noteUuid, ownerUuid);
    }

    private BoringNoteEntity populateEntity(BoringNoteEntity entity, BoringNoteDTO dto) {
        return new BoringNoteEntity(
            entity.uuid(),
            entity.ownerUuid(),
            (dto.title() != null) ? dto.title() : entity.title(),
            (dto.content() != null) ? dto.content() : entity.content(),
            entity.createdAt(),
            Instant.now()
        );
    }
}
