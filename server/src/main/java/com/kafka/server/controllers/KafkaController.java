package com.kafka.server.controllers;

import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.server.config.AdminConfig;
import com.kafka.server.impl.KafkaOperations;
import com.kafka.server.model.CreateTopic;

import reactor.core.publisher.Mono;

/**
 * @author Alcanzer
 *
 */
@RestController
@CrossOrigin("*")
public class KafkaController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaController.class);
	
	@Autowired
	private AdminConfig config;
	
	@Autowired
	private KafkaOperations operations;
	
	/**
	 * @return
	 */
	@GetMapping("/topics")
	public Mono<List<String>> getTopics() {
		LOGGER.info(operations.getTopics().toString());
		return Mono.fromSupplier(operations::getTopics);
	}
	
	/**
	 * @return
	 */
	@GetMapping("/status")
	public Mono<String> getStatus() {
		return Mono.fromSupplier(() -> "Active");
	}
	
	/**
	 * @return
	 */
	@GetMapping("/properties")
	public Properties getProperties() {
		return config.getProps();
	}
	
	/**
	 * @param topic
	 * @return
	 */
	@PostMapping("/create")
	public String createTopic(@RequestBody CreateTopic topic) {
		return operations.createTopic(topic);
	}
	
	/**
	 * @param name
	 * @return
	 */
	@GetMapping("/filter/{name}")
	public String filterTopic(@PathVariable String name) {
		return operations.filterTopic(name);
	}
	
	/**
	 * @param name
	 * @return
	 */
	@GetMapping("/delete/{name}")
	public String deleteTopic(@PathVariable String name) {
		return operations.deleteTopic(name);
	}
}
