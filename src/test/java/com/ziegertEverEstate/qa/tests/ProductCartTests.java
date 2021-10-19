/*
 * @author- Shishu Raj Pandey
 * 
 */

package com.ziegertEverEstate.qa.tests;

import java.sql.Time;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ziegertEverEstate.qa.base.TestBase;
import com.ziegertEverEstate.qa.pages.ProductPage;
import com.ziegertEverEstate.qa.pages.ProductCartPage;
import com.ziegertEverEstate.qa.utill.Log;
import com.ziegertEverEstate.qa.utill.TestUtil;

public class ProductCartTests extends TestBase {

	String signUpsheetName = "signUpData";

	public ProductCartTests() {
		super();
	}

	@BeforeMethod
	public void setup() {
		Log.info("Opening the Browser and Navigating to Base URL");
		intialisation();
		Log.endTestCase();
	}



	@AfterMethod
	public void tearDown() {
		Log.info("Closing all the opened browser");
		driver.quit();
	}
}
