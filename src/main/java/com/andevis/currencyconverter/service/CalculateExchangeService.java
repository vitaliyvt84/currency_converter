package com.andevis.currencyconverter.service;

import com.andevis.currencyconverter.entity.ConvertOperation;
import com.andevis.currencyconverter.entity.Currency;
import com.andevis.currencyconverter.entity.CurrencyRate;
import com.andevis.currencyconverter.entity.DataFromXML;
import com.andevis.currencyconverter.repository.CurrencyRateRepository;
import com.andevis.currencyconverter.repository.CurrencyRepository;
import com.andevis.currencyconverter.repository.HistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @author Vitalii Tarasenko
 * @created 09-07-2022
 */
@Service("calculateExchangeService")
public class CalculateExchangeService {

    private static final Logger logger = LoggerFactory.getLogger(CalculateExchangeService.class);
    @Autowired
    private CurrencyRepository currencyRepository;
    @Autowired
    private CurrencyRateRepository currencyRateRepository;
    @Autowired
    private HistoryRepository historyRepository;

    public double calculateValue(String firstCurrency, String secondCurrency, double amount) {
        CurrencyRate firstCurRate = null;
        CurrencyRate secondCurRate = null;
        DataFromXML data;
        LocalDate currentDate = LocalDate.now();

        Optional<CurrencyRate> ocr1 = currencyRateRepository.findByDateAndCharCode(currentDate, firstCurrency);
        if (ocr1.isPresent()) {
            firstCurRate = ocr1.get();
        } else if (firstCurrency.equals("EUR")) {
            firstCurRate = currencyRateRepository.findByCharCode("EUR");
        }
        Optional<CurrencyRate> ocr2 = currencyRateRepository.findByDateAndCharCode(currentDate, secondCurrency);
        if (ocr2.isPresent()) {
            secondCurRate = ocr2.get();
        } else if (secondCurrency.equals("EUR")) {
            secondCurRate = currencyRateRepository.findByCharCode("EUR");
        }

        if (firstCurRate == null || secondCurRate == null) {
            LocalDate actualDate = XMLService.getActualDate();

            LocalDate lastDateFromDB = currencyRateRepository.findTopByOrderByIdDesc().getDate();
            logger.info("Last date from DB: " + lastDateFromDB);

            if(!actualDate.equals(lastDateFromDB)) {
                data = XMLService.parseRates();
                for (CurrencyRate currencyRate : data.getCurrencyRates()) {
                    if (currencyRate.getCharCode().equals(firstCurrency)) {
                        firstCurRate = currencyRate;
                    }
                    if (currencyRate.getCharCode().equals(secondCurrency)) {
                        secondCurRate = currencyRate;
                    }
                    currencyRateRepository.save(currencyRate);
                }
            } else {
                Optional<CurrencyRate> optCurrencyRate1 = currencyRateRepository.findByDateAndCharCode(actualDate, firstCurrency);
                if (optCurrencyRate1.isPresent()) {
                    firstCurRate = optCurrencyRate1.get();
                }
                Optional<CurrencyRate> optCurrencyRate2 = currencyRateRepository.findByDateAndCharCode(actualDate, secondCurrency);
                if (optCurrencyRate2.isPresent()) {
                    secondCurRate = optCurrencyRate2.get();
                }
            }
        }
        logger.info("cr1:" + firstCurRate + ", cr2:" + secondCurRate);

        double resultCurrencyRate = firstCurRate.getRate()/secondCurRate.getRate();
        double result = amount/resultCurrencyRate;
        result = Math.round(result * 1000.0) / 1000.0;
        resultCurrencyRate = Math.round(resultCurrencyRate * 1000.0) / 1000.0;

        ConvertOperation convertOperation = new ConvertOperation(firstCurrency, secondCurrency, resultCurrencyRate, amount, result, currentDate);

        logger.info("Save data in history");
        historyRepository.save(convertOperation);

        return result;
    }

    public List<ConvertOperation> getHistory(String firstCurrency, String secondCurrency, LocalDate date) {
        return historyRepository.findByFirstCurrencyAndSecondCurrencyAndDate(firstCurrency, secondCurrency, date);
    }

    public Iterable<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }
}
