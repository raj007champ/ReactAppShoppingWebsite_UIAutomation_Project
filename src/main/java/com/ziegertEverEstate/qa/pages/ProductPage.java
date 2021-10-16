/*
 * @author- Shishu Raj Pandey
 * 
 */

package com.ziegertEverEstate.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.ziegertEverEstate.qa.base.TestBase;

import jdk.internal.org.jline.utils.Log;

public class ProductPage extends TestBase {

	WebDriverWait extentWait = new WebDriverWait(driver, 20);

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//p[@class='shelf-item__title']")
	WebElement productTitle;

	@FindBy(xpath = "//p[@class='shelf-item__title']")
	List<WebElement> listOfProductTitles;

	@FindBy(xpath = "//div[@class='shelf-item__price']")
	List<WebElement> listOfProductPrices;

	@FindBy(xpath = "//div[@class='shelf-item__price']")
	WebElement productPrice;

	@FindBy(xpath = "//div[@class='shelf-item__buy-btn']")
	WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='shelf-item__thumb']")
	WebElement productThumbNails;

	@FindBy(xpath = "//div[@class='filters-available-size']")
	WebElement availableSizes;

	@FindBy(xpath = "//div[@class='filters-available-size']//span[@class='checkmark']")
	List<WebElement> availableSizeButton;

	@FindBy(xpath = "//div[@class='sort']//select")
	WebElement orderByDropdown;

	@FindBy(xpath = "//option[@value='lowestprice']")
	WebElement lowestPriceOption;

	@FindBy(xpath = "//option[@value='highestprice']")
	WebElement highestPriceOption;

	// small[@class='products-found']/span

	@FindBy(xpath = "//small[@class='products-found']/span")
	WebElement productFoundText;

	@FindBy(xpath = "//div[@class='shelf-container']//div//div//div[1]")
	WebElement productPriceValue;

	@FindBy(xpath = "//div[@class='shelf-container']//div//div//div[2]")
	WebElement InstallementPriceValue;

	@FindBy(xpath = "//div[@class='buy-btn']")
	WebElement checkOutButton;

	@FindBy(xpath = "//p[@class='sub-price__val']")
	WebElement totalCartValue;

	@FindBy(xpath = "//div[@class='shelf-item__del']")
	WebElement deleteCartItem;

	public String getPageTitle() {
		return driver.getTitle();
	}

	public String getPageURL() {
		return driver.getCurrentUrl();
	}

	public void orderByLowestToHighest() {
		orderByDropdown.click();
		lowestPriceOption.click();
	}

	public void orderByHighestToLowest() {
		orderByDropdown.click();
		highestPriceOption.click();
	}

	public List<String> returnProductTitleList() {
		List<String> productTitleList = new ArrayList<>();
		for (WebElement product : listOfProductTitles) {
			productTitleList.add(product.getText());
		}
		return productTitleList;

	}

	public List<String> returnProductPricesList() {
		List<String> productPriceList = new ArrayList<>();
		for (WebElement product : listOfProductPrices) {
			String eachProductPriceText = product.getText();
			String[] actualPriceArr = eachProductPriceText.split("\n");
			productPriceList.add(actualPriceArr[0]);
		}
		return productPriceList;

	}

	public String returnProductFoundTextValue() {
		return productFoundText.getText();
	}

	public List<String> returnProductInstallemetList() {
		List<String> productInstallementList = new ArrayList<>();
		for (WebElement product : listOfProductPrices) {
			String eachProductPriceText = product.getText();
			String[] actualPriceArr = eachProductPriceText.split("\n");
			System.out.println(Arrays.toString(actualPriceArr));
			try {
				String[] actualInstallmentArr = actualPriceArr[1].split("or ");
				System.out.println(Arrays.toString(actualInstallmentArr));

				System.out.println(actualInstallmentArr[1]);
				productInstallementList.add(actualInstallmentArr[1].trim());
			} catch (ArrayIndexOutOfBoundsException e) {
				productInstallementList.add(" ");
			}
		}
		System.out.println(productInstallementList);
		return productInstallementList;

	}

	public void selectProductSize(String sizeType) {
		for (WebElement size : availableSizeButton) {
			if (size.getText().equals(sizeType)) {
				size.click();
			}
		}

	}

	public void unSelectProductSize(String sizeType) {
		for (WebElement size : availableSizeButton) {
			if (size.getText().equals(sizeType)) {
				size.click();
			}

		}
	}

	public void addProductToCart(String productName, String quantity) {
		int productCount = 1;
		for (WebElement productTitle : listOfProductTitles) {
			if (productTitle.getText().equals(productName)) {
				while (productCount <= Integer.valueOf(quantity)) {
					productTitle.click();
					productCount++;
				}
			}
		}
	}

	public void clickCheckoutButton() {
		checkOutButton.click();
	}

	public String returnTotalCartPriceValue() {
		return totalCartValue.getText();
	}

	public String returnSubcartValueFromAlertPopup() {
		Alert alert = driver.switchTo().alert();
		return alert.getText();
	}

	public void deleteCartItem() {
		deleteCartItem.click();
	}
}
