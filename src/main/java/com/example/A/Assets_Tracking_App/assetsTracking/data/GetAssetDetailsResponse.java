package com.example.A.Assets_Tracking_App.assetsTracking.data;

import com.example.A.Assets_Tracking_App.assetsTracking.common.PaginatedResponse;
import org.springframework.data.domain.Page;

/**
 * Response object containing details of assets.
 * Extends PaginatedResponse to support pagination.
 */
public class GetAssetDetailsResponse extends PaginatedResponse {

    // Page containing asset details summaries
    private final Page<GetAssetDetailsSummary> page;

    /**
     * Constructs a new GetAssetDetailsResponse object.
     *
     * @param page The page containing asset details summaries.
     */
    public GetAssetDetailsResponse(Page<GetAssetDetailsSummary> page) {
        this.page = page;
    }

    /**
     * Retrieves the page containing asset details summaries.
     *
     * @return The page containing asset details summaries.
     */
    public Page<GetAssetDetailsSummary> getPage() {
        return page;
    }
}
