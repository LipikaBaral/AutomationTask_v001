package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class MyAccount extends TestBase {

	@FindBy(xpath = "//a[@class='logout']")
	private WebElement signoutLink;

	@FindBy(xpath = " //a[@class='account']")
	private WebElement accName;

	public MyAccount() {
		PageFactory.initElements(driver, this);
	}

	public String verifyMyAccountPageTitle() {
		TestUtil.waitForTitlePresent("My account - My Store");
		return driver.getTitle();
	}

	public String verifyNameLink(String Name) {
		TestUtil.waitForElementPresent(accName);
		return accName.getText();
	}

	public boolean verifySignoutLink() {
		TestUtil.waitForElementPresent(signoutLink);
		return signoutLink.isDisplayed();
	}

}
