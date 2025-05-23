package testClasses;

import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.LoggerLoad;
import utilities.TestDataProvider;

public class RegisterTest extends Hooks {

	@Test (priority = 0)
	public void navigateRegisterPage() {
		LoggerLoad.info("Let's register to DsPortal App from register testclass");
		pfm.getHomePage().clickgetStarted();
		pfm.getRegisterPage().register_initial();
	}
	
	@Test (priority = 1, dataProvider = "EmptyUserRegisterData", dataProviderClass = TestDataProvider.class)
	public void emptyUsernameField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().getEmptyUsernameAlertMsg();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared under username field");
	}
	
	@Test (priority = 2, dataProvider = "EmptyPWRegisterData", dataProviderClass = TestDataProvider.class)
	public void emptyPWField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().register_initial();
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().getEmptyPasswordAlertMsg();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared under password field");
	}
	
	@Test (priority = 3, dataProvider = "EmptyConfirmPWRegisterData", dataProviderClass = TestDataProvider.class)
	public void emptyConfirmPWField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().register_initial();
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().getEmptyConfirmPasswordAlertMsg();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared under confirm password");
	}
	
	@Test (priority = 4, dataProvider = "MismatchPWRegisterData", dataProviderClass = TestDataProvider.class)
	public void mismatchPWField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().register_initial();
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().readErrorText();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared for mismatch password");
	}
	
	@Test (priority = 5, dataProvider = "ShortPWRegisterData", dataProviderClass = TestDataProvider.class)
	public void shortPWField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().register_initial();
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().readErrorText();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared for short password");
	}
	
	@Test (priority = 6, dataProvider = "NumPWRegisterData", dataProviderClass = TestDataProvider.class)
	public void numericPWField(String username, String password, String confirmpassword, String message) {
		pfm.getRegisterPage().register_initial();
		pfm.getRegisterPage().enterfields(username, password, confirmpassword);
		LoggerLoad.info("Entered data with username: " +username+ " password" +password+ " password: " +confirmpassword);
		String actualMsg = pfm.getRegisterPage().readErrorText();
		softAssert.assertEquals(actualMsg, message, "Error message is not appeared for numeric password");
		softAssert.assertAll();
	}
}
