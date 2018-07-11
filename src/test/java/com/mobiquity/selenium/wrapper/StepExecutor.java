package com.mobiquity.selenium.wrapper;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StepExecutor {

	final static Logger logger = Logger.getLogger(StepExecutor.class);

	static int _1secondWait = 1000;
	static int _2secondWait = 2000;
	static int start;
	static int end;
	static List<Integer> times = new ArrayList<Integer>();
	static String testStatus = "fail";

	/*
	 * @Description : This method contains the main business logic for executing the
	 * test steps of each test case. Fetches the test step from excel, locates its
	 * position based on the 'Locator' mentioned in Test Excel input and performs
	 * the action based on the specified 'Keyword'
	 */
	public static String StepDriver(String TestName, WebDriver webDriver, String testDataPath) throws Exception {
		WebDriverWait webDriverWait = new WebDriverWait(webDriver, 180);
		WebElement webElement = null;

		String excelPath = testDataPath;

		HSSFSheet testSheet = ExcelUtils.getSheetObj(excelPath, "Test_Steps");

		int stpscnt = testSheet.getLastRowNum();
		for (int i = 1; i <= stpscnt; i++) {
			String tcase = ExcelUtils.getExlCellData(testSheet, i, 0);
			String[] tc = tcase.split("_");
			if (tc[0].equalsIgnoreCase(TestName)) {
				start = i;
				break;
			}
		}
		for (int i = 1; i <= stpscnt; i++) {
			String tcase = ExcelUtils.getExlCellData(testSheet, i, 0);
			if (tcase == null) {
				break;
			}
			String[] tc = tcase.split("_");
			if (tc[0].equalsIgnoreCase(TestName)) {
				times.add(start);
			}
		}
		end = start + times.size() - 1;
		times.clear();
		for (int j = start; j <= end; j++) {
			String keyword = ExcelUtils.getExlCellData(testSheet, j, 1);
			String Locator = ExcelUtils.getExlCellData(testSheet, j, 2);
			String value = ExcelUtils.getExlCellData(testSheet, j, 3);
			String input = ExcelUtils.getExlCellData(testSheet, j, 4);

			try {
				// Finding the element based on locator specified in Test Data
				if (Locator.equalsIgnoreCase("id")) {
					webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id(value)));
					logger.info("Located the Element with id" + webElement);
				} else if (Locator.equalsIgnoreCase("xpath")) {
					webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(value)));
					logger.info("Located the Element with xpath" + webElement);
				} else if (Locator.equalsIgnoreCase("tagName")) {
					webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.tagName(value)));
					logger.info("Located the Element with tagName" + webElement);
				} else if (Locator.equalsIgnoreCase("className")) {
					webElement = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.className(value)));
					logger.info("Located the Element with className" + webElement);
				}
				// Performing Action on the element based on Keyword specified in Test Data
				if (keyword.equalsIgnoreCase("sendkeys")) {
					webElement.sendKeys(input);
					Thread.sleep(_1secondWait);
					logger.info(input + ": Value Entered Succesfully");
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("click")) {
					webElement.click();
					Thread.sleep(_2secondWait);
					logger.info("Element Clicked Succesfully: " + webElement);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("Switch")) {
					webDriver.switchTo().alert().accept();
					logger.info("Alert Accepted Succesfully: " + webElement);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("GetText")) {
					StepExecutorValues.elementTextValue = webElement.getText();
					logger.info("Element Text Fetched Succesfully: " + StepExecutorValues.elementTextValue);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("DoubleClick")) {
					Actions action = new Actions(webDriver);
					action.doubleClick(webElement).perform();
					Thread.sleep(_1secondWait);
					logger.info("Double clciked on element Succesfully: " + webElement);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("Clear")) {
					webElement.clear();
					logger.info("Cleared Element Text Succesfully");
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("RequiredGetText")) {
					StepExecutorValues.requiredFieldTextValue = webElement.getAttribute("validationMessage");
					logger.info("Fetched Required Field Text Succesfully" + StepExecutorValues.requiredFieldTextValue);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("AlertGetText")) {
					StepExecutorValues.alertTextValue = webDriver.switchTo().alert().getText();
					logger.info("Fetched Alert Text Succesfully" + StepExecutorValues.alertTextValue);
					testStatus = "pass";
				} else if (keyword.equalsIgnoreCase("GetClassName")) {
					StepExecutorValues.elementTextValue = webElement.getAttribute("className");
					logger.info("Fetched Class Name Successfully" + StepExecutorValues.elementTextValue);
					testStatus = "pass";
				}
			} catch (Exception exception) {
				testStatus = "fail";
				logger.error("Step Failure Details are : ");
				logger.error("Step Number : " + j);
				logger.error("TestCase Name : " + TestName);
				logger.error("Keyword : " + keyword);
				logger.error("Locator : " + Locator);
				logger.error(exception.getMessage());

			}
		}
		System.out.println(TestName + " ****** Test Scenario Executed");
		return testStatus;

	}
}