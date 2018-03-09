package com.zopa;

import com.zopa.calculator.QuoteCalculator;
import com.zopa.dao.MarketDao;
import com.zopa.model.MarketData;
import com.zopa.model.ZopaQuote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;


public class LowRateQuoteSystem {


    private static int requestedAmount;
    private static final String SEPRATOR = ",";
    private static String fileName ="";
    public static List<MarketData> marketDataList;
    public static void main(String[] args) {
        processInputData(args);

    }

    public static void processInputData(String[] args){
        if(args != null && args.length != 2){
            System.out.println("Please enter the path of file and requested amount for which you want to borrow");
        } else{
            if ( args.length > 1) {
                fileName =args[0].toString();
                String requestedMoney = args[1].toString();
                if(requestedMoney.matches("\\d+(\\.\\d+)?")){
                    setRequestedAmount(Integer.parseInt(requestedMoney));
                } else{
                    System.out.println("Please input number for borrowing money");
                }

            }
            processDataCsv(fileName);
            calculateZopaQuote(marketDataList,requestedAmount);
        }

    }

    public static void processDataCsv(String fileName) {

        try{

             int streamSize = (int) Files.lines(Paths.get(fileName)).count();
             List<List<String>> values = Files.lines(Paths.get(fileName)).
                     map(line -> Arrays.asList(line.split(","))).
                     collect(Collectors.toList()).
                     subList(1,streamSize);
             getMarketDataList(values);

        }catch (FileNotFoundException e){
            System.out.println("Please check if the given file exists");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void setRequestedAmount(int requestedAmount) {
        LowRateQuoteSystem.requestedAmount = requestedAmount;
    }

    public int getRequestedAmount() {
        return requestedAmount;
    }


    public static List<MarketData> getMarketDataList(List<List<String>> marketDatas) {
        marketDataList = new ArrayList<MarketData>();
        for(List<String> marketData:marketDatas){
            MarketData dao = new MarketData(marketData.get(0).toString(),
                    Double.parseDouble(marketData.get(1).toString()),
                    Integer.parseInt(marketData.get(2).toString()));
            marketDataList.add(dao);
        }
        new MarketDao().setMarketDataList(marketDataList);
        return marketDataList;
    }

    public static void calculateZopaQuote(List<MarketData> marketDataList,int requestedAmount){
        ZopaQuote quote = new ZopaQuote();
        quote = new QuoteCalculator(marketDataList,requestedAmount).getZopaQuote();
        System.out.println(quote);
    }



}
