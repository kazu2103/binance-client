package com.kazu.binance.service;

public interface KlinesClientService {
	public String klines(String symbol, String interval, String startTime, String endTime, String limit);
}
