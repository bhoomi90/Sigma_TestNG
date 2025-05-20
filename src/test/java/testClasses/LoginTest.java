package testClasses;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.LoggerLoad;
import utilities.TestDataProvider;
import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

public class LoginTest extends Hooks {
	
	

	@Test(priority =1,dataProvider = "loginData", dataProviderClass = TestDataProvider.class)
    public void validateLogin(Map<String, String> data) throws InvalidFormatException, IOException {
        pfm.getLoginPage().loginClick();

        String username = data.get("username");
        String password = data.get("password");
        String expectedMessage = data.get("expectedMessage");
        String validationType = data.get("validation");

        LoggerLoad.info("Running test with validation type: " + validationType);

        pfm.getLoginPage().loginClick(); // if needed before entering credentials

        pfm.getLoginPage().setUsername(username);
        pfm.getLoginPage().setPassword(password);
        pfm.getLoginPage().loginClick();

        String actualMsg;

        if (validationType.equalsIgnoreCase("ValidCredential")) {
            actualMsg = pfm.getLoginPage().compareLogoutMsg();
            Assert.assertTrue(actualMsg.contains(expectedMessage), "Expected logout message not found!");
            pfm.getLoginPage().clickSignOut();
        } else {
            actualMsg = pfm.getLoginPage().compareLoginMsg();
            Assert.assertTrue(actualMsg.contains(expectedMessage), "Expected error message not found!");
        }
    }
}

