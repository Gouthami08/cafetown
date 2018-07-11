package com.mobiquity.selenium.tests;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.mobiquity.selenium.wrapper.StepExecutorValues;
import com.mobiquity.selenium.wrapper.TestExecutor;
import com.mobiquity.selenium.DriverBase;

public class CreateUserIT extends DriverBase {

	final static Logger logger = Logger.getLogger(CreateUserIT.class);

	private String propertyFilePath;
	private Properties property;
	String logoutText = "";
	String requiredFieldMessage = "";
	String wrongDateValidationMessage = "";
	String wrongMailIDValidationMesaage = "";

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
		this.wrongDateValidationMessage = this.property.getProperty("wrongDateValidationMessage");
		this.wrongMailIDValidationMesaage = this.property.getProperty("wrongMailIDValidationMesaage");
		this.requiredFieldMessage = this.property.getProperty("requiredFieldMessage");

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
	 * @Description : Method to validate if an user can be created without entering
	 * any of the parameters for all the required input fields
	 */
	@Test
	public void createNewUserEmptyInputFields() {
		try {
			this.cafeTownLogin();
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.requiredFieldMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check if an error message is returned when 'Date'
	 * Field is given Empty and try to create an user
	 */
	@Test
	public void createNewUserEmptyDateField() {
		try {
			this.cafeTownLogin();
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.requiredFieldMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check if an error alert box is displayed when wrong
	 * format is given for 'Date' input field
	 */
	@Test
	public void createNewUserWrongDateField() {
		try {
			this.cafeTownLogin();
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.alertTextValue.contains(this.wrongDateValidationMessage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

	/*
	 * @Description : Method to check if validation message is displayed to user
	 * when 'Mail ID' is given empty
	 */
	@Test
	public void createNewUserWrongMailIDField() {
		try {
			this.cafeTownLogin();
			Assert.assertEquals(TestExecutor.testDriver(), "pass");
			Assert.assertTrue(StepExecutorValues.requiredFieldTextValue.contains(this.wrongMailIDValidationMesaage));
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}
}
