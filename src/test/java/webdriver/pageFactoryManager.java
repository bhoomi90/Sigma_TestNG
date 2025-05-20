package webdriver;

import pageFactory.ArrayPage;
import pageFactory.DataStructurePage;
import pageFactory.GraphPage;
import pageFactory.HomePage;
import pageFactory.LinkedListPage;
import pageFactory.LoginPage;
import pageFactory.QueuePage;
import pageFactory.RegisterPage;
import pageFactory.StackPage;
import pageFactory.TreePage;

public class pageFactoryManager {

	protected ArrayPage arrayPage ;
	protected DataStructurePage dsPage;
	protected GraphPage graphPage;
	protected HomePage homePage;
	protected LinkedListPage linkedlistPage;
	protected LoginPage loginPage;
	protected QueuePage queuePage;
	protected RegisterPage registerPage;
	protected StackPage stackPage;
	protected TreePage treePage;
	
	
	public ArrayPage getArrayPage() {
		if (arrayPage != null)
			return arrayPage;
		else {
			arrayPage = new ArrayPage();
			return arrayPage;
		}
	}
	
	public DataStructurePage getDataStructurePage() {
		if(dsPage!=null)
			return dsPage;
		else {
			dsPage = new DataStructurePage();
			return dsPage;
		}
	}
	
	public GraphPage getGraphPage() {
		if (graphPage != null)
			return graphPage;
		else {
			graphPage = new GraphPage();
			return graphPage;
		}
	}
	
	public HomePage getHomePage() {
		if (homePage != null)
			return homePage;
		else {
			homePage = new HomePage();
			return homePage;
		}
	}
	
	public LinkedListPage getLinkedListPage() {
		if (linkedlistPage != null)
			return linkedlistPage;
		else {
			linkedlistPage = new LinkedListPage();
			return linkedlistPage;
		}
	}
	
	public LoginPage getLoginPage() {
		if (loginPage != null)
			return loginPage;
		else {
			loginPage = new LoginPage();
			return loginPage;
		}
	}
	
	public QueuePage getQueuePage() {
		if (queuePage != null)
			return queuePage;
		else {
			queuePage = new QueuePage();
			return queuePage;
		}
	}
	
	public RegisterPage getRegisterPage() {
		if (registerPage != null)
			return registerPage;
		else {
			registerPage = new RegisterPage();
			return registerPage;
		}
	}
	
	public StackPage getStackPage() {
		if (stackPage != null)
			return stackPage;
		else {
			stackPage = new StackPage();
			return stackPage;
		}
	}
	
	public TreePage getTreePage() {
		if (treePage != null)
			return treePage;
		else {
			treePage = new TreePage();
			return treePage;
		}
	}
}


