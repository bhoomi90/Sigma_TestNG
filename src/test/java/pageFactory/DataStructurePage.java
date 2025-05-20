package pageFactory;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import utilities.CommonMethods;
import utilities.ConfigReader;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class DataStructurePage {

	/*private WebDriver driver = null;
	
	public String homePageURL = ConfigReader.getPropertyValue("URL");
	public String dsPageURL = homePageURL+"data-structures-introduction/";

	@FindBy(xpath = ("//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']")) private WebElement DataStrucIntro;
	@FindBy(xpath = ("//a[text()='Time Complexity']")) private WebElement timecomplex;
	
	@FindBy (xpath = ("//a[text()='Try here>>>']")) private WebElement tryHereClick;
	@FindBy (xpath=("//*[text()='Run']")) private WebElement runBttn;
	@FindBy (xpath=("//textarea[@spellcheck='false']")) private WebElement enterCode;
	@FindBy(id = "output") private WebElement output;	
	
	@FindBy (xpath = ("//a[text()='Practice Questions']")) private WebElement practiceQue;	
	@FindBy (className = ("container")) private WebElement pageContent;
	@FindBy (xpath=("//a[text()='Sign out']")) WebElement signOut;
	@FindBy (xpath=("//*[@role='alert']")) WebElement alertMsg;
	
	
	String loginText, logoutText;
	
	public DataStructurePage() {
		this.driver = DriverFactory.getdriver();
		PageFactory.initElements(driver, this);
		LoggerLoad.info("Initialized DataStructure Page");
	}	

	public void DataStruc_page() {
		DataStrucIntro.click();
		LoggerLoad.info("I am on DataStructure-Introduction Page");
	}
	public void TimecomplexInDataStruc_page() {
		timecomplex.click();
		LoggerLoad.info("Directed to Time Complexity in Datastructure page");
	}
	public void clickTryHere() {
		tryHereClick.click();
		LoggerLoad.info("I am on tryEditor window to try Python code");
	}	
	public void emptyCode(String emptyCode, String expectedResults) {
		enterCode.sendKeys(emptyCode);
		runBttn.click();			
	}	
	public void validCode(String validCode, String expectedResults) {
		CommonMethods.refreshPage();
		enterCode.sendKeys(validCode);
		runBttn.click();
		if(isOutputSuccess()) {
			assertTrue(isOutputSuccess(), "Success output not shown as expected: " +expectedResults);
			LoggerLoad.info("Output is successfully displayed");
		}
		else {
			assertTrue(false, "Test failed: No alert appeared and no output was displayed. Expected: " + expectedResults);
        	LoggerLoad.error("No output displayed, expected: "+ expectedResults);
		}
	}	
	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}
	public void invalidCode(String invalidCode, String expectedResults) {
		CommonMethods.refreshPage();
		enterCode.sendKeys(invalidCode);
		runBttn.click();
		
		String actualMsg = CommonMethods.getAlertText(driver);
		if(actualMsg==null) {
			LoggerLoad.error("Expected to receive Alert after invalid python code");
		}
		else {
			assertTrue(actualMsg.contains(expectedResults), "Expected Alert message to contain" +expectedResults+ "but got" +actualMsg);
			LoggerLoad.info("Alert message received: "+ actualMsg);
		}
	}
	
	public void clickPracticeQueLink() {
		practiceQue.click();
		LoggerLoad.info("I am on practice questions page");
	}

	public boolean checkPracticeQueContent() {
		LoggerLoad.info("Page content is: " +pageContent.getText());
		if(pageContent.getText().length()==0)
			return false;
		else
			return true;
	}
	public void clickSignOut() {
		signOut.click();
	}
	public void compareLogoutMsg() {
		logoutText = alertMsg.getText();
		Assert.assertEquals(logoutText, "Logged out successfully");
		LoggerLoad.info("User is logged out");
	}
	
	public void compareLoginMsg() {
		loginText = alertMsg.getText();
		Assert.assertEquals(loginText, "You are logged in");
		LoggerLoad.info("User is logged in");
	}*/

}