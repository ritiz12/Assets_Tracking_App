package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.Date;

public class SaveAssetDataRequest {

    final private String  title ;
    final private BigDecimal cost ;

    final private Date purchaseDate ;

    final private BigDecimal depreciationRate ;

    final private String currency ;

    public SaveAssetDataRequest(String title, BigDecimal cost, Date purchaseDate, BigDecimal depreciationRate , String currency) {
        this.title = title;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.depreciationRate = depreciationRate;
        this.currency = currency ;
    }

    public String getTitle() {
        return title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public String getCurrency() {
        return currency;
    }
}
