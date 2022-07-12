package com.andevis.currencyconverter.controller;

import com.andevis.currencyconverter.entity.*;
import com.andevis.currencyconverter.service.CalculateExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Vitalii Tarasenko
 * @created 10-07-2022
 */
@RestController
public class ConverterController {

    private static final Logger logger = LoggerFactory.getLogger(ConverterController.class);

    @Autowired
    CalculateExchangeService calculateExchangeService;

    @PostMapping("/convert")
    public ResponseEntity<?> convert(@RequestBody ConvertData convertData) {
        double exchangeResult = calculateExchangeService.calculateValue(convertData.getFirstCurrency(), convertData.getSecondCurrency(),
                convertData.getAmount());
        return ResponseEntity.ok(exchangeResult);
    }
    @GetMapping("/convert")
    public ModelAndView getData() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("converter");
        Iterable<Currency> currencies = calculateExchangeService.getAllCurrencies();
        mv.addObject("currencies", currencies);
        return mv;
    }

    @PostMapping("/history")
    public ResponseEntity<?> getHistory(@RequestBody HistoryData history) {
        logger.info("GET HISTORY");
        List<ConvertOperation> convertOperations = calculateExchangeService.getHistory(history.getFirstCurrency(), history.getSecondCurrency(),
                history.getDate());
        logger.info(convertOperations.toString());
        return ResponseEntity.ok(convertOperations);
    }
}
