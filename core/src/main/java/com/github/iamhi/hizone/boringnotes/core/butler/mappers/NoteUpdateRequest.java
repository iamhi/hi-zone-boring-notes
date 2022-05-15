package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import org.mapstruct.Mapper;

@Mapper
public class NoteUpdateRequest {

    String uuid;

    String title;

    String content;
}
