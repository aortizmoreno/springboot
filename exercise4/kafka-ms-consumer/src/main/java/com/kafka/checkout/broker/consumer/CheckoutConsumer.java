package com.kafka.checkout.broker.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka.checkout.api.models.Order;

@Service
public class CheckoutConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(CheckoutConsumer.class);

	@KafkaListener(topics = "t-order")
	public void listen(Order message) {
		// simulate processing
		LOG.info("Processing order {}, item {}",
		message.getOrderId(), message.getProduct().getProductId());
	}
}
