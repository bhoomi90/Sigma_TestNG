package webdriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ThreadGuard;
import utilities.LoggerLoad;

public class DriverFactory {

	private static ThreadLocal<WebDriver> threadlocalDriver = new ThreadLocal<>();
	
	// Start browser based on the input
	public static void init_driver(String browserName) {
		WebDriver driver;

		switch (browserName.toLowerCase()) {
		case "chrome":
			LoggerLoad.info(browserName + " Browser Started");
			driver = ThreadGuard.protect( new ChromeDriver());
			break;
		case "edge":	
			LoggerLoad.info(browserName + " Browser Started");
			driver = ThreadGuard.protect(new EdgeDriver());
			break;
		case "firefox":		
			LoggerLoad.info(browserName + " Browser Started");
			driver = ThreadGuard.protect(new FirefoxDriver());
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}
		threadlocalDriver.set(driver);
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
	}

	public static WebDriver getdriver() {
		return threadlocalDriver.get();
	}
	
	// Quit browser
	public static void quitBrowser() {
		if (threadlocalDriver.get() != null) {
			LoggerLoad.info("Closing local driver");
			threadlocalDriver.get().quit();
			threadlocalDriver.remove();
		}
	}
}
