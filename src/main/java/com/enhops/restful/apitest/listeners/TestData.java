package com.enhops.restful.apitest.listeners;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.relevantcodes.extentreports.LogStatus;

public class TestData {

	private String testCaseNum;
	private Map<Integer, String> key = new HashMap<Integer, String>();
	private Map<Integer, String> values = new HashMap<Integer, String>();
	private static Map<String, String> dataComb = new LinkedHashMap<String, String>();

	public void setTestcaseNumber(String testCaseNumber) throws EncryptedDocumentException, IOException {
		this.testCaseNum=testCaseNumber;
		readData();
	}
	private void readData() throws EncryptedDocumentException, IOException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(System.getProperty("user.dir") + "//TestData//NSDLScenarios.xlsx");
			Workbook wb = WorkbookFactory.create(file);
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheet(wb.getSheetName(i));
				for (Row row : sheet) {
					Cell firstcell = row.getCell(0);
					if (firstcell == null) {
						continue;
					}
					String label = firstcell.getStringCellValue();
					if (label.equalsIgnoreCase("TestCaseID")) {
						for (Cell cell : row) {
							key.put(cell.getColumnIndex(), cell.getStringCellValue());
						}
					} else if (label.equalsIgnoreCase(testCaseNum)) {
						for (Cell cell : row) {
							values.put(cell.getColumnIndex(), cell.getStringCellValue());
						}
					}
					for (int j : values.keySet()) {
						if (values.get(j) != "")
							dataComb.put(key.get(j), values.get(j));
					}
				}
				key.clear();
				values.clear();
			}
			ExtentTestManager.getTest().log(LogStatus.INFO, dataComb.toString());
		} catch (FileNotFoundException e) {
			ExtentTestManager.getTest().log(LogStatus.FAIL, "Unable to read data " + e.getMessage());
			e.printStackTrace();

		}
	}

	public static String get(String data) {
		if (dataComb.containsKey(data)) {
			return dataComb.get(data);
		} else {
			return null;
		}

	}

}
