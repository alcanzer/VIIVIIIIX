package com.kafka.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.client.model.Event;
import com.kafka.client.repository.EventRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Alcanzer
 *
 */

@Service
public class DatabaseService {
	private static final Logger logger = LoggerFactory.getLogger(DatabaseService.class);

	@Autowired
	private EventRepository eventRepository;

	/**
	 * @param session
	 * @return
	 */

	public Flux<Event> getBySession(Long session) {
		logger.info("Finding with session: {}", session);
		return eventRepository.findBySession(session);
	}

	/**
	 * @param evt
	 * @return
	 */
	public Mono<Event> save(Event evt) {
		logger.info("Saving event: {}", evt.toString());
		return eventRepository.save(evt);
	}

}
