package com.bmp.regression;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.bmp.Library.AppLibrary;
import com.bmp.Library.TestBase;

public class LoginUiTest extends TestBase{
	@BeforeClass
	public void setUp()
	{
		appLibrary= new AppLibrary("Login Test");
	}
	@Test
	public void loginUi() throws Exception
	{
		appLibrary.getDriverInstance();
		appLibrary.launchApp();
	}
	
	

}
