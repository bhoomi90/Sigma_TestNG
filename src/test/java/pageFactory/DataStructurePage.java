package pageFactory;



public class DataStructurePage {

}

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class DataStructurePage {

	@FindBy(xpath = ("//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']")) private WebElement DataStrucIntro;

	@FindBy(className = "list-group-item") public List<WebElement> dataStructPageLinks;
	
	@FindBy (xpath = ("//a[text()='Try here>>>']")) private WebElement tryHereButton;
	@FindBy (xpath=("//*[text()='Run']")) private WebElement runBttn;
	@FindBy (xpath=("//textarea[@spellcheck='false']")) private WebElement enterCode;
	@FindBy (id = "output") private WebElement output;	
	
	@FindBy (xpath = ("//a[text()='Practice Questions']")) private WebElement practiceQue;	
	@FindBy (className = ("container")) private WebElement pageContent;
	
	
	
	public DataStructurePage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized DataStructure Page");
	}	

	public void DataStruc_page() {
		DataStrucIntro.click();
	
	}
	
	public List<String> retriveDataStructPageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item: dataStructPageLinks) {
			LoggerLoad.info("Item: " +item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}
	public void clickDataStructPageLinks(String item) {
		for(WebElement e: dataStructPageLinks) {
			if(e.getText().equalsIgnoreCase(item)) {
				LoggerLoad.info("Clicked on : " +e.getText());
				e.click();
				break;
			}
		}
	}
	
	public void clickTryHere() {
		tryHereButton.click();
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
	
	public void clickPracticeQueLink() {
		practiceQue.click();
	}

	public boolean checkPracticeQueContent() {
		LoggerLoad.info("Page content is: " +pageContent.getText());
		if(pageContent.getText().length()==0)
			return false;
		else
			return true;
	}

}

