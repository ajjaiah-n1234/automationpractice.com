/** 
 *This class contains the functions which use the native web-driver methods
 *This class contains the actions which will be used more often in the Test execution
 *
 *@author - Sampath & Praveen
 *@since - 07/12/15
 */
package com.reusablefunctions;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.customexception.CustomException;
import com.utilities.ReadProperties;

//This class contains reusable predefined functions created using Selenium webdriver library

public class PredefinedFunctions {

	public WebDriver driver;
	static WebDriverWait wait;
	ReadProperties object = new ReadProperties();
	public CustomException objException = new CustomException();

	public Properties propconfig;
	public Properties testcaseconfig;
	String websiteURL;
	String browser;

	public PredefinedFunctions() throws IOException {
		propconfig = object.getRepository("config.properties");
		testcaseconfig = object.getRepository("testcases.properties");
		websiteURL = propconfig.getProperty("websiteURL");
		browser = propconfig.getProperty("browser");

	}

	// This method will launch the web browser mentioned in the
	// config.properties file
	public void launchBrowser(String Platform,String browser,String nodeURL) {
		try {

			if (browser.equalsIgnoreCase("firefox")) {
				DesiredCapabilities caps=DesiredCapabilities.firefox();
				caps.setBrowserName(browser);
				if(Platform.equalsIgnoreCase("MAC")){
					caps.setPlatform(org.openqa.selenium.Platform.MAC);
				}else if(Platform.equalsIgnoreCase("WINDOWS")){
					caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}
				
				driver=new RemoteWebDriver(new URL(nodeURL),caps);

			}

			else if (browser.equalsIgnoreCase("chrome")) {


				DesiredCapabilities caps=DesiredCapabilities.chrome();
				caps.setBrowserName(browser);
				if(Platform.equalsIgnoreCase("MAC")){
					caps.setPlatform(org.openqa.selenium.Platform.MAC);
				}else if(Platform.equalsIgnoreCase("WINDOWS")){
					caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}
				driver=new RemoteWebDriver(new URL(nodeURL),caps);
			}

			else if (browser.equalsIgnoreCase("iexplore")) {

				DesiredCapabilities caps=DesiredCapabilities.internetExplorer();
				caps.setBrowserName(browser);
				if(Platform.equalsIgnoreCase("MAC")){
					caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}else if(Platform.equalsIgnoreCase("WINDOWS")){
					caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
				}
				driver=new RemoteWebDriver(new URL(nodeURL),caps);
			}

			
			

		} catch (Exception e) {
			objException.exceptionHandling("Browser is not correct");
		}

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 15);
		// driver.manage().window().maximize();

	}

	// This method will launch the web-site mentioned in the config.properties
	// file
	public void launchURL() {
		driver.get(websiteURL);
	}

	// This method will find the object using locators
	private By getObject(String comppleteObject) {
		By by = null;
		try {

			String[] str = comppleteObject.split("=", 2);
			String objectType = str[0];
			String objectName = str[1];

			if (objectType.equalsIgnoreCase("XPATH")) {
				by = By.xpath(objectName);

			}

			else if (objectType.equalsIgnoreCase("CLASSNAME")) {
				by = By.className(objectName);

			}

			else if (objectType.equalsIgnoreCase("ID")) {
				by = By.id(objectName);

			}

			else if (objectType.equalsIgnoreCase("NAME")) {
				by = By.name(objectName);

			}

			else if (objectType.equalsIgnoreCase("CSS")) {
				by = By.cssSelector(objectName);

			}

			else if (objectType.equalsIgnoreCase("LINK")) {
				by = By.linkText(objectName);

			}

			else if (objectType.equalsIgnoreCase("PARTIALLINK")) {
				by = By.partialLinkText(objectName);

			}

		} catch (Exception e) {
			objException.exceptionHandling("Wrong object type");
		}

		return by;
	}

	// This method will click the WebElement
	public void click(String object) {
		try {
			driver.findElement(getObject(object)).click();
			return;
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
	}

	// This method will get the web-element
	public WebElement getWebElement(String object) {
		WebElement txtbox = null;
		try {
			txtbox = driver.findElement(getObject(object));
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
		return txtbox;
	}

	// This method will check whether element exists or not
	public boolean existsElement(String object) {
		try {
			driver.findElement(getObject(object));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// This method will select from the dropdown
	public void selectVisibleText(String object, String text) {
		try {
			Select dropdown = new Select(driver.findElement(getObject(object)));
			dropdown.selectByVisibleText(text);

			return;
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
	}

	// This method will hover on one link and click on the expanded link
	public void hoverAndClick(String hoverItem, String clickItem) {
		try {
			Actions action = new Actions(driver);
			WebElement hoverlink = driver.findElement(getObject(hoverItem));
			action.moveToElement(hoverlink).pause(1000l).perform();
			action.click(driver.findElement(getObject(clickItem))).build()
					.perform();

			return;
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
	}

	public void hoverOnItem(String hoverItem) {
		try {
			Actions action = new Actions(driver);
			WebElement hoverlink = driver.findElement(getObject(hoverItem));
			action.moveToElement(hoverlink).build().perform();
			Thread.sleep(3000);
			return;
		} catch (Exception e) {
			objException.exceptionHandling(e);
		}
	}

	// This method will quit browser
	public void quitBrowser() {
		driver.quit();
	}
}
