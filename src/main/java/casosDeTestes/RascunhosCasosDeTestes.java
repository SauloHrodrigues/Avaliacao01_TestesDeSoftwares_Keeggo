package casosDeTestes;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utilitarios.DSL;

public class RascunhosCasosDeTestes {
	WebDriver driver;
	DSL dsl;
	String sEmail = "saulohrodrigues@gmail.com";
	String sSenha = "5@123456";

	@Before
	public void inicioPadrao() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
		driver.get("https://www.amazon.com.br/");
		driver.manage().window().maximize();
		
	}

	@After
	public void finalizacaoPadrao() {
		//driver.quit();
	}

//	****************** Teste 01 ********************
	@Test
	public void Teste01_LogimAmazon() {
		Assert.assertEquals("Olá, Saulo", dsl.LogarAmazon(sEmail, sSenha));
	}

//	****************** Teste 02 ********************
	@Test  
	public void Teste02_RealizarLogaltAmazon() {
		dsl.LogarAmazon(sEmail, sSenha);
		dsl.fazeLogut();
		WebElement wTelaLogar = driver.findElement(By.xpath("//label[@for='ap_email']/../../h1"));
		Assert.assertEquals("Fazer login", wTelaLogar.getText());
	}
	
//	****************** Teste 03 ********************
	
	@Test
	public void validarItemEncontrado() {
		dsl.buscarProduto("bicicleta");
		Assert.assertTrue(dsl.validaProdutoEncontrado());
	}
	
	
	
//	****************** Teste 04 ********************
	@Test
	public void teste04_BuscaEValidacaoConteudoInexistente() {
		dsl.buscarProduto("'ITEMINEXISTENTE012345'");
		WebElement wResultado = driver.findElement(By.xpath("//span[text()='Nenhum resultado para ']"));
		System.out.println("getValue = " + wResultado.getAttribute("value"));
		Assert.assertEquals("Nenhum resultado para", wResultado.getText());
	}
	
//	****************** Teste 05 ********************

//	@Test  // => REVISAR RESULTADO
//	//@Ignore
//	public void teste05_ValidarRetornoFretePrazo() throws InterruptedException {
//		String sCpePesquisado = "06010067";
//		dsl.buscarProduto("frigideira");
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@data-component-id='2']"));
//		wPrimeiroElemento.click();
//		
//		WebElement wEndereco = driver.findElement(By.id("cipInsideDeliveryBlock_feature_div"));
//		wEndereco.click();//não clica pelo firefox
//		WebElement wCampoCEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
//		wCampoCEP.sendKeys(sCpePesquisado);
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		WebElement wBotaoConfirma = driver.findElement(By.id("GLUXZipUpdate"));
//		wBotaoConfirma.click();
//		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//		WebElement wResposta = driver.findElement(By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]"));
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
//		System.out.println("A resposta foi: "+wResposta.getText());
//		Assert.assertEquals("06010067", wResposta.getText());
//	}
	
	
//	****************** Teste 06 ********************
	
	
	
//	******************* teste 07 *********************
	@Test
	public void teste07_AdcionarCarrinhoCompra() {
		String sProdutoPesquisado = "lapiseira pentel";
		dsl.buscarProduto(sProdutoPesquisado);
		
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@id='s-skipLinkTargetForMainSearchResults']/..//span[@data-component-type='s-search-results']//div[@data-index='2']"));
		wPrimeiroElemento.click();
		String sNomeProduto = driver.findElement(By.id("productTitle")).getText();
		WebElement wBtnAdcionarCarrinho = driver.findElement(By.id("add-to-cart-button"));
		wBtnAdcionarCarrinho.click();			 
		WebElement wCarrinho = driver.findElement(By.id("nav-cart-count-container"));
		wCarrinho.click();
		WebElement wSacola = driver.findElement(By.id("activeCartViewForm"));
		WebElement wItem01 = wSacola.findElement(By.xpath(".//div[@data-item-index='1']"));
		Assert.assertTrue(wItem01.getText().contains(sNomeProduto));
	}
//	******************* teste 08 *********************
//	******************* teste 09 *********************
//	******************* teste 10 *********************
//	******************* teste 11 *********************
//	******************* teste 12 *********************
//	******************* teste 13 *********************
//	******************* teste 14 *********************
//	******************* teste 15 *********************

	
	
}
