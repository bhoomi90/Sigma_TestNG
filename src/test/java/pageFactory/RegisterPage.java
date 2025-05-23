package pageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class RegisterPage {

	@FindBy(xpath = ("//*[text()='Get Started']"))
	WebElement getStarted;
	@FindBy(xpath = ("//a[text()=' Register ']"))
	WebElement registerInitial;
	@FindBy(id = "id_username")
	WebElement username;
	@FindBy(id = "id_password1")
	WebElement password;
	@FindBy(id = "id_password2")
	WebElement passwordConfirmation;
	@FindBy(xpath = ("//input[@type='submit' and @value='Register']"))
	WebElement registerBttn;
	@FindBy(xpath = ("//*[@role='alert']"))
	WebElement alertMsg;

	String errorText;

	public RegisterPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Register Page");
	}

	public void getStarted() {
		getStarted.click();
	}

	public void register_initial() {
		registerInitial.click();
		LoggerLoad.info("Clicking Register button");
	}

	public void enterfields(String usernameInput, String passwordInput, String passwordConfirm) {
		username.sendKeys(usernameInput);
		password.sendKeys(passwordInput);
		passwordConfirmation.sendKeys(passwordConfirm);
		registerBttn.click();
	}

	public String getEmptyUsernameAlertMsg() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		String validationMsg = (String) js.executeScript("return arguments[0].validationMessage;", username);
		return validationMsg;
	}

	public String getEmptyPasswordAlertMsg() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		String validationMsg = (String) js.executeScript("return arguments[0].validationMessage;", password);
		return validationMsg;
	}

	public String getEmptyConfirmPasswordAlertMsg() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		String validationMsg = (String) js.executeScript("return arguments[0].validationMessage;", passwordConfirmation);
		return validationMsg;
	}

	public String readErrorText() {
		CommonMethods.waitForElementTobeClick(alertMsg);
		errorText = alertMsg.getText();
		return errorText;
	}

}
