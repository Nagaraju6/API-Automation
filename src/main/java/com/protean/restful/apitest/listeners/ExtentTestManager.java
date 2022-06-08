package com.protean.restful.apitest.listeners;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.relevantcodes.extentreports.DisplayOrder;
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
	private static ExtentReports extentD;
	

	public static ExtentTest testParent;
	public static String reportPath;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ThreadLocal<Boolean> isPassed = new ThreadLocal<Boolean>();

	public static int passCountD = 0;
	public static int failCountD = 0;
	public static int passCountM = 0;
	public static int failCountM = 0;

	public static Map<String, ExtentTestManager> classReportMap = new HashMap<String, ExtentTestManager>();

	public static void initReport(String suiteName) throws IOException {
		Date d=new Date();
		String filename=d.toString().replace(":", "_").replace(" ", "_")+".html";
		String folderName=suiteName+"_"+d.toString().replace(":", "_").replace(" ", "_");
		extentD = new ExtentReports(System.getProperty("user.dir")+"//ExtentReports//"+folderName+"//"+filename, true,DisplayOrder.NEWEST_FIRST);
		reportPath=System.getProperty("user.dir")+"//Reports//"+folderName;
		
	}

//	public TestReport(String testName, String description, String suiteName) {
//		initParent(testName, description, suiteName);
//	}

	public ExtentTestManager(String testName) {
		initParent(testName);
	}

	
	public static void log(LogStatus logStatus, String stepName) {
		test.get().log(logStatus, stepName);
		try {
		switch (logStatus) {
		case PASS: case SKIP: case WARNING: case INFO:
			test.get().log(logStatus, stepName);
			break;
		case FAIL:
			test.get().log(logStatus, stepName);
			isPassed.set(false);
			// 
			break;
		case ERROR:
			test.get().log(LogStatus.FAIL, stepName);
			isPassed.set(false);
			
			break;
		case UNKNOWN:
			isPassed.set(false);
			logStatus = LogStatus.FAIL;
			test.get().log(logStatus, stepName + " ");
			
			break;
		case FATAL:
			isPassed.set(false);
			test.get().log(logStatus, stepName);
			// System.out.println(isPassed.get());
			
			break;
		default:
			break;
		}
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

//	public void initParent(String testName, String description, String suiteName) {
//			testParent = extentD.startTest(testName, description);
//			testParentWss = extentDwss.startTest(testName, description);
//		
//	}

	public void initParent(String testName) {
			testParent = extentD.startTest(testName);
		
	}

	public static void initTest(String testName, String desc) {

		isPassed.set(true);
		test.set(getextent().startTest(testName, desc));
	}

	public static void assignCatogory(String Catogory) {
		test.get().assignCategory(Catogory);
		test.get().assignCategory(Catogory);
	}

	public static void endParent() {
			extentD.endTest(testParent);
	}

	public static void endReport() {
			extentD.flush();
			extentD.close();
	}

	private static ExtentReports getextent() {
			return extentD;
	}



	public static void appendParent() {
		testParent.appendChild(test.get());
			if (isPassed.get()) {
				passCountD = passCountD + 1;
			} else {
				failCountD = failCountD + 1;
			}
		test.remove();
		test.remove();
		isPassed.remove();

	}
}