package com.example.calculator.ui.calculator;

public class CurrencyData
{

    private double rates;
    private String symbol;
    public CurrencyData( String symbol,double rates) {
        this.rates = rates;
        this.symbol = symbol;
    }
    public double getRates() {
        return rates;
    }

    public String getSymbol() {
        return symbol;
    }
}
