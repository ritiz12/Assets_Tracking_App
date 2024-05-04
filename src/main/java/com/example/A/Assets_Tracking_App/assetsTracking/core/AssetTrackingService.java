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
    private AssetTrackingRepository assetTrackingRepository;

    @Autowired
    private CurrencyConversionService currencyConversionService;

    /**
     * Saves asset data.
     *
     * @param request The request containing asset data to be saved.
     * @return The response containing summary of the saved asset data.
     * @throws IllegalArgumentException If any required field is missing in the request.
     */

    public SaveAssetDataResponse saveAssetData(final SaveAssetDataRequest request) {

        if (request.getTitle() == null || request.getCurrency() == null || request.getCost() == null || request.getPurchaseDate() == null || request.getDepreciationRate() == null) {
            throw new IllegalArgumentException("Please Enter Complete Details");
        }
        Asset asset = new Asset(
                null,
                request.getTitle(),
                request.getCost(),
                request.getPurchaseDate(),
                request.getDepreciationRate(),
                request.getCurrency()
        );

        asset = assetTrackingRepository.save(asset);

        final var response = new SaveAssetDataResponse();
        response.addAssetRecord(new SaveAssetDataSummary(asset.getTitle()
                , asset.getCost()
                , asset.getPurchaseDate()
                , asset.getDepreciationRate()
                , asset.getCurrency()));
        return response;
    }

    /**
     * Retrieves asset details.
     *
     * @param request The request containing parameters for asset details retrieval.
     * @return The response containing asset details.
     * @throws IllegalStateException If no previous data is found.
     */

    public GetAssetDetailsResponse GetAssetDetails(final GetAssetDetailsRequest request) {

        Pageable pageable = PageRequest.of(0, 100, Sort.by(Sort.Direction.DESC, "purchaseDate"));

        Page<Asset> assetsPage = assetTrackingRepository.findAll(pageable);

        if(assetsPage.isEmpty())
        {
            throw new  IllegalStateException("No Previous Data is Found");
        }

        List<GetAssetDetailsSummary> assetDetailsSummaries = new ArrayList<>();

        for (Asset asset : assetsPage.getContent()) {

            BigDecimal currentDepreciatedValue = calculateDepreciatedValue(asset);

            assetDetailsSummaries.add(new GetAssetDetailsSummary(
                    asset.getTitle(),
                    asset.getPurchaseDate(),
                    asset.getCost(),
                    asset.getDepreciationRate(),
                    currentDepreciatedValue , asset.getCurrency()));
        }


        final long totalCount = assetTrackingRepository.count();
        Page<GetAssetDetailsSummary> page = new PageImpl<>(assetDetailsSummaries, pageable, totalCount);
        return new GetAssetDetailsResponse(page);
    }

    /**
     * Retrieves asset summary.
     *
     * @param request The request containing parameters for asset summary retrieval.
     * @return The response containing asset summary.
     * @throws IllegalStateException If no previous data is found.
     */

    public GetAssetSummaryResponse GetAssetSummary(final GetAssetSummaryRequest request) {
        final var response = new GetAssetSummaryResponse();
        Map<String, BigDecimal> rates = currencyConversionService.fetchExchangeRatesFromApi();
        BigDecimal exchangeRate = currencyConversionService.getExcahngeRate("INR", rates);
        long currentAssetCount = assetTrackingRepository.count();
        long currentcost = assetTrackingRepository.costOfTotalAsset(exchangeRate);
        BigDecimal totalDepreciatedValue = BigDecimal.ZERO;
        List<Asset> assets = assetTrackingRepository.findAll();
        if(assets.isEmpty())
        {
            throw new IllegalStateException("No Previous Data is Found");
        }
        for (Asset asset : assets) {
            BigDecimal currentDepreciatedValue = calculateDepreciatedValue(asset);
            totalDepreciatedValue = totalDepreciatedValue.add(currentDepreciatedValue);
        }

        response.setCurrentAssetCount(currentAssetCount);
        response.setCurrentCost(currentcost);
        response.setCurrentDepreciatedCurrentValue(totalDepreciatedValue);
        return response;


    }
    /**
     * Calculates depreciated value for the asset.
     *
     * @param asset The asset for which depreciated value needs to be calculated.
     * @return The depreciated value of the asset.
     */

    private BigDecimal calculateDepreciatedValue(final Asset asset) {
        BigDecimal cost = asset.getCost();
        BigDecimal depreciatedRate = asset.getDepreciationRate().divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
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

        BigDecimal currentDepreciatedValue = cost.multiply((BigDecimal.ONE.subtract(depreciatedRate)).pow((formattedCurrenteDate - formattedPurchaseDate) / 10000));
        currentDepreciatedValue = currentDepreciatedValue.setScale(3, RoundingMode.HALF_UP);
        return currentDepreciatedValue;
    }
}
