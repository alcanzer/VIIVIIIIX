package com.kafka.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kafka.client.handler.EventHandler;



@Configuration
public class AppConfig {
	
	@Bean
	public RouterFunction<ServerResponse> routes(EventHandler evt) {
		return RouterFunctions.route(RequestPredicates.GET("/status"),
				evt::getStatus)
				.andRoute(RequestPredicates.POST("/event"), evt::sendEvent)
				.andRoute(RequestPredicates.GET("/events/{session}"), evt::getEvents);
	}
}
