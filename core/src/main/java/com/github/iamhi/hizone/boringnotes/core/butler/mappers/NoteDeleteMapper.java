package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteDeleteMapper {

    NoteDeleteMapper INSTANCE = Mappers.getMapper(NoteDeleteMapper.class);

    NoteDeleteRequest map(UserInputDataDTO userInputDataD);
}
