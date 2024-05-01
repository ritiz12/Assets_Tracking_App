package com.example.A.ets_Tracking_App.assetsTracking.data;

import java.util.ArrayList;
import java.util.Collection;

public class GetAssetDetailsResponse {
 private  Collection<GetAssetDetailsSummary> assetDetailsRecord ;

 public void addAssetDetailsRecod(final GetAssetDetailsSummary data)
 {
     if(assetDetailsRecord == null)
     {
         assetDetailsRecord = new ArrayList<>();
     }
     assetDetailsRecord.add(data);

 }

    public Collection<GetAssetDetailsSummary> getAssetDetailsRecord() {
        return assetDetailsRecord;
    }
}
