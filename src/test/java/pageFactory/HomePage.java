package pageFactory;

    import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webdriver.DriverFactory;

 public class HomePage {

		public HomePage() {
			PageFactory.initElements(DriverFactory.getdriver(), this);
		}

		@FindBy(className = "btn" ) 
		private WebElement getStartedBtn;
		// Home Page Elements
		@FindBy(xpath = "//a[text()='NumpyNinja']")
		private WebElement numpyNinjaLink;

		@FindBy(xpath = "//a[contains(text(), 'Sign in')]")
		private WebElement signInLink;

		@FindBy(xpath = "//a[contains(text(), 'Register')]")
		private WebElement registerLink;

		@FindBy(xpath = "//a[@class='nav-link dropdown-toggle']")
		private WebElement dsDropdown;

		@FindBy(xpath = "//div[@class='dropdown-menu show']//a")
		private List<WebElement> dsDropdownOptions;

		@FindBy(xpath = "//div[contains(text(),'You are not logged in')]")
		private WebElement errMsg;

		@FindBy(xpath = "//a[text()='NumpyNinja' or text()='Data Structures' or normalize-space(text())='Register' or text()='Sign in']")
		private List<WebElement> homeHeaderLinks;

	
		public void getStartedBtnClick() {
			getStartedBtn.click();
		}
		

		public void clickNumpyNinja() {
			numpyNinjaLink.click();
		}

		public void signInLinkClick() {
			signInLink.click();
		}

		public void RegisterLinkClick() {
			registerLink.click();
		}

		

}