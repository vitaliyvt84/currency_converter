package com.andevis.currencyconverter.repository;

import com.andevis.currencyconverter.entity.ConvertOperation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@Repository
public interface HistoryRepository extends CrudRepository<ConvertOperation, Long> {

    List<ConvertOperation> findByFirstCurrencyAndSecondCurrencyAndDate(String firstCurrency, String secondCurrency, LocalDate date);
}
