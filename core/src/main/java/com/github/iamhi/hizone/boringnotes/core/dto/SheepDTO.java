package com.github.iamhi.hizone.boringnotes.core.dto;

import com.github.iamhi.hizone.boringnotes.out.SheepEntity;

public record SheepDTO(
    String uuid
) {

    public static SheepDTO fromEntity(SheepEntity sheepEntity) {
        return new SheepDTO(
            sheepEntity.uuid()
        );
    }
}
