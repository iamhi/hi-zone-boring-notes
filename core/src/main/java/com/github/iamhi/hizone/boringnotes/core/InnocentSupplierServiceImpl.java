package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class InnocentSupplierServiceImpl implements InnocentSupplierService {

    Map<SheepDTO, Consumer<String>> sheepConsumers = new HashMap<>();

    @Override
    public Flux<String> getMessages(SheepDTO sheepDTO) {
        return Flux.create(stringFluxSink -> sheepConsumers.put(sheepDTO, stringFluxSink::next));
    }

    @Override
    public void addMessage(String message, SheepDTO sheepDTO) {
        if (sheepConsumers.containsKey(sheepDTO)) {
            sheepConsumers.get(sheepDTO).accept(message);
        }
    }

    @Override
    public void cleanMessages(SheepDTO sheepDTO) {
        sheepConsumers.remove(sheepDTO);
    }
}
