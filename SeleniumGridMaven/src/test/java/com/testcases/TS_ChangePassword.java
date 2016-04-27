package com.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objects.ObjRepo;
import com.reusablefunctions.CustomFunctions;

public class TS_ChangePassword extends CustomFunctions {

	public TS_ChangePassword() throws IOException {
		super();
	}

	@BeforeMethod
	@Parameters({"browser","platform","nodeURL" })
	public void initialize(String browserName,String platform,String nodeURL) {
		try {
			launchBrowser(platform,browserName,nodeURL);
			launchURL();
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
	}

	@Test
	@Parameters({"browser", "Username","Password" })
	public void changePassword(String browserName,String Username,String Password) throws Exception {
		int TC_Result = 0;
		String TC_Name = testcaseconfig.getProperty("ChangePassword").concat("_")
				.concat(browserName);
		
		System.out.println(TC_Name);
		try {
			login(Username,Password);
			Thread.sleep(4000);
			click(ObjRepo.personalInfo);
			Thread.sleep(2000);
			getWebElement(ObjRepo.oldPassword).sendKeys(
					propconfig.getProperty("password"));
			Thread.sleep(2000);
			getWebElement(ObjRepo.newPassword).sendKeys(
					propconfig.getProperty("newpassword"));
			Thread.sleep(2000);
			getWebElement(ObjRepo.confirmPassword).sendKeys(
					propconfig.getProperty("newpassword"));
			Thread.sleep(2000);
			click(ObjRepo.saveInfo);
			Assert.assertTrue(existsElement(ObjRepo.saveNotification));
			signOut();
			login(Username,"Iseva1234");
			Assert.assertTrue(existsElement(ObjRepo.personalInfo));
			cleanUp();
			signOut();
			TC_Result = 1;
		} catch (Exception e) {
			objException.exceptionHandling(e);
		} 
	}

	@AfterMethod
	public void closeBrowser() {
		try {
			quitBrowser();
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}

	}
}
