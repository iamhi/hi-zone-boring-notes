package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import reactor.core.publisher.Flux;

public interface InnocentSupplierService {

    Flux<String> getMessages(SheepDTO sheepDTO);

    void addMessage(String message, SheepDTO sheepDTO);

    void cleanMessages(SheepDTO sheepDTO);
}
