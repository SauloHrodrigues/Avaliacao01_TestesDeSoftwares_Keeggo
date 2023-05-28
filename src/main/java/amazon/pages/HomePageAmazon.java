package amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class HomePageAmazon {

	@FindBy(how = How.ID, using = "nav-link-accountList-nav-line-1")
	private WebElement linkFazerLogin;
	
	
	
	
//	dsl.clicarElemento(By.id("nav-link-accountList-nav-line-1"));
}
