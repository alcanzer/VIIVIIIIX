package com.kafka.server.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class KafkaHandler {
	
	public Mono<ServerResponse> getStatus(ServerRequest request){
		return ServerResponse.ok()
				.body(Mono.just("Active"), String.class);
	}
}
