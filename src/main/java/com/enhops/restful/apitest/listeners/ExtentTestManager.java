package com.enhops.restful.apitest.listeners;

import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


/**
 * OB: extentTestMap holds the information of thread ids and ExtentTest instances.
 * ExtentReports instance created by calling getReporter() method from ExtentManager.
 * At startTest() method, an instance of ExtentTest created and put into extentTestMap with current thread id.
 * At endTest() method, test ends and ExtentTest instance got from extentTestMap via current thread id.
 * At getTest() method, return ExtentTest instance in extentTestMap by using current thread id.
 */


public class ExtentTestManager {  
    static Map extentTestMap = new HashMap();
    static ExtentTest test;
    static ExtentReports extent = ExtentManager.getReporter();

    public static synchronized ExtentTest getTest() {
        return (ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void endTest() {
        extent.endTest((ExtentTest) extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }

    public static synchronized ExtentTest startTest(String testName) {
        return startTest(testName, "");
    }

    @SuppressWarnings("unchecked")
	public static synchronized ExtentTest startTest(String testName, String desc) {
        test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);

        return test;
    }
    
    public static synchronized void log(LogStatus logStatus, String stepName) {
		switch (logStatus) {
		case PASS:
			test.log(logStatus, stepName);
			break;
		case FAIL:
			test.log(logStatus, stepName);
			break;
		case INFO:
		case SKIP:
			test.log(logStatus, stepName);
			break;
		case WARNING:
			test.log(logStatus, stepName);
			break;
		case ERROR:
			test.log(LogStatus.FAIL, stepName);
			break;
		case UNKNOWN:
			logStatus = LogStatus.FAIL;
			test.log(logStatus, stepName + " ");
			
			break;
		case FATAL:
			test.log(logStatus, stepName);
			
			break;
		default:
			break;
		}

	}
}