package testClasses;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class StackTest extends Hooks {
    List<String> stackItems;

    @Test(groups = { "login" }, priority = 0)
	public void navigateStackPage() {
		pfm.getStackPage().stackGetStartBtnClick();
		stackItems = pfm.getStackPage().retriveStackPageItems();
		String expectedTitle = "Stack";
        String actualTitle = driver.getTitle();
		LoggerLoad.info(
				"Verifying redirection to Stack page, expected title: " + expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Not directed to Stack page");
		}
    
    @Test(priority = 1, dataProvider = "EmptyPythonCode", dataProviderClass = TestDataProvider.class)
    public void testEmptyCodeAcrossAllStackTopics(String emptyCode, String expectedResult) {
        for (String topic : stackItems) {
            LoggerLoad.info("Testing Empty Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getStackPage().writeTryEditorCode(emptyCode);
            pfm.getStackPage().clickRunButton();
            driver.navigate().back(); // Go back to stack topic list
        }
    }

    @Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = TestDataProvider.class)
    public void testValidCodeAcrossAllStackTopics(String validCode, String expectedResult) {
        for (String topic : stackItems) {
            LoggerLoad.info("Testing Valid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getStackPage().writeTryEditorCode(validCode);
            pfm.getStackPage().clickRunButton();
            if (pfm.getStackPage().isOutputSuccess()) {
                assertTrue(true, "Output displayed as expected: " + expectedResult);
                LoggerLoad.info("Output successfully displayed");
            } else {
                LoggerLoad.error("Expected output not shown for: " + topic);
                assertTrue(false, "Expected output: " + expectedResult);
            }
            driver.navigate().back();
        }
    }

    @Test(priority = 3, dataProvider = "InvalidPythonCode", dataProviderClass = TestDataProvider.class)
    public void testInvalidCodeAcrossAllStackTopics(String invalidCode, String expectedAlert) {
        for (String topic : stackItems) {
            LoggerLoad.info("Testing Invalid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getStackPage().writeTryEditorCode(invalidCode);
            pfm.getStackPage().clickRunButton();
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
    }

    @Test(priority = 4)
    public void practiceQuePage() {
        pfm.getStackPage().clickPracticeQnsLink();
        String expectedTitle = "Practice Questions";
        LoggerLoad.info("Verifying Practice Questions page title");
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to practice questions page");
        Assert.assertTrue(pfm.getStackPage().checkPracticeQueContent(),
                "Page is blank. Expected list of practice questions");
    }

    @Test(priority = 5)
    public void backToStackPage() {
        pfm.getStackPage().dropdown_stack_page();
        Assert.assertEquals(driver.getTitle(), "Stack", "Not directed to Stack page");
    }

    @Test(priority = 6)
    public void signOutPage() {
        driver.navigate().back();
        pfm.getLoginPage().clickSignOut();
        Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");
        LoggerLoad.info("User is logged out");
    }


    private void navigateToTryEditor(String topic)  {
        try {
			pfm.getStackPage().clickStackPageLinks(topic);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        pfm.getStackPage().clickTryHereBtn();
        Assert.assertEquals(driver.getTitle(), "Assessment", "Not directed to Try Editor page");
    }
}

	
