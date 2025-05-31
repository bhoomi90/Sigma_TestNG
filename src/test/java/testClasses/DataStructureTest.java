package testClasses;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class DataStructureTest extends Hooks {

	List<String> dataStructItems;

	@Test(groups = { "login" }, priority = 0)
	public void navigateDataStructPage() {
		pfm.getDataStructurePage().DataStruc_page();
		dataStructItems = pfm.getDataStructurePage().retriveDataStructPageItems();
		
		String expectedTitle = "Data Structures-Introduction";
		LoggerLoad.info("Verifying redirection to Data-Structure page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to Data-Structure page");		
	}

	@Test(priority = 1, dataProvider = "EmptyPythonCode", dataProviderClass = TestDataProvider.class)
	public void testEmptyCodeAcrossAllDSTopics(String emptyCode, String expectedResult) {
		for (String topic : dataStructItems) {
			LoggerLoad.info("Testing Empty Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getDataStructurePage().writeTryEditorCode(emptyCode);
			pfm.getDataStructurePage().clickRunBttn();
			driver.navigate().back();
		}
		LoggerLoad.info("----------------------------------------------------");
	}

	@Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = TestDataProvider.class)
	public void testValidCodeAcrossAllDSTopics(String validCode, String expectedResult) {
		for (String topic : dataStructItems) {
			LoggerLoad.info("Testing Valid Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getDataStructurePage().writeTryEditorCode(validCode);
			pfm.getDataStructurePage().clickRunBttn();
			if (pfm.getDataStructurePage().isOutputSuccess()) {
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
		for (String topic : dataStructItems) {
			LoggerLoad.info("Testing Invalid Code on Topic: " + topic);
			navigateToTryEditor(topic);
			pfm.getDataStructurePage().writeTryEditorCode(invalidCode);
			pfm.getDataStructurePage().clickRunBttn();
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
	public void practiceQuePage() {

		pfm.getDataStructurePage().clickPracticeQueLink();

		String expectedTitle = "Practice Questions";
		LoggerLoad.info("Verifying redirection to Practice Questions page, expected title: " + expectedTitle);
		Assert.assertEquals(driver.getTitle(), "Practice Questions", "Not directed to practice questions page");

		if (pfm.getDataStructurePage().checkPracticeQueContent())
			LoggerLoad.info("List of Practice Questions are available");
		else
			LoggerLoad.error("Test failed: Found the page blank. Expected to have List of Practice Questions");
		Assert.assertTrue(pfm.getDataStructurePage().checkPracticeQueContent(),
				"Found the page blank. Expected to have List of Practice Questions");
	}

	@Test(priority = 5)
	public void backToDsPage() {
		driver.navigate().back();
		String expectedTitle = "Time Complexity";
		LoggerLoad
				.info("Verifying redirection to Time Complexity in Data Structures-Introduction page, expected title: "
						+ expectedTitle);
		Assert.assertEquals(driver.getTitle(), expectedTitle,
				"Not directed to Time Complexity in Data Structures-Introduction page");
	}

	@Test(priority = 6)
	public void signOutPage() {
		driver.navigate().back();
		pfm.getLoginPage().clickSignOut();

		LoggerLoad.info("User is logged out");
		Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");		
	}

	// Utility Method
	private void navigateToTryEditor(String topic) {
		pfm.getDataStructurePage().clickDataStructPageLinks(topic);
		pfm.getDataStructurePage().clickTryHere();
		Assert.assertEquals(driver.getTitle(), "Assessment", "Not directed to Try Editor page");
	}
}
