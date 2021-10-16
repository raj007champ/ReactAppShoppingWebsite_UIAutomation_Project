/*
 * @author- Shishu Raj Pandey
 * 
 */

package com.ziegertEverEstate.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ziegertEverEstate.qa.base.TestBase;
import com.ziegertEverEstate.qa.utill.Log;

public class ProductCartPage extends TestBase {

	public ProductCartPage() {
		PageFactory.initElements(driver, this);
	}

}
