package com.kafka.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.client.model.Event;
import com.kafka.client.service.DatabaseService;
import com.kafka.client.service.ProducerService;

import reactor.core.publisher.Flux;

/**
 * @author Alcanzer
 *
 */
@RestController
@CrossOrigin("*")
public class ClientController {
	private static final Logger logger = LoggerFactory.getLogger(ClientController.class);
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private DatabaseService databaseService;
	
	/**
	 * @param event
	 */
	@PostMapping("/events")
	public void sendEvents(@RequestBody Event event) {
		logger.info(event.toString());
		producerService.sendEvent(event);
	}
	
	/**
	 * @param session
	 * @return
	 */
	@GetMapping("/events/{session}")
	public Flux<Event> getEvents(@PathVariable long session) {
		return databaseService.getBySession(session);
	}
}
