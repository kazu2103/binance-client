package com.kazu.binance.common;

import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class Utilities {

    public static String signature(String queryStr, String secretKey) {
        try {
            SecretKeySpec sk = new SecretKeySpec(secretKey.getBytes(), "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(sk);

            byte[] macBytes = mac.doFinal(queryStr.getBytes());

            StringBuilder sb = new StringBuilder(2 * macBytes.length);
            for (byte b : macBytes) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            throw new RuntimeException("Unable to sign message.", e);
        }
    }

    public static long getTimestamp() {
        Date date = new Date();
        long msecTimestamp = date.getTime();
        return msecTimestamp;
    }
}
