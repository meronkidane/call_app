package com.streamhub.cmsservice.repository;

import com.streamhub.common.domain.Title;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TitleRepository extends ReactiveMongoRepository<Title, String> {
}
