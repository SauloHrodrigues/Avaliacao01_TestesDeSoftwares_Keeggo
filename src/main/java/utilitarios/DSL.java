package utilitarios;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DSL {
	WebDriver driver;

	
		
	public DSL(WebDriver driver) {
		this.driver = driver;
	}

	@Test
	public String LogarAmazon(String sEmail, String sSenha) {
		WebElement wFazerLogin = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		wFazerLogin.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement wCaixaDeEmail = driver.findElement(By.id("ap_email"));
		wCaixaDeEmail.sendKeys(sEmail);
		WebElement wBotaoContinuar = driver.findElement(By.id("continue"));
		wBotaoContinuar.click();
		WebElement wCaixaDeSenha = driver.findElement(By.id("ap_password"));
		wCaixaDeSenha.sendKeys(sSenha);
		WebElement wBotaoFazerLogin = driver.findElement(By.id("signInSubmit"));
		wBotaoFazerLogin.click();
		WebElement wNomeDoUsuario = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		return wNomeDoUsuario.getText();
	}
	
	public void fazeLogut() {
		WebElement sair = driver.findElement(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", sair);
	}
	


	public void buscarProduto(String sNomeProduto) {
		WebElement wCaixaDePesquisa = driver.findElement(By.id("twotabsearchtextbox"));
		wCaixaDePesquisa.sendKeys(sNomeProduto);
		WebElement wLupaDePesquisar = driver.findElement(By.id("nav-search-submit-button"));
		wLupaDePesquisar.click();
	}
	
	public boolean validaProdutoEncontrado() {
		WebElement teste = driver.findElement(By.xpath("//span[@data-component-type='s-result-info-bar']"));
		System.out.println("resultado "+teste.getText());
		if(teste.getText().contains("1-")|| teste.getText().contains("1")) {
			return true;
		}else {
			return false;
		}
	}
	
	public void clicarPrimeiroItemDaBusca() {
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@id='s-skipLinkTargetForMainSearchResults']/..//span[@data-component-type='s-search-results']//div[@data-index='2']"));
		wPrimeiroElemento.click();
	}
	
	public void adicionarItemNoCarrinho() {
		WebElement wBtnAdcionarCarrinho = driver.findElement(By.id("add-to-cart-button"));
		wBtnAdcionarCarrinho.click();
	}

	public void entrarNoCarrinho() {
		WebElement wCarrinho = driver.findElement(By.id("nav-cart-count-container"));
		wCarrinho.click();
	}
	public String itemNoCarrinho(int item) {
		WebElement wSacola = driver.findElement(By.id("activeCartViewForm"));
		WebElement wItem01 = wSacola.findElement(By.xpath(".//div[@data-item-index='"+item+"']"));
		return wItem01.getText();
	}
	
	public Double conversorTextoEmValor(String valor) {
		String[] sValor = valor.split(" ");
//		System.out.println("Valor splitado = "+sValor[1]);
		String[] sValorSV = sValor[1].split(",");
		String valor1 = sValorSV[0].concat(".").concat(sValorSV[1]);
		Double dValor = Double.parseDouble(valor1);
		return dValor;
	}
	
//	 public Object verificarItensNoCarrinho() {
//			WebElement wCarrinho = driver.findElement(By.id("nav-cart-count-container"));
//			wCarrinho.click();
//			WebElement wSacola = driver.findElement(By.id("activeCartViewForm"));
//			WebElement wItem01 = wSacola.findElement(By.xpath(".//div[@data-item-index='1']"));
//	 }
}
