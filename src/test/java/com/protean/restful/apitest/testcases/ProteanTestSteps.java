package com.protean.restful.apitest.testcases;


import org.junit.Assert;

import com.protean.restful.apitest.actions.ProjectConfig;
import com.protean.restful.apitest.baseAPI.RequestBuilder;
import com.protean.restful.apitest.baseAPI.RestResponse;
import com.protean.restful.apitest.restassuredFuntions.GenerateRequest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;


public class ProteanTestSteps{
	RestResponse res;

	@Given("^Add payload for \"([^\"]*)\" \"([^\"]*)\"$")
	public void add_payload_for(String scenario, String searchType) throws Throwable {
	    GenerateRequest.getRequest(scenario, searchType);
	}

	@When("^user calls SearchAPI with POST http request$")
	public void user_calls_SearchAPI_with_POST_http_request() throws Throwable {
		RequestBuilder request=new RequestBuilder("POST",ProjectConfig.getPropertyValue("RetailSearch"),GenerateRequest.headers,GenerateRequest.request);
		res=RestResponse.getRestResponse(request);
		Hooks.scenario.log(res.getResponse().asString());
	}

	@Then("^the API call is success with (\\d+)$")
	public void the_API_call_is_success_with(int statuscode) throws Throwable {
		if(res.getStatusCode()==statuscode) {
			Hooks.scenario.log(String.valueOf("Response Code is "+res.getStatusCode()));
		}else {
			Hooks.scenario.log("Response Code is not Validated Response Code is  "+res.getStatusCode());
			Assert.fail();
		}
	}

	@Then("^status in response body is \"([^\"]*)\"$")
	public void status_in_response_body_is(String status) throws Throwable {
		JsonPath json=new JsonPath(res.getResponse().asString());
		if(json.getString("message.ack.status").equalsIgnoreCase(status)) {
			Hooks.scenario.log("Status is matched "+status);
		}else {
			Hooks.scenario.log( "Status is not matched Expected Status is "+"'"+status+"'"+" But actual status is "+json.getString("message.ack.status"));
			Assert.fail();
		}
	}
}
