package utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods {

	public static final int DEFAULT_TIMEOUT = 10;
	
//	public static String getCurrentTitle() {
//		WebDriver driver = DriverFactory.getdriver();
//		return driver.getTitle();
//	}
//	public static String getCurrentUrl() {
//		WebDriver driver = DriverFactory.getdriver();
//		return driver.getCurrentUrl();
//	}	
//	public static void refreshPage() {
//		WebDriver driver = DriverFactory.getdriver();
//		driver.navigate().refresh();
//	}
//	public static void navigateBack() {
//		WebDriver driver = DriverFactory.getdriver();
//		driver.navigate().back();
//	}
	public static String getAlertText(WebDriver driver) {
    	try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.alertIsPresent());
        String alertMsg = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();
        return alertMsg;
    	} catch (Exception e) {
            System.out.println("No alert found within timeout: "+ DEFAULT_TIMEOUT  + " seconds");
            return null;
        }
	}
//	public static String getTextForElement(WebElement locator) {
//		return locator.getText();
//	}
}