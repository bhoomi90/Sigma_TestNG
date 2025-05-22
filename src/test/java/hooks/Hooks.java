package hooks;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;


import pageFactory.HomePage;
import pageFactory.LoginPage;


import utilities.ConfigReader;
import utilities.LoggerLoad;
import webdriver.DriverFactory;
import webdriver.pageFactoryManager;

public class Hooks {

	protected static WebDriver driver;
	protected pageFactoryManager pfm = new pageFactoryManager();
	protected static SoftAssert softAssert = new SoftAssert();

	@Parameters("browser")
	@BeforeClass
	public static void setUp(@Optional("chrome") String browser) throws Throwable {
		LoggerLoad.info("Loading Config file");
		ConfigReader.loadConfig();
		LoggerLoad.info("Setup browser executed");

		LoggerLoad.info("Initializing driver for : "+browser);				
		DriverFactory.init_driver(browser);
		driver = DriverFactory.getdriver();
		driver.get(ConfigReader.getPropertyValue("URL"));
		LoggerLoad.info("Open DSPortal App: " +ConfigReader.getPropertyValue("URL"));
	}
	
	@BeforeMethod(onlyForGroups = {"login"})
	public void logintoDsPortal() {
		LoggerLoad.info("Let's login to DsPortal App");
			pfm.getHomePage().getStartedBtnClick();
			pfm.getHomePage().signInLinkClick();
			pfm.getLoginPage().loginToApp();
		LoggerLoad.info("Clicked Login button");
	}
	
	@AfterClass
	public static void tearDown() {	
		if(driver!=null) {
			LoggerLoad.info("teardown browser executed: " + ConfigReader.getBrowserType());
			DriverFactory.quitBrowser();	
		}
	}
	
}

		LoggerLoad.info("Initializing driver for : " + browser);
		DriverFactory.init_driver(browser);
		driver = DriverFactory.getdriver();
		driver.get(ConfigReader.getPropertyValue("URL"));
		LoggerLoad.info("Open DSPortal App: " + ConfigReader.getPropertyValue("URL"));
	}

	@BeforeMethod(onlyForGroups = { "login" })
	public void logintoDsPortal() {
		LoggerLoad.info("Let's login to DsPortal App");

		pfm.getHomePage().clickgetStarted();
		pfm.getHomePage().clickSignIn();

		pfm.getLoginPage().loginToApp();
		LoggerLoad.info("Clicked Login button");
	}

	@AfterClass
	public static void tearDown() {
		if (driver != null) {
			LoggerLoad.info("teardown browser executed: ");
			DriverFactory.quitBrowser();
		}
	}

}
