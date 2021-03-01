package com.kazu.binance.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


import com.kazu.binance.service.ExchangeInfoClientService;
import com.kazu.binance.service.KlinesClientService;
import com.kazu.binance.service.PingClientsService;

@RestController
public class BinanceClientController {

	@Autowired
	PingClientsService pc;

	@Autowired
	KlinesClientService kc;

	@Autowired
	ExchangeInfoClientService eic;

	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = "/chart", method = RequestMethod.POST)
	public Map<String, String> chart(@ModelAttribute(name="symbol") String symbol, 
			@ModelAttribute(name="interval") String interval, 
			@ModelAttribute(name="startTime") String startTime,  
			@ModelAttribute(name="endTime")String endTime, 
			@ModelAttribute(name="limit")String limit
			) {
		Map<String, String> responseMap = new HashMap<>();
		String klineData = kc.klines(symbol, interval, startTime, endTime, limit);
		responseMap.put("klineData", klineData);
		
		return responseMap;
	}

	@RequestMapping(value = "/symbol", method = RequestMethod.GET)
	public Map<String, String> symbol() {
		Map<String, String> responseMap = new HashMap<>();
		String exchangeInfoData = eic.exchangeInfo();
		responseMap.put("exchangeInfoData", exchangeInfoData);
		
		return responseMap;
	}
}
