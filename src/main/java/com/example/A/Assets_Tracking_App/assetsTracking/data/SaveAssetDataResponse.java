package com.example.A.Assets_Tracking_App.assetsTracking.data;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Response object containing summary data of saved asset records.
 */
public class SaveAssetDataResponse {
    // Collection to store asset records
    private Collection<SaveAssetDataSummary> assetRecord;

    /**
     * Adds an asset record to the collection.
     *
     * @param data The asset record to add.
     */
    public void addAssetRecord(final SaveAssetDataSummary data) {
        // Initialize the collection if null
        if (assetRecord == null) {
            assetRecord = new ArrayList<>();
        }
        // Add the asset record to the collection
        assetRecord.add(data);
    }

    /**
     * Retrieves a copy of the collection of asset records.
     *
     * @return A copy of the collection of asset records.
     */
    public Collection<SaveAssetDataSummary> getAssetRecord() {
        // Return a new ArrayList containing all elements of the assetRecord collection
        return new ArrayList<>(assetRecord);
    }
}
