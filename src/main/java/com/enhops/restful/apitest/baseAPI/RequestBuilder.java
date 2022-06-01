package com.enhops.restful.apitest.baseAPI;

import com.enhops.restful.apitest.utilities.Helper;

import io.restassured.http.Headers;

public class RequestBuilder {

	public RequestBuilder() {
	}

	String method;
	String url;
	String body;
	String correlation_id;
	String basicAuth;
	String cookie;
	Headers headers;
	String accept;
	String contentType;
	String multiPartfile;
	Helper getHelp;

	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body
	public RequestBuilder(String Method, String corrID, String URL, String BasicAuth, String body) {
		this.setBasicAuth(BasicAuth);
		if (!body.equals("")) {
			this.setBody(body);
		}
		this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);
		this.setAccept("application/json");
		this.setContentType("application/json");
	}

	// Constructor with (Method, URL, Basic Auth, Request Body
	public RequestBuilder(String Method, String URL, String BasicAuth, String body) {
		this.setBasicAuth(BasicAuth);
		if (!body.equals("")) {
			this.setBody(body);
		}
		this.setMethod(Method);
		this.setUrl(URL);
		this.setAccept("application/json");
		this.setContentType("application/json");
	}
	
	public RequestBuilder(String Method, String URL,String body) {
		if (!body.equals("")) {
			this.setBody(body);
		}
		this.setMethod(Method);
		this.setUrl(URL);
		this.setAccept("application/json");
		this.setContentType("application/json");
	}
	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body,
	// additional headers
	public RequestBuilder(String Method, String corrID, String URL, String BasicAuth, String body, Headers header) {
		this.setBasicAuth(BasicAuth);
		if (!body.equals("")) {
			this.setBody(body);
		}
		//this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);
		this.setHeaders(header);
		this.setAccept("application/json");
		this.setContentType("application/json");
	}

	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body,
	// additional headers
	public RequestBuilder(String Method ,String URL, String body, Headers header) {
		if (!body.equals("")) {
			this.setBody(body);
		}
		//this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);
		this.setHeaders(header);
		this.setAccept("application/json");
		this.setContentType("application/json");
	}
	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body,
	// Accept, Content Type
	public RequestBuilder(String Method, String corrID, String URL, String BasicAuth, String body, String accept,
			String contentType) {
		this.setBasicAuth(BasicAuth);
		if (!body.equals("")) {
			this.setBody(body);
		}
		this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);
		if (!accept.equalsIgnoreCase("")) {
			this.setAccept(accept);
		}

		if (!contentType.equalsIgnoreCase("")) {
			this.setContentType(contentType);
		}
	}

	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body,
	// additional headers, Accept, Content Type
	public RequestBuilder(String Method, String corrID, String URL, String BasicAuth, String body, Headers header,
			String accept, String contentType) {
		this.setBasicAuth(BasicAuth);
		if (!body.equals("")) {
			this.setBody(body);
		}
		this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);
		this.setHeaders(header);
		if (!accept.equalsIgnoreCase("")) {
			this.setAccept(accept);
		}

		if (!contentType.equalsIgnoreCase("")) {
			this.setContentType(contentType);
		}
	}

	// Constructor with (Method, Correlation ID, URL, Basic Auth, Request Body,
	// additional headers, Accept, Content Type
	public RequestBuilder(String Method, String corrID, String URL, String BasicAuth, String MultiPartFile,
			String contentType) {
		this.setBasicAuth(BasicAuth);
		if (!MultiPartFile.equals("")) {
			this.setmultiPartfile(MultiPartFile);
		}
		this.setCorrelation_id(corrID);
		this.setMethod(Method);
		this.setUrl(URL);

		if (!contentType.equalsIgnoreCase("")) {
			this.setContentType(contentType);
		}
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		this.accept = accept;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Headers getHeaders() {
		return headers;
	}

	public void setHeaders(Headers headers) {
		this.headers = headers;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getmultiPartfile() {
		return multiPartfile;
	}

	public void setmultiPartfile(String multiPartfile) {
		this.multiPartfile = multiPartfile;
	}

	public String getCorrelation_id() {
		return correlation_id;
	}

	public void setCorrelation_id(String correlation_id) {
		this.correlation_id = correlation_id;
	}

	public String getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

}
