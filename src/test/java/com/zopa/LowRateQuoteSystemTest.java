package com.zopa;

import com.zopa.LowRateQuoteSystem;
import com.zopa.dao.MarketDao;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.HashMap;

import static org.junit.Assert.*;

public class LowRateQuoteSystemTest {
    HashMap<DecimalFormat,Integer> marketDataMap;
    LowRateQuoteSystem quoteSystem;
    String[] args= new String[2];
    @Before
    public void setUp() throws Exception {
        quoteSystem = new LowRateQuoteSystem();
        args[0] = "C:\\Users\\lmer569\\divyatest\\src\\test\\resources\\Market Data for Exercise - csv.csv";
        args[1] ="1000";
        quoteSystem.main(args);
        marketDataMap = new HashMap<DecimalFormat, Integer>();
    }

    @Test
    public void shouldReturnRequestedAmountWhenEnteredThroughArgs(){
        assertEquals(1000,quoteSystem.getRequestedAmount());
    }

    @Test
    public void shouldCreateListWithInputCSV(){
        assertSame(7,LowRateQuoteSystem.marketDataList.size());;
    }


}