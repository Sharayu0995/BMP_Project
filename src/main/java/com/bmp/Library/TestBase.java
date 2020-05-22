package com.bmp.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

public class TestBase {
	public WebDriver driver;
	protected AppLibrary appLibrary; // Application Library instance
	Properties usersProperties = null;
	private String suite;
	protected String testName;
	ITestContext testContext;


	@BeforeClass(alwaysRun = true)
	public void setUp(ITestContext context) throws Exception {

		suite = context.getCurrentXmlTest().getSuite().getName();
		suite = ((suite != null && !(suite.equals("Default suite"))) ? suite
				: InetAddress.getLocalHost().getHostName());
		suite = (suite.contains("Neo") ? "Neo" : suite);
		testName = this.getClass().getSimpleName();
		testName = ((testName != null && !(testName.equals("Default test"))) ? testName
				: this.getClass().getSimpleName());

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MMMddyyyyhhmmssaz");
		String currentDate = sdf.format(date);

		if (System.getProperty("Build") == null) {
			System.setProperty("Build", suite + "_" + currentDate);
			System.setProperty("Suite", suite);
		}
		System.setProperty("Test", testName);

		appLibrary = new AppLibrary("");
		appLibrary.autoLogger("Suite: " + suite, true, false);
		appLibrary.autoLogger("TestName: " + testName, true, false);
		testContext = context;
	}

	

	public Properties loadUserProperties() {
		if (usersProperties == null) {
			usersProperties = new Properties();
			try {
				File f = new File("Users.properties");
				if (!f.exists()) {
					f = new File("TestData/users.properties");
				}
				usersProperties.load(new FileInputStream(f));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return usersProperties;
	}

	@AfterMethod
	public void checkAlerts(ITestResult result) throws Exception {
		if (result.getStatus() == ITestResult.FAILURE) {
			try {
				String screenshotName = result.getName() + "_" + appLibrary.browser + "_" + appLibrary.randInt() + ".png";
				appLibrary.getScreenshot(screenshotName);
				appLibrary.autoLogger("Failed at URL: " + appLibrary.getCurrentDriverInstance().getCurrentUrl(), false,
						true);
				int paramsLength = result.getParameters().length;
				appLibrary.autoLogger("ScreenShot for " + testName + " "
						+ ((paramsLength > 0) ? " with parameter " + result.getParameters()[1] : "") + "saved as "
						+ screenshotName + ".png", false, true);

			} catch (Exception e) {
				appLibrary.autoLogger("Failed fetching URL and screenshot due to error:" + e.getMessage(), false, true);
				e.printStackTrace();
			}

			if (appLibrary.getCurrentSessionID() != null) {
				appLibrary.autoLogger("Session id for " + testName + " is " + appLibrary.getCurrentSessionID(), false,
						true);
				appLibrary.autoLogger("Session details for " + testName
						+ " can be found at https://www.browserstack.com/automate/sessions/"
						+ appLibrary.getCurrentSessionID() + ".json", false, true);
			}
		}
		try {
			Alert alert = appLibrary.getCurrentDriverInstance().switchTo().alert();
			String alertText = alert.getText();
			alert.accept();
			appLibrary.autoLogger("Unexpected Alert opened: " + alertText, true, true);
		} catch (NoAlertPresentException e) {

		}

		try {
			while (appLibrary.getCurrentDriverInstance().getWindowHandles().size() > 1) {
				appLibrary.getCurrentDriverInstance().switchTo()
						.window((String) appLibrary.getCurrentDriverInstance().getWindowHandles().toArray()[1]).close();
			}
			appLibrary.getCurrentDriverInstance().quit();
		} catch (Exception e) {
			if (appLibrary.getCurrentSessionID() != null) {
				appLibrary.autoLogger("Session id for " + testName + " is " + appLibrary.getCurrentSessionID(), false,
						true);
				appLibrary.autoLogger("Session details for " + testName
						+ " can be found at https://www.browserstack.com/automate/sessions/"
						+ appLibrary.getCurrentSessionID(), false, true);
			}
			throw e;
		}
	}

	@AfterClass(alwaysRun = true)
	public void quitBrowser() {
		appLibrary.closeBrowser();
		appLibrary.autoLogger("Closing the Browser Successfully", false, true);
		appLibrary.autoLogger("", false, true);
	}

	@BeforeMethod
	public void nameBefore(Method method) {
		appLibrary = new AppLibrary(testName + "." + method.getName());
	}
}
