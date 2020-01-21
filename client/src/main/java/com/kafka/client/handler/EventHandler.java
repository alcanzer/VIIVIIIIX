package com.kafka.client.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kafka.client.model.Event;
import com.kafka.client.service.DatabaseService;
import com.kafka.client.service.ProducerService;

import reactor.core.publisher.Mono;

@Component
public class EventHandler {
	private static final Logger logger = LoggerFactory.getLogger(EventHandler.class);
	
	@Autowired
	private ProducerService producerService;
	
	@Autowired
	private DatabaseService databaseService;
	
	public Mono<ServerResponse> getStatus(ServerRequest req) {
		return ServerResponse.ok()
				.body(Mono.just("Active"), String.class);
	}
	
	public Mono<ServerResponse> sendEvent(ServerRequest req) {
		return req.bodyToMono(Event.class)
				.doOnNext(evt -> {
					logger.info(evt.toString());
					producerService.sendEvent(evt);;
				})
				.then(ServerResponse.ok().build());
	}
	
	public Mono<ServerResponse> getEvents(ServerRequest req) {
		String session = req.pathVariable("session");
		return ServerResponse.ok()
				.body(databaseService.getBySession(Long.getLong(session)), Event.class);
	}
}
