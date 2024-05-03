package com.example.A.Assets_Tracking_App.assetsTracking.persistence;

import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface AssetTrackingRepository extends JpaRepository<Asset , Integer> {

    Page<Asset> findAll(Pageable pageable);


    /*@Query(value = "SELECT SUM(cost) FROM Asset")
    long costOfTotalAsset();

     */
    @Query(value = "SELECT SUM(CASE WHEN currency = 'INR' THEN cost ELSE cost * :exchangeRate END) FROM Asset")
    long costOfTotalAsset(BigDecimal exchangeRate);

}
