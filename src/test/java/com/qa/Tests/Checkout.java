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
import com.qa.Pages.PaymentPage;
import com.qa.Pages.ProceedToCheckoutPage;
import com.qa.Pages.RegdPage;
import com.qa.Pages.ShoppingPage;

public class Checkout extends TestBase {

	HomePage homePage;
	AccountPage accountPage;
	RegdPage regdPage;
	MyAccount myAccount;
	ShoppingPage shoppingPage;
	ProceedToCheckoutPage checkoutPage;
	PaymentPage paymentPage;

	public Checkout() {
		super();
	}

	
	//@BeforeTest
	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setUp() {
		initialization();
		homePage = new HomePage();
		shoppingPage = new ShoppingPage();
		accountPage = homePage.clickSignIn();
		accountPage.loginApp(prop.getProperty("emailid"), prop.getProperty("password"));
	}

//	@Test(priority = 1)
//	public void selectAndCheckoutTest() throws Exception {
//		Thread.sleep(3000);
//		checkoutPage = shoppingPage.selectAndCheckoutItem();
//
//	}

	@Test(priority = 1)
	public void proceedToCheckoutTest() {
		try {
			checkoutPage = shoppingPage.selectAndCheckoutItem();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String summaryTitle = checkoutPage.verifyShoppingCartSummaryTitle();
		Assert.assertEquals(summaryTitle, "Order - My Store");

		checkoutPage.summaryTabProceedCheckoutLink();
		checkoutPage.addressTabProceedCheckoutLink();
		paymentPage = checkoutPage.shippingTabProceedCheckoutLink();
	}

	@Test(priority = 2)
	public void paymentTest() {
		try {
			checkoutPage = shoppingPage.selectAndCheckoutItem();
		} catch (Exception e) {
			e.printStackTrace();
		}
		checkoutPage.summaryTabProceedCheckoutLink();
		checkoutPage.addressTabProceedCheckoutLink();
		paymentPage = checkoutPage.shippingTabProceedCheckoutLink();
		Assert.assertTrue(paymentPage.verifyPaymentTab());
		paymentPage.clickOnBankWireLink();
		paymentPage.clickConfirmOrderLink();	
		Assert.assertTrue(paymentPage.verifyOrderCompletion());
		String orderConfirmPageTitle = paymentPage.verifyOrderConfirmPageTitle();
		Assert.assertEquals(orderConfirmPageTitle, "Order confirmation - My Store");
		System.out.println("Order Confirmation page title is verified..");
		Assert.assertTrue(paymentPage.verifyOrderConfirmPage());
		System.out.println("Order confirmation text is present...");
		Assert.assertTrue(paymentPage.verifyOrderCompletion());
		System.out.println("This is the last page of the Order...");

	}
	
//	@Test(priority = 3)
//	public void verifyOrderConfirmPageTitleTest() {
//		
//		String orderConfirmPageTitle = paymentPage.verifyOrderConfirmPageTitle();
//		Assert.assertEquals(orderConfirmPageTitle, "Order confirmation - My Store");
//		Assert.assertTrue(paymentPage.verifyOrderConfirmPage());
//		
//	}
	
//	@Test(priority = 4)
//	public void verifyLastPageOfOrderTest() {
//		Assert.assertTrue(paymentPage.verifyOrderCompletion());
//	}
	//@AfterTest
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
