package com.example.demo.terra;

import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class DemoKafka {

	public static void main(String[] args) {
		KafkaMessageProducer messageProducer = new KafkaMessageProducer("localhost:9092");
		messageProducer.produceMessage("mytopic", "demomessage");
		KafkaMessageConsumer messageConsumer = new KafkaMessageConsumer("localhost:9092", "demogroupid");
		List<ConsumerRecord<String, String>> recordlist = messageConsumer.consumeMessage("mytopic");
		for (ConsumerRecord<String, String> record : recordlist) {
			System.out
					.println("offset = " + record.offset() + ", key = " + record.key() + ", value = " + record.value());
		}
	}

}
