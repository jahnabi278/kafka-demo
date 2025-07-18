package com.example.demo.terra;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface MessageConsumer {
	public List<ConsumerRecord<String, String>> consumeMessage(String topic);

}
