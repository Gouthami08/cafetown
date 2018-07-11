package com.mobiquity.selenium.wrapper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.mobiquity.selenium.tests.CreateUserIT;

public class CaptureScreenshot {

	final static Logger logger = Logger.getLogger(CreateUserIT.class);

	/*
	 * @Description : This method is used to capture the screenshot on each Test
	 * Step Failure
	 */
	public static void getSnapShot(WebDriver drvobj, String TcName) throws Exception {
		final String ESCAPE_PROPERTY = "org.uncommons.reportng.escape-output";
		try {
			String imgpath = System.getProperty("user.dir").concat("\\Screenshot\\Snapshot" + TcName);
			File file = new File(imgpath);
			if (!file.exists()) {
				file.mkdir();
			}

			Date d = new Date();
			SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yy_HH_mm_ss_a");
			String timestamp = sd.format(d);
			String imgname = imgpath + "\\" + timestamp + ".png";

			// Snapshot code
			TakesScreenshot snpobj = ((TakesScreenshot) drvobj);
			File srcfile = snpobj.getScreenshotAs(OutputType.FILE);
			File destFile = new File(imgname);
			FileUtils.copyFile(srcfile, destFile);

			System.setProperty(ESCAPE_PROPERTY, "false");
			String path1 = "<a href=" + imgname + ">Click</a>";
			Reporter.log(path1);
		} catch (Exception exception) {
			logger.error(exception.getMessage());
		}
	}

}
