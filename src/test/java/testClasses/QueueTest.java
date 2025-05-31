package testClasses;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class QueueTest extends Hooks {

	List<String> queueItems;

    @Test(groups = { "login" }, priority = 0)
	public void navigateQueuePage() {
		pfm.getQueuePage().queueGetStartBtnClick();
		queueItems = pfm.getQueuePage().retriveQueuePageItems();
		String expectedTitle = "Queue";
		String actualTitle = driver.getTitle();
		LoggerLoad.info(
				"Verifying redirection to Queue Intro page, expected title: " + expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Not directed to Queue Intro page");
	
		}
    
    @Test(priority = 1, dataProvider = "EmptyPythonCode", dataProviderClass = TestDataProvider.class)
    public void testEmptyCodeAcrossAllQueueTopics(String emptyCode, String expectedResult) {
        for (String topic : queueItems) {
            LoggerLoad.info("Testing Empty Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getQueuePage().writeTryEditorCode(emptyCode);
            pfm.getQueuePage().clickRunButton();
            driver.navigate().back(); 
        }
    }

    @Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = TestDataProvider.class)
    public void testValidCodeAcrossAllQueueTopics(String validCode, String expectedResult) {
        for (String topic : queueItems) {
            LoggerLoad.info("Testing Valid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getQueuePage().writeTryEditorCode(validCode);
            pfm.getQueuePage().clickRunButton();
            if (pfm.getQueuePage().isOutputSuccess()) {
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
    public void testInvalidCodeAcrossAllQueueTopics(String invalidCode, String expectedAlert) {
        for (String topic : queueItems) {
            LoggerLoad.info("Testing Invalid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getQueuePage().writeTryEditorCode(invalidCode);
            pfm.getQueuePage().clickRunButton();
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
        pfm.getQueuePage().clickPracticeQnsLink();
        String expectedTitle = "Practice Questions";
        LoggerLoad.info("Verifying Practice Questions page title");
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to practice questions page");
        Assert.assertTrue(pfm.getQueuePage().checkPracticeQueContent(),
                "Page is blank. Expected list of practice questions");
    }

    @Test(priority = 5)
    public void backToQueuePage() {
        pfm.getQueuePage().dropdown_queue_page();
        Assert.assertEquals(driver.getTitle(), "Queue", "Not directed to Queue page");
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
			pfm.getQueuePage().clickQueuePageLinks(topic);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        pfm.getQueuePage().clickTryHereBtn();
        Assert.assertEquals(driver.getTitle(), "Assessment", "Not directed to Try Editor page");
    }
}
	
