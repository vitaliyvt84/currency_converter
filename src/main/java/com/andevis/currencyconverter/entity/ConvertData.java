package com.andevis.currencyconverter.entity;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
public class ConvertData {

    private String firstCurrency;
    private String secondCurrency;
    private double amount;

    public String getFirstCurrency() {
        return firstCurrency;
    }

    public void setFirstCurrency(String firstCurrency) {
        this.firstCurrency = firstCurrency;
    }

    public String getSecondCurrency() {
        return secondCurrency;
    }

    public void setSecondCurrency(String secondCurrency) {
        this.secondCurrency = secondCurrency;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
