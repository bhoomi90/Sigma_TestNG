package testClasses;

import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class LoginTest extends Hooks {
	
	@Test (priority = 0)
	public void navigateLoginPage() {
		LoggerLoad.info("Let's login to DsPortal App from login testclass");
		pfm.getHomePage().clickgetStarted();
		pfm.getHomePage().clickSignIn();
	}

	@Test (priority = 1, dataProvider = "InvalidUserLoginData", dataProviderClass = TestDataProvider.class)
	public void invalidValidField(String username, String password, String message) {
		pfm.getLoginPage().enterfields(username, password);
		LoggerLoad.info("Entered data with invalid username: " +username+ " valid password" +password);
		String actualMsg = pfm.getLoginPage().readErrorText();
		String expectedMSG = message; 
		softAssert.assertEquals(actualMsg, expectedMSG, "Error message is not appeared");
		
	}
	
	@Test (priority = 2, dataProvider = "InvalidPWLoginData", dataProviderClass = TestDataProvider.class)
	public void validInvalidField(String username, String password, String message) {
		driver.navigate().refresh();
		pfm.getLoginPage().enterfields(username, password);
		LoggerLoad.info("Entered data with valid username: " +username+ " invalid password" +password);
		String actualMsg = pfm.getLoginPage().readErrorText();
		String expectedMSG = message; 
		softAssert.assertEquals(actualMsg, expectedMSG, "Error message is not appeared");
	}
	
	@Test (priority = 3, dataProvider = "EmptyUserLoginData", dataProviderClass = TestDataProvider.class)
	public void emptyValidField(String username, String password, String message) {
		driver.navigate().refresh();
		pfm.getLoginPage().enterfields(username, password);
		LoggerLoad.info("Entered data with empty username: " +username+ " valid password" +password);
		String actualMsg = pfm.getLoginPage().getEmptyUserNameAlertMsg();
		String expectedMSG = message ; 
		softAssert.assertEquals(actualMsg, expectedMSG, "Error message is not appeared under username field");
	}
	
	@Test (priority = 4, dataProvider = "EmptyPWLoginData", dataProviderClass = TestDataProvider.class)
	public void validEmptyField(String username, String password, String message) {
		driver.navigate().refresh();
		pfm.getLoginPage().enterfields(username, password);
		LoggerLoad.info("Entered data with valid username: " +username+ " empty password" +password);
		String actualMsg = pfm.getLoginPage().getEmptyPasswordAlertMsg();
		String expectedMSG = message; 
		softAssert.assertEquals(actualMsg, expectedMSG, "Error message is not appeared under password field");
		softAssert.assertAll();
	}
}

