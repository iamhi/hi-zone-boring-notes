package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoteReadResponse {

    String uuid;

    String ownerUuid;

    String title;

    String content;

    String createdAt;

    String updatedAt;
}
