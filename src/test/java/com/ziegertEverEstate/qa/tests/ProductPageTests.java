/*
 * @author- Shishu Raj Pandey
 * 
 */

package com.ziegertEverEstate.qa.tests;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.ziegertEverEstate.qa.base.TestBase;
import com.ziegertEverEstate.qa.pages.ProductPage;
import com.ziegertEverEstate.qa.utill.Log;
import com.ziegertEverEstate.qa.utill.TestUtil;

public class ProductPageTests extends TestBase {

	ProductPage productPage;
	String productSizeDataSheet = "sizeProductData";
	String productsAndQuantityDataSheet = "productsAndQuantityData";

	public ProductPageTests() {
		super();
	}

	@BeforeMethod
	public void setup() {
		Log.info("Opening the Browser and Navigating to demo Shop application base URL");
		intialisation();
		productPage=new ProductPage();
	}

	@Test(description = "Verify that user is on Demo Shop application Landing Page")
	public void verify_User_Is_On_LandingPage()
	{
		Log.info("Verify that user is on Demo Shop application Landing Page");
		Assert.assertEquals(productPage.getPageTitle(), "React Shopping Cart");
		
		Log.info("Verify that user is on correct URL");
		Assert.assertEquals(productPage.getPageURL(), "https://react-shopping-cart-67954.firebaseapp.com/");
	}
	
	@Test(description = "Verify that user is able to see all 16 products on the page")
	public void verify_user_can_see_all_Products()
	{
		Log.info("Verify that user is able to see all 16 products on the page");
		List<String> productTitleList=productPage.returnProductTitleList();
		Assert.assertEquals(String.valueOf(productTitleList.size()), "16");
	}
	
	@Test(description = "Verify that user is able filter Products as per the size", dataProvider = "getProductSizeData")
	public void verify_user_is_able_to_filter_Products_by_size(String sizeType, String noOfProducts) throws InterruptedException
	{
		Log.info("Verify that user is able filter Products as per the size");
		productPage.selectProductSize(sizeType);
		Thread.sleep(2000);
		List<String> productTitleList=productPage.returnProductTitleList();
		Log.info("Verify that user is getting correct no of products after filtering with size"+sizeType);
		Assert.assertEquals(String.valueOf(productTitleList.size()), noOfProducts);
		productPage.unSelectProductSize(sizeType);
	}
		
	
	@DataProvider
	public Object[][] getProductSizeData() throws EncryptedDocumentException, InvalidFormatException {
		Object[][] signUpData = TestUtil.getTestData(productSizeDataSheet);
		return signUpData;
	}
	
	@Test(description = "Verify that user is able to sort Product from Lowest To Highest")
	public void verify_user_is_able_to_sort_Products_from_lowest_to_highest() throws InterruptedException
	{
		Log.info("Verify that user is able to sort Product from Lowest To Highest");
		
		productPage.orderByLowestToHighest();
		Thread.sleep(2000);
		
		List<String> productPriceList= productPage.returnProductPricesList();
		List<String> OriginalPriceList=productPriceList;		
		Comparator comp=new Comparator() {
			
		        public int compare(Object o1, Object o2) 
		        {
				String o3=((String) o1).replace("$", "");
				String o4=((String) o2).replace("$", "");
				if(Double.valueOf(o3)>Double.valueOf(o4))
				{
					return 1;
				}
				return -1;
		        }
		};
		
		Log.info("Sorting thr Original Product Price list using Java Comparator method");
		Collections.sort(productPriceList, comp);
		
		Log.info("Verify that Original Product Price List is equals to Sorted Product price List");
		Assert.assertTrue(OriginalPriceList.equals(productPriceList));
		
		
	}
	
	@Test(description = "Verify that user is able to sort Product from Highest To Lowest")
	public void verify_user_is_able_to_sort_Products_from_highest_to_lowest() throws InterruptedException
	{
		Log.info("Verify that user is able to sort Product from Highest To Lowest");
		productPage.orderByHighestToLowest();
		Thread.sleep(2000);
		List<String> productPriceList= productPage.returnProductPricesList();
		List<String> OriginalPriceList=productPriceList;		
		Comparator comp=new Comparator() {
			
	        public int compare(Object o1, Object o2) 
	        {
			String o3=((String) o1).replace("$", "");
			String o4=((String) o2).replace("$", "");
			if(Double.valueOf(o3)>Double.valueOf(o4))
			{
				return 1;
			}
			return -1;
	        }
	};
		
		Log.info("Sorting thr Original Product Price list using Java Comparator method");
		Collections.sort(productPriceList, comp);
		
		Log.info("Verify that Original Product Price List is equals to Sorted Product price List");
		Assert.assertTrue(OriginalPriceList.equals(productPriceList));
		
	}
	
	@Test(description = "Verify that user is able to add products in Cart as per given Quantity", dataProvider = "getproductsAndQuantityData")
	public void verify_user_is_able_to_add_Products_in_cart(String productName, String quantity, String subTotalCartValue) throws InterruptedException
	{
		Log.info("Verify that user is able to add products in Cart as per given Quantity");
		productPage.addProductToCart(productName,quantity);
		Log.info("Verify that Total Cart Value is Equal to Expected Sub Total Cart Value");
		Assert.assertEquals(productPage.returnTotalCartPriceValue(), subTotalCartValue);
	}
	
	@DataProvider
	public Object[][] getproductsAndQuantityData() throws EncryptedDocumentException, InvalidFormatException {
		Object[][] signUpData = TestUtil.getTestData(productsAndQuantityDataSheet);
		return signUpData;
	}
	
	@Test(description = "Verify that user is able to Check out Products", dataProvider = "getproductsAndQuantityData")
	public void verify_user_is_able_to_check_out_products(String productName, String quantity, String subTotalCartValue) throws InterruptedException
	{
		Log.info("Verify that user is able to add products in Cart as per given Quantity");
		productPage.addProductToCart(productName,quantity);
		Log.info("Click on Checkout Product Button");
		productPage.clickCheckoutButton();
		String checkOutAlertText=productPage.returnSubcartValueFromAlertPopup();
		Log.info("Verify the Alert Popup text");
		Assert.assertEquals(checkOutAlertText, "Checkout - Subtotal: "+subTotalCartValue);
	}
	

	@Test(description = "Verify that user is able to remove products from Cart", dataProvider = "getproductsAndQuantityData")
	public void verify_user_is_able_to_remove_product_from_cart(String productName, String quantity, String subTotalCartValue) throws InterruptedException
	{
		Log.info("Verify that user is able to remove products from Cart");
		productPage.addProductToCart(productName,quantity);
		Assert.assertEquals(productPage.returnTotalCartPriceValue(), subTotalCartValue);
		productPage.deleteCartItem();
		Log.info("Verify that after removing product from cart, total cart value is $ 0.00");
		Assert.assertEquals(productPage.returnTotalCartPriceValue(),"$ 0.00");
	}
	
	
	@AfterMethod
	public void tearDown() {
		Log.info("Closing all the opened browser");
		driver.quit();
	}
}

