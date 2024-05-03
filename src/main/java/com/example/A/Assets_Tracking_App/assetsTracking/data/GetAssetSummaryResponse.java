package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;

public class GetAssetSummaryResponse {
    private long currentAssetCount;
    private long currentCost;
    private BigDecimal currentDepreciatedCurrentValue;

    // Constructor
    public GetAssetSummaryResponse() {
    }

    // Getter and Setter for currentAssetCount
    public long getCurrentAssetCount() {
        return currentAssetCount;
    }

    public void setCurrentAssetCount(long currentAssetCount) {
        this.currentAssetCount = currentAssetCount;
    }

    // Getter and Setter for currentCost
    public long getCurrentCost() {
        return currentCost;
    }

    public void setCurrentCost(long currentCost) {
        this.currentCost = currentCost;
    }

    // Getter and Setter for currentDepreciatedCurrentValue
    public BigDecimal getCurrentDepreciatedCurrentValue() {
        return currentDepreciatedCurrentValue;
    }

    public void setCurrentDepreciatedCurrentValue(BigDecimal currentDepreciatedCurrentValue) {
        this.currentDepreciatedCurrentValue = currentDepreciatedCurrentValue;
    }


}
