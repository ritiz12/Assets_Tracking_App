package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.util.ArrayList;
import java.util.Collection;

public class SaveAssetDataResponse {
    private Collection<SaveAssetDataSummary> assetRecord ;

    public void addAssetRecord(final SaveAssetDataSummary data)
    {
        if(assetRecord == null)
        {
            assetRecord = new ArrayList<>();
        }
        assetRecord.add(data);
    }

    public Collection<SaveAssetDataSummary> getAssetRecord() {
        return assetRecord;
    }
}
