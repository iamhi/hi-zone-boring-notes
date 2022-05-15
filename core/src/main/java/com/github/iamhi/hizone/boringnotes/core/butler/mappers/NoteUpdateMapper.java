package com.github.iamhi.hizone.boringnotes.core.butler.mappers;

import com.github.iamhi.hizone.boringnotes.core.dto.UserInputDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NoteUpdateMapper {

    NoteUpdateMapper INSTANCE = Mappers.getMapper(NoteUpdateMapper.class);

    NoteUpdateRequest map(UserInputDataDTO userInputDataD);
}
