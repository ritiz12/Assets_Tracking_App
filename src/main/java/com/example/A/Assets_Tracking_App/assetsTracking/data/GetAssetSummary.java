package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;

public class GetAssetSummary {
    private  long  assetCount ;
    private BigDecimal cost ;
    private BigDecimal depreciatedcurrentValue;

    public GetAssetSummary(long assetCount, BigDecimal cost, BigDecimal currentValue) {
        this.assetCount = assetCount;
        this.cost = cost;
        this.depreciatedcurrentValue = currentValue;
    }

    public long getAssetCount() {
        return assetCount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public BigDecimal getDepreciatedcurrentValue() {
        return depreciatedcurrentValue;
    }
}
