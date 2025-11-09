package com.streamhub.cmsservice.repository;

import com.streamhub.common.domain.Asset;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface AssetRepository extends ReactiveMongoRepository<Asset, String> {
}
