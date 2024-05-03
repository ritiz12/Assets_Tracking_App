package com.example.A.Assets_Tracking_App.assetsTracking.api;


import com.example.A.Assets_Tracking_App.assetsTracking.core.AssetTrackingService;
import com.example.A.Assets_Tracking_App.assetsTracking.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/asset")
@RestController
public class AssetTrackingAPI {

    @Autowired
    AssetTrackingService assetTrackingService ;

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<SaveAssetDataResponse> saveAssetData(@RequestBody SaveAssetDataRequest request)
    {
        final var response = assetTrackingService.saveAssetData(request);
        return ResponseEntity.ok(response);
    }

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Page<GetAssetDetailsSummary>> GetAssetDetails(GetAssetDetailsRequest request) {
        GetAssetDetailsResponse response = assetTrackingService.GetAssetDetails(request);

        return  ResponseEntity.ok(response.getPage());
    }

    @CrossOrigin
    @GetMapping("/summary")
    public  ResponseEntity<GetAssetSummaryResponse> GetAssetSummary(GetAssetSummaryRequest request)
    {
        final var reponse = assetTrackingService.GetAssetSummary(request);
        return ResponseEntity.ok(reponse);
    }







}
