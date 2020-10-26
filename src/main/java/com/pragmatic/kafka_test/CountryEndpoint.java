package com.pragmatic.kafka_test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pragmatic.kafka_test.models.soap.GetCountryRequest;
import com.pragmatic.kafka_test.models.soap.GetCountryResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "http://pragmatic.com/kafka_test/models/soap";

	@Autowired 
	private KafkaTemplate<String, Object> kafkaTemplate;
	
	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));
		kafkaTemplate.send("test4","Key => "+response.getCountry().getName(),response.getCountry());
		return response;
	}
}