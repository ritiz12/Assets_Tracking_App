package com.example.A.ets_Tracking_App.assetsTracking.core;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;
import com.example.A.ets_Tracking_App.assetsTracking.data.*;
import com.example.A.ets_Tracking_App.assetsTracking.domain.Asset;
import com.example.A.ets_Tracking_App.assetsTracking.persistence.AssetTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class AssetTrackingService {

    @Autowired
    private AssetTrackingRepository assetTrackingRepository ;


    public SaveAssetDataResponse saveAssetData(final SaveAssetDataRequest request) {
        Asset asset = new Asset();
        asset.setTitle(request.getTitle());
        asset.setCost(request.getCost());
        asset.setPurchaseDate(request.getPurchaseDate());
        asset.setDepreciationRate(request.getDepreciationRate());
        asset = assetTrackingRepository.save(asset);

        final var response = new SaveAssetDataResponse();
        response.addAssetRecord(new SaveAssetDataSummary(asset.getTitle(),asset.getCost(),asset.getPurchaseDate(),asset.getDepreciationRate()));
        return response;
    }

    public GetAssetDetailsResponse GetAssetDetails(GetAssetDetailsRequest request) {
        final var response = new GetAssetDetailsResponse();
        for (Asset asset : assetTrackingRepository.findAll()) {
            int assetId = asset.getId();
            Asset foundAsset = assetTrackingRepository.findById(String.valueOf(assetId))
                    .orElseThrow(() -> new RuntimeException("Asset with ID " + assetId + " not found"));
            if (foundAsset != null) {
                BigDecimal currentDepreciatedValue = calculateDepreciatedValue(foundAsset);
                foundAsset.setDepreciatedValue(currentDepreciatedValue);

                response.addAssetDetailsRecod(new GetAssetDetailsSummary(foundAsset.getTitle(), foundAsset.getPurchaseDate(), foundAsset.getCost(), foundAsset.getDepreciationRate(), foundAsset.getDepreciatedValue()));
            } else {

                System.out.println("Asset with ID " + assetId + " not found.");
            }
        }
        return response;
    }


    private BigDecimal calculateDepreciatedValue(Asset asset) {
        BigDecimal cost  = asset.getCost();
        BigDecimal depreciatedRate = asset.getDepreciationRate().divide(BigDecimal.valueOf(100),2,RoundingMode.CEILING);
        Date purchaseDate = asset.getPurchaseDate();
        Calendar calendar1 = Calendar.getInstance();
        int formattedCurrenteDate = calendar1.get(Calendar.YEAR) * 10000 +
                (calendar1.get(Calendar.MONTH) + 1) * 100 +
                calendar1.get(Calendar.DAY_OF_MONTH);


        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(purchaseDate);

        int formattedPurchaseDate = calendar2.get(Calendar.YEAR) * 10000 +
                (calendar2.get(Calendar.MONTH) + 1) * 100 +
                calendar2.get(Calendar.DAY_OF_MONTH);

        BigDecimal currentDepreciatedValue = cost.multiply((BigDecimal.ONE.subtract(depreciatedRate)).pow((formattedCurrenteDate - formattedPurchaseDate)/10000));

        System.out.println(currentDepreciatedValue);
        return currentDepreciatedValue;
    }


}
