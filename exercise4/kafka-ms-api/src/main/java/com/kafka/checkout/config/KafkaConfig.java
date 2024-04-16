package com.kafka.checkout.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

	@Bean
	public NewTopic topicOrder() {
		return TopicBuilder.name("t-order").partitions(2).replicas(1).build();
	}

	@Bean
	public NewTopic topicCategories() {
		return TopicBuilder.name("t-categories").partitions(2).replicas(1).build();
	}
	
	@Bean
	public NewTopic topicProducts() {
		return TopicBuilder.name("t-products").partitions(2).replicas(1).build();
	}

	@Bean
	public NewTopic topicDealOfTheDay() {
		return TopicBuilder.name("t-deal-of-the-day").partitions(2).replicas(1).build();
	}
}
