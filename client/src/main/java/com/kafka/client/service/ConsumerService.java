package com.kafka.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;

import com.kafka.client.model.Event;

import reactor.core.publisher.Mono;

/**
 * @author Alcanzer
 *
 */
@EnableKafka
@Configuration
public class ConsumerService {
	private static final Logger logger = LoggerFactory.getLogger(ConsumerService.class);
	
	@Autowired
	private DatabaseService databaseService;
	
	/**
	 * @param evt
	 */
	@KafkaListener(topics = "webpage-data", groupId = "consumer-1")
	public void listen(Event evt) {
		logger.info("Consumer received: {}", evt.toString());
		try {
			Mono<Event> result = databaseService.save(evt);
			logger.info("Event saved: {}", result.thenReturn(true));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
