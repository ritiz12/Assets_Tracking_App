package com.example.A.Assets_Tracking_App.assetsTracking.data;

public class GetAssetSummaryResponse {
    private long currentAssetCount;
    private long currentCost;
    private long currentDepreciatedCurrentValue;

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
    public long getCurrentDepreciatedCurrentValue() {
        return currentDepreciatedCurrentValue;
    }

    public void setCurrentDepreciatedCurrentValue(long currentDepreciatedCurrentValue) {
        this.currentDepreciatedCurrentValue = currentDepreciatedCurrentValue;
    }


}
