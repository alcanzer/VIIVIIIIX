package com.kafka.client.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.kafka.client.model.Event;

import reactor.core.publisher.Flux;

/**
 * @author Alcanzer
 *
 */
@Repository
public interface EventRepository  extends ReactiveMongoRepository<Event, Long>{
	
	/**
	 * @param session
	 * @return
	 */
	public Flux<Event> findBySession(Long session);
}
