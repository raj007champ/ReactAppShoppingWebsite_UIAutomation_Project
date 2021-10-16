/*
 * @author- Shishu Raj Pandey
 * 
 */

package com.ziegertEverEstate.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.ziegertEverEstate.qa.utill.Log;
import com.ziegertEverEstate.qa.utill.TestUtil;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	static String currentDirectory = System.getProperty("user.dir");
//	public static String chromeDriverLocation = currentDirectory + "\\browserDrivers\\chromedriver.exe";
//	public static String firefoxDriverLocation = currentDirectory + "\\browserDrivers\\geckodriver.exe";

	public TestBase() {
		System.out.println(currentDirectory);
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					currentDirectory + "\\src\\main\\java\\com\\ziegertEverEstate\\qa\\config\\config.properties");
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void intialisation() {
		String browserName = prop.getProperty("BROWSER");
		if (browserName.equalsIgnoreCase("chrome")) {
			Log.info("Opening Chrome Browser");
			//System.setProperty("webdriver.chrome.driver", chromeDriverLocation);
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			Log.info("Opening Firefox Browser");
		//	System.setProperty("webdriver.gecko.driver", firefoxDriverLocation);
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("IE")) {
			WebDriverManager.edgedriver().setup();
		}
		Log.info("Maximising the Browser Window");
		driver.manage().window().maximize();
		Log.info("Deleting all cookies of Browser");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		Log.info("Opening Base URL--->" + prop.getProperty("BASE_URL"));
		driver.get(prop.getProperty("BASE_URL"));
	}
}
