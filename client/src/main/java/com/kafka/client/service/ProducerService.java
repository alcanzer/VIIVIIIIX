package com.kafka.client.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.client.model.Event;

/**
 * @author Alcanzer
 *
 */
@Service
public class ProducerService {
	private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

	@Value("${kafka.topicName}")
	private String topicName;

	@Autowired
	KafkaTemplate<String, Event> kafkaTemplate;

	/**
	 * @param evt
	 */
	public void sendEvent(Event evt) {
		logger.info("Producer sent: {}", evt.toString());
		kafkaTemplate.send(topicName, evt);
	}

}
