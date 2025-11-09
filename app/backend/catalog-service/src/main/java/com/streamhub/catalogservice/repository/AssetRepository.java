package com.streamhub.catalogservice.repository;

import com.streamhub.common.domain.Asset;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface AssetRepository extends ReactiveMongoRepository<Asset, String> {
    Flux<Asset> findByParentId(String parentId);
}
