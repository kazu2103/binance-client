package com.kazu.binance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Slf4j
abstract class BinanceClientService {

	@Value("${api-key}")
	protected String apiKey;

	@Value("${secret-key}")
	protected String secretKey;

	@Value("${binance-endpoint-main}")
	protected String binanceEndpoint;

	@Value("${api-version}")
	protected String apiVersion;

	@Value("${recvWindow}")
	protected String recvWindow;

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Autowired
	protected RestTemplate restTemplate;

	// private String binanceEndpoint = BinanceEndpoint.getInstance().getEndpoint();

	public String sendRequest(String uri, RequestEntity<?> reqEntity, Class<?> responseType)
			throws RestClientException, JsonProcessingException {
		log.info("============ start request to {} ============", uri);
		log.info("Method: {}", reqEntity.getMethod());
		log.info("Headers: {}", reqEntity.getHeaders());
		log.info("Body: {}", reqEntity.getBody());
		ResponseEntity<?> resEntity = restTemplate.exchange(reqEntity, responseType);
		log.info("============ finished request to {} ============", uri);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(resEntity.getBody());
	}
}
