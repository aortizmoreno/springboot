package com.kafka.checkout.broker.consumer;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.kafka.checkout.broker.message.Product;

@Service
public class DealOfTheDayConsumer {

	private static final Logger LOG = LoggerFactory.getLogger(DealOfTheDayConsumer.class);

	@KafkaListener(topics = "t-deal-of-the-day")
	public void listen(List<Product> message) {
		// simulate processing
		LOG.info("Processing Deal of the day {}",
		message);
	}
}
