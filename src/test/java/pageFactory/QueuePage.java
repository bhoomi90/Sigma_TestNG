package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.CommonMethods;
import webdriver.DriverFactory;

public class QueuePage {

	@FindBy(xpath = "//a[@href='queue']")
	private WebElement queueGetStartBtn;
	@FindBy(xpath = ("//*[@data-toggle='dropdown']"))
	private WebElement dropdown;
	@FindBy(xpath = ("//a[text()='Queue']"))
	private WebElement dropdownqueue;

	@FindBy(className = "list-group-item")
	private List<WebElement> QueuePageLinks;

	@FindBy(xpath = ("//*[text()='Run']"))
	private WebElement runBttn;
	@FindBy(linkText = "Try here>>>")
	private WebElement btnTryHere;
	@FindBy(css = ".CodeMirror.cm-s-default")
	private WebElement codeMirror;
	@FindBy(id = "output")
	private WebElement output;
	@FindBy(xpath = "//a[text()='Practice Questions']")
	private WebElement practiceQnsLink;
	@FindBy(className = ("container"))
	private WebElement pageContent;

	public QueuePage() {
		PageFactory.initElements(DriverFactory.getdriver(), this);
	}

	public void queueGetStartBtnClick() {
		queueGetStartBtn.click();
	}

	public void dropdown_queue_page() {
		dropdown.click();
		dropdownqueue.click();
	}

	public List<String> retriveQueuePageItems() {
		List<String> itemTexts = new ArrayList<String>();
		for (WebElement item : QueuePageLinks) {
			itemTexts.add(item.getText());
		}
		return itemTexts;
	}

	public void clickQueuePageLinks(String item) {
		for (WebElement e : QueuePageLinks) {
			if (e.getText().equalsIgnoreCase(item)) {
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
