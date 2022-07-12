package com.andevis.currencyconverter.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Entity
public class ConvertOperation {
    @Id @GeneratedValue
    @Column
    private Long id;
    @Column
    private String firstCurrency;
    @Column
    private String secondCurrency;
    @Column
    private double currencyRate;
    @Column
    private double sourceSum;
    @Column
    private double resultSum;
    @Column
    private LocalDate date;

    public ConvertOperation() {
    }

    public ConvertOperation(String firstCurrency, String secondCurrency, double currencyRate, double sourceSum, double resultSum, LocalDate date) {
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.currencyRate = currencyRate;
        this.sourceSum = sourceSum;
        this.resultSum = resultSum;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public double getCurrencyRate() {
        return currencyRate;
    }

    public void setCurrencyRate(double currencyRate) {
        this.currencyRate = currencyRate;
    }

    public double getSourceSum() {
        return sourceSum;
    }

    public void setSourceSum(double sourceSum) {
        this.sourceSum = sourceSum;
    }

    public double getResultSum() {
        return resultSum;
    }

    public void setResultSum(double resultSum) {
        this.resultSum = resultSum;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ConvertOperation{" +
                "id=" + id +
                ", firstCurrency='" + firstCurrency + '\'' +
                ", secondCurrency='" + secondCurrency + '\'' +
                ", currencyRate=" + currencyRate +
                ", sourceSum=" + sourceSum +
                ", resultSum=" + resultSum +
                ", date=" + date +
                '}';
    }
}
