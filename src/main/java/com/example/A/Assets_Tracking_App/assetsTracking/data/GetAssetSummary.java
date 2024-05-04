package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;

/**
 * A summary of asset data.
 */
public class GetAssetSummary {
    // Total count of assets
    private final long assetCount;

    // Total cost of assets
    private final BigDecimal cost;

    // Total depreciated current value of assets
    private final BigDecimal depreciatedcurrentValue;

    /**
     * Constructs a new GetAssetSummary object.
     *
     * @param assetCount            The total count of assets.
     * @param cost                  The total cost of assets.
     * @param depreciatedcurrentValue The total depreciated current value of assets.
     */
    public GetAssetSummary(final long assetCount,
                           final BigDecimal cost,
                           final BigDecimal depreciatedcurrentValue) {
        this.assetCount = assetCount;
        this.cost = cost;
        this.depreciatedcurrentValue = depreciatedcurrentValue;
    }

    /**
     * Retrieves the total count of assets.
     *
     * @return The total count of assets.
     */
    public long getAssetCount() {
        return assetCount;
    }

    /**
     * Retrieves the total cost of assets.
     *
     * @return The total cost of assets.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Retrieves the total depreciated current value of assets.
     *
     * @return The total depreciated current value of assets.
     */
    public BigDecimal getDepreciatedcurrentValue() {
        return depreciatedcurrentValue;
    }
}
