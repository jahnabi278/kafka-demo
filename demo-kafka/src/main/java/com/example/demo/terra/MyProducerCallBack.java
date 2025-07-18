package com.example.demo.terra;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class MyProducerCallBack implements Callback {

	@Override
	public void onCompletion(RecordMetadata metadata, Exception exception) {
		if (exception != null) {
			System.out.println("message sending failed");
		} else {
			System.out.println("message sent");
		}
	}

}
