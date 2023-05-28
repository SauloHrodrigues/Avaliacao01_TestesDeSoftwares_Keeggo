package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import utilitarios.DSL;

public class HomePageAmazon {
	private DSL dsl;
	
	@FindBy(how = How.ID, using = "nav-link-accountList-nav-line-1")
	private WebElement linkFazerLogin;

	public HomePageAmazon() {
		dsl = new DSL();
	}
	
	public void fazerLogin() {
		dsl.clicar(linkFazerLogin);
	}

}
