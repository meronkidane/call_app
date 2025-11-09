package com.streamhub.cmsservice.repository;

import com.streamhub.common.domain.Episode;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EpisodeRepository extends ReactiveMongoRepository<Episode, String> {
}
