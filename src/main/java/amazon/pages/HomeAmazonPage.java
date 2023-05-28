package amazon.pages;



import static utilitarios.DriverFactory.getDriver;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilitarios.DSL;

public class HomeAmazonPage {
	private DSL dsl;
	
	@FindBy(how = How.ID, using = "nav-link-accountList-nav-line-1")
	private WebElement linkFazerLogin;

	@FindBy(how = How.ID, using = "glow-ingress-line1")
	private WebElement lblEnviarPara;

	//construtor
	public HomeAmazonPage() {
		dsl = new DSL();
		PageFactory.initElements(getDriver(), this);
	}
	
	//metodos
	public void clicarFazerLogin() {
		dsl.clicar(linkFazerLogin);
	}
	
	public void validaLogin() {
		String enviarPara = dsl.retornaConteudo(lblEnviarPara);
		String[] aux = (dsl.retornaConteudo(linkFazerLogin)).split(" ");
		String login = aux[1] ;
		Assert.assertTrue(enviarPara.contains(login));
	}
}
