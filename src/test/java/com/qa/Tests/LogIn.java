package com.qa.Tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.Assert;

import com.qa.Base.TestBase;
import com.qa.Pages.AccountPage;
import com.qa.Pages.HomePage;
import com.qa.Pages.MyAccount;
import com.qa.Pages.RegdPage;

//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;

public class LogIn extends TestBase {

	HomePage homePage;
	AccountPage accountPage;
	RegdPage regdPage;
	MyAccount myAccount;

	public LogIn() {
		super();
	}

	// @BeforeTest
	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setUp() {
		initialization();
		homePage = new HomePage();
		// accountPage = new AccountPage();
		myAccount = new MyAccount();
	}

	@Test(priority = 1)
	// @Severity(SeverityLevel.NORMAL)
	public void verifyHomePageTitleTest() {

		String homePageTitle = homePage.verifyHomePageTitle();
		System.out.println(homePageTitle);
		Assert.assertEquals(homePageTitle, "My Store");
	}

//	@Test(priority = 2)
//	public void clickSignInTest() {
//		accountPage = homePage.clickSignIn();
//	}

	@Test(priority = 2)
	public void loginAppTest() throws Exception {
		accountPage = homePage.clickSignIn();
		myAccount = accountPage.loginApp(prop.getProperty("emailid"), prop.getProperty("password"));
		Thread.sleep(3000);
		String myAccPageTitle = myAccount.verifyMyAccountPageTitle();
		Assert.assertEquals(myAccPageTitle, "My account - My Store");
		System.out.println("My account page title is verified after login...");
		Assert.assertTrue(myAccount.verifySignoutLink());
		System.out.println("Signout link is verified after login...");
	}

//	@Test(priority = 3)
//	public void verifyMyAccountPageTitleTest_Login() {
//		String myAccPageTitle = myAccount.verifyMyAccountPageTitle();
//		Assert.assertEquals(myAccPageTitle, "My account - My Store");
//	}

//	@Test(priority = 4)
//	public void verifySignOutLinkTest() {
//		Assert.assertTrue(myAccount.verifySignoutLink());
//		// String nameLinkText = myAccount.verifyNameLink(Name);
//
//	}
	// @AfterTest
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
