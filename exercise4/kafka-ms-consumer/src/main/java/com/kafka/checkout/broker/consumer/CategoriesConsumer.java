package com.kafka.checkout.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class CategoriesConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(CategoriesConsumer.class);

	@KafkaListener(topics = "t-categories")
	public void listen(String message) {
		// simulate processing
		LOG.info("Processing Categories {}",
				message);
	}
}
