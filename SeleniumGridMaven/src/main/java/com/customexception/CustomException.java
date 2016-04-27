/** 
 *This class contains the exceptions defined by user
 *This is the repository of all the exceptions those can occur in the code and how it should be handled.
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */

package com.customexception;

import org.testng.Assert;


//import org.testng.Assert;

public class CustomException extends Exception {
	String msg = "Please check the below exception Stack Trace";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// This is a constructor which calls the base class constructor
	public CustomException() {
		super();
	}

	/**
	 * This overridden method will catch the error report from the code and
	 * print here
	 * 
	 * @param e
	 *            is the exception occurred in the code printStackTrace will
	 *            print the error report of the exception
	 */
	public void exceptionHandling(Exception e) {
		if (e.getMessage().contains("ElementNotVisibleException")) {
			msg = "Accessing an element which is not available in the page";
		} else if (e.getMessage().contains("StaleElementReferenceException")) {
			msg = "Element is no longer appearing on the DOM page";
		} else if (e.getMessage().contains("NoSuchElementException")) {
			msg = "FindBy method can’t find the element";
		} else if (e.getMessage().contains("TimeoutException")) {
			msg = "the command did not complete in enough time";
		} else if (e.getMessage().contains("WebDriverException")) {
			msg = "Base webdriver exception";
		}

		else
			e.printStackTrace();

		Assert.fail(msg);

	}

	/**
	 * This overridden method will print the message
	 * 
	 * @param message
	 *            will be sent from the code and will be printed here
	 */
	public void exceptionHandling(String message) {
		Assert.fail(message);

	}

}
