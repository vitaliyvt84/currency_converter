package com.andevis.currencyconverter.entity;

import java.util.List;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
public class DataFromXML {

    private List<Currency> currencies;
    private List<CurrencyRate> currencyRates;

    public DataFromXML(List<Currency> currencies, List<CurrencyRate> currencyRates) {
        this.currencies = currencies;
        this.currencyRates = currencyRates;
    }

    public List<Currency> getCurrencies() {
        return currencies;
    }

    public List<CurrencyRate> getCurrencyRates() {
        return currencyRates;
    }
}
