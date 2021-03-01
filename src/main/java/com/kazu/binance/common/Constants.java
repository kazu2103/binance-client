package com.kazu.binance.common;

public class Constants {

    /* API name */
    public final static String API_PING = "/ping";
    public final static String API_KLINES = "/klines";
    public final static String API_EXCHANGE_INFO = "/exchangeInfo";

    /* request parameters */
    // common
    public final static String RECV_WINDOW = "recvWindow";
    public final static String TIMESTAMP = "timestamp";
    public final static String SIGNATURE = "signature";

    // Kline/Candlestick Data
    public final static String SYMBOL = "symbol";
    public final static String INTERVAL = "interval";
    public final static String START_TIME = "startTime";
    public final static String END_TIME = "endTime";
    public final static String LIMIT = "limit";

    /* messages */
    public final static String FAILED_TO_CONNECT = "couldn't connect to {}";
    public final static String FAILED_TO_PARSE = "couldn't parse object to json {}";
    public final static String MISSING_MANDATORY_PARAMS = "There are missing mandatory parameter(s)";
}
