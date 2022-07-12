package com.andevis.currencyconverter.service;

import com.andevis.currencyconverter.entity.Currency;
import com.andevis.currencyconverter.entity.CurrencyRate;
import com.andevis.currencyconverter.entity.DataFromXML;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Vitalii Tarasenko
 * @created 09-07-2022
 */
@Service
public class XMLService {

    private static final Logger logger = LoggerFactory.getLogger(XMLService.class);

    public static DataFromXML parseRates() {
        try {
            String URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
            Map<String, String> currencyNames = parseCurrenciesNames();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
            Element root = doc.getDocumentElement();
            NodeList nodeListRootCube = root.getElementsByTagName("Cube");
            Element rootCubeEl = (Element) nodeListRootCube.item(0);
            NodeList nodeListCubeWithTime = rootCubeEl.getElementsByTagName("Cube");
            Element innerCubeWithTimeEl = (Element) nodeListCubeWithTime.item(0);
            LocalDate date = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(innerCubeWithTimeEl.getAttribute("time"), formatter);
            logger.info(String.format("Reference rates %s", date));

            NodeList nodeListCubeCurrencyRate = innerCubeWithTimeEl.getElementsByTagName("Cube");

            List<Currency> currencies = new ArrayList<>();
            List<CurrencyRate> currencyRates = new ArrayList<>();

            for (int i = 0; i < nodeListCubeCurrencyRate.getLength(); i++) {
                Element cubeCurrencyEl = (Element) nodeListCubeCurrencyRate.item(i);

                String charCode = cubeCurrencyEl.getAttribute("currency");
                Currency currency = new Currency(charCode,
                        currencyNames.getOrDefault(charCode, ""));
                currencies.add(currency);

                CurrencyRate currencyRate = new CurrencyRate(date,
                        charCode,
                        Double.parseDouble(cubeCurrencyEl.getAttribute("rate")));
                currencyRates.add(currencyRate);
                logger.info(currency.toString());
                logger.info(currencyRate.toString());
            }

            return new DataFromXML(currencies, currencyRates);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public static Map<String, String> parseCurrenciesNames() {
        Map<String, String> result = new HashMap<>();

        String URL = "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html";
        try {
            org.jsoup.nodes.Document doc = Jsoup.connect(URL).timeout(5000).get();

            Elements currencies = doc.getElementsByClass("currency");
            Elements names = doc.getElementsByClass("alignLeft");
            for (int i = 0; i < currencies.size(); i++) {
                result.put(currencies.get(i).text(), names.get(i).text());
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return result;
    }

    public static LocalDate getActualDate() {
        try {
            String URL = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml";
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(URL);
            Element root = doc.getDocumentElement();
            NodeList nodeListRootCube = root.getElementsByTagName("Cube");
            Element rootCubeEl = (Element) nodeListRootCube.item(0);
            NodeList nodeListCubeWithTime = rootCubeEl.getElementsByTagName("Cube");
            Element innerCubeWithTimeEl = (Element) nodeListCubeWithTime.item(0);
            LocalDate date = null;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = LocalDate.parse(innerCubeWithTimeEl.getAttribute("time"), formatter);
            logger.info(String.format("Actual date is: %s", date));
            return date;
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
