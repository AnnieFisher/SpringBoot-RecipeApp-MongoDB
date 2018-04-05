package com.Spring5.repositories.reactive;

import com.Spring5.model.UnitOfMeasure;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface UnitOfMeasureReactiveRepository extends ReactiveMongoRepository<UnitOfMeasure, String>{

    Mono<UnitOfMeasure> findByDescription(String description);
}
