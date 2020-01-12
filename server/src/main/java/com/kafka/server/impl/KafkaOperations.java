package com.kafka.server.impl;

import java.util.List;

import com.kafka.server.model.CreateTopic;

/**
 * @author Alcanzer
 *
 */
public interface KafkaOperations {
	/**
	 * @return
	 */
	public List<String> getTopics();
	/**
	 * @param topic
	 * @return
	 */
	public String createTopic(CreateTopic topic);
	/**
	 * @param name
	 * @return
	 */
	public String filterTopic(String name);
	/**
	 * @param name
	 * @return
	 */
	public String deleteTopic(String name);
}
