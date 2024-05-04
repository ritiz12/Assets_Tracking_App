package com.example.A.Assets_Tracking_App.assetsTracking.persistence;

import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Repository interface for managing assets in the database.
 */
@Repository
public interface AssetTrackingRepository extends JpaRepository<Asset , Integer> {

    /**
     * Retrieves all assets with pagination.
     *
     * @param pageable Pagination information.
     * @return A page of assets.
     */
    Page<Asset> findAll(Pageable pageable);

    /**
     * Calculates the total cost of all assets in a specific currency.
     * If the currency is not the base currency, it applies the exchange rate.
     *
     * @param exchangeRate The exchange rate to apply if the currency is not the base currency.
     * @return The total cost of all assets.
     */
    @Query(value = "SELECT SUM(CASE WHEN " +
            "currency = 'INR' THEN cost " +
            "ELSE cost * :exchangeRate END) " +
            "FROM Asset")
    long costOfTotalAsset(BigDecimal exchangeRate);
}
