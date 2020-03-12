package com.qa.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.Base.TestBase;

public class RegdPage extends TestBase {

	@FindBy(xpath = "//input[@id='id_gender1']")
	private WebElement radioBtn1;

	@FindBy(xpath = "//input[@id='id_gender2']")
	private WebElement radioBtn2;

	@FindBy(xpath = "//input[@id='customer_firstname']")
	private WebElement firstName1;

	@FindBy(xpath = "//input[@id='customer_lastname']")
	private WebElement lastName1;

	@FindBy(xpath = "//input[@id='passwd']")
	private WebElement password;

	@FindBy(xpath = "//input[@id='firstname']")
	private WebElement firstName2;

	@FindBy(xpath = "//input[@id='lastname']")
	private WebElement lastName2;

	@FindBy(xpath = "//input[@id='address1']")
	private WebElement streetAddress;

	@FindBy(xpath = "//input[@id='city']")
	private WebElement city;

	@FindBy(xpath = "//input[@id='postcode']")
	private WebElement zipCode;

	@FindBy(xpath = "//input[@id='phone_mobile']")
	private WebElement contactNo;

	@FindBy(xpath = "//input[@id='alias']")
	private WebElement adddressAlias;

	@FindBy(xpath = "//select[@id='id_state']")
	private WebElement state;

	@FindBy(xpath = "//button[@id='submitAccount']")
	private WebElement registerBtn;

	public RegdPage() {
		PageFactory.initElements(driver, this);
	}

	public String verifyCreateAccountPageTitle() {
		// TestUtil.waitForTitlePresent("Login - My Store");
		return driver.getTitle();
	}

	public MyAccount createNewAccount(String Title, String FName, String LName, String Address, String CityName,
			String StateName, String Zip, String Contact, String Alias) {
		// Title radio button
		if (Title.equals("Mr")) {
			radioBtn1.click();
		} else if (Title.equals("Mrs")) {
			radioBtn2.click();
		}
		// First,Last Names
		firstName1.sendKeys(FName);
		lastName1.sendKeys(LName);

		// Password
		password.sendKeys(prop.getProperty("password"));

		// Address details
		// firstName2.sendKeys(FName);
		// lastName2.sendKeys(LName);
		streetAddress.sendKeys(Address);
		city.sendKeys(CityName);

		Select select = new Select(state);
		select.selectByVisibleText(StateName);

		zipCode.sendKeys(Zip);
		contactNo.sendKeys(Contact);
		adddressAlias.sendKeys(Alias);

		registerBtn.click();

		return new MyAccount();

	}

}
