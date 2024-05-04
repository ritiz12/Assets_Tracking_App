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
    AssetTrackingService assetTrackingService;

    /**
     * Endpoint to save asset data.
     *
     * @param request The request containing asset data to be saved.
     * @return ResponseEntity with the response containing summary of the saved asset data.
     */

    @CrossOrigin
    @PostMapping("/save")
    public ResponseEntity<SaveAssetDataResponse> saveAssetData(@RequestBody SaveAssetDataRequest request) {
        final var response = assetTrackingService.saveAssetData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint to retrieve all asset details.
     *
     * @param request The request containing parameters for asset details retrieval.
     * @return ResponseEntity with the response containing page of asset details summaries.
     */

    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Page<GetAssetDetailsSummary>> GetAssetDetails(GetAssetDetailsRequest request) {
        GetAssetDetailsResponse response = assetTrackingService.GetAssetDetails(request);

        return ResponseEntity.ok(response.getPage());
    }

    /**
     * Endpoint to retrieve asset summary.
     *
     * @param request The request containing parameters for asset summary retrieval.
     * @return ResponseEntity with the response containing asset summary.
     */

    @CrossOrigin
    @GetMapping("/summary")
    public ResponseEntity<GetAssetSummaryResponse> GetAssetSummary(GetAssetSummaryRequest request) {
        final var reponse = assetTrackingService.GetAssetSummary(request);
        return ResponseEntity.ok(reponse);
    }


}
