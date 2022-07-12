package com.andevis.currencyconverter.repository;

import com.andevis.currencyconverter.entity.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {

    Currency findByCharCode(String charCode);
}
