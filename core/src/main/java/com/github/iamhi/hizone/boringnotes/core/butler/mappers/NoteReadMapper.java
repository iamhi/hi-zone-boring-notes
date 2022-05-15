package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteReadMapper {

    NoteReadMapper INSTANCE = Mappers.getMapper(NoteReadMapper.class);

    NoteReadRequest map(UserInputDataDTO userInputDataD);
}
