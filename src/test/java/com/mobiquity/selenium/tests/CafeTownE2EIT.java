package com.mobiquity.selenium.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.mobiquity.selenium.DriverBase;
import com.mobiquity.selenium.wrapper.StepExecutorValues;
import com.mobiquity.selenium.wrapper.TestExecutor;

public class CafeTownE2EIT extends DriverBase {

	final static Logger logger = Logger.getLogger(CafeTownE2EIT.class);

	private String propertyFilePath;
	private Properties property;
	private String logoutText;
	private String createdUser;
	private String loginText;

	/*
	 * @Description : Method to set up and Load Properties File
	 */
	@BeforeClass
	public void suiteSetup() throws IOException {
		this.propertyFilePath = "/testdata/CafeTown.properties";
		this.property = new Properties();
		this.property.load(getClass().getResourceAsStream(this.propertyFilePath));
		TestExecutor.testDataPath = this.property.getProperty("ExcelTestData");
		this.logoutText = this.property.getProperty("logoutText");
		this.createdUser = this.property.getProperty("createdUser");
		this.loginText = this.property.getProperty("loginText");
	}

	/*
	 * @Description : Method to Initialize the Chrome Web Driver before each test
	 * method is executed
	 */
	@BeforeMethod
	public void testSetup() throws Exception {
		TestExecutor.webDriver = getDriver();
		TestExecutor.webDriver.get(this.property.getProperty("cafeTownURL"));
		TestExecutor.webDriver.manage().window().maximize();
	}

	/*
	 * @Description : Method to reset all StepExecutor variables and close the
	 * WebDriver after each test is completed
	 */
	@AfterMethod
	public void testReset() {
		StepExecutorValues.elementTextValue = "";
		StepExecutorValues.requiredFieldTextValue = "";
		StepExecutorValues.alertTextValue = "";
	}

	/*
	 * @Description : Login Method which is common to all the test methods in this
	 * class
	 */
	public void cafeTownLogin() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.logoutText));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to create new user by passing all valid input fields
	 */
	public void createUser() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.createdUser));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to edit the input field of previously created user
	 */

	public void editUser() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.createdUser));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to delete the created user
	 */
	public void userDelete() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}
	
	/*
	 * @Description : Method to check if the Logout functionality works fine on
	 * Employee Page
	 */
	public void logoutEmployeePage() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.loginText));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}


	/*
	 * @Description : This method is used to verify the End to End Flow right from
	 * Login , Create New USer , Edit the Created User , Delete teh Created USer and
	 * Logout
	 */
	@Test
	public void E2EFlow() {
		this.cafeTownLogin();
		this.createUser();
		this.editUser();
		this.userDelete();
		this.logoutEmployeePage();
	}
}
