package com.andevis.currencyconverter.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Entity
@Table(name = "currency_rate")
public class CurrencyRate {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    @Column
    private String id;
    @Column
    private LocalDate date;
    @Column
    private String charCode;
    @Column
    private double rate;

    public CurrencyRate() {
    }

    public CurrencyRate(LocalDate date, String charCode, double rate) {
        this.date = date;
        this.charCode = charCode;
        this.rate = rate;
    }

    public String getId() {
        return id == null ? null : id.toUpperCase();
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "CurrencyRate{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", charCode='" + charCode + '\'' +
                ", rate=" + rate +
                '}';
    }
}
