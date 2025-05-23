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

	@Test(groups = { "login" }, priority = 1)
	public void verifySuccessfulLogin() {
		Assert.assertEquals(pfm.getLoginPage().compareLoginMsg(), "You are logged in");
		LoggerLoad.info("User is logged in");

		String expectedTitle = "NumpyNinja";
		LoggerLoad.info("Verifying redirection to Home page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Home page");

		LoggerLoad.info("Login verified successfully. Landed on: " + driver.getTitle());
	}

	@Test(priority = 2)
	public void navigateArrayPage() {
		pfm.getArrayPage().arrayGetStarted();
		String expectedTitle = "Array";
		LoggerLoad.info("Verifying redirection to Array page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Array page");
	}

	@Test(priority = 3)
	public void validateArrayMainPage() {
		List<String> arrayItems = pfm.getArrayPage().retriveArrayPageItems();
		for (String item : arrayItems) {
			LoggerLoad.info("Clicking on: " + item + " on Array page");
			pfm.getArrayPage().clickArrayPageLinks(item);

			validateTryEditorWindow();
			practiceQuePage();
		}
	}

	@Test(priority = 4)
	public void validateArrayPractQuePage() throws InterruptedException {
		List<String> arrayItems = pfm.getArrayPage().retriveArrayPageItems();
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

	public void validatePracticeQuePage(String queType) throws InterruptedException {
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

	public void validateTryEditorWindow() {
		List<Map<String, String>> allData = TestDataProvider.getAllCodeData();

		for (Map<String, String> row : allData) {
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
				throw new IllegalArgumentException("Unsupported validationType: " + validationType);
			}
		}
	}

	public void emptyCodeTest(String emptyCode, String expectedResults) {
		pfm.getArrayPage().clickTryHere();
		String expectedTitle = "Assessment";
		LoggerLoad.info("Verifying redirection to tryEditor page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to try editor page");

		pfm.getArrayPage().emptyCode(emptyCode);
	}

	public void validCodeTest(String validCode, String expectedResults) {
		driver.navigate().refresh();
		pfm.getArrayPage().validCode(validCode);
		if (pfm.getArrayPage().isOutputSuccess()) {
			assertTrue(pfm.getArrayPage().isOutputSuccess(),
					"Success output not shown as expected: " + expectedResults);
			LoggerLoad.info("Output is successfully displayed");
		} else {
			assertTrue(false,
					"Test failed: No alert appeared and no output was displayed. Expected: " + expectedResults);
			LoggerLoad.error("No output displayed, expected: " + expectedResults);
		}
	}

	public void invalidCodeTest(String invalidCode, String expectedResults) {
		driver.navigate().refresh();
		pfm.getArrayPage().invalidCode(invalidCode);
		String actualMsg = CommonMethods.getAlertText(driver);
		if (actualMsg == null) {
			LoggerLoad.error("Expected to receive Alert after invalid python code");
		} else {
			assertTrue(actualMsg.contains(expectedResults),
					"Expected Alert message to contain" + expectedResults + "but got" + actualMsg);
			LoggerLoad.info("Alert message received: " + actualMsg);
		}
	}

	public void checkOutputafterRun(String codeType, String questionPage, String expectedRunOutput) {
		// SoftAssert softAssert = new SoftAssert();
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

	public void checkOutputafterSubmit(String codeType, String questionPage, String expectedSubmitOutput) {
		// SoftAssert softAssert = new SoftAssert();
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

	public void practiceQuePage() {
		driver.navigate().back();
		pfm.getArrayPage().clickPracticeQuesOfArray();

		String expectedTitle = "Practice Questions";
		LoggerLoad.info("Verifying redirection to Practice Questions page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), "Practice Questions", "Not directed to practice questions page");

		Assert.assertTrue(pfm.getArrayPage().checkPracticeQueContent(),
				"Found the page blank. Expected to have List of Practice Questions");
		if (pfm.getArrayPage().checkPracticeQueContent())
			LoggerLoad.info("List of Practice Questions are available");
		else
			LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");

		pfm.getArrayPage().dropdown_array_page();
		expectedTitle = "Array";
		LoggerLoad.info("Verifying redirection to Array page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Array page");
	}

	@Test(priority = 5)
	public void signOutPage() {

		driver.navigate().back();
		pfm.getLoginPage().clickSignOut();

		Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");
		LoggerLoad.info("User is logged out");

	}
}
