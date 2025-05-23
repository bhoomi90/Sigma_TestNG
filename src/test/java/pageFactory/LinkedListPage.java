package pageFactory;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
	@FindBy(xpath = ("//textarea[@spellcheck='false']"))
	private WebElement enterCode;
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

	public void emptyCode(String emptyCode) {
		if (emptyCode == null)
			runBttn.click();
		else {
			enterCode.sendKeys(emptyCode);
			runBttn.click();
		}
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
