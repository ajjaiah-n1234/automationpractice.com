/** 
 *This class contains Livemocha Testcase which will create a new account
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */

package com.testcases;

import java.io.IOException;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objects.ObjRepo;
import com.reusablefunctions.CustomFunctions;

public class TS_PlaceOrder extends CustomFunctions {

	public TS_PlaceOrder() throws IOException {
		super();
	}

	@BeforeMethod
	@Parameters({"browser","platform","nodeURL"})
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
	public void placeOrder(String browserName,String Username,String Password) throws Exception {
		int TC_Result = 0;
		String TC_Name = testcaseconfig.getProperty("PlaceOrder").concat("_")
				.concat(browserName);
		try {
			login(Username,Password);
			click(ObjRepo.mnuWomen);
			Thread.sleep(2000);
			addProductToCart();
			Thread.sleep(3000);
			placeOrder();
			Thread.sleep(3000);
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