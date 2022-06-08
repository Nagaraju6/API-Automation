/*
 * package com.protean.restful.apitest.testcases;
 * 
 * import java.util.Map;
 * 
 * import org.json.JSONException; import org.json.JSONObject; import
 * org.testng.Assert; import org.testng.annotations.Test;
 * 
 * import com.protean.restful.apitest.actions.ProjectConfig; import
 * com.protean.restful.apitest.baseAPI.RequestBuilder; import
 * com.protean.restful.apitest.baseAPI.RestResponse; import
 * com.protean.restful.apitest.listeners.ExtentTestManager; import
 * com.relevantcodes.extentreports.LogStatus;
 * 
 * public class ProteanTestCases {
 * 
 * 
 * public void RetailsSearchResponse() throws JSONException { RequestBuilder
 * request=new
 * RequestBuilder("POST",ProjectConfig.getPropertyValue("RetailSearch"),
 * GenerateRequest.headers,GenerateRequest.request); RestResponse
 * res=RestResponse.getRestResponse(request);
 * ExtentTestManager.log(LogStatus.INFO,
 * "Response is "+res.getResponse().asString()); if(res.getStatusCode()==200) {
 * ExtentTestManager.log(LogStatus.
 * INFO,"--------------------------Response------------------------  "+res.
 * getStatusCode());
 * ExtentTestManager.log(LogStatus.INFO,""+res.getStatusCode()); }else {
 * ExtentTestManager.log(LogStatus.
 * FAIL,"Response Code is not Validated Response Code is  "+res.getStatusCode())
 * ; } JSONObject json=new JSONObject(res.getResponse().asString()); Map<String,
 * String> retMap=res.jsonToMap(json);
 * if(!retMap.get("transaction_id").isEmpty()) {
 * ExtentTestManager.log(LogStatus.PASS,
 * "Transaction ID is "+retMap.get("transaction_id")); }else {
 * ExtentTestManager.log(LogStatus.PASS,
 * "Transaction ID is empty "+retMap.get("transaction_id")); Assert.fail(); } }
 * 
 * @Test(groups= {"smoke","regression"}) public void
 * RetailsSearchResponseWithoutHeader() throws JSONException { RequestBuilder
 * request=new
 * RequestBuilder("POST",ProjectConfig.getPropertyValue("RetailSearch"),
 * GenerateRequest.request); RestResponse
 * res=RestResponse.getRestResponse(request);
 * ExtentTestManager.log(LogStatus.INFO,
 * "Response is "+res.getResponse().asString()); if(res.getStatusCode()==401) {
 * ExtentTestManager.log(LogStatus.
 * INFO,"--------------------------Response Code------------------------  ");
 * ExtentTestManager.log(LogStatus.INFO,""+res.getStatusCode()); }else {
 * ExtentTestManager.log(LogStatus.
 * FAIL,"Response Code is not Validated Response Code is  "+res.getStatusCode())
 * ; } JSONObject json=new JSONObject(res.getResponse().asString()); Map<String,
 * String> retMap=res.jsonToMap(json);
 * if(!retMap.get("transaction_id").isEmpty()) {
 * ExtentTestManager.log(LogStatus.PASS,
 * "Transaction ID is "+retMap.get("transaction_id")); }else {
 * ExtentTestManager.log(LogStatus.PASS,
 * "Transaction ID is empty "+retMap.get("transaction_id")); Assert.fail(); } }
 * }
 */