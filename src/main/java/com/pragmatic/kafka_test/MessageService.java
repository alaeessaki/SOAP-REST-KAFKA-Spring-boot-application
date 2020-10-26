package com.pragmatic.kafka_test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.pragmatic.kafkaTest.model.ModelTest;
import com.pragmatic.kafka_test.models.soap.Country;

@Service
public class MessageService {
	
	@KafkaListener(topics = "test4", groupId = "groupe-ms")
	public void onMessage(ConsumerRecord<String, Country> cr) {
		//traitement ghabiy
		System.out.println(cr.value());
	}
}


