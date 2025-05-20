package pageFactory;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;
import utilities.ConfigReader;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class LoginPage {
	
	@FindBy (id=("id_username")) private WebElement userName;
	@FindBy (id=("id_password")) private WebElement passWord;
	@FindBy (xpath=("//*[@type='submit']")) private WebElement login;
	@FindBy (xpath=("//a[text()='Sign out']")) private WebElement signOut;
	@FindBy (xpath=("//*[@role='alert']")) private WebElement alertMsg;
	
	String filePath = ConfigReader.getPropertyValue("EXCELPATH");
	String loginText, logoutText;
	
	public LoginPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Login Page");
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
	
	public void clickSignOut() {
		signOut.click();
	}
}
