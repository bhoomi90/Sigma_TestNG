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
		LoggerLoad.info("user should see alert message when user not logged in to application that -- "
				+ pfm.getHomePage().getErrorMessage());
	}

	@Test
	public void register() {
		pfm.getHomePage().clickRegisterButton();
		Assert.assertEquals(DriverFactory.getdriver().getTitle(), "Registration");
		LoggerLoad.info("user should land on registration page. Tital is: " + DriverFactory.getdriver().getTitle());
	}

	@Test
	public void signIn() {
		pfm.getHomePage().clickSignIn();
		Assert.assertEquals(DriverFactory.getdriver().getTitle(), "Login");
		LoggerLoad.info("user should land on login page. Title is: " + DriverFactory.getdriver().getTitle());
	}

	@Test
	public void testDropdownCount() {
		pfm.getHomePage().clickDSDropdown();
		int count = pfm.getHomePage().getDropdownItemCount();
		LoggerLoad.info("Total dropdown items: " +count);
		Assert.assertEquals(count, 6, "Unexpected number of dropdown items: " + count);
	}

	@Test
	public void testClickDropdownOption() {
		pfm.getHomePage().clickDropdownOption("Arrays");
		String currentUrl = DriverFactory.getdriver().getCurrentUrl();
		LoggerLoad.info("Current URL after click on dropdown array is: " + currentUrl);
		Assert.assertFalse(currentUrl.toLowerCase().contains("array"), "Not navigated to Arrays page");
	}

	@Test
	public void testClickGetStartedButtons() {

		pfm.getHomePage().clickDSIntroGetStarted();
		LoggerLoad.info("Current URL after click on DS intro is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("data-structures-introduction"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickArrayGetStarted();
		LoggerLoad.info("Current URL after click on Array is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("array"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickLinkedListGetStarted();
		LoggerLoad.info("Current URL after click on Linked list is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("linked-list"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickStackGetStarted();
		LoggerLoad.info("Current URL after click on Stack is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("stack"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickQueueGetStarted();
		LoggerLoad.info("Current URL after click on Queue is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("queue"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickTreeGetStarted();
		LoggerLoad.info("Current URL after click on Tree is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("tree"));

		DriverFactory.getdriver().navigate().back();

		pfm.getHomePage().clickGraphGetStarted();
		LoggerLoad.info("Current URL after click on Graph is: " + DriverFactory.getdriver().getCurrentUrl());
		Assert.assertFalse(DriverFactory.getdriver().getCurrentUrl().contains("graph"));
	}

}


