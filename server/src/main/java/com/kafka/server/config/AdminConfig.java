package com.kafka.server.config;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.kafka.server.handler.KafkaHandler;

/**
 * @author Alcanzer
 *
 */
@Configuration
public class AdminConfig {
	
	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${kafka.client-id}")
	private String clientId;
	
	/**
	 * @return
	 */
	public Properties getProps( ) {
		Properties props = new Properties();
		props.put("bootstrap.servers", bootstrapServers);
		props.put("client.id", clientId);
		return props;
	}
	
	/**
	 * @return
	 */
	@Bean
	public AdminClient getClient() {
		return AdminClient.create(getProps());
	}
	
	@Bean
	public RouterFunction<ServerResponse> route(KafkaHandler handler) {
		return RouterFunctions.route(RequestPredicates.GET("/status"), handler::getStatus);
	}
}
