package com.example.demo.terra;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class KafkaMessageProducer implements MessageProducer {

	private String connectionString;

	public KafkaMessageProducer(String connectionString) {
		this.connectionString = connectionString;
	}

	private Properties setProperties() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", connectionString);
		properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		return properties;
	}

	@Override
	public void produceMessage(String topic, String message) {
		Properties properties = setProperties();
		KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
		producer.send(record, new MyProducerCallBack());
		producer.close();
	}

}
