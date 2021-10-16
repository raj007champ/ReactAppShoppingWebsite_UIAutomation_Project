package com.ziegertEverEstate.qa.utill;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.ziegertEverEstate.qa.base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 30;
	public static long IMPLICIT_WAIT = 10;
	public static String currentDir = System.getProperty("user.dir");
	public static String TESTDATA_SHEET_PATH = currentDir + "\\src\\main\\java\\com\\ziegertEverEstate\\qa\\testData\\TestData.xlsx";
	static Workbook book;
	static Sheet sheet;

	/*
	 * Uitiliy method to switch to frame using frame WebElement
	 */
	public void switchToFrame(WebElement frameElement) {
		driver.switchTo().frame(frameElement);
	}

	/*
	 * Uitiliy method to switch to frame using frame Name
	 */

	public void switchToFrame(String frameName) {
		driver.switchTo().frame(frameName);
	}

	/*
	 * Uitiliy method to switch to frame using frame ID
	 */

	public void switchToFrame(int ID) {
		driver.switchTo().frame(ID);
	}

	private CellStyle formatDecimalStyle(Workbook workbook, CreationHelper createHelper) {  
	    CellStyle style = workbook.createCellStyle();
	    style.setDataFormat(createHelper.createDataFormat().getFormat("0"));
	    return style;   
	}
	/*
	 * Utility method to read the data from Excel sheet.
	 */
	public static Object[][] getTestData(String sheetName)
			throws EncryptedDocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
			}
		}
		return data;
	}

	/*
	 * Utility Method to take screenshot at the end of the test
	 */

	public static void takeScreenshotAtEndOfTest() throws IOException {

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));

	}

	/*
	 * Uitility Method to Take screenshot with timestamp to avoid overwrite.
	 */

	public static String getScreenshot(String screenshotName) throws Exception {

		// below line is just to append the date format with the screenshot name to
		// avoid duplicate names

		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);

		// after execution, you could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		// Returns the captured file path
		return destination;
	}

}
