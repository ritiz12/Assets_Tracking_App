package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A summary of asset details.
 */
public class GetAssetDetailsSummary {
    // Title of the asset
    private final String title;

    // Purchase time of the asset
    private final Date purchaseTime;

    // Cost of the asset
    private final BigDecimal cost;

    // Depreciation rate of the asset
    private final BigDecimal depreciationRate;

    // Depreciation value of the asset
    private BigDecimal depreciationValue;

    // Currency of the asset
    private final String currency;

    /**
     * Constructs a new GetAssetDetailsSummary object.
     *
     * @param title            The title of the asset.
     * @param purchaseTime     The purchase time of the asset.
     * @param cost             The cost of the asset.
     * @param depreciationRate The depreciation rate of the asset.
     * @param depreciationValue The depreciation value of the asset.
     * @param currency         The currency of the asset.
     */
    public GetAssetDetailsSummary(final String title,
                                  final Date purchaseTime,
                                  final BigDecimal cost,
                                  final BigDecimal depreciationRate,
                                  final BigDecimal depreciationValue,
                                  final String currency) {
        this.title = title;
        this.purchaseTime = purchaseTime;
        this.cost = cost;
        this.depreciationRate = depreciationRate;
        this.depreciationValue = depreciationValue;
        this.currency = currency;
    }

    /**
     * Retrieves the title of the asset.
     *
     * @return The title of the asset.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Retrieves the purchase time of the asset.
     *
     * @return The purchase time of the asset.
     */
    public Date getPurchaseTime() {
        return purchaseTime;
    }

    /**
     * Retrieves the cost of the asset.
     *
     * @return The cost of the asset.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Retrieves the depreciation rate of the asset.
     *
     * @return The depreciation rate of the asset.
     */
    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    /**
     * Retrieves the depreciation value of the asset.
     *
     * @return The depreciation value of the asset.
     */
    public BigDecimal getDepreciationValue() {
        return depreciationValue;
    }

    /**
     * Sets the depreciation value of the asset.
     *
     * @param depreciationValue The depreciation value of the asset.
     */
    public void setDepreciationValue(BigDecimal depreciationValue) {
        this.depreciationValue = depreciationValue;
    }

    /**
     * Retrieves the currency of the asset.
     *
     * @return The currency of the asset.
     */
    public String getCurrency() {
        return currency;
    }
}
