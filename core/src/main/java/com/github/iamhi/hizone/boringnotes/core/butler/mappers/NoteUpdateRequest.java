package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import lombok.Data;

@Data
public class NoteUpdateRequest {

    String uuid;

    String title;

    String content;
}
