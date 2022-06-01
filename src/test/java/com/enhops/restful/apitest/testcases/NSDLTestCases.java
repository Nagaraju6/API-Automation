package com.enhops.restful.apitest.testcases;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.enhops.restful.apitest.actions.ProjectConfig;
import com.enhops.restful.apitest.baseAPI.RequestBuilder;
import com.enhops.restful.apitest.baseAPI.RestResponse;
import com.enhops.restful.apitest.listeners.ExtentTestManager;
import com.enhops.restful.apitest.listeners.TestNGMethods;
import com.enhops.restful.apitest.restassuredFuntions.GenerateRequest;
import com.relevantcodes.extentreports.LogStatus;

public class NSDLTestCases extends TestNGMethods{
	
	RequestBuilder response = new RequestBuilder();
	
	@Test(groups= {"smoke","regression"})
	public void RetailsSearchResponse() throws JSONException {
		RequestBuilder request=new RequestBuilder("POST",ProjectConfig.getPropertyValue("RetailSearch"),GenerateRequest.headers,GenerateRequest.request);
		RestResponse res=RestResponse.getRestResponse(request);
		ExtentTestManager.log(LogStatus.INFO, "Response is "+res.getResponse().asString());
		if(res.getStatusCode()==200) {
			ExtentTestManager.log(LogStatus.INFO,"--------------------------Response------------------------  "+res.getStatusCode());
			ExtentTestManager.log(LogStatus.INFO,""+res.getStatusCode());
		}else {
			ExtentTestManager.log(LogStatus.FAIL,"Response Code is not Validated Response Code is  "+res.getStatusCode());
		}
		JSONObject json=new JSONObject(res.getResponse().asString());
		Map<String, String> retMap=res.jsonToMap(json);
		if(!retMap.get("transaction_id").isEmpty()) {
			ExtentTestManager.log(LogStatus.PASS, "Transaction ID is "+retMap.get("transaction_id"));
		}else {
			ExtentTestManager.log(LogStatus.PASS, "Transaction ID is empty "+retMap.get("transaction_id"));
			Assert.fail();
		}
	}
}
