package com.enhops.restful.apitest.listeners;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.BeforeMethod;

import com.enhops.restful.apitest.restassuredFuntions.GenerateRequest;


public abstract class TestNGMethods{
	
	TestData data=new TestData();
  
	@BeforeMethod
    public void beforeMethod(Method method) throws EncryptedDocumentException, IOException {
		String testCase=method.getName();
        ExtentTestManager.startTest(testCase);
        data.setTestcaseNumber(testCase);
        GenerateRequest.getRequest();
	}
	
}