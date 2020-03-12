package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class ShoppingPage extends TestBase {

	@FindBy(linkText = "WOMEN")
	private WebElement womenLink;

	@FindBy(xpath = "(//a[contains(text(),'T-shirts')])[1]")
	private WebElement tShirtSubMenuLink;

	@FindBy(xpath = "//img[@title='Faded Short Sleeve T-shirts']")
	private WebElement tshirtLabel;

	@FindBy(xpath = "//span[contains(text(),'Add to cart')]")
	private WebElement addToCartLink;

	@FindBy(xpath = "//span[contains(text(),'Proceed to checkout')]")
	private WebElement checkoutLink;

	public ShoppingPage() {
		PageFactory.initElements(driver, this);
	}

	public ProceedToCheckoutPage selectAndCheckoutItem() throws Exception {

		Actions action = new Actions(driver);
		action.moveToElement(womenLink).build().perform();
		Thread.sleep(2000);
		tShirtSubMenuLink.click();
		Thread.sleep(3000);
		TestUtil.scrollToAnElement(tshirtLabel);
		Thread.sleep(2000);
		action.moveToElement(tshirtLabel).build().perform();
		addToCartLink.click();
		checkoutLink.click();

		return new ProceedToCheckoutPage();

	}
//	public void proceedToCheckout() {
//		checkoutLink.click();
//	}

}
