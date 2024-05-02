package com.example.A.Assets_Tracking_App.assetsTracking.core;
import org.springframework.data.domain.*;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import com.example.A.Assets_Tracking_App.assetsTracking.data.*;
import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import com.example.A.Assets_Tracking_App.assetsTracking.persistence.AssetTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

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
       // final var response = new GetAssetDetailsResponse();
        Pageable pageable = PageRequest.of(0 , 100);
        Page<Asset> assetsPage = assetTrackingRepository.findAll(pageable);
        List<GetAssetDetailsSummary> assetDetailsSummaries = new ArrayList<>();
        for (Asset asset : assetsPage.getContent()) {
            int assetId = asset.getId();
            Asset foundAsset = assetTrackingRepository.findById(String.valueOf(assetId))
                    .orElseThrow(() -> new RuntimeException("Asset with ID " + assetId + " not found"));

                BigDecimal currentDepreciatedValue = calculateDepreciatedValue(foundAsset);
                foundAsset.setDepreciatedValue(currentDepreciatedValue);
                assetTrackingRepository.save(foundAsset);

            assetDetailsSummaries.add(new GetAssetDetailsSummary(foundAsset.getTitle(), foundAsset.getPurchaseDate(), foundAsset.getCost(), foundAsset.getDepreciationRate(), foundAsset.getDepreciatedValue()));
            }
        final long totalCount = assetTrackingRepository.count();
        Page<GetAssetDetailsSummary> page = new PageImpl<>(assetDetailsSummaries , pageable , totalCount);

        return new GetAssetDetailsResponse(page);
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


    public GetAssetSummaryResponse GetAssetSummary(final GetAssetSummaryRequest request) {
        final var response = new GetAssetSummaryResponse();
        long currentAssetCount = assetTrackingRepository.count();
        long currentcost = assetTrackingRepository.costOfTotalAsset();
        long currentdepreciatedTotalValue = assetTrackingRepository.depreciatedCostOfTotalAsset();
        response.setCurrentAssetCount(currentAssetCount);
        response.setCurrentCost(currentcost);
        response.setCurrentDepreciatedCurrentValue(currentdepreciatedTotalValue);
        return response ;


    }
}
