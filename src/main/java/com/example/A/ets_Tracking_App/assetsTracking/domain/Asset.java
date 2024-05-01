package com.example.A.ets_Tracking_App.assetsTracking.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.aspectj.bridge.IMessage;

import javax.crypto.Mac;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "asset")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , nullable = false)
    private Integer id ;



    @NotNull(message = "Please Enter the Title , it can not be null")
    @NotBlank(message = "Title field can not be Blank , please Enter valid value")
    @Column(name = "title" , nullable = false , updatable = false)
    private String title ;


    @NotNull(message = "Please Enter the Cost , it can not be null")

    @Column(name = "cost" , nullable = false , updatable = false)
    private BigDecimal cost ;


    @Column(name = "purchaseDate" , nullable = false , updatable = false)
    private Date purchaseDate;


    @NotNull(message = "Please Enter the Depreciation Rate , it can not be null")
    @Column(name = "depreciationRate" , nullable = false , updatable = false)
    private BigDecimal depreciationRate ;

    @Column(name = "depreciatedValue" , nullable = true , updatable = true)
    private BigDecimal depreciatedValue ;

    public Asset()
    {
        super();
    }

    public Asset(Integer id, String title, BigDecimal cost, Date purchaseDate, BigDecimal depreciationRate , BigDecimal depreciatedValue) {
        this.id = id;
        this.title = title;
        this.cost = cost;
        this.purchaseDate = purchaseDate;
        this.depreciationRate = depreciationRate;
        this.depreciatedValue = depreciatedValue ;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public BigDecimal getDepreciatedValue() {
        return depreciatedValue;
    }

    public void setDepreciatedValue(BigDecimal depreciatedValue) {
        this.depreciatedValue = depreciatedValue;
    }
}
