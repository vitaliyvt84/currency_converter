package com.andevis.currencyconverter.entity;

import java.time.LocalDate;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
public class HistoryData {

    private String firstCurrency;
    private String secondCurrency;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
