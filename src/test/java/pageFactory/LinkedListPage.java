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

public class LinkedListPage {

	@FindBy(xpath = ("//h5[text()='Linked List']/../a[text()='Get Started']"))
	WebElement linkedListClick;
	@FindBy(xpath = ("//*[@data-toggle='dropdown']"))
	WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Linked List']"))
	WebElement dropdownLinkedList;

	@FindBy(className = "list-group-item")
	public List<WebElement> linkedListPageLinks;

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

	public LinkedListPage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
		LoggerLoad.info("Initialized Linked List Page");
	}

	public void open_linkedList_page() {
		linkedListClick.click();
	}

	public void dropdown_linkedList_page() {
		dropdown.click();
		dropdownLinkedList.click();
		LoggerLoad.info("I am on Linked List Page");
	}

	public List<String> retriveLinkedListPageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item : linkedListPageLinks) {
			LoggerLoad.info("Item: " + item.getText());
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}

	public void clickLinkedListPageLinks(String item) {
		for (WebElement e : linkedListPageLinks) {
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
