package com.streamhub.userservice.repository;

import com.streamhub.common.domain.Profile;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ProfileRepository extends ReactiveMongoRepository<Profile, String> {
    Flux<Profile> findByUserId(String userId);
}
