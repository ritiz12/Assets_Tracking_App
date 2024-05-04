package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * A request object used to save asset data.
 */
public class SaveAssetDataRequest {

    // Title of the asset
    final private String title;

    // Cost of the asset
    final private BigDecimal cost;

    // Purchase date of the asset
    final private Date purchaseDate;

    // Depreciation rate of the asset
    final private BigDecimal depreciationRate;

    // Currency of the asset
    final private String currency;

    /**
     * Constructs a new SaveAssetDataRequest object.
     *
     * @param title            The title of the asset.
     * @param cost             The cost of the asset.
     * @param purchaseDate     The purchase date of the asset.
     * @param depreciationRate The depreciation rate of the asset.
     * @param currency         The currency of the asset.
     */
    public SaveAssetDataRequest(final String title,
                                final BigDecimal cost,
                                final Date purchaseDate,
                                final BigDecimal depreciationRate,
                                final String currency) {
        this.title = title;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.depreciationRate = depreciationRate;
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
     * Retrieves the cost of the asset.
     *
     * @return The cost of the asset.
     */
    public BigDecimal getCost() {
        return cost;
    }

    /**
     * Retrieves the purchase date of the asset.
     *
     * @return The purchase date of the asset.
     */
    public Date getPurchaseDate() {
        return purchaseDate;
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
     * Retrieves the currency of the asset.
     *
     * @return The currency of the asset.
     */
    public String getCurrency() {
        return currency;
    }
}
