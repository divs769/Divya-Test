package com.zopa.dao;

import com.zopa.LowRateQuoteSystem;
import com.zopa.model.MarketData;

import java.util.List;

public class MarketDao {

    public static final double MAXIMUM_AVAILABLE=15000;
    public static final double MINIMUM_AVAILABLE=1000;
    public static final double AMOUNT_MULTIPLES=100;

    public  List<MarketData> marketDataList;
    public  List<MarketData> getMarketDataList() {
        return marketDataList;
    }

    public static void setMarketDataList(List<MarketData> marketDataList) {
        marketDataList = marketDataList;
    }
}
