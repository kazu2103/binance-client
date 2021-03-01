package com.kazu.binance.service;

import com.kazu.binance.common.Constants;
import com.kazu.binance.domain.TestConnectivity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PingClientServiceImpl extends BinanceClientService implements PingClientsService {

    @Value("${api-version}")
    private String apiVersion;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public int ping(String endpoint) {
        String uri = endpoint + apiVersion + Constants.API_PING;
        RequestEntity<?> reqEntity = RequestEntity.get(uri).header("X-MBX-APIKEY", apiKey).build();
        try {
            ResponseEntity<?> resEntity = restTemplate.exchange(reqEntity, TestConnectivity.class);
            return resEntity.getStatusCode().value();
        } catch (RestClientException rce) {
            log.warn(Constants.FAILED_TO_CONNECT, uri);
            return -1;
        }
    }
}
