package com.bmp.Page.Object;

import com.bmp.Library.AppLibrary;

public class Login_Screen {

	private AppLibrary applibrary;

	public static String emailaddress = "xpath://input[@id='txtEmail']";

	public Login_Screen(AppLibrary appLibrary) {
		this.applibrary = applibrary;

	}

	public Login_Screen LoginUI() {
		applibrary.findElement(emailaddress);
		return new Login_Screen(applibrary);
	}

}
