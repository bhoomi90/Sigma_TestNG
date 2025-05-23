package testClasses;

import org.testng.Assert;
import org.testng.annotations.Test;

import hooks.Hooks;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class HomeTest extends Hooks {

	
	@Test
	public void DS_Introduction() {
		pfm.getHomePage().clickgetStarted();
		pfm.getHomePage().clickDSIntroGetStarted();
		Assert.assertEquals(pfm.getHomePage().getErrorMessage(), "You are not logged in");
		LoggerLoad.info("user should see alert message when user not logged in to application");
	       }
	
	 @Test
	public void register() {
		pfm.getHomePage().clickRegisterButton();
		Assert.assertEquals(DriverFactory.getdriver().getTitle(), "Registration");
		LoggerLoad.info("user should land on registration page");
		  }
	 
    @Test
	public void signIn() {
		pfm.getHomePage().clickSignIn();
		Assert.assertEquals(DriverFactory.getdriver().getTitle(), "Login");
		LoggerLoad.info("user should land on login page");
          }
    
	 @Test
	 public void testDropdownCount() {
	     pfm.getHomePage().clickDSDropdown();
	     int count = pfm.getHomePage().getDropdownItemCount();
	     Assert.assertEquals(count, 6, "Unexpected number of dropdown items");
	      }
	 
	 @Test
	 public void testClickDropdownOption() {
	      pfm.getHomePage().clickDropdownOption("Arrays");
	      String currentUrl = DriverFactory.getdriver().getCurrentUrl();
	       Assert.assertFalse(currentUrl.toLowerCase().contains("array"), "Not navigated to Arrays page");
	    }
	 
	    @Test
	   public void testClickGetStartedButtons() {
	      pfm.getHomePage().clickDSIntroGetStarted();
	      Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("data-structures-introduction"));

	        DriverFactory.getdriver().navigate().back();

	      pfm.getHomePage().clickArrayGetStarted();
	      Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("array"));

	        DriverFactory.getdriver().navigate().back();

	      pfm.getHomePage().clickLinkedListGetStarted();
	      Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("linked-list"));

	        DriverFactory.getdriver().navigate().back();

	      pfm.getHomePage().clickStackGetStarted();
	      Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("stack"));

	        DriverFactory.getdriver().navigate().back();

	      pfm.getHomePage().clickQueueGetStarted();
	      Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("queue"));

	        DriverFactory.getdriver().navigate().back();

	       pfm.getHomePage().clickTreeGetStarted();
	       Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("tree"));

	        DriverFactory.getdriver().navigate().back();

	       pfm.getHomePage().clickGraphGetStarted();
	       Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("graph"));
	    } 
	 
}
