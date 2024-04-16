package com.kafka.checkout.broker.producer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class CategoriesProducer {

	private static final Logger LOG = LoggerFactory.getLogger(CategoriesProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void publish(String message) {

		ProducerRecord producer = new ProducerRecord<String, String>("t-categories", message);

		kafkaTemplate.send(producer)
				.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

					@Override
					public void onSuccess(SendResult<String, String> result) {
						LOG.info("Categories {} published succesfully", message);
					}

					@Override
					public void onFailure(Throwable ex) {
						LOG.info("Categories {} failed to publish because {}", message, ex.getMessage());
					}
				});

		LOG.info("Just a dummy message for categories {}", message);
	}

}
