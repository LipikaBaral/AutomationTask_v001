package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class PaymentPage extends TestBase {

	@FindBy(xpath = "//a[@class='bankwire']")
	private WebElement bankWireLink;

	@FindBy(xpath = "//span[contains(text(),'Payment')]")
	private WebElement paymentTab;

	@FindBy(xpath = "//span[contains(text(),'I confirm my order')]")
	private WebElement confirmOrderLink;

	@FindBy(xpath = "//h1[contains(text(),'Order confirmation')]")
	private WebElement orderConfirmLabel;

	public PaymentPage() {
		PageFactory.initElements(driver, this);
	}

	public boolean verifyPaymentTab() {
		return paymentTab.isDisplayed();
	}

	public void clickOnBankWireLink() {
		TestUtil.scrollToAnElement(bankWireLink);
		bankWireLink.click();
	}

	public void clickConfirmOrderLink() {
		confirmOrderLink.click();
	}

	public String verifyOrderConfirmPageTitle() {
		return driver.getTitle();
	}
	public boolean verifyOrderConfirmPage() {
		return orderConfirmLabel.isDisplayed();
	}

	public boolean verifyOrderCompletion() {
		return driver.getPageSource().contains("Your order on My Store is complete.");

	}
}
