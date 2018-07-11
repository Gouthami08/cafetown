package com.mobiquity.selenium.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mobiquity.selenium.DriverBase;
import com.mobiquity.selenium.wrapper.StepExecutorValues;
import com.mobiquity.selenium.wrapper.TestExecutor;

public class LoginIT extends DriverBase {

	final static Logger logger = Logger.getLogger(LoginIT.class);

	private String requiredFieldMessage;
	private String invalidCredentialsMessage;
	private String propertyFilePath;
	private Properties property;

	/*
	 * @Description : Method to set up and Load Properties File
	 */
	@BeforeClass
	public void suiteSetup() throws IOException {
		this.propertyFilePath = "/testdata/CafeTown.properties";
		this.property = new Properties();
		this.property.load(getClass().getResourceAsStream(this.propertyFilePath));
		TestExecutor.testDataPath = this.property.getProperty("ExcelTestData");
		this.requiredFieldMessage = this.property.getProperty("requiredFieldMessage");
		this.invalidCredentialsMessage = this.property.getProperty("invalidCredentialsMessage");
	}

	/*
	 * @Description : Method to Initialize the Web Driver before each test method is
	 * executed
	 */
	@BeforeMethod
	public void testSetup() throws Exception {
		TestExecutor.webDriver = getDriver();
		TestExecutor.webDriver.get(this.property.getProperty("cafeTownURL"));
		TestExecutor.webDriver.manage().window().maximize();
	}

	/*
	 * @Description : Method to reset all StepExecutor variables
	 */
	@AfterMethod
	public void testReset() throws Exception {
		StepExecutorValues.elementTextValue = "";
		StepExecutorValues.requiredFieldTextValue = "";
		StepExecutorValues.alertTextValue = "";
	}

	/*
	 * @Description : Method to check whether 'UserName' is a Mandatory Field by
	 * giving empty input to 'UserName' Field
	 */
	@Test
	public void loginEmptyUserName() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.requiredFieldMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check whether 'Password' is a Mandatory Field by
	 * giving empty input to 'Password' Field
	 */
	@Test
	public void loginEmptyPassword() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.requiredFieldMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check whether 'UserName' and 'Password' both are
	 * Mandatory Fields by giving empty input to them
	 */
	@Test
	public void loginEmptyUserPassword() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.requiredFieldMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check whether 'UserName' is validated against
	 * correct user by giving wrong username input to 'UserName' Field
	 */
	@Test
	public void loginWrongUserName() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.invalidCredentialsMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check whether 'Password' is validated against
	 * correct user by giving wrong password input to 'Password' Field
	 */
	@Test
	public void loginWrongPassword() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.invalidCredentialsMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check whether both 'UserName' and 'Password' is
	 * validated against correct user details by giving wrong input
	 */
	@Test
	public void loginWrongUserPassword() {
		try {
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.elementTextValue.contains(this.invalidCredentialsMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}
}
