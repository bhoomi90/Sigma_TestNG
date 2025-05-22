package testClasses;

import static org.testng.Assert.assertTrue;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class DataStructureTest extends Hooks {
		
	@Test (groups = {"login"}, priority = 1)     
	public void verifySuccessfulLogin() {
		Assert.assertEquals(pfm.getLoginPage().compareLoginMsg(), "You are logged in");
		LoggerLoad.info("User is logged in");
	    
		String expectedTitle = "NumpyNinja";
		LoggerLoad.info("Verifying redirection to Home page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Home page");
		
	    LoggerLoad.info("Login verified successfully. Landed on: " + driver.getTitle());
	}
	   
	@Test (priority = 2)
	public void navigateDataStructPage() {
		pfm.getDataStructurePage().DataStruc_page();	
		String expectedTitle = "Data Structures-Introduction";
		LoggerLoad.info("Verifying redirection to Data-Structure page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Data-Structure page");
	}
	
	@Test (priority = 3)
	public void validateDataStructMainPage() {
		List<String> dataStructItems = pfm.getDataStructurePage().retriveDataStructPageItems();
		for(String item : dataStructItems) {
			LoggerLoad.info("Clicking on: " +item +" on Data-Structure page");
			pfm.getDataStructurePage().clickDataStructPageLinks(item);
			
			validateTryEditorWindow();
			practiceQuePage();
		}
		softAssert.assertAll();
	}
	
	public void validateTryEditorWindow() {
		List<Map<String,String>> allData = TestDataProvider.getAllCodeData();
		
		for(Map<String, String> row : allData) {
			String validationType = row.get("codeValidations");
			String pythonCode = row.get("code");
	        String expectedResult = row.get("expectedResults");

	        LoggerLoad.info("Validation Type: " + validationType);
	        LoggerLoad.info("Python Code: " + pythonCode);
	        LoggerLoad.info("Expected Result: " + expectedResult);
	        LoggerLoad.info("-----------");
	        
	        switch (validationType.trim().toLowerCase()) {
			case "empty":
				emptyCodeTest(pythonCode, expectedResult);
				break;
			case "valid":
				validCodeTest(pythonCode, expectedResult);
				break;
			case "invalid":
				invalidCodeTest(pythonCode, expectedResult);
				break;
			default:
				throw new IllegalArgumentException("Unsupported validationType: " +validationType);
			}
		}
	}
	public void emptyCodeTest(String emptyCode, String expectedResults) {
		pfm.getDataStructurePage().clickTryHere();	
		String expectedTitle = "Assessment";
		LoggerLoad.info("Verifying redirection to tryEditor page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to try editor page");
		
		pfm.getDataStructurePage().emptyCode(emptyCode);
	}
	
	
	public void validCodeTest(String validCode, String expectedResults) {
		driver.navigate().refresh();
		pfm.getDataStructurePage().validCode(validCode);
		if(pfm.getDataStructurePage().isOutputSuccess()) {
			assertTrue(pfm.getDataStructurePage().isOutputSuccess(), "Success output not shown as expected: " +expectedResults);
			LoggerLoad.info("Output is successfully displayed");
		}
		else {
			assertTrue(false, "Test failed: No alert appeared and no output was displayed. Expected: " + expectedResults);
        	LoggerLoad.error("No output displayed, expected: "+ expectedResults);
		}
	}
	

	public void invalidCodeTest(String invalidCode, String expectedResults) {
		driver.navigate().refresh();
		pfm.getDataStructurePage().invalidCode(invalidCode);
		String actualMsg = CommonMethods.getAlertText(driver);
		if(actualMsg==null) {
			LoggerLoad.error("Expected to receive Alert after invalid python code");
		}
		else {
			assertTrue(actualMsg.contains(expectedResults), "Expected Alert message to contain" +expectedResults+ "but got" +actualMsg);
			LoggerLoad.info("Alert message received: "+ actualMsg);
		}
	}
	
	public void practiceQuePage() {
		driver.navigate().back();
		pfm.getDataStructurePage().clickPracticeQueLink();
		
		String expectedTitle = "Practice Questions";
		LoggerLoad.info("Verifying redirection to Practice Questions page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), "Practice Questions", "Not directed to practice questions page");
		
		softAssert.assertTrue(pfm.getDataStructurePage().checkPracticeQueContent(), "Found the page blank. Expected to have List of Practice Questions");		
		if(pfm.getDataStructurePage().checkPracticeQueContent()) 
			LoggerLoad.info("List of Practice Questions are available");
		else
			LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");
		
		driver.navigate().back();
		expectedTitle = "Time Complexity";
		LoggerLoad.info("Verifying redirection to Time Complexity in Data Structures-Introduction page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Time Complexity in Data Structures-Introduction page");
	}
	
	@Test (priority = 4)
	public void signOutPage() {
		driver.navigate().back();
		pfm.getLoginPage().clickSignOut();
		
		Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");
		LoggerLoad.info("User is logged out");
	}
}
