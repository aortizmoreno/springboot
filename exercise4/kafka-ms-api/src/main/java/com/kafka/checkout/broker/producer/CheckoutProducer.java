package com.kafka.checkout.broker.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;
import com.kafka.checkout.api.models.Order;

@Service
public class CheckoutProducer {

	private static final Logger LOG = LoggerFactory.getLogger(CheckoutProducer.class);

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;

	public void publish(Order message) {

		ProducerRecord producer = new ProducerRecord<String, Order>("t-order", null, null, message, null);

		kafkaTemplate.send(producer)
				.addCallback(new ListenableFutureCallback<SendResult<String, Order>>() {

					@Override
					public void onSuccess(SendResult<String, Order> result) {
						LOG.info("Order {} published succesfully", message.getFirstName());
					}

					@Override
					public void onFailure(Throwable ex) {
						LOG.info("Order {} failed to publish because {}", message.getFirstName(), ex.getMessage());
					}
				});

		LOG.info("Just a dummy message for order {}", message.getFirstName());
	}

}
