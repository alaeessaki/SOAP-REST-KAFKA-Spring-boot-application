package com.pragmatic.kafka_test;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pragmatic.kafkaTest.model.ModelTest;

@RestController
public class RestControllerTest {
	
	
	private static final Logger logger = LoggerFactory.getLogger(RestControllerTest.class);

	
	@Autowired 
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	@GetMapping("/send/{message}/{topic}")
	public String send(@PathVariable String message, @PathVariable String topic) {
		//traitement (check contrat)
		kafkaTemplate.send(topic,"Key => "+message.length(),message);
		return "Message Sent";
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE},path = "/send/{topic}")
	public ModelTest sendXml(@RequestBody ModelTest modelTest, @PathVariable String topic) {
		logger.info(String.format("User created -> %s", modelTest));
		modelTest.setName("AlaeModified");
	    this.kafkaTemplate.send(topic,"Key =>"+Model.class.getName() ,modelTest);
		return modelTest;
	}
}


