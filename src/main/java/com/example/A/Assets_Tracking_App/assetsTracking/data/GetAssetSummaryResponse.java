package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;

/**
 * Response object containing summary data of assets.
 */
public class GetAssetSummaryResponse {
    // Current count of assets
    private long currentAssetCount;

    // Current total cost of assets
    private long currentCost;

    // Current total depreciated current value of assets
    private BigDecimal currentDepreciatedCurrentValue;

    // Default constructor
    public GetAssetSummaryResponse() {
    }

    /**
     * Retrieves the current count of assets.
     *
     * @return The current count of assets.
     */
    public long getCurrentAssetCount() {
        return currentAssetCount;
    }

    /**
     * Sets the current count of assets.
     *
     * @param currentAssetCount The current count of assets.
     */
    public void setCurrentAssetCount(long currentAssetCount) {
        this.currentAssetCount = currentAssetCount;
    }

    /**
     * Retrieves the current total cost of assets.
     *
     * @return The current total cost of assets.
     */
    public long getCurrentCost() {
        return currentCost;
    }

    /**
     * Sets the current total cost of assets.
     *
     * @param currentCost The current total cost of assets.
     */
    public void setCurrentCost(long currentCost) {
        this.currentCost = currentCost;
    }

    /**
     * Retrieves the current total depreciated current value of assets.
     *
     * @return The current total depreciated current value of assets.
     */
    public BigDecimal getCurrentDepreciatedCurrentValue() {
        return currentDepreciatedCurrentValue;
    }

    /**
     * Sets the current total depreciated current value of assets.
     *
     * @param currentDepreciatedCurrentValue The current total depreciated current value of assets.
     */
    public void setCurrentDepreciatedCurrentValue(BigDecimal currentDepreciatedCurrentValue) {
        this.currentDepreciatedCurrentValue = currentDepreciatedCurrentValue;
    }
}
