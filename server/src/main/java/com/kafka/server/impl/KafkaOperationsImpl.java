package com.kafka.server.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.DeleteTopicsResult;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kafka.server.config.AdminConfig;
import com.kafka.server.model.CreateTopic;

/**
 * @author Alcanzer
 *
 */
@Service
public class KafkaOperationsImpl implements KafkaOperations{
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaOperations.class);
	
	@Autowired
	private AdminConfig config;
	
	/* (non-Javadoc)
	 * @see com.kafka.server.impl.KafkaOperations#getTopics()
	 */
	@Override
	public List<String> getTopics() {
		LOGGER.info("Listing topics");
		try {
			AdminClient client = config.getClient();
			LOGGER.info("Retrieved topics: {}", client.listTopics().listings().get().toString());
			return client.listTopics().listings().get().stream().map(TopicListing::name).collect(Collectors.toList());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see com.kafka.server.impl.KafkaOperations#createTopic(com.kafka.server.model.CreateTopic)
	 */
	@Override
	public String createTopic(CreateTopic topic) {
		LOGGER.info("Creating topic with: {}", topic.toString());
		AdminClient client = config.getClient();
		try {
			CreateTopicsResult result = client.createTopics(Collections.singleton(new NewTopic(topic.getName(),
					topic.getNumPartitions(), topic.getReplications())));
			result.values().get(topic.getName()).get(5, TimeUnit.SECONDS);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
			String error = e.getLocalizedMessage();
			return error.substring(error.indexOf(":") + 1);
		}
		return "Success";
	}

	/* (non-Javadoc)
	 * @see com.kafka.server.impl.KafkaOperations#filterTopic(java.lang.String)
	 */
	@Override
	public String filterTopic(String name) {
		LOGGER.info("Filtering topic with name: {}", name);
		AdminClient client = config.getClient();
		try {
			DescribeTopicsResult result = client.describeTopics(Collections.singleton(name));
			String kafkaResult = result.all().get().toString();
			LOGGER.info("Result for filterTopic: {}", kafkaResult);
			return kafkaResult;
		} catch(InterruptedException | ExecutionException e) {
			e.printStackTrace();
			String error = e.getLocalizedMessage();
			return error.substring(error.indexOf(":") + 1);
		}
	}

	/* (non-Javadoc)
	 * @see com.kafka.server.impl.KafkaOperations#deleteTopic(java.lang.String)
	 */
	@Override
	public String deleteTopic(String name) {
		LOGGER.info("Deleting topic with name: {}", name);
		AdminClient client = config.getClient();
		try {
			DeleteTopicsResult result = client.deleteTopics(Collections.singleton(name));
			result.all().get();
			LOGGER.info("Result for deleteTopic: {}", "Success");
			return "Success";
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			String error = e.getLocalizedMessage();
			return error.substring(error.indexOf(":") + 1);
		}
	}
}
