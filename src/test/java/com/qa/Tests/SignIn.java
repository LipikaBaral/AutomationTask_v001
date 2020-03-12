package com.qa.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import com.qa.Base.TestBase;
import com.qa.Pages.AccountPage;
import com.qa.Pages.HomePage;
import com.qa.Pages.MyAccount;
import com.qa.Pages.RegdPage;
import com.qa.Util.TestUtil;

//import io.qameta.allure.Description;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import io.qameta.allure.Story;

public class SignIn extends TestBase {

	HomePage homePage;
	AccountPage accountPage;
	RegdPage regdPage;
	MyAccount myAccount;

	public SignIn() {
		super();
	}

	// @BeforeTest
	@BeforeMethod
	public void setUp() {
		initialization();
		homePage = new HomePage();
	}

	// @Severity(SeverityLevel.NORMAL)
	// @Description("Test case description : Verify Home Page Title")
	// @Story("Story Name : Check home page title")
	@Test(priority = 1)
	public void verifyHomePageTitleTest() {

		String homePageTitle = homePage.verifyHomePageTitle();
		System.out.println(homePageTitle);
		log.info("URL is opened and the home page title is " + homePageTitle);
		Assert.assertEquals(homePageTitle, "My Store");
		log.info("Homepage title is verified");
	}

//	@Test(priority = 2)
//	//@Severity(SeverityLevel.BLOCKER)
//	//@Description("Test case description : Click on Sign In link")
//	//@Story("Story Name : Click on the sign in link")
//	public void clickSignInTest() {
//		accountPage = homePage.clickSignIn();
//	}

	// @Severity(SeverityLevel.NORMAL)
	// @Description("Test case description : Verify Home Page Title")
	// @Story("Story Name : Check home page title")

	@Test(priority = 2)
	public void verifyAccountPageTitleTest() {
		accountPage = homePage.clickSignIn();
		String accountPageTitle = accountPage.verifyAccountPageTitle();
		Assert.assertEquals(accountPageTitle, "Login - My Store");
	}

//	@Severity(SeverityLevel.NORMAL)
//	@Description("Test case description : Verify Home Page Title")
//	@Story("Story Name : Check home page title")
	@Test(priority = 3)
	public void createAccountTest() {
		accountPage = homePage.clickSignIn();
		regdPage = accountPage.createAccount();
		String regdPageTitle = regdPage.verifyCreateAccountPageTitle();
		Assert.assertEquals(regdPageTitle, "Login - My Store");

	}

	@DataProvider
	public Object[][] getTestData() {
		Object data[][] = TestUtil.getTestDataFromExcel("RegdPage");
		return data;
	}

//	@Severity(SeverityLevel.NORMAL)
//	@Description("Test case description : Verify Home Page Title")
//	@Story("Story Name : Check home page title")
	@Test(priority = 4, dataProvider = "getTestData")
	public void createNewAccountTest(String Title, String FName, String LName, String Address, String CityName,
			String StateName, String Zip, String Contact, String Alias) {

		accountPage = homePage.clickSignIn();
		regdPage = accountPage.createAccount();
		myAccount = regdPage.createNewAccount(Title, FName, LName, Address, CityName, StateName, Zip, Contact, Alias);
		log.info("A new user is registered...");
		String fullName = FName + " " + LName;
		System.out.println(fullName);
		boolean flag = driver.findElement(By.xpath("//span[contains(text(),fullName)]")).isDisplayed();
		System.out.println("User name displayed is : " + fullName);
		Assert.assertTrue(flag);
		String myAccTitle = myAccount.verifyMyAccountPageTitle();
		Assert.assertEquals(myAccTitle, "My account - My Store");
		System.out.println("My account page title is verified..");
		Assert.assertTrue(myAccount.verifySignoutLink());
		System.out.println("Signout link is present..");

	}
//	@Test(priority = 5)
//	public void verifyMyAccountPageTitleTest() {
//		String myAccTitle = myAccount.verifyMyAccountPageTitle();
//		Assert.assertEquals(myAccTitle, "My account - My Store");
//	}

//	@Test(priority = 6)
//	public void verifySignoutLinkTest() {
//		Assert.assertTrue(myAccount.verifySignoutLink());
//	}

	// @AfterTest
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
