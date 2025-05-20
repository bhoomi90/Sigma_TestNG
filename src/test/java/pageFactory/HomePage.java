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

		// DSOptions Page Elements
		@FindBy(xpath = "//a[contains(text(), 'Sign out')]")
		private WebElement signout;

		@FindBy(xpath = "//div[contains(text(), 'You are logged in')]")
		private WebElement loginSuccessAlert;

		@FindBy(xpath = "//a[@href='data-structures-introduction']")
		private WebElement dsIntroGetStartBtn;

		@FindBy(xpath = "//a[@href='array']")
		private WebElement arrayGetStartBtn;

		@FindBy(xpath = "//a[@href='linked-list']")
		private WebElement linkedListGetStartBtn;

		@FindBy(xpath = "//a[@href='stack']")
		private WebElement stackGetStartBtn;

		@FindBy(xpath = "//a[@href='queue']")
		private WebElement queueGetStartBtn;

		@FindBy(xpath = "//a[@href='tree']")
		private WebElement treeGetStartBtn;

		@FindBy(xpath = "//a[@href='graph']")
		private WebElement graphGetStartBtn;


		public void getStartedBtnClick() {
			getStartedBtn.click();
		}
		
		// Common Methods
		//public String getTitle() {
			//return driver.getTitle();
		//}

		public void clickNumpyNinja() {
			numpyNinjaLink.click();
		}

		public void signInLinkClick() {
			signInLink.click();
		}

		public void RegisterLinkClick() {
			registerLink.click();
		}

		public void clickHeaderLink(String linkText) {
			for (WebElement link : homeHeaderLinks) {
				if (link.getText().trim().equalsIgnoreCase(linkText)) {
					link.click();
					return;
				}
			}
			throw new RuntimeException("Link text not found: " + linkText);
		}

		public boolean isHomeHeaderLinksDisplayed(String headerlink) {
			for (WebElement e : homeHeaderLinks) {
				if (e.getText().trim().equalsIgnoreCase(headerlink)) {
					return e.isDisplayed();
				}
			}
			return false;
		}

		public boolean isLoginErrMsgDisplayed() {
			return errMsg.isDisplayed();
		}

		public String getLoginErrMsg() {
			return errMsg.getText();
		}

		public boolean isLoginSuccessMsgDisplayed() {
			return loginSuccessAlert.isDisplayed();
		}

		public String getLoginSuccessMsg() {
			return loginSuccessAlert.getText();
		}

		public boolean signoutLinkDisplayed() {
			return signout.isDisplayed();
		}

		public String loggedInUser(String username) {
			WebElement loggedInUserName = driver.findElement(By.xpath("//a[contains(text(), '" + username + "')]"));
			return loggedInUserName.getText().trim().toLowerCase();
		}

		public void clickDataStructuresDropdown() {
			dsDropdown.click();
		}

		public boolean areDropdownOptionsVisible(List<String> expectedOptions) {
			for (String expected : expectedOptions) {
				boolean found = false;
				for (WebElement option : dsDropdownOptions) {
					if (option.getText().trim().equalsIgnoreCase(expected)) {
						found = true;
						break;
					}
				}
				if (!found) return false;
			}
			return true;
		}
		public void clickDropdownList(String panelName) {
			CommonMethods.waitForElementToBeVisible(driver, dsDropdown);
			dsDropdown.click();

			for (WebElement e : dsDropdownOptions) {
				if (e.getText().trim().equalsIgnoreCase(panelName)) {
					e.click();
					return;
				}
			}
			throw new RuntimeException("Dropdown option not found: " + panelName);
		}
		
		public void clickGettingStartedForPanel(String panelName) {
			switch (panelName.trim().toLowerCase()) {
			case "data-structures-introduction":
				dsIntroGetStartBtn.click();
				break;
			case "array":
				arrayGetStartBtn.click();
				break;
			case "linked-list":
				linkedListGetStartBtn.click();
				break;
			case "stack":
				stackGetStartBtn.click();
				break;
			case "queue":
				queueGetStartBtn.click();
				break;
			case "tree":
				treeGetStartBtn.click();
				break;
			case "graph":
				graphGetStartBtn.click();
				break;
			default:
				throw new IllegalArgumentException("Invalid panel name: " + panelName);
		}
		}	
			public DataStructurePage dsIntroGetStartBtnClick() {
				dsIntroGetStartBtn.click();
				return new DataStructurePage();
			}
			
			public ArrayPage arrayGetStartBtnClick() {
				arrayGetStartBtn.click();
				return new ArrayPage();
			}
			
			public LinkedListPage linkedListGetStartBtnClick() {
				linkedListGetStartBtn.click();
				return new LinkedListPage();
			}
			
			public StackPage stackGetStartBtnClick() {
				stackGetStartBtn.click();
				return new StackPage();
			}
			
			public QueuePage queueGetStartBtnClick() {
				queueGetStartBtn.click();
				return new QueuePage();
			}
			
			public TreePage treeGetStartBtnClick() {
				treeGetStartBtn.click();
				return new TreePage();
			}
			
			public GraphPage graphGetStartBtnClick() {
				graphGetStartBtn.click();
				return new GraphPage();
			}
		

		


}