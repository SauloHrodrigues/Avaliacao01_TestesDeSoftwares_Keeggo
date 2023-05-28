package amazon.pages;



import static utilitarios.DriverFactory.getDriver;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import amazon.gerenciadores.GerenciadorPages;
import utilitarios.DSL;

public class HomeAmazonPage {
	private DSL dsl;
	
	@FindBy(how = How.ID, using = "nav-link-accountList-nav-line-1")
	private WebElement linkFazerLogin;

	@FindBy(how = How.ID, using = "glow-ingress-line1")
	private WebElement lblEnviarPara;

	@FindBy(how = How.ID, using = "nav-item-signout")
	private WebElement linkSairDaConta;

	//construtor
	public HomeAmazonPage() {
		dsl = new DSL();
		PageFactory.initElements(getDriver(), this);
	}
	
	//metodos
	public void clicarFazerLogin() {
		dsl.clicar(linkFazerLogin);
	}

	public void clicarSairDaConta() {
		dsl.clicarComJS(linkSairDaConta);
	}
	
	private boolean verificaLogin() {
		String enviarPara = dsl.retornaConteudo(lblEnviarPara);
		String[] aux = (dsl.retornaConteudo(linkFazerLogin)).split(" ");
		String login = aux[1] ;
		
		if(enviarPara.contains(login)) {
			return true;
		}else {
			return false;
		}	
	}
	
	public void validaLogin() {
		Assert.assertTrue(verificaLogin());
	}
	
	public void validaLogout() {
		GerenciadorPages pages = new GerenciadorPages();
		LoginAmazonPage login = pages.getLoginPage();
	}
}
