
package pageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class HomePage {

	
	@FindBy (xpath=("//*[text()='Get Started']")) WebElement getStarted;
	@FindBy (xpath=("//*[text()='Sign in']")) WebElement signIn ;
  @FindBy(xpath = "//a[text()='NumpyNinja']")
   private WebElement numpyNinjaLink;
	@FindBy(xpath = "//a[@class='dropdown-item']")
	private List<WebElement> dropdownItems;

        @FindBy(xpath = "//a[text()='Data Structures']")
	private WebElement dsDropdown;
        @FindBy(xpath = "//a[@href='/register']")
	private WebElement registerBtn;
        @FindBy(xpath = "//div[@class='alert alert-primary']")
	private WebElement errorMsg;
        @FindBy(xpath = "//a[@href ='data-structures-introduction']")
	private WebElement dsIntroGetStarted;
        @FindBy(xpath = "//a[@href ='array']")
	private WebElement arrayGetStarted;
        @FindBy(xpath = "//a[@href ='linked-list']")
	private WebElement linkedListGetStarted;
        @FindBy(xpath = "//a[@href ='stack']")
	private WebElement stackGetStarted;
        @FindBy(xpath = "//a[@href ='queue']")
	private WebElement queueGetStarted;
        @FindBy(xpath = "//a[@href ='tree']")
	private WebElement treeGetStarted;
        @FindBy(xpath = "//a[@href ='graph']")
	private WebElement graphGetStarted;


	public HomePage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Home Page");
	}

	public void clickgetStarted() {
		LoggerLoad.info("Clicking get started button in Home page");
		getStarted.click();
	}

	public void clickSignIn() {
		LoggerLoad.info("Clicking signIn button in Home page");
		signIn.click();
	}
	 public String getLandingPageTitle() {
	        return DriverFactory.getdriver().getTitle();
	    }
	public void clickNumpyNinja() {
			numpyNinjaLink.click();
		}
	public void clickRegisterButton() {
		        registerBtn.click();
		    }

	    public Boolean[] isDisplayedOnHome() {
	        return new Boolean[] {
	            signIn.isDisplayed(),
	            registerBtn.isDisplayed(),
	            numpyNinjaLink.isDisplayed()
	        };
	    }

	    public void clickDSDropdown() {
	        dsDropdown.click();
	    }

	    public int getDropdownItemCount() {
	        dsDropdown.click();
	        return dropdownItems.size();
	    }

	    public String getErrorMessage() {
	        return errorMsg.getText();
	    }

	   
      public void clickDropdownOption(String optionText) {
	        dsDropdown.click();
	        for (WebElement item : dropdownItems) {
	            if (item.getText().equalsIgnoreCase(optionText)) {
	                item.click();
	                break;
	            }
	        }
	    }

	    public String getRegistrationPageTitle() {
	        registerBtn.click();
	        return DriverFactory.getdriver().getTitle();
	    }

	    public void clickDSIntroGetStarted() {
	        dsIntroGetStarted.click();
	    }

	    public void clickArrayGetStarted() {
	        arrayGetStarted.click();
	    }

	    public void clickLinkedListGetStarted() {
	        linkedListGetStarted.click();
	    }

	    public void clickStackGetStarted() {
	        stackGetStarted.click();
	    }

	    public void clickQueueGetStarted() {
	        queueGetStarted.click();
	    }

	    public void clickTreeGetStarted() {
	        treeGetStarted.click();
	    }

	    public void clickGraphGetStarted() {
	        graphGetStarted.click();
	    }
		
}
