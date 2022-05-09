package com.github.iamhi.hizone.boringnotes.out;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

public record SheepEntity(

    @Id
    String uuid,

    @Indexed(name = "sheep_entity_passcode_index", unique = true, sparse = true)
    String passcode) {
}
