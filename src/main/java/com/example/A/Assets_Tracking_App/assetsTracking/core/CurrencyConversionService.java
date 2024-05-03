package com.example.A.Assets_Tracking_App.assetsTracking.core;

import com.example.A.Assets_Tracking_App.assetsTracking.persistence.CurrencyConversionInterface;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyConversionService {
   private static final String EXCHANGE_RATE_API_URL = "https://api.exchangerate-api.com/v4/latest/USD";
/*@Autowired
private CurrencyConversionInterface currencyConversionInterface;


 */

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    Map<String, BigDecimal> rates ;
    public Map<String, BigDecimal> fetchExchangeRatesFromApi() {
        Map<String, Object> exchangeRates = restTemplateBuilder.build().getForObject(EXCHANGE_RATE_API_URL, Map.class);
        Map<String, BigDecimal> rates = new HashMap<>();

        if (exchangeRates != null) {
            Map<String, Object> exchangeRatesObject = (Map<String, Object>) exchangeRates.get("rates");
            for (Map.Entry<String, Object> entry : exchangeRatesObject.entrySet()) {
                if (entry.getValue() instanceof Double) {
                    rates.put(entry.getKey(), BigDecimal.valueOf((Double) entry.getValue()));
                } else if (entry.getValue() instanceof Integer) {
                    rates.put(entry.getKey(), BigDecimal.valueOf((Integer) entry.getValue()));
                } else {
                    // Handle other types if necessary
                }
            }
        }

        return rates;
    }

    public BigDecimal getExcahngeRate(String currency , Map<String, BigDecimal> rates) {
        return rates != null ? rates.get(currency) : BigDecimal.ZERO ;
    }



    public void printExchangeRates() {
        Map<String, BigDecimal> exchangeRates = fetchExchangeRatesFromApi();
        if (exchangeRates != null) {
            System.out.println("Exchange Rates:");
            for (Map.Entry<String, BigDecimal> entry : exchangeRates.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Failed to fetch exchange rates from API.");
        }
    }
}
