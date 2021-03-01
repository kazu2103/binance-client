package com.kazu.binance.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kazu.binance.common.Constants;
import com.kazu.binance.domain.KlineCandlestickDataResp;

@Service
@Slf4j
public class KlinesClientServiceImpl extends BinanceClientService implements KlinesClientService {

	@Override
	public String klines(String symbol, String interval, String startTime, String endTime, String limit) {
		if(symbol.isEmpty() || interval.isEmpty()){
			log.warn(Constants.MISSING_MANDATORY_PARAMS);
			return null;
		}
		// create query string
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(binanceEndpoint + apiVersion + Constants.API_KLINES);
		builder.queryParam(Constants.SYMBOL, symbol);
		builder.queryParam(Constants.INTERVAL, interval);
		if(!startTime.isEmpty()){
			builder.queryParam(Constants.START_TIME, startTime);
		}
		if(!endTime.isEmpty()){
			builder.queryParam(Constants.END_TIME, endTime);
		}
		if(!limit.isEmpty()){
			builder.queryParam(Constants.LIMIT, limit);
		}
		String uri = builder.toUriString();

		// set request header
		HttpHeaders headers = new HttpHeaders();
		headers.set("X-MBX-APIKEY", apiKey);
		RequestEntity<?> reqEntity = RequestEntity.get(uri).header("X-MBX-APIKEY", apiKey).build();

		// send request
		try {
			String json = sendRequest(uri, reqEntity, KlineCandlestickDataResp[].class);
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
