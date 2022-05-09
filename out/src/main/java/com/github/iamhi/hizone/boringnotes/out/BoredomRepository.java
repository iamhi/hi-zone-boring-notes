package com.github.iamhi.hizone.boringnotes.out;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BoredomRepository extends ReactiveMongoRepository<BoringNoteEntity, String> {

    Flux<BoringNoteEntity> findByOwnerUuid();

    Mono<BoringNoteEntity> findByUuidAndOwnerUuid();
}
