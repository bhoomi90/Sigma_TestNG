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

public class GraphPage {

	@FindBy(xpath = ("//*[@data-toggle='dropdown']"))
	private WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Graph']"))
	private WebElement dropdowngraph;
	@FindBy(xpath = ("//h5[text()='Graph']/../a[text()='Get Started']"))
	private WebElement graphIntro;

	@FindBy(className = "list-group-item")
	public List<WebElement> graphPageLinks;

	@FindBy(xpath = "//a[text()='Try here>>>']")
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

	public GraphPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Graph Page");
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
		for (WebElement item : graphPageLinks) {
			LoggerLoad.info("Item: " + item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}

	public void clickGraphPageLinks(String item) {
		for (WebElement e : graphPageLinks) {
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

	public void writeTryEditorCode(String code) {
	    JavascriptExecutor js = (JavascriptExecutor) DriverFactory.getdriver();
		 //get HTML element document.querySelector('.CodeMirror') & reference to the actual CodeMirror editor instance. Stores code-mirror editor instance to editor variable.
		js.executeScript(
			"let editor = document.querySelector('.CodeMirror').CodeMirror;" +
			"editor.setValue(arguments[0]);", code);	//passing code as a parameter rather than hardcoding it inside the JS string.editor.setValue(code);	
	}

	public void clickRunButton() {
		CommonMethods.waitForElementTobeClick(runBttn);
		runBttn.click();
	}
	public boolean isOutputSuccess() {
		return output.isDisplayed();
	}

}
