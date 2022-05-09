package com.github.iamhi.hizone.boringnotes.core;

import com.github.iamhi.hizone.boringnotes.core.dto.SheepDTO;
import com.github.iamhi.hizone.boringnotes.out.SheepEntity;
import com.github.iamhi.hizone.boringnotes.out.SheepRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public record SheepServiceImpl(
    SheepRepository repository
) implements SheepService {

    @Override
    public Mono<SheepDTO> readSheep(String uuid) {
        return repository.findById(uuid).map(SheepDTO::fromEntity);
    }

    @Override
    public Mono<SheepDTO> getSheepWithPasscode(String passcode) {
        return repository.findByPasscode(passcode)
            .switchIfEmpty(repository.save(new SheepEntity(
                UUID.randomUUID().toString(),
                passcode
            )))
            .map(SheepDTO::fromEntity);
    }
}
