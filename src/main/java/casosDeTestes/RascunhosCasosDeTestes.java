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
		// driver.quit();
	}

//	****************** Teste 01 ********************
	@Test
	public void Teste01_LogimAmazon() {
		String sNumeroDoTeste = "#0001";
		Assert.assertEquals("Olá, Saulo", dsl.LogarAmazon(sEmail, sSenha));
	}

//	****************** Teste 02 ********************
	@Test
	public void Teste02_RealizarLogaltAmazon() {
		String sNumeroDoTeste = "#0002";
		dsl.LogarAmazon(sEmail, sSenha);
		dsl.fazeLogut();
		WebElement wTelaLogar = driver.findElement(By.xpath("//label[@for='ap_email']/../../h1"));
		Assert.assertEquals("Fazer login", wTelaLogar.getText());
	}

//	****************** Teste 03 REVISADO ********************

	@Test
	public void validarItemEncontrado() {
		String sNumeroDoTeste = "#0003";
		dsl.buscarProduto("bicicleta");
		Assert.assertTrue(dsl.validarProdutoEncontrado());
	}

//	****************** Teste 04 - REVISADO ********************
	@Test
	public void validarConteudoInexistente() {
		String sNumeroDoTeste = "#0004";
		dsl.buscarProduto("'ITEMINEXISTENTE012345'");
		Assert.assertEquals("Nenhum resultado para",
				dsl.obterConteudo(By.xpath("//span[text()='Nenhum resultado para ']")));
	}

//	****************** Teste 05 - REVISADO  ****************************
	@Test
	public void validarRetornoFretePrazo() throws InterruptedException {
		String sNumeroDoTeste = "#0005";
		String sCpePesquisado = "000000";
		dsl.buscarProduto("frigideira");
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@data-component-id='2']")));
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@data-component-id='2']"));
		wPrimeiroElemento.click();
		WebElement wEndereco = driver.findElement(By.id("cipInsideDeliveryBlock_feature_div"));
		wEndereco.click();
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("GLUXZipUpdateInput_0")));
		WebElement wCampoCEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
		wCampoCEP.sendKeys(sCpePesquisado);
		WebElement wBotaoConfirma = driver.findElement(By.id("GLUXZipUpdate"));
		wait.until(ExpectedConditions.visibilityOf(wBotaoConfirma));
		wBotaoConfirma.click();
		WebElement wResposta = driver.findElement(By.xpath("//span[@id='GLUXZipError']"));
		wait.until(ExpectedConditions.visibilityOf(wResposta));
		Assert.assertEquals("Insira um CEP válido", wResposta.getText());
	}

//	**************** teste 06 - REVISADO ********************
	@Test
	public void validarMensagemCepInvalido() throws InterruptedException {
		String sNumeroDoTeste = "#0006";
		String sCEP = "000000000";
		dsl.buscarProduto("copo");
		dsl.clicarPrimeiroItemDaBusca();
		WebElement wSelecionarEndereco = driver.findElement(By.id("contextualIngressPtLabel_deliveryShortLine"));
		wait.until(ExpectedConditions.visibilityOf(wSelecionarEndereco));
		wSelecionarEndereco.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("GLUXZipUpdateInput_0")));// VER AULA 63
		WebElement wCEEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
		wCEEP.sendKeys(sCEP);
		driver.findElement(By.id("GLUXZipUpdate")).click();
		WebElement wMsg = driver.findElement(By.xpath("//div[text()='Insira um CEP válido']"));
		wait.until(ExpectedConditions.visibilityOf(wMsg));
		Assert.assertEquals("Insira um CEP válido", wMsg.getText());
	}

//	******************* teste 07 - REVISADO *********************
	@Test
	public void conferirIntemNoCarrinho() throws InterruptedException {
		String sNumeroDoTeste = "#0007";
		String sProdutoPesquisado ="limpa vidro";
		dsl.buscarProduto(sProdutoPesquisado);
		dsl.clicarPrimeiroItemDaBusca();
		WebElement wProdutoSelecionado = driver.findElement(By.id("productTitle"));
		String sTituloProdutoSelecionado = wProdutoSelecionado.getText().toLowerCase();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();	
		WebElement wItemCarrinho = driver.findElement(By.xpath("//div[@data-item-index='1']"));
		String sTituloProdutoCarrinho = wItemCarrinho.getText().toLowerCase();
		Assert.assertTrue(sTituloProdutoCarrinho.contains(sTituloProdutoSelecionado));
	}

	// ************************ TESTE 08 - VALIDADO*******************
	@Test
	public void aumentarQuantidadeValidarPreco() throws InterruptedException {
		String sNumeroDoTeste = "#0008";
		dsl.buscarProduto("Lapis de cor");
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();
		WebElement element = driver.findElement(By.id("quantity"));
		Select combo = new Select(element);
		int iQtdeProdutos = 2;
		combo.selectByIndex(iQtdeProdutos);

		WebElement wValorUnitario = driver.findElement(By
				.xpath("//div[@data-item-count='1']//div[@class='sc-list-item-content']//span[contains(text(),'R$')]"));
		String vu = wValorUnitario.getText();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'("+
		iQtdeProdutos+" itens):') ] ")));
		WebElement wValorTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span"));
		String vt = wValorTotal.getText();
		double dValorUnidadeConvertido = dsl.conversorTextoEmValor(vu);
		double dValorTotalConvertido = dsl.conversorTextoEmValor(vt);
		Assert.assertTrue(dValorUnidadeConvertido*iQtdeProdutos == dValorTotalConvertido);
	}

//	******************* teste 09 - REVISADO *************************
	@Test
	public void confirmarDoisItensNaCesta() {
		String sNumeroDoTeste = "#0009";
		String sItem1 = "fogão";
		String sItem2 = "Geladeira";
		
		String[] lItensPesuisados = { sItem1, sItem2 };

		for (int i = 0; i < lItensPesuisados.length; i++) {
			dsl.buscarProduto(lItensPesuisados[i]);
			dsl.clicarPrimeiroItemDaBusca();
			dsl.adicionarItemNoCarrinho();
		}
		dsl.entrarNoCarrinho();
		Assert.assertTrue(dsl.obterQuantidadeItensCarrinho()==2);
	}

//	******************* teste 10 - REVISADO *********************

	@Test
	public void excluirItemDoCarrinho() throws InterruptedException {
		String sNumeroDoTeste = "#0010";
		dsl.buscarProduto("garrafa");
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();
		WebElement wDeletarDoCarrinho = driver
				.findElement(By.xpath("//div[@data-item-index='1']//span[@data-action='delete']//input"));
		wDeletarDoCarrinho.click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]")));
		WebElement wMsgItemRemovido = driver
				.findElement(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
		Assert.assertEquals("Seu carrinho de compras da Amazon está vazio.", wMsgItemRemovido.getText());
	}

//	******************* teste 11 - REVISADO *********************
	@Test
	public void validarQuantidadeDeProdutosNoCarrinho() {
		dsl.buscarProduto("fogao");
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.buscarProduto("bicicleta");
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();
		dsl.removerItemDoCarrinho(1);
		Assert.assertTrue(dsl.obterQuantidadeItensCarrinho()==1);
	}
//	******************* teste 12 - REVISADO*********************
	@Test
	public void validarNescessidadeDeLogim() {
		String sNumeroDoTeste = "#0012";
		String sProdutoPesquisado = "televisao";
		dsl.buscarProduto(sProdutoPesquisado);
		dsl.clicarPrimeiroItemDaBusca();
		dsl.adicionarItemNoCarrinho();
		dsl.entrarNoCarrinho();
		WebElement wBtnFecharPedido = driver.findElement(By.name("proceedToRetailCheckout"));
		wBtnFecharPedido.click();
		WebElement wTelaDeLogin = driver.findElement(By.xpath("//h1"));
		Assert.assertEquals("Fazer login", wTelaDeLogin.getText());
	}

//	******************* teste 13 - REVISADO *************************
	@Test
	public void validarBuscaCategoriaDepartamento() throws InterruptedException {
		String sIdTeste = "#0013";
		WebElement wBtnMenu = driver.findElement(By.xpath("//*[@id='nav-main']/div/a"));
		wBtnMenu.click();
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//div[@id='hmenu-content']/ul//a[@data-menu-id='16']")));
		WebElement wInformatica = driver.findElement(By.xpath("//div[@id='hmenu-content']/ul//a[@data-menu-id='16']"));
		dsl.clicarComJS(wInformatica);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Notebooks']")));
		WebElement wNotebook = driver.findElement(By.xpath("//a[text()='Notebooks']"));
		dsl.clicarComJS(wNotebook);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'resultados para')]")));
		WebElement wResultado = driver.findElement(By.xpath("//span[contains(text(),'resultados para')]"));
		String[] sR1 = wResultado.getText().split("-");
		Integer iResultado = Integer.parseInt(sR1[0]);
		Assert.assertTrue(iResultado > 0);
	}

//	******************* teste 14 *********************
	
//	******************* teste 15 *********************

}
