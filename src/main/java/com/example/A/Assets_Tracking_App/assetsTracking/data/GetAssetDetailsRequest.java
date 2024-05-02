package com.example.A.Assets_Tracking_App.assetsTracking.data;

import com.example.A.Assets_Tracking_App.assetsTracking.common.PaginatedRequest;
import com.example.A.Assets_Tracking_App.assetsTracking.common.Request;

import java.math.BigDecimal;
import java.util.Date;

public class GetAssetDetailsRequest  extends PaginatedRequest {
    final String title ;
    final BigDecimal cost ;
    final Date purchaseDate ;

    final BigDecimal depreciationRate ;

    public GetAssetDetailsRequest(String title, BigDecimal cost, Date purchaseDate, BigDecimal depreciationRate) {
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
