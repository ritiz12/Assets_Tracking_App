package com.example.A.Assets_Tracking_App.assetsTracking.api;


import com.example.A.Assets_Tracking_App.assetsTracking.core.AssetTrackingService;
import com.example.A.Assets_Tracking_App.assetsTracking.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/asset")
@RestController
public class AssetTrackingAPI {

    @Autowired
    AssetTrackingService assetTrackingService ;

    @PostMapping("/saveAsset")
    public ResponseEntity<SaveAssetDataResponse> saveAssetData(@RequestBody SaveAssetDataRequest request)
    {
        final var response = assetTrackingService.saveAssetData(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/assetDetails")
    public ResponseEntity<GetAssetDetailsResponse> GetAssetDetails(GetAssetDetailsRequest request) {
        final var response = assetTrackingService.GetAssetDetails(request);
        return  ResponseEntity.ok(response);

    }

    @GetMapping("/summary")
    public  ResponseEntity<GetAssetSummaryResponse> GetAssetSummary(GetAssetSummaryRequest request)
    {
        final var reponse = assetTrackingService.GetAssetSummary(request);
        return ResponseEntity.ok(reponse);
    }







}
