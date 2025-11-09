package com.streamhub.catalogservice.repository;

import com.streamhub.common.domain.Title;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface TitleRepository extends ReactiveMongoRepository<Title, String> {
    Flux<Title> findByGenresContainingIgnoreCase(String genre);
}
