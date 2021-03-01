package com.kazu.binance.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
@JsonPropertyOrder()
@JsonIgnoreProperties(ignoreUnknown = true)
public class KlineCandlestickDataResp {
    private long openTime;
    private float open;
    private float high;
    private float low;
    private float close;
    private float volume;
    private long closeTime;
    private float quoteAssetVolume;
    private long numberOfTrades;
    private float takerBuyBaseAssetVolume;
    private float TakerBuyQuoteAssetVolume;
    private float ignore;
}
