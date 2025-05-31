package testClasses;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.CommonMethods;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class TreeTest extends Hooks {
	
	List<String> treeItems;

    @Test(groups = { "login" }, priority = 0)
	public void navigateTreePage() {
		pfm.getTreePage().treeGetStartedBtnClick();
		treeItems = pfm.getTreePage().retriveTreePageItems();
		String expectedTitle = "Tree";
		String actualTitle = driver.getTitle();
		LoggerLoad.info(
				"Verifying redirection to Tree page, expected title: " + expectedTitle);
		Assert.assertEquals(actualTitle, expectedTitle, "Not directed to Tree page");
		
		}
    
    @Test(priority = 1, dataProvider = "EmptyPythonCode", dataProviderClass = TestDataProvider.class)
    public void testEmptyCodeAcrossAllTreeTopics(String emptyCode, String expectedResult) {
        for (String topic : treeItems) {
            LoggerLoad.info("Testing Empty Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getTreePage().writeTryEditorCode(emptyCode);
            pfm.getTreePage().clickRunButton();
            driver.navigate().back(); 
        }
        LoggerLoad.info("----------------------------------------------------");
    }

    @Test(priority = 2, dataProvider = "ValidPythonCode", dataProviderClass = TestDataProvider.class)
    public void testValidCodeAcrossAllTreeTopics(String validCode, String expectedResult) {
        for (String topic : treeItems) {
            LoggerLoad.info("Testing Valid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getTreePage().writeTryEditorCode(validCode);
            pfm.getTreePage().clickRunButton();
            if (pfm.getTreePage().isOutputSuccess()) {
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
    public void testInvalidCodeAcrossAllTreeTopics(String invalidCode, String expectedAlert) {
        for (String topic : treeItems) {
            LoggerLoad.info("Testing Invalid Code on Topic: " + topic);
            navigateToTryEditor(topic);
            pfm.getTreePage().writeTryEditorCode(invalidCode);
            pfm.getTreePage().clickRunButton();
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
        pfm.getTreePage().clickPracticeQnsLink();
        String expectedTitle = "Practice Questions";
        LoggerLoad.info("Verifying Practice Questions page title");
        Assert.assertEquals(driver.getTitle(), expectedTitle, "Not directed to practice questions page");
        Assert.assertTrue(pfm.getTreePage().checkPracticeQueContent(),
                "Page is blank. Expected list of practice questions");
    }

    @Test(priority = 5)
    public void backToTreePage() {
        pfm.getTreePage().dropdown_tree_page();
        Assert.assertEquals(driver.getTitle(), "Tree", "Not directed to Tree page");
    }

    @Test(priority = 6)
    public void signOutPage() {
        driver.navigate().back();
        pfm.getLoginPage().clickSignOut();
        Assert.assertEquals(pfm.getLoginPage().compareLogoutMsg(), "Logged out successfully");
        LoggerLoad.info("User is logged out");
    }

    private void navigateToTryEditor(String topic)  {
 
    	pfm.getTreePage().clickTreePageLinks(topic);
        pfm.getTreePage().clickTryHereBtn();
        Assert.assertEquals(driver.getTitle(), "Assessment", "Not directed to Try Editor page");
    }
}

