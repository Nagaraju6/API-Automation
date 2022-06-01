package com.enhops.restful.apitest.baseAPI;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import static io.restassured.RestAssured.given;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RestResponse {

  Response response;
  Headers headers;
  int statusCode;
  long responseTime;
  private static RequestBuilder requestBuilder;
  private static RequestSpecification requestSpec;

  public static RestResponse getRestResponse(RequestBuilder RequestBuilder) {
    requestBuilder = RequestBuilder;
    requestSpec = buildDefaultRequestSpec();
    return getRestAssuredResponse();
  }

  public static RestResponse getRestResponse(RequestBuilder RequestBuilder, RequestSpecification requestSpecification) {
    requestBuilder = RequestBuilder;
    requestSpec = requestSpecification;
    return getRestAssuredResponse();
  }

  private static RequestSpecification buildDefaultRequestSpec() {
    RequestSpecBuilder builder = new RequestSpecBuilder();
    Headers requestHeaders = requestBuilder.getHeaders();

    if(requestHeaders !=null) {
      for (Header header : requestHeaders) {
        builder.addHeader(header.getName(), header.getValue());
      }
    }

    if(requestBuilder.getAccept()!=null) {
      builder.addHeader("accept", requestBuilder.getAccept());
    }

    if(requestBuilder.getContentType()!=null) {
      builder.addHeader("Content-Type", requestBuilder.getContentType());
    }

    if(requestBuilder.getCorrelation_id()!=null) {
      builder.addHeader("X-Correlation-Id", requestBuilder.getCorrelation_id());
    }

    if(requestBuilder.getBasicAuth()!=null) {
      builder.addHeader("Authorization", requestBuilder.getBasicAuth());
    }

    if(requestBuilder.getCookie() != null) {
      builder.addHeader("Cookie", requestBuilder.getCookie());
    }

    if(!requestBuilder.getUrl().contains("https://")) {
      builder.setPort(80);
    }

    else {
      builder.setPort(443);
    }

    return builder.build();
  }

  private static RestResponse getRestAssuredResponse() {
    RestResponse RestResponse = new RestResponse();
    Response restResponseObject = null;
    List<String> updateMethods = Arrays.asList( "post", "delete", "put", "patch") ;

    if(updateMethods.contains(requestBuilder.getMethod().toLowerCase())) {
      restResponseObject = performUpdateRequest();
    }
    else if(requestBuilder.getMethod().equalsIgnoreCase("get")) {
      restResponseObject =
          given()
              .spec(requestSpec)
              .when()
              .get(requestBuilder.getUrl())
              .then()
              .extract()
              .response();
    }else if(requestBuilder.getMethod().equalsIgnoreCase("post")) {
    	restResponseObject =
    	          given()
    	              .spec(requestSpec)
    	              .when()
    	              .post(requestBuilder.getUrl())
    	              .then()
    	              .extract()
    	              .response();
    }

    RestResponse.response = restResponseObject;
    RestResponse.headers = restResponseObject.headers();
    RestResponse.statusCode = restResponseObject.getStatusCode();
    RestResponse.responseTime = restResponseObject.getTime();

    return RestResponse;
  }

  private static Response performUpdateRequest(){
    Response restResponseObject;
      if (requestBuilder.getBody() != null) {
        restResponseObject =
            given()
                .spec(requestSpec)
                .body(requestBuilder.getBody())
                .when()
                .request(requestBuilder.getMethod(), requestBuilder.getUrl())
                .then()
                .extract()
                .response();
      } else {
        restResponseObject =
            given()
                .spec(requestSpec)
                .when()
                .request(requestBuilder.getMethod(), requestBuilder.getUrl())
                .then()
                .extract()
                .response();
      }

    return restResponseObject;
  }

  public Response getResponse() {    return response;  }

  public Headers getHeaders() {
    return headers;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public long getResponseTime() {
    return responseTime;
  }
  
  @SuppressWarnings("unchecked")
public static Map<String, Object> toMap(String object) throws JsonMappingException, JsonProcessingException {
	  Map<String, Object> mapping = new ObjectMapper().readValue(object, HashMap.class);
	return mapping;
  }
  
  public HashMap<String, String> jsonToMap(JSONObject json) throws JSONException {
	    Map<String, Object> retMap = new HashMap<String, Object>();
	    HashMap<String,String>responseMap=new HashMap<>();
	    
	    if(json != JSONObject.NULL) {
	        retMap = toMap(json);
	    }
	    List<String> responseList = new ArrayList<String>(Arrays.asList(retMap.get("context").toString().split(",")));
	    for(int i=0;i<responseList.size()-1;i++) {
	    	String []values=responseList.get(i).split("=");
	    	if(values[0].contains("{")) {
	    		values[0]=values[0].replace("{", "");
	    	}
	    	responseMap.put(values[0], values[1]);
	    }
		return responseMap;
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);
	        
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }
	        
	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
  
}
