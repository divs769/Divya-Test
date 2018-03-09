package com.zopa.model;

public class ZopaQuote {

    private double rate;
    private double monthlyRepayment;
    private double totalRepayment;

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMonthlyRepayment() {
        return monthlyRepayment;
    }

    public void setMonthlyRepayment(double monthlyRepayment) {
        this.monthlyRepayment = monthlyRepayment;
    }

    public double getTotalRepayment() {
        return totalRepayment;
    }

    public void setTotalRepayment(double totalRepayment) {
        this.totalRepayment = totalRepayment;
    }

    public String toString(){
        return "rate :"+getRate() + " monthlyRepayment :" +getMonthlyRepayment() + " totalRepayment :" +getTotalRepayment();
    }
}


