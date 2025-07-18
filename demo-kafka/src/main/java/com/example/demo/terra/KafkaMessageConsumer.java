package com.example.demo.terra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class KafkaMessageConsumer implements MessageConsumer {

	String connectionString;
	String groupid;

	public KafkaMessageConsumer(String connectionString, String groupid) {
		this.connectionString = connectionString;
		this.groupid = groupid;
	}

	private Properties setProperties() {
		Properties properties = new Properties();
		properties.put("bootstrap.servers", connectionString);
		properties.put("group.id", groupid);
		properties.put("enable.auto.commit", "true");
		properties.put("auto.commit.interval.ms", "1000");
		properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return properties;
	}

	@Override
	public List<ConsumerRecord<String, String>> consumeMessage(String topic) {
		Properties properties = setProperties();
		KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);
		consumer.subscribe(Arrays.asList(topic));
		List<ConsumerRecord<String, String>> recordlist = new ArrayList<ConsumerRecord<String, String>>();
		while (true) {
			ConsumerRecords<String, String> records = consumer.poll(100);
			for (ConsumerRecord<String, String> record : records) {
				recordlist.add(record);
			}
			return recordlist;
		}
	}

}
