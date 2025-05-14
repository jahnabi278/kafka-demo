package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

	private static Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;

	public void saveCreateUserLogs(User user) {
		logger.info(String.format("User created -> %s", user));
		kafkaTemplate.send(AppConstants.TOPIC_NAME_USER_LOG, user);
	}

}
