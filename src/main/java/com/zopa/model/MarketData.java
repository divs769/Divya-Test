package com.zopa.model;

public final class MarketData {

    private final String name;
    private final double rate;
    private final int available;

    public MarketData(String name, double rate, int available){
        this.name = name;
        this.rate= rate;
        this.available= available;
    }


    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getAvailable() {
        return available;
    }


}
