package com.example.A.ets_Tracking_App.assetsTracking.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
