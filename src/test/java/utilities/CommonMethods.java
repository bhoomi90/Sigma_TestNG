package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import webdriver.DriverFactory;

public class CommonMethods {

	public static final int DEFAULT_TIMEOUT = 10;

	public static String getAlertText(WebDriver driver) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			wait.until(ExpectedConditions.alertIsPresent());
			String alertMsg = driver.switchTo().alert().getText();
			driver.switchTo().alert().accept();
			return alertMsg;
		} catch (Exception e) {

			System.out.println("No alert found within timeout: " + DEFAULT_TIMEOUT + " seconds");
			return null;
		}
	}

	public static void waitForElementTobeClick(WebElement locator) {
		try {
			WebDriver driver = DriverFactory.getdriver();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (Exception e) {
			LoggerLoad.info("No element found within timeout: " + DEFAULT_TIMEOUT + " seconds");
		}
	}
}
