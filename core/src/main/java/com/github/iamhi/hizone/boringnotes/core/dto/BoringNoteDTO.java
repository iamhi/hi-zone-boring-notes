package com.github.iamhi.hizone.boringnotes.core.dto;

import com.github.iamhi.hizone.boringnotes.out.BoringNoteEntity;

import java.time.Instant;

public record BoringNoteDTO(

    String uuid,

    String ownerUuid,

    String title,

    String content,

    Instant createdAt,

    Instant updatedAt
) {
    public static BoringNoteDTO fromEntity(BoringNoteEntity entity) {
        return new BoringNoteDTO(
            entity.uuid(),
            entity.ownerUuid(),
            entity.title(),
            entity.content(),
            entity.createdAt(),
            entity.updatedAt()
        );
    }

    public BoringNoteEntity toEntity() {
        return new BoringNoteEntity(
            this.uuid(),
            this.ownerUuid(),
            this.title(),
            this.content(),
            this.createdAt(),
            this.updatedAt()
        );
    }
}
