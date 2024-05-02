package com.example.A.Assets_Tracking_App.assetsTracking.persistence;

import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetTrackingRepository extends JpaRepository<Asset , String> {

    Page<Asset> findAll(Pageable pageable);


    @Query(value = "SELECT SUM(cost) FROM Asset")
    long costOfTotalAsset();



    @Query(value = "SELECT SUM(depreciatedValue) FROM Asset")
    long depreciatedCostOfTotalAsset();
}
