package amazon.pages;


import static utilitarios.DriverFactory.getDriver;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import utilitarios.DSL;
import utilitarios.DriverFactory;

public class LoginAmazonPage {
	
	private DSL dsl;
	
	@FindBy(how = How.ID, using = "ap_email")
	private WebElement inputEmail;

	@FindBy(how = How.ID, using = "continue")
	private WebElement btnContinuar;

	@FindBy(how = How.ID, using = "ap_password")
	private WebElement imputSenha;

	@FindBy(how = How.ID, using = "signInSubmit")
	private WebElement btnFazerLogin;
	
//	CONSTRUTOR
	public LoginAmazonPage() {
		dsl = new DSL();
		PageFactory.initElements(getDriver(), this);
	}

		
	public void entrarEmail(String email) {
		dsl.escrever(inputEmail, email);
	}
	
	public void clicarContinuar() {
		dsl.clicar(btnContinuar);
	}

	public void entrarSenha(String senha) {
		dsl.escrever(imputSenha, senha);
	}
	
	public void clicarFazerLogin() {
		dsl.clicar(btnFazerLogin);
	}
	
	public void validaLogout() {
		Assert.assertTrue(inputEmail.isDisplayed());
	}
}
