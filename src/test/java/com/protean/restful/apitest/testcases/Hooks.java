package com.protean.restful.apitest.testcases;


import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;


public class Hooks {
	
	public static Scenario scenario;
	@Before
	public void setUp(Scenario scenario) {
		Hooks.scenario=scenario;
	}

}
