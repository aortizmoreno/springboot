package com.kafka.checkout.broker.producer;

import java.util.List;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.checkout.api.models.Product;

@Service
public class ProductsProducer {

	private static final Logger LOG = LoggerFactory.getLogger(ProductsProducer.class);

	@Autowired
	private KafkaTemplate<String, List<Product>> kafkaTemplate;

	public void publish(List<Product> message) {

		ProducerRecord producer = new ProducerRecord<String, List<Product>>("t-products", message);

		kafkaTemplate.send(producer)
				.addCallback(new ListenableFutureCallback<SendResult<String, List<Product>>>() {

					@Override
					public void onSuccess(SendResult<String, List<Product>> result) {
						LOG.info("Products {} published succesfully", message);
					}

					@Override
					public void onFailure(Throwable ex) {
						LOG.info("Products {} failed to publish because {}", message, ex.getMessage());
					}
				});

		LOG.info("Just a dummy message for products {}", message);
	}

}
