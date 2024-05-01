package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.Date;

public class GetAssetDetailsSummary {
  private String title ;
  private Date purchaseTime ;

  private BigDecimal cost ;
  private BigDecimal depreciationRate ;

   private  BigDecimal depreciationValue ;

    public GetAssetDetailsSummary(String title, Date purchaseTime, BigDecimal cost, BigDecimal depreciationRate, BigDecimal depreciationValue) {
        this.title = title;
        this.purchaseTime = purchaseTime;
        this.cost = cost;
        this.depreciationRate = depreciationRate;
        this.depreciationValue = depreciationValue;
    }

    public String getTitle() {
        return title;
    }

    public Date getPurchaseTime() {
        return purchaseTime;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public BigDecimal getDepreciationValue() {
        return depreciationValue;
    }

    public void setDepreciationValue(BigDecimal depreciationValue) {
        this.depreciationValue = depreciationValue;
    }
}
