package com.andevis.currencyconverter.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @GenericGenerator(name = "generator", strategy = "uuid2")
    @GeneratedValue(generator = "generator")
    @Column
    private String id;
    @Column
    private String charCode;
    @Column
    private String name;

    public Currency() {
    }

    public Currency(String charCode, String name) {
        this.charCode = charCode;
        this.name = name;
    }

    public String getId() {
        return id == null ? null : id.toUpperCase();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id='" + id + '\'' +
                ", charCode='" + charCode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
