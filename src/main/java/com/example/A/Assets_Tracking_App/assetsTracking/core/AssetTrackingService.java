package com.example.A.Assets_Tracking_App.assetsTracking.core;
import org.springframework.data.domain.*;

import java.math.RoundingMode;
import java.util.*;

import com.example.A.Assets_Tracking_App.assetsTracking.data.*;
import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import com.example.A.Assets_Tracking_App.assetsTracking.persistence.AssetTrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AssetTrackingService {

    @Autowired
    private AssetTrackingRepository assetTrackingRepository ;

     @Autowired
     private CurrencyConversionService currencyConversionService;




    public SaveAssetDataResponse saveAssetData(final SaveAssetDataRequest request) {
        Asset asset = new Asset();
        asset.setTitle(request.getTitle());
        asset.setCurrency(request.getCurrency());
        asset.setCost(request.getCost());

        asset.setPurchaseDate(request.getPurchaseDate());
        asset.setDepreciationRate(request.getDepreciationRate());
        asset = assetTrackingRepository.save(asset);

        final var response = new SaveAssetDataResponse();
        response.addAssetRecord(new SaveAssetDataSummary(asset.getTitle(),asset.getCost(),asset.getPurchaseDate(),asset.getDepreciationRate() , asset.getCurrency()));
        return response;
    }

    private BigDecimal convertCostInUSD(BigDecimal exchangeRate, BigDecimal cost) {
        BigDecimal costInUsd = cost.multiply(exchangeRate);
        return costInUsd;
    }

    public GetAssetDetailsResponse GetAssetDetails(GetAssetDetailsRequest request) {
       // final var response = new GetAssetDetailsResponse();
      //  Pageable pageable = PageRequest.of(0 , 100);
        Pageable pageable = PageRequest.of(0 , 100, Sort.by(Sort.Direction.DESC, "purchaseDate"));
        Page<Asset> assetsPage = assetTrackingRepository.findAll(pageable);
        List<GetAssetDetailsSummary> assetDetailsSummaries = new ArrayList<>();
        for (Asset asset : assetsPage.getContent()) {
            int assetId = asset.getId();
            Asset foundAsset = assetTrackingRepository.findById(assetId)
                    .orElseThrow(() -> new RuntimeException("Asset with ID " + assetId + " not found"));

                BigDecimal currentDepreciatedValue = calculateDepreciatedValue(foundAsset);
                assetTrackingRepository.save(foundAsset);

            assetDetailsSummaries.add(new GetAssetDetailsSummary(foundAsset.getTitle(), foundAsset.getPurchaseDate(), foundAsset.getCost(), foundAsset.getDepreciationRate(),currentDepreciatedValue ));
            }
        final long totalCount = assetTrackingRepository.count();
        Page<GetAssetDetailsSummary> page = new PageImpl<>(assetDetailsSummaries , pageable , totalCount);

        return new GetAssetDetailsResponse(page);
    }


    private BigDecimal calculateDepreciatedValue(final Asset asset) {
        BigDecimal cost  = asset.getCost();
        BigDecimal depreciatedRate = asset.getDepreciationRate().divide(BigDecimal.valueOf(100),2,RoundingMode.HALF_UP);
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
        currentDepreciatedValue = currentDepreciatedValue.setScale(3, RoundingMode.HALF_UP);
        System.out.println(currentDepreciatedValue);
        return currentDepreciatedValue;
    }


    public GetAssetSummaryResponse GetAssetSummary(final GetAssetSummaryRequest request) {
        final var response = new GetAssetSummaryResponse();
        Map<String, BigDecimal> rates =  currencyConversionService.fetchExchangeRatesFromApi();
        BigDecimal exchangeRate = currencyConversionService.getExcahngeRate("INR", rates);
        long currentAssetCount = assetTrackingRepository.count();
        long currentcost = assetTrackingRepository.costOfTotalAsset(exchangeRate);
        BigDecimal totalDepreciatedValue = BigDecimal.ZERO;
       // Asset asset = new Asset();
        List<Asset> assets = assetTrackingRepository.findAll();
        for(Asset asset : assets)
        {
            BigDecimal currentDepreciatedValue = calculateDepreciatedValue(asset);
            totalDepreciatedValue = totalDepreciatedValue.add(currentDepreciatedValue);
        }

        response.setCurrentAssetCount(currentAssetCount);
        response.setCurrentCost(currentcost);
        response.setCurrentDepreciatedCurrentValue(totalDepreciatedValue);
        return response ;


    }
}
