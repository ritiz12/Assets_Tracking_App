package com.example.A.Assets_Tracking_App.assetsTracking.data;

import com.example.A.Assets_Tracking_App.assetsTracking.common.PaginatedResponse;
import com.example.A.Assets_Tracking_App.assetsTracking.common.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.Collection;

public class GetAssetDetailsResponse  extends PaginatedResponse {
 private  final Page<GetAssetDetailsSummary> page;

 public  GetAssetDetailsResponse(Page<GetAssetDetailsSummary> page)
 {
     this.page = page ;
 }

    @JsonProperty
    public Page<GetAssetDetailsSummary> getPage()
    {
        return page ;
    }
}
