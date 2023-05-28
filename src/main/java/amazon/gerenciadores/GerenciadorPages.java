package amazon.gerenciadores;

import amazon.pages.HomeAmazonPage;
import amazon.pages.LoginAmazonPage;

public class GerenciadorPages {

	private HomeAmazonPage homePage;
	private LoginAmazonPage loginPage; 
	
	public HomeAmazonPage getHomePage(){
		return (homePage == null) ? homePage = new HomeAmazonPage() : homePage;
	}
	
	public LoginAmazonPage getLoginPage(){	
		return (loginPage == null) ? loginPage = new LoginAmazonPage() : loginPage;		
	}
	
}
