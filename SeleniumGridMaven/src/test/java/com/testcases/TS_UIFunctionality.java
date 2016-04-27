/** 
 *This class contains Livemocha Testcase which will create a new account
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */

package com.testcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.objects.ObjRepo;
import com.reusablefunctions.CustomFunctions;

public class TS_UIFunctionality extends CustomFunctions {

	public TS_UIFunctionality() throws IOException {
		super();
	}

	@BeforeMethod
	@Parameters({ "browser","platform","nodeURL" })
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
	public void uiFunctionality(String browserName,String Username,String Password) throws Exception {
		int TC_Result = 0;
		String TC_Name = testcaseconfig.getProperty("UIFunctionality").concat("_")
				.concat(browserName);
		try {
			login(Username,Password);
			Thread.sleep(3000);
			click(ObjRepo.mnuDresses);
			click(ObjRepo.sortOption);
			Thread.sleep(3000);
			click(ObjRepo.listIcon);
			Thread.sleep(3000);
			click(ObjRepo.gridIcon);
			Thread.sleep(3000);
			click(ObjRepo.checkBox);
			Thread.sleep(3000);

			click(ObjRepo.mnuDresses);
			Thread.sleep(3000);
			click(ObjRepo.dressSecondItem);
			Thread.sleep(3000);
			if (existsElement(ObjRepo.imgsBlockDress)) {
				hoverOnItem(ObjRepo.imgsBlockDress);
			}
			Thread.sleep(3000);
			click(ObjRepo.viewLarger);
			Thread.sleep(3000);
			Assert.assertTrue(existsElement(ObjRepo.lnkPrevious));
			Assert.assertTrue(existsElement(ObjRepo.lnkNext));
			click(ObjRepo.lnkClose);
			Thread.sleep(3000);
			click(ObjRepo.btnPlus);
			Thread.sleep(3000);
			click(ObjRepo.btnMinus);
			Thread.sleep(3000);
			selectVisibleText(ObjRepo.selectSize, "L");
			Thread.sleep(3000);
			click(ObjRepo.lnkColorPick);
			Thread.sleep(3000);
			click(ObjRepo.lnkColorPick);
			Thread.sleep(3000);
			click(ObjRepo.lblWriteaReview);
			Thread.sleep(6000);
			click(ObjRepo.starFiveRating);
			Thread.sleep(3000);
			getWebElement(ObjRepo.txtReviewTitle).sendKeys(
					propconfig.getProperty("ReviewTitle"));
			Thread.sleep(3000);
			getWebElement(ObjRepo.txtareaContent).sendKeys(
					propconfig.getProperty("ReviewComment"));
			Thread.sleep(3000);
			click(ObjRepo.btnSend);
			Thread.sleep(3000);
			if (existsElement(ObjRepo.LabelComment)) {
				Thread.sleep(3000);
				click(ObjRepo.btnOk);
				Thread.sleep(6000);
				
			}
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
