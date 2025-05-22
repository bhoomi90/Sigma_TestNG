package pageFactory;

import java.util.ArrayList;
import java.util.List;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import webdriver.DriverFactory;

public class TreePage {

	@FindBy(xpath ="//a[@href='tree']")
	private WebElement treeGetStartedBtn;
	
	@FindBy(xpath = ("//*[@data-toggle='dropdown']")) 
	private WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Tree']")) 
	private WebElement dropdowntree;
	
	@FindBy(className = "list-group-item")
	private List<WebElement> treePageLinks;
	
	@FindBy (xpath=("//*[text()='Run']")) 
	private WebElement runBttn;
	@FindBy(linkText = "Try here>>>") 
	private WebElement btnTryHere;
	@FindBy (xpath=("//textarea[@spellcheck='false']"))
	private WebElement enterCode;
	@FindBy(id = "output")
	private WebElement output;	
	@FindBy (xpath = "//a[text()='Practice Questions']")
	private WebElement practiceQnsLink;
	@FindBy (className = ("container")) 
	private WebElement pageContent;

	
	public TreePage() {
        PageFactory.initElements(DriverFactory.getdriver(), this);	
	}

	public void treeGetStartedBtnClick() {
		treeGetStartedBtn.click();
		}

	public void dropdown_tree_page() {
		dropdown.click();
		dropdowntree.click();
		
	}
	
	public List<String> retriveTreePageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item: treePageLinks) {
			itemTexts.add(item.getText());
		}
		return itemTexts;
	 }
	
	public void clickTreePageLinks(String item) throws InterruptedException{
		for(WebElement e: treePageLinks) {
			if(e.getText().equalsIgnoreCase(item)) {
				e.click();
				break;
				
			}
		}
	  }
		
	public boolean isRunBtnDisplayed() {
		return runBttn.isDisplayed();
	    }
	
	public boolean isTryHereBtnDisplayed() {
		return btnTryHere.isDisplayed();
	     }
	
	public void clickTryHereBtn() {
		btnTryHere.click();
	     }
	
	public void clickRunTryHere() {
		runBttn.click();
	    }
	
	public void clickPracticeQnsLink() {
		practiceQnsLink.click();
	    }
	
	public boolean isPracticeQnsDisplayed() {
		return practiceQnsLink.isDisplayed();
	     }

	
	public boolean checkPracticeQueContent() {
		if(pageContent.getText().length()==0)
			return false;
		else
			return true;
	}
	
	public void emptyCode(String emptyCode) {
		enterCode.sendKeys(emptyCode);
		runBttn.click();			
	}	
	public void validCode(String validCode) {
		enterCode.sendKeys(validCode);
		runBttn.click();
	}	
	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}
	public void invalidCode(String invalidCode) {
		enterCode.sendKeys(invalidCode);
		runBttn.click();
	}

  }
