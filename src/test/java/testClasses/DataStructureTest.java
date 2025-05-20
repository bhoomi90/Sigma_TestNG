package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import pageFactory.DataStructurePage;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;
import webdriver.pageFactoryManager;

public class DataStructureTest extends Hooks {

	/*pageFactoryManager pfm = new pageFactoryManager();
		
	@Test (groups = {"login"}, priority = 1)     
	public void verifySuccessfulLogin() {
	   DataStructurePage dsPage = pfm.getDataStructurePage();
	   dsPage.compareLoginMsg();
	   String actualTitle = CommonMethods.getCurrentTitle();
	   String expectedTitle = "NumpyNinja";  
	   Assert.assertEquals(actualTitle, expectedTitle, "Login failed or incorrect landing page.");

	   LoggerLoad.info("Login verified successfully. Landed on: " + actualTitle);
	}
	   
	@Test (priority = 2, dataProvider = "ValidLoginData", dataProviderClass = TestDataProvider.class)
	public void validate_DataStructure_test(String username, String password) {
		LoggerLoad.info("I am inside validate_DataStructure_test method");
		LoggerLoad.info("Valid username: " +username);
		LoggerLoad.info("Valid password: " +password);
		DataStructurePage dsPage = pfm.getDataStructurePage();
		dsPage.DataStruc_page();
		dsPage.TimecomplexInDataStruc_page();
	}
	
	@Test (priority = 3, dataProvider = "EmptyCode", dataProviderClass = TestDataProvider.class)
	public void emptyCodeTest(String emptyCode, String expectedResults) {
		DataStructurePage dsPage = pfm.getDataStructurePage();
		dsPage.clickTryHere();
		dsPage.emptyCode(emptyCode, expectedResults);
	}
	
	@Test (priority = 4, dataProvider = "ValidCode", dataProviderClass = TestDataProvider.class)
	public void validCodeTest(String validCode, String expectedResults) {
		DataStructurePage dsPage = pfm.getDataStructurePage();
		dsPage.validCode(validCode, expectedResults);
	}
	
	@Test (priority = 5, dataProvider = "invalidCode", dataProviderClass = TestDataProvider.class)
	public void invalidCodeTest(String invalidCode, String expectedResults) {
		DataStructurePage dsPage = pfm.getDataStructurePage();
		dsPage.invalidCode(invalidCode, expectedResults);
	}
	
	@Test (priority = 6)
	public void practiceQuePage() {
		DataStructurePage dsPage = pfm.getDataStructurePage();
		CommonMethods.navigateBack();
		dsPage.clickPracticeQueLink();
		Assert.assertFalse(dsPage.checkPracticeQueContent(), "Found the page blank. Expected to have List of Practice Questions");
		if(dsPage.checkPracticeQueContent()) 
			LoggerLoad.info("List of Practice Questions are available");
		else
			LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");
	}
	
	@Test (priority = 7)
	public void signOutPage() {
		DataStructurePage dsPage = pfm.getDataStructurePage();
		CommonMethods.navigateBack();
		dsPage.clickSignOut();
		dsPage.compareLogoutMsg();
	}*/
}