package com.github.iamhi.hizone.boringnotes.out;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public record BoringNoteEntity(

    @Id
    String uuid,

    String ownerUuid,

    String title,

    String content,

    Instant createdAt,

    Instant updatedAt
) {
}
