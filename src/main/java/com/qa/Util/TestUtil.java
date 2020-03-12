package com.qa.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.utils.FileUtil;
import com.qa.Base.TestBase;

public class TestUtil extends TestBase {

	public static long PAGE_LOAD_TIMEOUT = 20;
	
	static WebDriverWait wait;

	
	//*********************************Get testdata from Excel****************************************
	
	static String EXCEL_PATH = System.getProperty("user.dir")
			+ "/src/main/java/com/qa/Testdata/AutomationTask_TestData.xlsx";
	static FileInputStream fis;
	static XSSFWorkbook workbook;
	
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		try {
			fis = new FileInputStream(EXCEL_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		 try {
			workbook = new XSSFWorkbook(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 XSSFSheet sheet = workbook.getSheet(sheetName);
		 int totalRow = sheet.getLastRowNum();
		 int totalCol = sheet.getRow(0).getLastCellNum();
		 
		 Object testdata[][] = new Object[totalRow][totalCol];
		 
		 for(int row =0;row<totalRow;row++) {
			 XSSFRow currentRow = sheet.getRow(row+1);
			 
			 for(int col =0;col<totalCol;col++) {
				 testdata[row][col] = currentRow.getCell(col).toString();
			 }
		 }
		 
		 return testdata;
	}
	
	//***********************************Scroll to view an element********************************************
	public static void scrollToAnElement(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true);",element);
	}
	
	//***********************************Take Screenshot*******************************************************
	public static String takeScreenshot() {
		
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String destPath = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		
		File destFile = new File(destPath);
		try {
			FileUtils.copyFile(srcFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("screenshot captured failed...");
		}
		
		return destPath;
		
	}
	//***********************************Explicit waits**********************************************************
	
	public static boolean waitForTitlePresent(String title) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.titleIs(title));
		return true;
	}
	
	public static boolean waitForElementPresent(WebElement element) {
		new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(element));
		return true;
	}
}
