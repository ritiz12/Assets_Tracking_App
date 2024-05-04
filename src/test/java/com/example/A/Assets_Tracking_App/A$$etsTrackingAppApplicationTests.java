package com.example.A.Assets_Tracking_App;

import static org.junit.jupiter.api.Assertions.*;
import com.example.A.Assets_Tracking_App.assetsTracking.core.AssetTrackingService;
import com.example.A.Assets_Tracking_App.assetsTracking.data.*;
import com.example.A.Assets_Tracking_App.assetsTracking.domain.Asset;
import com.example.A.Assets_Tracking_App.assetsTracking.persistence.AssetTrackingRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertEquals;
//@SpringBootTest(classes = A$$etsTrackingAppApplication.class)
class A$$etsTrackingAppApplicationTests {

//	@Autowired
	private AssetTrackingService assetTrackingService ;

//	@Autowired
	private AssetTrackingRepository assetTrackingRepository;
	@Before("")
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testAddAndGetAssetRecord() {
		String title = "Laptop";
		BigDecimal cost = BigDecimal.valueOf(100000);
		Date purchaseDate = new Date();
		BigDecimal depreciationRate = BigDecimal.valueOf(0.1);
		String currency = "USD";
		SaveAssetDataRequest request = new SaveAssetDataRequest(title, cost, purchaseDate, depreciationRate, currency);
		SaveAssetDataResponse response = assetTrackingService.saveAssetData(request);
		assertNotNull(response);
		Collection<SaveAssetDataSummary> assetRecords = response.getAssetRecord();
		SaveAssetDataSummary retrievedSummary = assetRecords.iterator().next();
		assertEquals(title, retrievedSummary.getTitle());
		assertEquals(cost, retrievedSummary.getCost());
		assertEquals(purchaseDate, retrievedSummary.getPurchaseDate());
		assertEquals(depreciationRate, retrievedSummary.getDepreciationRate());
		assertEquals(currency, retrievedSummary.getCurrency());
	}




}
