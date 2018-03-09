package com.zopa.calculator;

import com.zopa.LowRateQuoteSystem;
import com.zopa.dao.MarketDao;
import com.zopa.model.MarketData;
import com.zopa.model.ZopaQuote;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuoteCalculator {

    List<MarketData> marketDataList = new ArrayList<MarketData>();
    int requestedAmount =0;
    public QuoteCalculator(List<MarketData> marketDataList,int amount){
        this.marketDataList = marketDataList;
        this.requestedAmount = amount;
    }


    public  boolean validateRequestAmount() {

        boolean validRequestedAmount = false;
        if( this.requestedAmount % MarketDao.AMOUNT_MULTIPLES == 0){
            validRequestedAmount = true;
        }

        if(this.requestedAmount > MarketDao.MINIMUM_AVAILABLE && this.requestedAmount < MarketDao.MAXIMUM_AVAILABLE)
        {
            validRequestedAmount = true;
        }
        validRequestedAmount = checkIfRequiredAmountAvailable();
        return validRequestedAmount;
    }

    public  boolean checkIfRequiredAmountAvailable() {
        boolean isAvailable = true;
        int totalAmountAvailable =0;
        if(marketDataList.size() > 0){
            for(MarketData marketData:this.marketDataList){
                totalAmountAvailable += marketData.getAvailable();
            }
        }

        if(this.requestedAmount > totalAmountAvailable){
            isAvailable = false;
            System.out.println("Required amount is not available with our System at the moment. Please come back later.");
        }
        return isAvailable;
    }

    public  double rateOfInterest(){
        double interestRate = 0.0;
        int totalAmountAvailable =0;
        marketDataList.stream().sorted(new Comparator<MarketData>() {
            @Override
            public int compare(MarketData o1, MarketData o2) {
                return (int)o1.getRate()-(int)o2.getRate();
            }
        });
        int count =0;
        for(MarketData marketData:marketDataList){
            totalAmountAvailable += marketData.getAvailable();
            interestRate += marketData.getRate();
            count++;
            if( totalAmountAvailable >= this.requestedAmount){
                break;
            }
        }
        interestRate = interestRate/count;
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(interestRate));
    }

    public double monthlyRepayment(){
        double monthlyRepayment=0;
        double interestRate = rateOfInterest();
        monthlyRepayment = (requestedAmount * (1+ interestRate/12))/12;

        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(monthlyRepayment));
    }


    public double totalRepayment(double monthlyRepayment, int months){
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(monthlyRepayment * months));
    }


    public  ZopaQuote getZopaQuote() {
        ZopaQuote quote = new ZopaQuote();
        if(validateRequestAmount()){
            quote.setRate(rateOfInterest());
            double monthlyRepaymentAmount = monthlyRepayment();
            quote.setMonthlyRepayment(monthlyRepaymentAmount);
            quote.setTotalRepayment(totalRepayment(monthlyRepaymentAmount,12));
        }
        return quote;
    }
}
