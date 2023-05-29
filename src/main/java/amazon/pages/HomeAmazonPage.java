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

	@FindBy(how = How.ID, using = "twotabsearchtextbox")//twotabsearchtextbox
	private WebElement inputPesquisar;

	@FindBy(how = How.ID, using = "nav-search-submit-button")
	private WebElement btnPesquisar;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Nenhum resultado para')]")
	private WebElement msgProdutoInexistente;

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
	
	public void clicarLupaPesquisar() {
		dsl.clicar(btnPesquisar);
	}
	
	public void pesquisar(String produto) {
		System.out.println("Entrou no pesquisar");
		dsl.escrever(inputPesquisar, produto);
	}
	
	public void validaProdutoInexistente() {
		Assert.assertTrue(msgProdutoInexistente.isDisplayed());
	}
	
	public void validaLogin() {
		String enviarPara = dsl.retornaConteudo(lblEnviarPara);
		String[] aux = (dsl.retornaConteudo(linkFazerLogin)).split(" ");
		String login = aux[1] ;
		Assert.assertTrue(enviarPara.contains(login));	
	}
	
	
}
