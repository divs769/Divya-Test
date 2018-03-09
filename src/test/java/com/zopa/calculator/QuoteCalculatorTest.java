package com.zopa.calculator;

import com.zopa.model.MarketData;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class QuoteCalculatorTest {

    QuoteCalculator calculator;
    List<MarketData> marketDataList;

    @Before
    public void setUp() throws Exception {

        marketDataList = new ArrayList<MarketData>();
        marketDataList.add(new MarketData("Bob",0.075,64));
        marketDataList.add(new MarketData("Jane",0.069,480));
        marketDataList.add(new MarketData("Fred",0.071,520));
        marketDataList.add(new MarketData("Mary",0.104,170));
        marketDataList.add(new MarketData("John",0.081,320));
        marketDataList.add(new MarketData("Dave",0.074,140));
        marketDataList.add(new MarketData("Angela",0.071,60));
        calculator = new QuoteCalculator(marketDataList,1000);
    }

    @Test
    public void shouldReturnMonthlyRepaymentWhenPrincipleIsThousand(){
        assertEquals(84.03,calculator.monthlyRepayment(),0.01);
    }

    @Test
    public void shouldReturnTotalRepaymentWhenMonthlyPaymentThirty(){
        assertEquals(1080,calculator.totalRepayment(30,36),0.01);
    }

    @Test
    public void shouldReturnTrueIfRequestedAmountMultiplesOfHundred(){
        assertTrue(calculator.validateRequestAmount());
    }

    @Test
    public void shouldReturnFalseIfRequestedAmountMultiplesOfHundred(){
        assertTrue(calculator.validateRequestAmount());
    }

    @Test
    public void shouldReturnTrueIfRequestedAmountGreaterThanThousand(){
        assertTrue(calculator.validateRequestAmount());
    }

    @Test
    public void shouldReturnFalseIfRequestedAmountLessThanThousand(){
        assertTrue(calculator.validateRequestAmount());
    }

    @Test
    public void shouldReturnTrueIfRequestedAmountLessrThanFifteenThousand(){
        assertTrue(calculator.validateRequestAmount());
    }

    @Test
    public void shouldReturnFalseIfRequestedAmountGreatrThanFifteenThousand(){
        calculator = new QuoteCalculator(marketDataList,16500);
        assertFalse(calculator.validateRequestAmount());
    }

    @Test
    public void shouldTestIfRequiredAmountIsAvailable(){
        calculator = new QuoteCalculator(marketDataList,1500);
        assertTrue(calculator.checkIfRequiredAmountAvailable());
        calculator = new QuoteCalculator(marketDataList,2500);
        assertFalse(calculator.checkIfRequiredAmountAvailable());
    }

}