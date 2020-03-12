package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class ProceedToCheckoutPage extends TestBase {

	@FindBy(xpath = "//a[@class='button btn btn-default standard-checkout button-medium']")
	private WebElement proceedToCheckout_summary;

	@FindBy(xpath = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout_address;

	@FindBy(xpath = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
	private WebElement proceedToCheckout_shipping;

	@FindBy(xpath = "//input[@id='cgv']")
	private WebElement serviceCheckbox;

	public ProceedToCheckoutPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyShoppingCartSummaryTitle() {
		return driver.getTitle();
	}

	public void summaryTabProceedCheckoutLink() {
		TestUtil.scrollToAnElement(proceedToCheckout_summary);
		proceedToCheckout_summary.click();

	}

	public void addressTabProceedCheckoutLink() {
		TestUtil.scrollToAnElement(proceedToCheckout_address);
		proceedToCheckout_address.click();

	}

	public PaymentPage shippingTabProceedCheckoutLink() {
		TestUtil.scrollToAnElement(proceedToCheckout_shipping);
		serviceCheckbox.click();
		proceedToCheckout_shipping.click();
		return new PaymentPage();
	}

}
