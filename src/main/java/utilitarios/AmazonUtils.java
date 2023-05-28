package utilitarios;

import static utilitarios.DriverFactory.getDriver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonUtils {

	DSL dsl;
	WebDriverWait wait;
	ExcelUtils excel;
	
	

//	public AmazonUtils(WebDriver driver, DSL dsl) {
//		
//		this.driver = driver;
//		this.dsl = dsl;
//		this.wait = new WebDriverWait(driver, 30);
//		this.excel =excel = new ExcelUtils();
//	}
	public AmazonUtils() {
		
		getDriver();
		this.dsl = new DSL();
		
	}

	public void login() {
//		String[] credencial = excel.credenciais();
//		dsl.clicarElemento(By.id("nav-link-accountList-nav-line-1"));
//		dsl.escrever(By.id("ap_email"), credencial[0]);
//		dsl.clicarElemento(By.id("continue"));
//		dsl.escrever(By.id("ap_password"), credencial[1]);
//		dsl.clicarElemento(By.id("signInSubmit"));
//		Assert.assertEquals("Olá, Saulo", dsl.retornaConteudo(By.id("nav-link-accountList-nav-line-1")) );
		DriverFactory.getDriver().get("https://www.google.com.br/");
		System.out.println("olá mundo!"); 
	}

//	public void logout() {
//		login();
//		dsl.esperarPresencaElemento(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
//		WebElement sair = driver.findElement(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", sair);
//		Assert.assertEquals("Fazer login", dsl.retornaConteudo(By.xpath("//label[@for='ap_email']/../../h1")));
//	}
//
//
//	public void pesquisar(String sNomeProduto) {
//		dsl.escrever(By.xpath("//form[@name='site-search']//input[@type='text']"), sNomeProduto);
//		dsl.clicar(By.id("nav-search-submit-button"));
//	}
//
//	private int retornaProdutoExistente(String cenario) {
//		pesquisar(excel.buscarBanco(cenario));
//		dsl.esperarPresencaElemento(By.xpath("//div[@data-cel-widget='UPPER-RESULT_INFO_BAR-0']/.."));
//		WebElement retornoNaoEncontrado = dsl
//				.elemento(By.xpath("//div[@data-cel-widget='UPPER-RESULT_INFO_BAR-0']/.."));
//		String sAux = retornoNaoEncontrado.getAttribute("data-component-id");
//		int iAux = dsl.converterStringToInteiro(sAux);
//		return iAux;		
//	}
//	
//	public void validarProdutoExistente(String cenario) {
//		Assert.assertTrue(retornaProdutoExistente(cenario)!=1);
//	}
//	
//	public void validarMsgProdutoNaoEncontrado(String cenario) {	
//		retornaProdutoExistente(cenario);
//		Assert.assertEquals("Nenhum resultado para", dsl.retornaConteudo(By.xpath("//span[text()='Nenhum resultado para ']")));			
//	}
//
//	public void clicarPrimeiroProdutoBusca() {
//		dsl.esperarPresencaElemento(By.xpath("//div[@data-index='2']"));
//		dsl.clicar(By.xpath("//div[@data-index='2']"));
//	}
//
//	public void clicarEndereco() {
//		dsl.clicar(By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']"));
//	}
//
//	private void inserirCep(String cep) {
//
//		dsl.esperarPresencaElemento(By.id("GLUXZipUpdateInput_0"));
//		dsl.escrever(By.id("GLUXZipUpdateInput_0"), cep);
//		dsl.clicarElemento(By.id("GLUXZipUpdate"));
//	}
//
//	public void validarRetornoFrete_prazo(String cep) {
//		pesquisar(excel.buscarBanco("#0005"));
//		clicarPrimeiroProdutoBusca();
//		clicarEndereco();
//		inserirCep(cep);
//		dsl.esperarPresencaElemento(By
//				.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[contains(text(),'Enviar para')]"));
//		WebElement wCepOk = driver.findElement(By
//				.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[contains(text(),'" + cep + "')]"));
//		Assert.assertTrue(wCepOk.getText().contains(cep));
//	}
//
//	public void validaMsgCepErrado(String cep) {
//		pesquisar(excel.buscarBanco("#0006"));
//		clicarPrimeiroProdutoBusca();
//		clicarEndereco();		
//		inserirCep(cep);
//		WebElement wMsg =dsl.elemento(By.xpath("//div[text()='Insira um CEP válido']"));
//		dsl.esperarElementoVisivel(By.xpath("//div[text()='Insira um CEP válido']"));
//		Assert.assertEquals("Insira um CEP válido",dsl.retornaConteudo(By.xpath("//div[text()='Insira um CEP válido']")));
//		
//	}
//
//	public void adicionarProdutoCarrinho() {
//		WebElement wProdutoSelecionado = driver.findElement(By.id("productTitle"));
//		String sTituloProdutoAdicionado = wProdutoSelecionado.getText().toLowerCase();
//
//		WebElement wBtnAdcionarCarrinho = driver.findElement(By.id("add-to-cart-button"));
//		String sAtributoClass = wBtnAdcionarCarrinho.getAttribute("class");
//		if (sAtributoClass.equalsIgnoreCase("a-button a-spacing-small a-button-primary a-button-icon natc-enabled")) {
//			// cancela garantia
//			WebElement wBtnNaoIncluirGarantia = driver.findElement(By.id("attachSiNoCoverage-announce"));
//			wBtnNaoIncluirGarantia.click();
//		}
//		wBtnAdcionarCarrinho.click();
//	}
//
//	public void irParaCarrinho() {
//		dsl.clicar(By.id("nav-cart-count-container"));
//	}
//
//	public int obterQdeItensCarrinho() {
//		String conteudo = dsl.retornaConteudo(By.id("nav-cart-count"));
//		return dsl.converterStringToInteiro(conteudo);
//	}
//
//	public void validarItemCarrinho() {		
//		pesquisar(excel.buscarBanco("#0007"));
//		clicarPrimeiroProdutoBusca();
//		dsl.esperarPresencaElemento(By.id("productTitle"));
//		String sTitulo = dsl.retornaConteudo(By.id("productTitle"));
//		adicionarProdutoCarrinho();
//		irParaCarrinho();
//		dsl.esperarPresencaElemento(By.xpath("//div[@data-item-index='1']"));
//		Assert.assertTrue(dsl.retornaConteudo(By.xpath("//div[@data-item-index='1']")).contains(sTitulo));
//	}
//
//	private int aumentarQtdeCarrinho(int iQtdeProdutos) {
//		dsl.selecionarOpcao(By.id("quantity"), iQtdeProdutos);
//		return iQtdeProdutos;
//	}
//
//	public void validarValorTotalItem() {
//		pesquisar(excel.buscarBanco("#0008"));
//		clicarPrimeiroProdutoBusca();
//		adicionarProdutoCarrinho();
//		irParaCarrinho();
//		aumentarQtdeCarrinho(2);		
//		String valorSelecionado = dsl.valorSetadoCombo(By.id("quantity"));
//		int iQtdeProdutos = Integer.parseInt(valorSelecionado);
//		WebElement wValorUnitario = driver.findElement(By
//				.xpath("//div[@data-item-count='1']//div[@class='sc-list-item-content']//span[contains(text(),'R$')]"));
//		Double dValorUnitario = dsl.converterStringToDouble(wValorUnitario.getText(), " ");
//		dsl.esperarPresencaElemento(By.xpath("//span[contains(text(),'(" + iQtdeProdutos + " itens):') ] "));
//		WebElement wValorTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span"));
//		Double dValorTotal = dsl.converterStringToDouble(wValorTotal.getText(), " ");
//		Assert.assertTrue((dValorUnitario*2)== dValorTotal);
//	}
//
//	public void excluirItemCarrinho() {
//
//		dsl.clicar(By.xpath("//div[@data-item-index='1']//span[@data-action='delete']//input"));
//	
//	}
//
//	public void validarMensagemCarinhoVazio() {
//		pesquisar(excel.buscarBanco("#0010"));
//		clicarPrimeiroProdutoBusca();
//		adicionarProdutoCarrinho();
//		irParaCarrinho();
//		excluirItemCarrinho();		
//		dsl.esperarPresencaElemento(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
//		String msg = dsl.retornaConteudo(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
//		Assert.assertEquals("Seu carrinho de compras da Amazon está vazio.", msg);
//	}
//
//	public void fecharPedido() {
//		dsl.clicar(By.name("proceedToRetailCheckout"));
////		WebElement wTelaDeLogin = driver.findElement(By.xpath("//h1"));
//	}
//
//	public void msgNecessarioLogin() {		
//		pesquisar(excel.buscarBanco("#0012"));
//		clicarPrimeiroProdutoBusca();
//		adicionarProdutoCarrinho();
//		irParaCarrinho();
//		fecharPedido();
//		Assert.assertEquals("Fazer login", dsl.retornaConteudo(By.xpath("//h1")));	
//	}
//
//	public void abrirMenuLateral() {
//		dsl.clicar(By.xpath("//*[@id='nav-main']/div/a"));
//
//	}
//
//	public void clicarCategoria(String valor) {
//		String categoria = valor;
//		dsl.esperarPresencaElemento(
//				By.xpath("//div[@id='hmenu-content']/ul//div[contains(text(),'" + categoria + "')]"));
//		dsl.clicarComJS(By.xpath("//div[@id='hmenu-content']/ul//div[contains(text(),'" + categoria + "')]"));
//	}
//
//	public void ordenarPreco(String... opcao) {
//
//		dsl.esperarPresencaElemento(By.id("a-autoid-0-announce"));
//		dsl.clicar(By.id("a-autoid-0-announce"));
//
//		if (opcao[0].equalsIgnoreCase("maior") && opcao[1].equalsIgnoreCase("menor")) {
//			dsl.clicar(By.xpath("//option[text()='Preço: alto a baixo']"));
//		}
//	}
//
//	public void validarOndenacaoPrecoMaiorMenor() {
//		abrirMenuLateral();
//		clicarCategoria("Informática");
//		clicarSubCateoria("Notebooks");
//		selecionarMarca("Lenovo");	
//		ordenarPreco("maior", "menor");
//		
//		Double[] valor = { 0.0, 0.0 };
//		for (int i = 1; i <= 2; i++) {
//			dsl.esperarPresencaElemento(By.xpath("//div[@data-index='" + i + "']"));
//			String conteudo = dsl.retornaConteudo(By.xpath("//div[@data-index='" + i + "']")).toLowerCase();
//			if (conteudo.contains("r$")) {
//				String valorTexto = dsl
//						.retornaConteudo(By.xpath("//div[@data-index='" + i + "']//span[@aria-hidden='true']/span[2]"));
//				valor[i - 1] = Double.parseDouble(valorTexto);
//			}
//		}
//		Assert.assertTrue(valor[0] > valor[1]);
//	}
//
//	public void clicarSubCateoria(String subCategoria) {
//		String sErroPagina = driver.getCurrentUrl();
//		if (sErroPagina.contains("site-directory?ref=nav_em_linktree_fail")) {
//			do {
//				dsl.esperarPresencaElemento(By.xpath("//div[@id='h']//a"));
//				dsl.clicar(By.xpath("//div[@id='h']//a"));
//				dsl.esperarPresencaElemento(By.xpath("//*[@id='nav-main']/div/a"));
//				abrirMenuLateral();
//				clicarCategoria("Informática");
//				sErroPagina = driver.getCurrentUrl();
//			} while (sErroPagina.contains("site-directory?ref=nav_em_linktree_fail"));
//		}
//
//		dsl.esperarPresencaElemento(By.xpath("//a[text()='" + subCategoria + "']"));
//		dsl.clicar(By.xpath("//a[text()='" + subCategoria + "']"));
//	}
//
//	public void validaReornoResultadoBuscaCatehoria() {
//		
//		abrirMenuLateral();
//		clicarCategoria("Informática");
//		clicarSubCateoria(excel.buscarBanco("#0013"));		
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'resultados para')]")));
//		String sResultado = dsl.retornaConteudo(By.xpath("//span[contains(text(),'resultados para')]/.."));
//		System.out.println("resultao = "+sResultado);
//		Assert.assertTrue(sResultado.contains("resultados para "+excel.buscarBanco("#0013")));
//		
//	}
//
//	public void selecionarMarca(String marca) {
//		dsl.esperarPresencaElemento(By.xpath("//span[text()='Marca']/../../ul//*[text()='Lenovo']"));
//		dsl.clicarComJS(By.xpath("//span[text()='Marca']/../../ul//*[text()='Lenovo']"));
//	}
//
//	public void validarPrimeiroProdutoMarcaLenovo() {
//		abrirMenuLateral();
//		clicarCategoria("Informática");
//		clicarSubCateoria("Notebooks");
//		selecionarMarca("Lenovo");		
//		dsl.esperarPresencaElemento(By.xpath("//div[@data-index='1']"));
//		String sDescritivo = dsl.retornaConteudo(By.xpath("//div[@data-index='1']"));
//		Assert.assertTrue(sDescritivo.contains("Lenovo"));
//	}
//	@Test
//	public void validarDoisItensNaCesta() {
//		String sItem1 = excel.buscarBanco("#0009-1");
//		String sItem2 = excel.buscarBanco("#0009-2");
//		adcionarVariosItensCarrinho(sItem1,sItem2);
//		irParaCarrinho();
//		Assert.assertTrue(obterQdeItensCarrinho()== 2);
//	}
//	
//	private void adcionarVariosItensCarrinho(String... itens) {
//		for (int i = 0; i < itens.length; i++) {
//			pesquisar(itens[i]);
//			clicarPrimeiroProdutoBusca();
//			adicionarProdutoCarrinho();
//		}	
//	}
//	
//	public void validarQuantidadeItensCarinho() {
//		String sNumeroDoTeste = "#0011";
//		String produto1 = excel.buscarBanco("#0011-1");
//		String produto2 = excel.buscarBanco("#0011-2");
//		adcionarVariosItensCarrinho(produto1, produto2);
//		irParaCarrinho();
//		excluirItemCarrinho();
//		Assert.assertTrue(obterQdeItensCarrinho()==1);
//	}

}//Fim da Classe
