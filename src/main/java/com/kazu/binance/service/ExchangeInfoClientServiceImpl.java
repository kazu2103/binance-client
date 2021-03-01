package com.kazu.binance.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kazu.binance.common.Constants;
import com.kazu.binance.domain.ExchangeInformationDataResp;

@Service
@Slf4j
public class ExchangeInfoClientServiceImpl extends BinanceClientService implements ExchangeInfoClientService {

	@Override
	public String exchangeInfo() {

		// create query string
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(binanceEndpoint + apiVersion + Constants.API_EXCHANGE_INFO);
		String uri = builder.toUriString();

		// set request header
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-MBX-APIKEY", apiKey);
		RequestEntity<?> reqEntity = RequestEntity.get(uri).header("X-MBX-APIKEY", apiKey).build();

		// send request
		try {
			String json = sendRequest(uri, reqEntity, ExchangeInformationDataResp.class);
			return json;
		} catch (RestClientException rce) {
			log.error(Constants.FAILED_TO_CONNECT, uri);
			rce.printStackTrace();
			return null;
		} catch (JsonProcessingException jpe){
			log.error(Constants.FAILED_TO_PARSE, uri);
			return null;
		}
	}

}
