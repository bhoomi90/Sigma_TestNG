package pageFactory;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class LoginPage {

	@FindBy(id = ("id_username"))
	private WebElement userName;
	@FindBy(id = ("id_password"))
	private WebElement passWord;
	@FindBy(xpath = ("//*[@type='submit']"))
	private WebElement login;
	@FindBy(xpath = ("//a[text()='Sign out']"))
	private WebElement signOut;
	@FindBy(xpath = ("//*[@role='alert']"))
	private WebElement alertMsg;

	String filePath = ConfigReader.getPropertyValue("EXCELPATH");
	String loginText, logoutText, errorText;

	public LoginPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Login Page");
	}

	public void loginClick() {
		login.click();
	}

	public void setUsername(String username) {
		userName.sendKeys(username);
	}

	public void setPassword(String password) {
		passWord.sendKeys(password);
	}

	public void loginToApp() {
		String sheetName = "Login";
		String validationType = "ValidCredential";
		String validusername = null;
		String validpassword = null;
		List<Map<String, String>> testData;
		ExcelReader reader = new ExcelReader(filePath);
		testData = reader.getDataAll(sheetName);

		for (Map<String, String> row : testData) {
			String validationTestData = row.get("validation");

			if (validationType.equalsIgnoreCase(validationTestData)) {
				validusername = row.get("username");
				validpassword = row.get("password");
				break;
			}
		}

		LoggerLoad.info("Let's login to DsPortal App with valid credentials");
		userName.sendKeys(validusername);
		passWord.sendKeys(validpassword);
		login.click();
	}

	public String compareLogoutMsg() {
		CommonMethods.waitForElementTobeClick(alertMsg);
		logoutText = alertMsg.getText();
		return logoutText;
	}

	public String compareLoginMsg() {
		CommonMethods.waitForElementTobeClick(alertMsg);
		loginText = alertMsg.getText();
		return loginText;
	}
	
	public String readErrorText() {
		CommonMethods.waitForElementTobeClick(alertMsg);
		errorText = alertMsg.getText();
		return errorText;
	}

	public void enterfields(String usernameInput, String passwordInput) {
		userName.sendKeys(usernameInput);
		passWord.sendKeys(passwordInput);
		login.click();
	}
	
	public String getEmptyUserNameAlertMsg() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		String validationMsg = (String) js.executeScript ("return arguments[0].validationMessage;", userName);
		return 	validationMsg;
	}
	
	public String getEmptyPasswordAlertMsg() {
		JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		String validationMsg = (String) js.executeScript( "return arguments[0].validationMessage;", passWord);
		return 	validationMsg;
	}
	
	public void clickSignOut() {
		signOut.click();
	}

}
