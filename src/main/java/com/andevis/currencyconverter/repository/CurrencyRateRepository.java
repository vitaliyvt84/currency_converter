package com.andevis.currencyconverter.repository;

import com.andevis.currencyconverter.entity.CurrencyRate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Repository
public interface CurrencyRateRepository extends CrudRepository<CurrencyRate, String> {

    Optional<CurrencyRate> findByDateAndCharCode(LocalDate date, String charCode);
    CurrencyRate findByCharCode(String charCode);
    CurrencyRate findTopByOrderByIdDesc();
}
