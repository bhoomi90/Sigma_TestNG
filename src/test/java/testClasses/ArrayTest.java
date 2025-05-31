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
import webdriver.DriverFactory;

public class ArrayTest extends Hooks {

	List<String> arrayItems;

	@Test(groups = { "login" }, priority = 0)
	public void navigateArrayPage() {
		pfm.getArrayPage().arrayGetStarted();
		arrayItems = pfm.getArrayPage().retriveArrayPageItems();
		
		String expectedTitle = "Array";
		LoggerLoad.info("Verifying redirection to Array page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Array page");
	}

	@Test(priority = 1, dataProvider = "EmptyPythonCode", dataProviderClass = TestDataProvider.class)
	public void testEmptyCodeAcrossAllDSTopics(String emptyCode, String expectedResult) {
		for (String topic : arrayItems) {
			LoggerLoad.info("Testing Empty Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getArrayPage().writeTryEditorCode(emptyCode);
			pfm.getArrayPage().clickRunButton();
			driver.navigate().back();
		}
		LoggerLoad.info("----------------------------------------------------");
	}

	@Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = TestDataProvider.class)
	public void testValidCodeAcrossAllDSTopics(String validCode, String expectedResult) {
		for (String topic : arrayItems) {
			LoggerLoad.info("Testing Valid Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getArrayPage().writeTryEditorCode(validCode);
			pfm.getArrayPage().clickRunButton();
			if (pfm.getArrayPage().isOutputSuccess()) {
				assertTrue(true, "Output displayed as expected: " + expectedResult);
				LoggerLoad.info("Output successfully displayed");
			} else {
				LoggerLoad.error("Expected output not shown for: " + topic);
				assertTrue(false, "Expected output: " + expectedResult);
			}
			driver.navigate().back();
		}
		LoggerLoad.info("----------------------------------------------------");
	}

	@Test(priority = 3, dataProvider = "InvalidPythonCode", dataProviderClass = TestDataProvider.class)
	public void testInvalidCodeAcrossAllDSTopics(String invalidCode, String expectedAlert) {
		for (String topic : arrayItems) {
			LoggerLoad.info("Testing Invalid Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getArrayPage().writeTryEditorCode(invalidCode);
			pfm.getArrayPage().clickRunButton();
			String actualMsg = CommonMethods.getAlertText(driver);
			if (actualMsg == null) {
				LoggerLoad.error("Expected Alert not received for invalid code");
				assertTrue(false, "Expected alert message: " + expectedAlert);
			} else {
				assertTrue(actualMsg.contains(expectedAlert),
						"Alert message mismatch. Expected to contain: " + expectedAlert + ", but got: " + actualMsg);
				LoggerLoad.info("Received Alert message: " + actualMsg);
			}
			driver.navigate().back();
		}
		LoggerLoad.info("----------------------------------------------------");
	}

	@Test(priority = 4)
	public void validateArrayPractQuePage() throws InterruptedException {
		//List<String> arrayItems = pfm.getArrayPage().retriveArrayPageItems();
		String firstItem = arrayItems.get(0);

		LoggerLoad.info("Clicking on: " + firstItem + " on Array page");
		pfm.getArrayPage().clickArrayPageLinks(firstItem);

		pfm.getArrayPage().clickPracticeQuesOfArray();

		List<String> arrayQueItems = pfm.getArrayPage().retriveArrayPageItems();
		for (String queItem : arrayQueItems) {
			LoggerLoad.info("Clicking on " + queItem + " on Array Practice Que Page");
			pfm.getArrayPage().clickArrayPageLinks(queItem);
			validatePracticeQuePage(queItem);
		}
		softAssert.assertAll();
	}

	@Test(priority = 5)
	public void practiceQuePage() {
		driver.navigate().back();
		pfm.getArrayPage().clickPracticeQuesOfArray();

		String expectedTitle = "Practice Questions";
		LoggerLoad.info("Verifying redirection to Practice Questions page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), "Practice Questions", "Not directed to practice questions page");

		if (pfm.getArrayPage().checkPracticeQueContent())
			LoggerLoad.info("List of Practice Questions are available");
		else
			LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");
		Assert.assertTrue(pfm.getArrayPage().checkPracticeQueContent(),
				"Found the page blank. Expected to have List of Practice Questions");
	}

	@Test(priority = 6)
	public void backToArrayPage() {
		pfm.getArrayPage().dropdown_array_page();
		String expectedTitle = "Array";
		LoggerLoad.info("Verifying redirection to Array page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Array page");
	}
	
	@Test(priority = 7)
	public void signOutPage() {

		driver.navigate().back();
		pfm.getLoginPage().clickSignOut();

		LoggerLoad.info("User is logged out");
		Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");	
	}
	
	// Utility Method
	private void navigateToTryEditor(String topic) {
		pfm.getArrayPage().clickArrayPageLinks(topic);
		pfm.getArrayPage().clickTryHere();
		Assert.assertEquals(driver.getTitle(), "Assessment", "Not directed to Try Editor page");
	}
	
	private void validatePracticeQuePage(String queType) throws InterruptedException {
		List<Map<String, String>> practiceQueCodeData = TestDataProvider.getAllpracticeQueCodeData();

		int counter = 0;
		for (Map<String, String> row : practiceQueCodeData) {

			String questionType = row.get("Questions");

			if (questionType.equalsIgnoreCase(queType) && counter < 3) {

				String validationType = row.get("CodeValidations");
				String pythonCode = row.get("code");
				String expectedRunOutput = row.get("errorRun");
				String expectedSubmitOutput = row.get("errorSubmit");

				LoggerLoad.info("------------------------------------");
				LoggerLoad.info("Validation Type: " + validationType);
				LoggerLoad.info("Question Type: " + questionType);

				pfm.getArrayPage().writeCode(pythonCode);
				pfm.getArrayPage().clickRunButton();
				checkOutputafterRun(validationType, questionType, expectedRunOutput);

				pfm.getArrayPage().clickSubmitButton();
				Thread.sleep(1000);
				checkOutputafterSubmit(validationType, questionType, expectedSubmitOutput);

				driver.navigate().refresh();

				counter++;
				if (counter == 3) {
					LoggerLoad.info("Breaking the loop at count = 3");
					break;
				}
			}

		}
		driver.navigate().back();
	}

	private void checkOutputafterRun(String codeType, String questionPage, String expectedRunOutput) {
		
		String actualOutput = null;

		LoggerLoad.info("------------------------------------");
		LoggerLoad.info("Expected Output after run button for " + codeType + " is: " + expectedRunOutput);
		if (codeType.equals("ValidCode")) {
			actualOutput = pfm.getArrayPage().getOutput();
			LoggerLoad.info("Actual Output after run button for " + codeType + " is: " + actualOutput);
			int comparison = actualOutput.compareTo(expectedRunOutput);
			if (comparison == 0)
				LoggerLoad.info("Output after " + codeType + " matched for " + questionPage
						+ " Practice que page after click on Run button");
			else
				LoggerLoad.error("Output after " + codeType + " mismatched for " + questionPage
						+ " Practice que page after click on Run button");
		} else {
			actualOutput = CommonMethods.getAlertText(DriverFactory.getdriver());
			LoggerLoad.info("Actual Output after run button for " + codeType + " is: " + actualOutput);
			if (actualOutput == null) {
				LoggerLoad.error("Expected to receive Alert after invalid python code");
			} else {
				softAssert.assertTrue(actualOutput.contains(expectedRunOutput),
						"Expected Alert message to contain" + expectedRunOutput + "but got" + actualOutput);
				LoggerLoad.info("Alert message received: " + actualOutput);
			}
		}
	}

	private void checkOutputafterSubmit(String codeType, String questionPage, String expectedSubmitOutput) {
		
		String actualOutput = null;

		actualOutput = pfm.getArrayPage().getOutput();
		LoggerLoad.info("---------------------------------------");
		LoggerLoad.info("Actual output after submit button for " + codeType + " is: " + actualOutput);
		LoggerLoad.info("Expected output after submit button for " + codeType + " is: " + expectedSubmitOutput);

		softAssert.assertEquals(actualOutput, expectedSubmitOutput, "Output after " + codeType + " mismatched for "
				+ questionPage + " Practice que page after click on Submit button");

		int comparison = actualOutput.compareTo(expectedSubmitOutput);
		if (comparison == 0)
			LoggerLoad.info("Output after " + codeType + " matched for " + questionPage
					+ " Practice que page after click on Submit button");
		else
			LoggerLoad.error("Output after " + codeType + " mismatched for " + questionPage
					+ " Practice que page after click on Submit button");
	}
}
