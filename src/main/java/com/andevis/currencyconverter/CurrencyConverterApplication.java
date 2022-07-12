package com.andevis.currencyconverter;

import com.andevis.currencyconverter.entity.Currency;
import com.andevis.currencyconverter.entity.CurrencyRate;
import com.andevis.currencyconverter.entity.DataFromXML;
import com.andevis.currencyconverter.repository.CurrencyRateRepository;
import com.andevis.currencyconverter.repository.CurrencyRepository;
import com.andevis.currencyconverter.service.XMLService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CurrencyConverterApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConverterApplication.class, args);
    }

    @Bean
    ApplicationRunner init(CurrencyRepository repository, CurrencyRateRepository rateRepository) {

        DataFromXML data = XMLService.parseRates();
        return args -> {
            repository.save(new Currency("EUR", "Euro"));
            for (Currency c : data.getCurrencies())
                repository.save(c);
            rateRepository.save(new CurrencyRate(null, "EUR", 1.0));
            for (CurrencyRate cr : data.getCurrencyRates())
                rateRepository.save(cr);
        };
    }
}
