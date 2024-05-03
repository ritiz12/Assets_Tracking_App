package com.example.A.Assets_Tracking_App.assetsTracking.persistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import  java.util.Map ;
import java.math.BigDecimal;
import java.util.HashMap;

@Repository
public interface CurrencyConversionInterface {

String currencyConversionURL = "https://api.exchangerate-api.com/v4/latest/USD";

}
