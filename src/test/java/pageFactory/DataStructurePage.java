package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.LoggerLoad;
import webdriver.DriverFactory;

public class DataStructurePage {

	@FindBy(xpath = ("//h5[text()='Data Structures-Introduction']/../a[text()='Get Started']"))
	private WebElement DataStrucIntro;

	@FindBy(className = "list-group-item")
	public List<WebElement> dataStructPageLinks;

	@FindBy(xpath = ("//a[text()='Try here>>>']"))
	private WebElement tryHereButton;
	@FindBy(xpath = ("//*[text()='Run']"))
	private WebElement runBttn;

	@FindBy(css = ".CodeMirror.cm-s-default")
	private WebElement codeMirror;
	@FindBy(id = "output")
	private WebElement output;

	@FindBy(xpath = ("//a[text()='Practice Questions']"))
	private WebElement practiceQue;
	@FindBy(className = ("container"))
	private WebElement pageContent;

	public DataStructurePage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized DataStructure Page");
	}

	public void DataStruc_page() {
		DataStrucIntro.click();

	}

	public List<String> retriveDataStructPageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item : dataStructPageLinks) {
			LoggerLoad.info("Item: " + item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}

	public void clickDataStructPageLinks(String item) {
		for (WebElement e : dataStructPageLinks) {
			if (e.getText().equalsIgnoreCase(item)) {
				LoggerLoad.info("Clicked on : " + e.getText());
				e.click();
				break;
			}
		}
	}

	public void clickTryHere() {
		tryHereButton.click();
	}

	public void writeTryEditorCode(String code) {
	    JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		 //get HTML element document.querySelector('.CodeMirror') & reference to the actual CodeMirror editor instance. Stores code-mirror editor instance to editor variable.
		js.executeScript(
			"let editor = document.querySelector('.CodeMirror').CodeMirror;" +
			"editor.setValue(arguments[0]);", code);	//passing code as a parameter rather than hardcoding it inside the JS string.editor.setValue(code);	
	}
	
	public void clickRunBttn() {
		runBttn.click();
	}

	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}

	public void clickPracticeQueLink() {
		practiceQue.click();
	}

	public boolean checkPracticeQueContent() {
		LoggerLoad.info("Page content is: " + pageContent.getText());
		if (pageContent.getText().length() == 0)
			return false;
		else
			return true;
	}

}
