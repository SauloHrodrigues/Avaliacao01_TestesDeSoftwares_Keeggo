package casosDeTestes;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilitarios.AmazonUtils;
import utilitarios.DSL_antiga;
import utilitarios.DSL;



public class RascunhosCasosDeTestes {
	WebDriver driver;
	WebDriverWait wait;
	DSL_antiga dsl_1;
	DSL dsl;
	AmazonUtils amazon;
	String sEmail = "saulohrodrigues@gmail.com";
	String sSenha = "5@123456";

	@Before
	public void inicioPadrao() {
		driver = new ChromeDriver();
		dsl = new DSL(driver);
		wait = new WebDriverWait(driver, 30);
		amazon = new AmazonUtils(driver, dsl);
		boolean bValidaSite = false;
		while (bValidaSite == false) {
			driver.get("https://www.amazon.com.br/");
			WebElement wCaixaPesquisa = driver
					.findElement(By.xpath("//form[@name='site-search']//input[@type='text']"));
			String sIdentificaPagina = wCaixaPesquisa.getAttribute("id");
			if (sIdentificaPagina.equalsIgnoreCase("twotabsearchtextbox")) {
				driver.manage().window().maximize();
				bValidaSite = true;
			} else {
				System.out.println("Site errado!!");
				 driver.quit();
			}
		}
	}


	@After
	public void finalizacaoPadrao() {
		 driver.quit();
	}
	
	
	

//	****************** Teste 01 ********************
	@Test
	public void validarLogimAmazon() {
		String sNumeroDoTeste = "#0001";
		amazon.login(sEmail, sSenha);
		Assert.assertEquals("Olá, Saulo", dsl.retornaConteudo(By.id("nav-link-accountList-nav-line-1")) );
	} 

//	****************** Cenário 02 ********************
	@Test
	public void validarLogaltAmazon() {
		String sNumeroDoTeste = "#0002";
		amazon.login(sEmail, sSenha);
		amazon.logut();
		Assert.assertEquals("Fazer login", dsl.retornaConteudo(By.xpath("//label[@for='ap_email']/../../h1")));
	}

//	****************** Teste 03 REVISADO ********************

	@Test
	public void validarItemEncontrado() {
		String sNumeroDoTeste = "#0003";
		Assert.assertTrue(amazon.validarProdutoEncontrado("'ITEMINEXISTENTE012345'"));
	}

//	****************** Teste 04 - REVISADO ********************
	@Test
	public void validarConteudoInexistente() {
		String sNumeroDoTeste = "#0004";
		amazon.pesquisar("'ITEMINEXISTENTE012345'");
		Assert.assertEquals("Nenhum resultado para", dsl.retornaConteudo(By.xpath("//span[text()='Nenhum resultado para ']")));				
	}

//	****************** Teste 05 - REVISADO  ****************************
	@Test
	public void validarPrazoFrete() {
		String sNumeroDoTeste = "#0005";
		String sCpePesquisado = "13091561";
		amazon.pesquisar("frigideira");
		amazon.clicarPrimeiroProdutoBusca();
		amazon.clicarEndereco();
		Assert.assertTrue(amazon.validarCalculoFrete(sCpePesquisado));
	} 
	
//	**************** teste 06 - REVISADO ********************
	@Test
	public void validarMensagemCepInvalido() {
		String sNumeroDoTeste = "#0006";
		String sCEP = "000000000";
		amazon.pesquisar("Copo");
		amazon.clicarPrimeiroProdutoBusca();
		amazon.clicarEndereco();
		Assert.assertEquals("Insira um CEP válido", amazon.validarCepErrado(sCEP));
	}

//	******************* teste 07 - REVISADO *********************
	@Test
	public void conferirIntemNoCarrinho() {
		String sNumeroDoTeste = "#0007";
		String sProdutoPesquisado ="limpa vidro";
		amazon.pesquisar(sProdutoPesquisado);
		amazon.clicarPrimeiroProdutoBusca();
		amazon.adicionarProdutoCarrinho();
		amazon.irParaCarrinho();
		amazon.retornaItemCarrinho();
		Assert.assertTrue(amazon.retornaItemCarrinho().contains(sProdutoPesquisado));
	}

	// ************************ TESTE 08 - VALIDADO *******************
	@Test
	public void validarPrecoDobrado() {
		String sNumeroDoTeste = "#0008";
		amazon.pesquisar("Lapis");
		amazon.clicarPrimeiroProdutoBusca();
		amazon.adicionarProdutoCarrinho();
		amazon.irParaCarrinho();
		amazon.aumentarQtdeCarrinho(3);
		Assert.assertTrue(amazon.validarValorTotalItem());	
	}

//	******************* teste 09 - REVISADO *************************

	@Test
	public void confirmarDoisItensNaCesta() {
		
		String sNumeroDoTeste = "#0009";
		String sItem1 = "fogão";
		String sItem2 = "Geladeira";
		
		String[] sItensPesuisados = { sItem1, sItem2 };

		for (int i = 0; i < sItensPesuisados.length; i++) {
			amazon.pesquisar(sItensPesuisados[i]);
			amazon.clicarPrimeiroProdutoBusca();
			amazon.adicionarProdutoCarrinho();
		}
		amazon.irParaCarrinho();
		Assert.assertTrue(amazon.obterQdeItensCarrinho()== sItensPesuisados.length);
		
	}

//	******************* teste 10 - REVISADO *********************

	@Test
	public void excluirItemDoCarrinho() throws InterruptedException {
		String sNumeroDoTeste = "#0010";
		dsl_1.buscarProduto("garrafa");
		dsl_1.clicarPrimeiroItemDaBusca();
		dsl_1.adicionarItemNoCarrinho();
		dsl_1.entrarNoCarrinho();
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
		dsl_1.buscarProduto("fogao");
		dsl_1.clicarPrimeiroItemDaBusca();
		dsl_1.adicionarItemNoCarrinho();
		dsl_1.buscarProduto("bicicleta");
		dsl_1.clicarPrimeiroItemDaBusca();
		dsl_1.adicionarItemNoCarrinho();
		dsl_1.entrarNoCarrinho();
		dsl_1.removerItemDoCarrinho(1);
		Assert.assertTrue(dsl_1.obterQuantidadeItensCarrinho()==1);
	}
//	******************* teste 12 - REVISADO*********************
	@Test
	public void validarNescessidadeDeLogim() {
		String sNumeroDoTeste = "#0012";
		String sProdutoPesquisado = "televisao";
		dsl_1.buscarProduto(sProdutoPesquisado);
		dsl_1.clicarPrimeiroItemDaBusca();
		dsl_1.adicionarItemNoCarrinho();
		dsl_1.entrarNoCarrinho();
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
		dsl_1.clicarComJS(wInformatica);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Notebooks']")));
		WebElement wNotebook = driver.findElement(By.xpath("//a[text()='Notebooks']"));
		dsl_1.clicarComJS(wNotebook);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'resultados para')]")));
		WebElement wResultado = driver.findElement(By.xpath("//span[contains(text(),'resultados para')]"));
		String[] sR1 = wResultado.getText().split("-");
		Integer iResultado = Integer.parseInt(sR1[0]);
		Assert.assertTrue(iResultado > 0);
	}

//	******************* teste 14 *********************
	
//	******************* teste 15 *********************

}
