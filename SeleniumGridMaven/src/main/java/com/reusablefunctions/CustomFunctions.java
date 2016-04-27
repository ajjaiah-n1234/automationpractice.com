/** 
 *This class contains the functions defined by user
 *This class contains the methods which will be used more often in the Test execution
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */
package com.reusablefunctions;

import java.io.IOException;

import org.testng.Assert;

import com.objects.ObjRepo;

//This class contains reusable custom functions defined by users
public class CustomFunctions extends PredefinedFunctions {

	public CustomFunctions() throws IOException {
		super();
	}

	public void login(String password) throws InterruptedException {
		Thread.sleep(3000);
		click(ObjRepo.lnkSignIn);
		Thread.sleep(3000);
		getWebElement(ObjRepo.txtLoginName).sendKeys(
				propconfig.getProperty("username"));
		Thread.sleep(2000);
		if (password.equals("password"))
			getWebElement(ObjRepo.txtLoginPassword).sendKeys(
					propconfig.getProperty("password"));
		else if (password.equals("newpassword"))
			getWebElement(ObjRepo.txtLoginPassword).sendKeys(
					propconfig.getProperty("newpassword"));
		Thread.sleep(2000);
		click(ObjRepo.btnSignin);

	}
	
	public void login(String Username,String Password) throws InterruptedException {
		Thread.sleep(3000);
		click(ObjRepo.lnkSignIn);
		Thread.sleep(3000);
		getWebElement(ObjRepo.txtLoginName).sendKeys(Username);
		Thread.sleep(2000);
		
		getWebElement(ObjRepo.txtLoginPassword).sendKeys(Password);
			
		Thread.sleep(2000);
		click(ObjRepo.btnSignin);

	}

	public void cleanUp() throws InterruptedException {
		Thread.sleep(2000);
		click(ObjRepo.personalInfo);
		Thread.sleep(2000);
		getWebElement(ObjRepo.oldPassword).sendKeys(
				propconfig.getProperty("newpassword"));
		Thread.sleep(2000);
		getWebElement(ObjRepo.newPassword).sendKeys(
				propconfig.getProperty("password"));
		Thread.sleep(2000);
		getWebElement(ObjRepo.confirmPassword).sendKeys(
				propconfig.getProperty("password"));
		Thread.sleep(2000);
		click(ObjRepo.saveInfo);
	}

	
	public void signOut() throws InterruptedException {
		click(ObjRepo.btnSignOut);
		Thread.sleep(3000);
	}

	public void addProductToCart() throws InterruptedException {
		Thread.sleep(4000);
		click(ObjRepo.secondItem);
		Thread.sleep(5000);
		click(ObjRepo.btnAddToCart);
		Thread.sleep(6000);
		Assert.assertTrue(existsElement(ObjRepo.btnProceedToCheck));
		click(ObjRepo.btnProceedToCheck);
	}
	public void placeOrder() throws InterruptedException
	{
		Thread.sleep(3000);
		click(ObjRepo.proceedToCart);
		Thread.sleep(3000);
		click(ObjRepo.proceedToCart);
		Thread.sleep(3000);
		click(ObjRepo.chkTermsAndCondition);
		Thread.sleep(3000);
		click(ObjRepo.proceedToCart);
		Thread.sleep(3000);
		Thread.sleep(3000);
		click(ObjRepo.payByBank);
		Thread.sleep(3000);
		click(ObjRepo.confirmOrder);
		Thread.sleep(3000);
		Assert.assertTrue(driver.getPageSource().contains("Order confirmation"));
	}
	public void verifyPlacedOrder() throws InterruptedException
	{
		Assert.assertTrue((existsElement(ObjRepo.verifyOrderReference)));
		
	}

}
