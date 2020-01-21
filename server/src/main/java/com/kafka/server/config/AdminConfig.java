package com.kafka.server.config;

import java.util.Properties;

import org.apache.kafka.clients.admin.AdminClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
