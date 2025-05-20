package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class GraphPage {

	@FindBy(xpath = ("//*[@data-toggle='dropdown']")) private WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Graph']")) private WebElement dropdowngraph;
	@FindBy(xpath = ("//h5[text()='Graph']/../a[text()='Get Started']")) private WebElement graphIntro;

	@FindBy(className = "list-group-item") public List<WebElement> graphPageLinks;
//	@FindBy(xpath = "//a[@class='list-group-item'][text()='Graph']") private WebElement graphPage;
//	@FindBy(xpath = "//a[text()='Graph Representations']") private WebElement graphRep;

	@FindBy(xpath = "//a[text()='Try here>>>']") private WebElement tryHereButton;
	@FindBy (xpath=("//*[text()='Run']")) private WebElement runBttn;
	@FindBy (xpath=("//textarea[@spellcheck='false']")) private WebElement enterCode;
	@FindBy (id = "output") private WebElement output;	
	
	@FindBy (xpath = ("//a[text()='Practice Questions']")) private WebElement practiceQue;	
	@FindBy (className = ("container")) private WebElement pageContent;
	
	public GraphPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized DataStructure Page");
	}	
	
	public void GraphIntro() {
		graphIntro.click();
		LoggerLoad.info("Directed to Graph Introduction page");
	}
	
	public void dropdown_graph_page() {
		dropdown.click();
		dropdowngraph.click();
		LoggerLoad.info("Directed to Graph Page from dropdown button");
	}

	public List<String> retriveGraphPageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item: graphPageLinks) {
			LoggerLoad.info("Item: " +item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}
	public void clickGraphPageLinks(String item) throws InterruptedException {
		for(WebElement e: graphPageLinks) {
			if(e.getText().equalsIgnoreCase(item)) {
				LoggerLoad.info("Clicked on : " +e.getText());
				e.click();
				break;
			}
		}
	}
	
//	public void GraphElementPage() {
//		graphPage.click();
//		LoggerLoad.info("Directed to Graph page");
//	}
//	public void GraphRepresentations() {
//		graphRep.click();
//		LoggerLoad.info("Directed to Graph Representation page");
//	}
	
	public void clickTryHere() {
		tryHereButton.click();
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
	
	public void emptyCode(String emptyCode, String expectedResults) {
		enterCode.sendKeys(emptyCode);
		runBttn.click();			
	}	
	public void validCode(String validCode, String expectedResults) {
		enterCode.sendKeys(validCode);
		runBttn.click();
	}	
	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}
	public void invalidCode(String invalidCode, String expectedResults) {
		enterCode.sendKeys(invalidCode);
		runBttn.click();
	}
}
