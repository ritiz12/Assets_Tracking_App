package com.example.A.Assets_Tracking_App.assetsTracking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Represents an asset in the application domain.
 */
@Entity
@Table(name = "Asset")
public class Asset {

    // Cost of the asset
    @NotNull(message = "Please Enter the Cost, it cannot be null")
    @Column(name = "cost", nullable = false, updatable = false)
    private BigDecimal cost;

    // Currency of the asset
    @NotNull(message = "Please Select the currency, it cannot be null")
    @NotBlank(message = "Currency field cannot be Blank, please enter a valid value")
    @Column(name = "currency", nullable = false, updatable = false)
    private String currency;

    // Depreciation rate of the asset
    @NotNull(message = "Please Enter the Depreciation Rate, it cannot be null")
    @Column(name = "depreciationRate", nullable = false, updatable = false)
    private BigDecimal depreciationRate;

    // Unique identifier for the asset
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    // Purchase date of the asset
    @Column(name = "purchaseDate", nullable = false, updatable = false)
    private Date purchaseDate;

    // Title of the asset
    @NotNull(message = "Please Enter the Title, it cannot be null")
    @NotBlank(message = "Title field cannot be Blank, please enter a valid value")
    @Column(name = "title", nullable = false, updatable = false)
    private String title;

    // Default constructor, needed for JPA
    private Asset() {
        super();
    }

    // Constructor for creating a new asset
    public Asset(final Integer id,
                 final String title,
                 final BigDecimal cost,
                 final Date purchaseDate,
                 final BigDecimal depreciationRate,
                 final String currency) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.depreciationRate = depreciationRate;
        this.currency = currency;
    }

    // Getter and setter methods for the properties

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public BigDecimal getDepreciationRate() {
        return depreciationRate;
    }

    public void setDepreciationRate(BigDecimal depreciationRate) {
        this.depreciationRate = depreciationRate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
