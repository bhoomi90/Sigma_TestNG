package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;
import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class ArrayPage {

	@FindBy(xpath = ("//h5[text()='Array']/../a[text()='Get Started']")) private WebElement arrayGetStarted;
	@FindBy(xpath = ("//*[@data-toggle='dropdown']")) private WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Arrays']")) private WebElement dropdownArray;

	@FindBy(css = ".CodeMirror.cm-s-default")  private WebElement writePractQueCode;
	@FindBy(id="output")  private WebElement output;
	@FindBy(xpath = ("//*[@type='submit']")) private WebElement submitButton;
	
	@FindBy(xpath = "//a[text()='Try here>>>']") private WebElement tryHereButton;
	@FindBy (xpath=("//textarea[@spellcheck='false']")) private WebElement writeTryEditorCode;	
	@FindBy (xpath=("//*[text()='Run']")) private WebElement runBttn;
	
	@FindBy(xpath = ("//a[text()='Practice Questions']")) private WebElement PracticeQuesOfArray;
	@FindBy (className = ("list-group"))  private WebElement pageContent;
	
	@FindBy(className = ("list-group-item")) private List<WebElement> arrayPageLinks;
	
	public ArrayPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Array Page");
	}
	
	public void arrayGetStarted() {
		arrayGetStarted.click();
	}

	public void dropdown_array_page() {
		dropdown.click();
		dropdownArray.click();
		LoggerLoad.info("I am on Array Page");
	}
	
	public List<String> retriveArrayPageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item: arrayPageLinks) {
			LoggerLoad.info("Item: " +item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}
	public void clickArrayPageLinks(String item) {
		for(WebElement e: arrayPageLinks) {
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
		writeTryEditorCode.sendKeys(emptyCode);
		runBttn.click();			
	}	
	public void validCode(String validCode) {
		writeTryEditorCode.sendKeys(validCode);
		runBttn.click();
	}	
	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}
	public String getOutput() {
		CommonMethods.waitForElementTobeClick(output);
		return output.getText();
	}
	public void invalidCode(String invalidCode) {
		writeTryEditorCode.sendKeys(invalidCode);
		runBttn.click();
	}
	
	public void clickRunButton() {
		CommonMethods.waitForElementTobeClick(runBttn);
		runBttn.click();
	}
	public void clickSubmitButton() {
		CommonMethods.waitForElementTobeClick(submitButton);
		submitButton.click();
	}
	
	public void writeCode(String code) {
		
		CommonMethods.waitForElementTobeClick(writePractQueCode);

	    JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
	   
		 //get HTML element document.querySelector('.CodeMirror') & reference to the actual CodeMirror editor instance. Stores code-mirror editor instance to editor variable.
		
	    js.executeScript(
			"let editor = document.querySelector('.CodeMirror').CodeMirror;" +
			"editor.setValue(arguments[0]);", code);	//passing code as a parameter rather than hardcoding it inside the JS string.editor.setValue(code);	
	}	
	
	public void clickPracticeQuesOfArray() {
		PracticeQuesOfArray.click();
	}

	public boolean checkPracticeQueContent() {
		LoggerLoad.info("Page content is: " +pageContent.getText());
		if(pageContent.getText().length()==0)
			return false;
		else
			return true;
	}
}
