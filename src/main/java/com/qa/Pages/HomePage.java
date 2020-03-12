package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class HomePage extends TestBase {

	@FindBy(xpath = "//a[@class='login']")
	private WebElement signinBtn;

	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyHomePageTitle() {
		TestUtil.waitForTitlePresent("My Store");
		return driver.getTitle();

	}

	public AccountPage clickSignIn() {
		signinBtn.click();
		return new AccountPage();
	}

}
