package com.streamhub.analyticsservice.repository;

import com.streamhub.common.domain.EventRecord;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EventRepository extends ReactiveMongoRepository<EventRecord, String> {
}
