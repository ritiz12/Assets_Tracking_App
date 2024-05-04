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
    // URL of the exchange rate API
    private static final String EXCHANGE_RATE_API_URL = "https://api.exchangerate-api.com/v4/latest/USD";

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    // Map to store exchange rates
    Map<String, BigDecimal> rates;

    // Method to fetch exchange rates from the API
    public Map<String, BigDecimal> fetchExchangeRatesFromApi() {
        // Call the API and retrieve exchange rate data
        Map<String, Object> exchangeRates = restTemplateBuilder.build().getForObject(EXCHANGE_RATE_API_URL, Map.class);
        Map<String, BigDecimal> rates = new HashMap<>();

        // Extract exchange rates from the API response
        if (exchangeRates != null) {
            Map<String, Object> exchangeRatesObject = (Map<String, Object>) exchangeRates.get("rates");
            for (Map.Entry<String, Object> entry : exchangeRatesObject.entrySet()) {
                // Convert exchange rate values to BigDecimal and add to the rates map
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

    // Method to get the exchange rate for a specific currency
    public BigDecimal getExcahngeRate(String currency, Map<String, BigDecimal> rates) {
        // Retrieve exchange rate for the given currency from the rates map
        return rates != null ? rates.get(currency) : BigDecimal.ZERO;
    }

    // Method to print exchange rates to the console
    public void printExchangeRates() {
        // Fetch exchange rates from the API
        Map<String, BigDecimal> exchangeRates = fetchExchangeRatesFromApi();
        if (exchangeRates != null) {
            // Print exchange rates to the console
            System.out.println("Exchange Rates:");
            for (Map.Entry<String, BigDecimal> entry : exchangeRates.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            // Handle the case where exchange rates cannot be fetched from the API
            System.out.println("Failed to fetch exchange rates from API.");
        }
    }
}
