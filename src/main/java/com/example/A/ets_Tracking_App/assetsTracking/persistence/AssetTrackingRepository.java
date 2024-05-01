package com.example.A.ets_Tracking_App.assetsTracking.persistence;

import com.example.A.ets_Tracking_App.assetsTracking.domain.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetTrackingRepository extends JpaRepository<Asset , String> {
}
