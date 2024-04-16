package com.kafka.checkout.broker.consumer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kafka.checkout.broker.message.Product;

@Service
public class ProductsConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsConsumer.class);

	@KafkaListener(topics = "t-products")
	public void listen(List<Product> message) {
		// simulate processing
		LOG.info("Processing products {}",
		message);
	}
}
