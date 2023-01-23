package casosDeTestes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilitarios.DSL;

public class RascunhosCasosDeTestes {
	WebDriver driver;
	WebDriverWait wait;
	DSL dsl;
	String sEmail = "saulohrodrigues@gmail.com";
	String sSenha = "5@123456";

	@Before
	public void inicioPadrao() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
		driver.get("https://www.amazon.com.br/");
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, 30);
		
	}

	@After
	public void finalizacaoPadrao() {
		//driver.quit();
	}

//	****************** Teste 01 ********************
	@Test
	public void Teste01_LogimAmazon() {
		String sNumeroDoTeste ="#0001";
		Assert.assertEquals("Olá, Saulo", dsl.LogarAmazon(sEmail, sSenha));
	}

//	****************** Teste 02 ********************
	@Test  
	public void Teste02_RealizarLogaltAmazon() {
		String sNumeroDoTeste ="#0002";
		dsl.LogarAmazon(sEmail, sSenha);
		dsl.fazeLogut();
		WebElement wTelaLogar = driver.findElement(By.xpath("//label[@for='ap_email']/../../h1"));
		Assert.assertEquals("Fazer login", wTelaLogar.getText());
	}
	
//	****************** Teste 03 ********************
	
	@Test
	public void validarItemEncontrado() {
		String sNumeroDoTeste ="#0003";
		dsl.buscarProduto("bicicleta");
		Assert.assertTrue(dsl.validaProdutoEncontrado());
	}
	
	
	
//	****************** Teste 04 ********************
	@Test
	public void teste04_BuscaEValidacaoConteudoInexistente() {
		String sNumeroDoTeste ="#0004";
		dsl.buscarProduto("'ITEMINEXISTENTE012345'");
		WebElement wResultado = driver.findElement(By.xpath("//span[text()='Nenhum resultado para ']"));
		System.out.println("getValue = " + wResultado.getAttribute("value"));
		Assert.assertEquals("Nenhum resultado para", wResultado.getText());
	}
	
//	****************** Teste 05 ********************
	@Test  // => REVISAR RESULTADO
	public void teste05_ValidarRetornoFretePrazo() throws InterruptedException {
		String sNumeroDoTeste ="#0005";
//		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String sCpePesquisado = "000000";
		dsl.buscarProduto("frigideira");
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@data-component-id='2']"));
		wait.until(ExpectedConditions.visibilityOf(wPrimeiroElemento));
		wPrimeiroElemento.click();
		
		WebElement wEndereco = driver.findElement(By.id("cipInsideDeliveryBlock_feature_div"));
		wEndereco.click();
		Thread.sleep(800); // revisar
		WebElement wCampoCEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
		wait.until(ExpectedConditions.visibilityOf(wCampoCEP));
		wCampoCEP.sendKeys(sCpePesquisado);
		
		WebElement wBotaoConfirma = driver.findElement(By.id("GLUXZipUpdate"));
		wait.until(ExpectedConditions.visibilityOf(wBotaoConfirma));
		wBotaoConfirma.click();
		
//		Thread.sleep(3000);
		WebElement wResposta = driver.findElement(By.xpath("//span[@id='GLUXZipError']"));
		wait.until(ExpectedConditions.visibilityOf(wResposta));
		Assert.assertEquals("Insira um CEP válido", wResposta.getText());
//		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
//	**************** teste 06 ********************
	@Test
	public void validarMensagemCepInvalido() throws InterruptedException {
		String sNumeroDoTeste ="#0006";
		String sCEP = "000000000";
		dsl.buscarProduto("copo");
		dsl.clicarPrimeiroItemDaBusca();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement wSelecionarEndereco = driver.findElement(By.id("contextualIngressPtLabel_deliveryShortLine"));
		wait.until(ExpectedConditions.visibilityOf(wSelecionarEndereco));
		wSelecionarEndereco.click();
		Thread.sleep(800);
		WebElement wCEEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
		wait.until(ExpectedConditions.visibilityOf(wCEEP));
		wCEEP.sendKeys(sCEP);
		driver.findElement(By.id("GLUXZipUpdate")).click();
		WebElement wMsg = driver.findElement(By.xpath("//div[text()='Insira um CEP válido']"));
		wait.until(ExpectedConditions.visibilityOf(wMsg));

		Assert.assertEquals("Insira um CEP válido", wMsg.getText());
	}
	
	
	
//	******************* teste 07 *********************
	@Test
	public void conferirIntemNoCarrinho() {
		String sNumeroDoTeste ="#0007";
		String sProdutoPesquisado = "lapiseira pentel";
		dsl.buscarProduto(sProdutoPesquisado);
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();
		Assert.assertTrue(dsl.itemNoCarrinho(1).toLowerCase().contains(sProdutoPesquisado.toLowerCase()));
	}

	//************************ TESTE 08 *******************
		@Test
		public void aumentarQuantidadeValidarPreco() throws InterruptedException {
			String sNumeroDoTeste ="#0008";
			String sProduto = "Lapis de cor";
			dsl.buscarProduto(sProduto);
			dsl.clicarPrimeiroItemDaBusca();
			dsl.adicionarItemNoCarrinho();
			dsl.entrarNoCarrinho();
			
			WebElement element = driver.findElement(By.id("quantity"));
			Select combo = new Select(element);
			int iQtdeProdutos =2;
			combo.selectByIndex(2);
//			wait.until(ExpectedConditions.visibilityOf(element));
			Thread.sleep(3000);
//			não funciona sem o thred
			WebElement wValorUnitario = driver.findElement(By.xpath("//div[@data-item-count='1']//div[@class='sc-list-item-content']//span[contains(text(),'R$')]"));
//			wait.until(ExpectedConditions.visibilityOf(wValorUnitario));
			WebElement wValorTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span"));
//			wait.until(ExpectedConditions.visibilityOf(wValorTotal));
			Double dValorUnitario = dsl.conversorTextoEmValor(wValorUnitario.getText());
			Double dValorTotal= dsl.conversorTextoEmValor(wValorTotal.getText());
			Double dValorTotalCalculado = iQtdeProdutos*dValorUnitario;
			Assert.assertEquals(dValorTotalCalculado, dValorTotal);
		}
	
//	******************* teste 09 *********************
		@Test
		public void confirmarDoisItensNaCesta() {
			String sNumeroDoTeste = "#0009";
			String[] lItensPesuisados = { "fogao", "geladeira" };

			for (int i = 0; i < lItensPesuisados.length; i++) {
				dsl.buscarProduto(lItensPesuisados[i]);
				dsl.clicarPrimeiroItemDaBusca();
				dsl.adicionarItemNoCarrinho();
			}
			dsl.entrarNoCarrinho();
			WebElement wQtdeItens = driver.findElement(By.id("nav-cart-count"));
			Integer iQtdeItens = Integer.parseInt(wQtdeItens.getText());
			if (lItensPesuisados.length == iQtdeItens) {
				Assert.assertTrue(true);
			} else {
				Assert.assertTrue(false);
			}
		}		
		
//	******************* teste 10 *********************
//	******************* teste 11 *********************
//	******************* teste 12 *********************
//	******************* teste 13 *********************
//	******************* teste 14 *********************
//	******************* teste 15 *********************

	
	
}
