package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.Date;

public class SaveAssetDataSummary {
    final private String  title ;
    final private BigDecimal cost ;

    final private Date purchaseDate ;

    final private BigDecimal depreciationRate ;

    public SaveAssetDataSummary(String title, BigDecimal cost, Date purchaseDate, BigDecimal depreciationRate) {
        this.title = title;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.depreciationRate = depreciationRate;
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
}
