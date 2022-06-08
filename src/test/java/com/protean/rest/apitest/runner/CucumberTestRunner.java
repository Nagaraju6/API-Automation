package com.protean.rest.apitest.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "FeatureFiles", 
		glue = { "com.protean.restful.apitest.testcases" }, 
		plugin = { "pretty","html:target/cucumber-reports/reports.html" }, 
		monochrome = true)
public class CucumberTestRunner {

}
