package com.bmp.Library;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Configuration {
	private String url;
	private String user;
	private String pwd;
	private String browserName;
	private String driverAgent;
	private String executionEnvironment;
	private String browserStackUserName;
	private String browserStackAuthKey;
	private String browserStackBrowserVersion;
	private String browserStackOSVersion;
	private String browserStackPlatform;
	private String browserStackDevice;
	private String isEmulator;
	private String os;
	private String remoteGridUrl;
	private String browserVersion;
	private String deviceName;
	private String deviceVersion;

	public Configuration() {
		final Properties prop = new Properties();

		try {
			prop.load(new FileInputStream(new File("config.properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		user = prop.getProperty("user");
		pwd = prop.getProperty("pwd");
		browserName = prop.getProperty("browser.name");
		url = prop.getProperty("instance.url");
		driverAgent = prop.getProperty("driver.agent");
		executionEnvironment = prop.getProperty("executionEnvironment");
		remoteGridUrl = prop.getProperty("remoteGridUrl");
		os = prop.getProperty("os");
		browserStackUserName = prop.getProperty("browserstack.username");
		browserStackAuthKey = prop.getProperty("browserstack.authkey");
		browserStackBrowserVersion = prop.getProperty("browserstack.browserversion");
		browserStackOSVersion = prop.getProperty("browserstack.osversion");
		browserStackPlatform = prop.getProperty("browserstack.platform");
		browserStackDevice = prop.getProperty("browserstack.device");
		isEmulator = prop.getProperty("browserstack.isEmulator");
		browserVersion = prop.getProperty("browser.version");
		deviceName = prop.getProperty("device.name");
		deviceVersion = prop.getProperty("device.version");

		AppLibrary applib = new AppLibrary("");
		applib.autoLogger("url:" + url, false, true);
		applib.autoLogger("browserName:" + browserName, false, true);
		applib.autoLogger("driverAgent:" + driverAgent, false, true);
		applib.autoLogger("executionEnvironment:" + executionEnvironment, false, true);
		applib.autoLogger("browserStackUserName:" + browserStackUserName, false, true);
		applib.autoLogger("browserStackAuthKey:" + browserStackAuthKey, false, true);
		applib.autoLogger("browserStackBrowserVersion:" + browserStackBrowserVersion, false, true);
		applib.autoLogger("browserStackOSVersion:" + browserStackOSVersion, false, true);
		applib.autoLogger("os:" + os, false, true);
	}

	public String getURL() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPwd() {
		return pwd;
	}

	public String getBrowserName() {
		return browserName;
	}

	public String getExecutionEnvironment() {
		return executionEnvironment;
	}

	public String getBrowserStackUserName() {
		return browserStackUserName;
	}

	public String getBrowserStackAuthKey() {
		return browserStackAuthKey;
	}

	public String getBrowserStackBrowserVersion() {
		return browserStackBrowserVersion;
	}

	public String getBrowserStackOSVersion() {
		return browserStackOSVersion;
	}

	public String getRemoteGridUrl() {
		return remoteGridUrl;
	}

	public String getBrowserStackPlatform() {
		return browserStackPlatform;
	}

	public String getBrowserStackDevice() {
		return browserStackDevice;
	}

	public String getBrowserStackIsEmulator() {
		return isEmulator;
	}

	public String getOS() {
		return os;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public String getDeviceVersion() {
		return deviceVersion;
	}

	public String getDriverAgent() {
		return driverAgent;
	}
}