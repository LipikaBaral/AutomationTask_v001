package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.Base.TestBase;
import com.qa.Util.TestUtil;

public class AccountPage extends TestBase {

	@FindBy(xpath = "//input[@id='email_create']")
	private WebElement signupId;

	@FindBy(xpath = "//button[@id='SubmitCreate']")
	private WebElement createAccntBtn;

	@FindBy(xpath = "//div[@id='create_account_error']")
	private WebElement errorMsg;

	@FindBy(xpath = "//input[@id='email']")
	private WebElement loginId;

	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement password;

	@FindBy(xpath = "//button[@id='SubmitLogin']")
	private WebElement signInBtn;

	public AccountPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyAccountPageTitle() {
		TestUtil.waitForTitlePresent("Login - My Store");
		return driver.getTitle();
	}

	public RegdPage createAccount() {
		signupId.sendKeys(prop.getProperty("emailid"));
		createAccntBtn.click();
		return new RegdPage();

	}

	public MyAccount loginApp(String user, String pwd) {
		loginId.sendKeys(user);
		password.sendKeys(pwd);
		signInBtn.click();
		return new MyAccount();

	}

}
