package com.pragmatic.kafka_test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaTestApplication {

	public static void main(String[] args) {		
		SpringApplication.run(KafkaTestApplication.class, args);
	}

}


