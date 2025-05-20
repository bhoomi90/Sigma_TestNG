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

public class QueueTest extends Hooks{
		    
			@Test(groups = {"login"},priority = 1)
			public void verifySuccessfulLogin() {
			   LoggerLoad.info("I am inside verifySuccessfulLogin method");
			   String expectedTitle = "NumpyNinja";  
			      String actualTitle = driver.getTitle();
			   Assert.assertEquals(actualTitle, expectedTitle, "Login failed or incorrect landing page.");

			   LoggerLoad.info("Login verified successfully. Landed on: " + actualTitle);
			}
			
			@Test(priority = 2)
			public void navigateQueuePage() {
				pfm.getQueuePage().queueGetStartBtnClick();
				String expectedTitle = "Queue";
				LoggerLoad.info("Verifying redirection to Queue page, expected title: " + expectedTitle +driver.getCurrentUrl());
				Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Queue page");
			}
			
			@Test(priority = 3)
			public void validateQueueMainPage() throws InterruptedException{
				List<String> queueItems = pfm.getQueuePage().retriveQueuePageItems();
				for(String item : queueItems) {
					LoggerLoad.info("Clicking on: " +item +" on Queue page");
					pfm.getQueuePage().clickQueuePageLinks(item);
					
					validateTryEditorWindow();
					practiceQuePage();
				}
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
				pfm.getQueuePage().clickTryHereBtn();	
				String expectedTitle = "Assessment";
				LoggerLoad.info("Verifying redirection to tryEditor page, expected title: " + expectedTitle);
				Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to try editor page");
				
				pfm.getQueuePage().emptyCode(emptyCode, expectedResults);
			}
			
			
			public void validCodeTest(String validCode, String expectedResults) {
				driver.navigate().refresh();
				pfm.getQueuePage().validCode(validCode, expectedResults);
				if(pfm.getQueuePage().isOutputSuccess()) {
					assertTrue(pfm.getQueuePage().isOutputSuccess(), "Success output not shown as expected: " +expectedResults);
					LoggerLoad.info("Output is successfully displayed");
				}
				else {
					assertTrue(false, "Test failed: No alert appeared and no output was displayed. Expected: " + expectedResults);
		        	LoggerLoad.error("No output displayed, expected: "+ expectedResults);
				}
			}
			

			public void invalidCodeTest(String invalidCode, String expectedResults) {
				driver.navigate().refresh();
				pfm.getQueuePage().invalidCode(invalidCode, expectedResults);
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
				pfm.getQueuePage().clickPracticeQnsLink();
				
				String expectedTitle = "Practice Questions";
				LoggerLoad.info("Verifying redirection to Practice Questions page, expected title: " + expectedTitle);
				Assert.assertEquals(driver.getTitle(), "Practice Questions", "Not directed to practice questions page");
				
				Assert.assertFalse(pfm.getQueuePage().checkPracticeQueContent(), "Found the page blank. Expected to have List of Practice Questions");		
				if(pfm.getStackPage().checkPracticeQueContent()) 
					LoggerLoad.info("List of Practice Questions are available");
				else
					LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");
				
				pfm.getQueuePage().dropdown_queue_page();
				expectedTitle = "Queue";
				LoggerLoad.info("Verifying redirection to Queue page, expected title: " + expectedTitle);
				Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Queue page");
			}
			
			@Test (priority = 4)
			public void signOutPage() {
				driver.navigate().back();
				pfm.getLoginPage().clickSignOut();
				
				Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");
				LoggerLoad.info("User is logged out");
			}



}
