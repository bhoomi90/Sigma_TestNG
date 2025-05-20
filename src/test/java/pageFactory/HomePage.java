package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class HomePage {
	
	private WebDriver driver = null;
	
	@FindBy (xpath=("//*[text()='Get Started']")) WebElement getStarted;
	@FindBy (xpath=("//*[text()='Sign in']")) WebElement signIn ;

	public HomePage() {
		this.driver = DriverFactory.getdriver();
		PageFactory.initElements(driver, this);
		LoggerLoad.info("Initialized Home Page");
	}
	
	public void clickgetStarted() {
		LoggerLoad.info("Clicking get started button in Home page");
		getStarted.click();
	}
	public void clickSignIn() {
		LoggerLoad.info("Clicking signIn button in Home page");
		signIn.click();
	}
}
