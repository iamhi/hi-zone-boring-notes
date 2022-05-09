package com.github.iamhi.hizone.boringnotes.out;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SheepRepository extends ReactiveMongoRepository<SheepEntity, String> {

    Mono<SheepEntity> findByPasscode(String passcode);
}
