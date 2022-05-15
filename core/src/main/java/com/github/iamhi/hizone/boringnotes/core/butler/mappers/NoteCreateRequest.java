package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import lombok.Data;

@Data
public class NoteCreateRequest {

    private String title;

    private String content;
}
