package com.kazu.binance.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeInformationDataResp {
    private String timezone;
    private long serverTime;
    private List<RateLimit> rateLimits;
    private List<Filter> exchangeFilters;
    private List<Symbol> symbols;

    @Getter
    @Setter
    static class Symbol{
        private String symbol; 
        private String status; 
        private String baseAsset; 
        private long baseAssetPrecision; 
        private String quoteAsset; 
        private long quotePrecision; 
        private long quoteAssetPrecision; 
        private long baseCommissionPrecision; 
        private long quoteCommissionPrecision; 
        private List<String> orderTypes; 
        private boolean icebergAllowed; 
        private boolean ocoAllowed; 
        private boolean quoteOrderQtyMarketAllowed; 
        private boolean isSpotTradingAllowed; 
        private boolean isMarginTradingAllowed; 
        private List<Filter> filters; 
        private List<String> permissions; 
    }

    @Getter
    @Setter
    static class RateLimit{
        private String rateLimitType; 
        private String interval;
        private long intervalNum; 
        private long limit;
    }

    @Getter
    @Setter
    static class Filter{
        private String filterType; 
        private String minPrice; 
        private String maxPrice; 
        private String tickSize;
    }
}
