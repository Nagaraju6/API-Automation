package com.protean.restful.apitest.actions;

import java.io.FileInputStream;
import java.util.Properties;

public class ProjectConfig {
	public static Properties prop = new Properties();
	public static String filepath;
	static {
		try {
			filepath = System.getProperty("user.dir");
				filepath = filepath + "\\src\\main\\resources\\Constants.properties";
				prop.load(new FileInputStream(filepath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPropertyValue(String key) {
		return prop.getProperty(key);
	}
}

