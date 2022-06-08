package com.protean.restful.apitest.listeners;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.path.json.JsonPath;

public class Practice {

	public static void main(String[] args) throws JSONException {
		String s="{\r\n"
				+ "  \"context\": {\r\n"
				+ "    \"domain\": \"nic2004:52110\",\r\n"
				+ "    \"country\": \"IND\",\r\n"
				+ "    \"city\": \"std:080\",\r\n"
				+ "    \"action\": \"search\",\r\n"
				+ "    \"core_version\": \"0.9.1\",\r\n"
				+ "    \"bap_id\": \"mock_bap1\",\r\n"
				+ "    \"bap_uri\": \"http://localhost:8089/\",\r\n"
				+ "    \"transaction_id\": \"5b7f471c-4388-4162-af2f-545ae4721c53\",\r\n"
				+ "    \"message_id\": \"153922db-69f7-4418-b7ce-1c6f28f9d9c4\",\r\n"
				+ "    \"timestamp\": \"2022-03-07T07:05:37.976711Z\"\r\n"
				+ "  },\r\n"
				+ "  \"message\": {\r\n"
				+ "    \"ack\": {\r\n"
				+ "      \"status\": \"ACK\"\r\n"
				+ "    }\r\n"
				+ "  }\r\n"
				+ "}";
		//obj.getString("status");
		JsonPath obj=new JsonPath(s);
		System.out.println(obj.getString("message.ack.status"));
	}

}
