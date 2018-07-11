package com.mobiquity.selenium.wrapper;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.mobiquity.selenium.wrapper.StepExecutor;

public class TestExecutor {

	final static Logger logger = Logger.getLogger(TestExecutor.class);

	public static WebDriver webDriver;
	public static String testDataPath;
	public static String pass = "pass";

	/*
	 * @Description : This method is used to abstract the commonly used set of calls
	 * in all Test Classes
	 */
	public static String testDriver() throws Exception {
		String testCaseName = Thread.currentThread().getStackTrace()[2].getMethodName();
		logger.info(testCaseName + " started");
		String testCaseStatus = StepExecutor.StepDriver(testCaseName, webDriver, testDataPath);
		logger.info(testCaseName + " ended with status: " + testCaseStatus);
		return testCaseStatus;
	}
}
