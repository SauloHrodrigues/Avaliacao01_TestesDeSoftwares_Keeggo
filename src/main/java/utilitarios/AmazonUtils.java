package utilitarios;

import org.hamcrest.core.StringContains;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonUtils {
	WebDriver driver;
	DSL dsl;
	WebDriverWait wait;

	public AmazonUtils(WebDriver driver, DSL dsl) {
		super();
		this.driver = driver;
		this.dsl = dsl;
		this.wait = new WebDriverWait(driver, 30);
	}

	public void login(String email, String senha) {
		dsl.clicarElemento(By.id("nav-link-accountList-nav-line-1"));
		dsl.escrever(By.id("ap_email"), email);
		dsl.clicarElemento(By.id("continue"));
		dsl.escrever(By.id("ap_password"), senha);
		dsl.clicarElemento(By.id("signInSubmit"));
	}

	public void logut() {
		WebElement sair = driver.findElement(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", sair);
	}

	public void pesquisar(String sNomeProduto) {
		WebElement wBtnPesquisar;
		WebElement wCaixaPesquisa = dsl.elemento(By.xpath("//form[@name='site-search']//input[@type='text']"));
		String sIdentificaPagina = wCaixaPesquisa.getAttribute("id");
		if (sIdentificaPagina.equalsIgnoreCase("twotabsearchtextbox")) {
			wCaixaPesquisa.sendKeys(sNomeProduto);
			wBtnPesquisar = dsl.elemento(By.id("nav-search-submit-button"));
//			dsl.clicarElemento(By.id("nav-search-submit-button"));

		} else {
			wCaixaPesquisa.sendKeys(sNomeProduto);
//			dsl.clicarElemento(By.xpath("//input[@value='Ir'][@type='submit']"));
			wBtnPesquisar = dsl.elemento(By.xpath("//input[@value='Ir'][@type='submit']"));
		}
		wBtnPesquisar.click();

	}

	public String msgProdutoInexiste() {
		dsl.esperarPresencaElemento(By.xpath("//div[@data-cel-widget='UPPER-RESULT_INFO_BAR-0']/.."));
		WebElement retornoNaoEncontrado = dsl
				.elemento(By.xpath("//div[@data-cel-widget='UPPER-RESULT_INFO_BAR-0']/.."));
		String aux = retornoNaoEncontrado.getAttribute("data-component-id");
		if (aux.equalsIgnoreCase("1")) {
			return "Nenhum resultado para";
		} else {
			return null;
		}

	}

	public void clicarPrimeiroProdutoBusca() {
		dsl.esperarPresencaElemento(By.xpath("//div[@data-component-id='2']"));
		dsl.clicar(By.xpath("//div[@data-component-id='2']"));
	}

	public void clicarEndereco() {
		dsl.clicar(By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']"));
	}

	public void inserirCep(String cep) {

		dsl.esperarPresencaElemento(By.id("GLUXZipUpdateInput_0"));
		dsl.escrever(By.id("GLUXZipUpdateInput_0"), cep);
		dsl.clicarElemento(By.id("GLUXZipUpdate"));
	}

	public boolean validarCalculoFrete(String cep) {//
		inserirCep(cep);
		dsl.esperarPresencaElemento(By
				.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[contains(text(),'Enviar para')]"));
		WebElement wCepOk = driver.findElement(By
				.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[contains(text(),'" + cep + "')]"));
		System.out.println("Variavel = " + wCepOk.getText());
		if (wCepOk.getText().contains(cep)) {
			return true;
		} else {
			return false;
		}
	}

	public String validarCepErrado(String cep) {
		inserirCep(cep);
		WebElement wMsg = driver.findElement(By.xpath("//div[text()='Insira um CEP válido']"));
		wait.until(ExpectedConditions.visibilityOf(wMsg));
		return wMsg.getText();
	}

	public void adicionarProdutoCarrinho() {
		WebElement wProdutoSelecionado = driver.findElement(By.id("productTitle"));
		String sTituloProdutoAdicionado = wProdutoSelecionado.getText().toLowerCase();

		WebElement wBtnAdcionarCarrinho = driver.findElement(By.id("add-to-cart-button"));
		String sAtributoClass = wBtnAdcionarCarrinho.getAttribute("class");
		if (sAtributoClass.equalsIgnoreCase("a-button a-spacing-small a-button-primary a-button-icon natc-enabled")) {
			// cancela garantia
			WebElement wBtnNaoIncluirGarantia = driver.findElement(By.id("attachSiNoCoverage-announce"));
			wBtnNaoIncluirGarantia.click();
		}
		wBtnAdcionarCarrinho.click();
	}

	public void irParaCarrinho() {
		dsl.clicar(By.id("nav-cart-count-container"));
	}

	public int obterQdeItensCarrinho() {
		String conteudo = dsl.retornaConteudo(By.id("nav-cart-count"));
		return dsl.converterStringToInteiro(conteudo);
	}

	public String retornaItemCarrinho() {
		dsl.esperarPresencaElemento(By.xpath("//div[@data-item-index='1']"));
		return dsl.retornaConteudo(By.xpath("//div[@data-item-index='1']")).toLowerCase();
	}

	public int aumentarQtdeCarrinho(int iQtdeProdutos) {
		dsl.selecionarOpcao(By.id("quantity"), iQtdeProdutos);
		return iQtdeProdutos;
	}

	public boolean validarValorTotalItem() {
		String valorSelecionado = dsl.valorSetadoCombo(By.id("quantity"));
		int iQtdeProdutos = Integer.parseInt(valorSelecionado);
		WebElement wValorUnitario = driver.findElement(By
				.xpath("//div[@data-item-count='1']//div[@class='sc-list-item-content']//span[contains(text(),'R$')]"));
		Double dValorUnitario = dsl.converterStringToDouble(wValorUnitario.getText(), " ");
		dsl.esperarPresencaElemento(By.xpath("//span[contains(text(),'(" + iQtdeProdutos + " itens):') ] "));
		WebElement wValorTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span"));
		Double dValorTotal = dsl.converterStringToDouble(wValorTotal.getText(), " ");
		if ((dValorUnitario * iQtdeProdutos) == dValorTotal) {
			return true;
		} else {
			return false;
		}
	}

	public void excluirItemCarrinho() {
//		WebElement wDeletarDoCarrinho = driver
//				.findElement(By.xpath("//div[@data-item-index='1']//span[@data-action='delete']//input"));
//		wDeletarDoCarrinho.click();
		dsl.clicar(By.xpath("//div[@data-item-index='1']//span[@data-action='delete']//input"));
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]")));

//		WebElement wMsgItemRemovido = driver
//				.findElement(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
//		
	}

	public String retornaMsgCarrinho() {
		dsl.esperarPresencaElemento(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
		String msg = dsl.retornaConteudo(By.xpath("//div[@id='sc-active-cart']//h1[contains(text(),'está vazio.')]"));
		return msg;
	}

	public void fecharPedido() {
		dsl.clicar(By.name("proceedToRetailCheckout"));
//		WebElement wTelaDeLogin = driver.findElement(By.xpath("//h1"));
	}

	public String msgNecessarioLogin() {
		String msg = dsl.retornaConteudo(By.xpath("//h1"));
		return msg;
	}

	public void abrirMenuLateral() {
		dsl.clicar(By.xpath("//*[@id='nav-main']/div/a"));

	}

	public void clicarCategoria(String valor) {
		String categoria = valor;
		dsl.esperarPresencaElemento(
				By.xpath("//div[@id='hmenu-content']/ul//div[contains(text(),'" + categoria + "')]"));
		dsl.clicarComJS(By.xpath("//div[@id='hmenu-content']/ul//div[contains(text(),'" + categoria + "')]"));
	}

	public void ordenarPreco(String... opcao) {

		dsl.esperarPresencaElemento(By.id("a-autoid-0-announce"));
		dsl.clicar(By.id("a-autoid-0-announce"));

		if (opcao[0].equalsIgnoreCase("maior") && opcao[1].equalsIgnoreCase("menor")) {
			dsl.clicar(By.xpath("//option[text()='Preço: alto a baixo']"));
		}
	}

	public boolean confirmarOndenacao() {
		Double[] valor = { 0.0, 0.0 };
		for (int i = 1; i <= 2; i++) {
			dsl.esperarPresencaElemento(By.xpath("//div[@data-index='" + i + "']"));
			String conteudo = dsl.retornaConteudo(By.xpath("//div[@data-index='" + i + "']")).toLowerCase();
			if (conteudo.contains("r$")) {
				String valorTexto = dsl
						.retornaConteudo(By.xpath("//div[@data-index='" + i + "']//span[@aria-hidden='true']/span[2]"));
				valor[i - 1] = Double.parseDouble(valorTexto);
			}
		}
		if (valor[0] > valor[1]) {
			return true;
		} else {
			return false;
		}
	}

	public void clicarSubCateoria(String subCategoria) {
		String sErroPagina = driver.getCurrentUrl();
		if (sErroPagina.contains("site-directory?ref=nav_em_linktree_fail")) {
			do {
				dsl.esperarPresencaElemento(By.xpath("//div[@id='h']//a"));
				dsl.clicar(By.xpath("//div[@id='h']//a"));
				dsl.esperarPresencaElemento(By.xpath("//*[@id='nav-main']/div/a"));
				abrirMenuLateral();
				clicarCategoria("Informática");
				sErroPagina = driver.getCurrentUrl();
			} while (sErroPagina.contains("site-directory?ref=nav_em_linktree_fail"));
		}

		dsl.esperarPresencaElemento(By.xpath("//a[text()='" + subCategoria + "']"));
		dsl.clicar(By.xpath("//a[text()='" + subCategoria + "']"));
	}

	public boolean validaReornoResultado() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'resultados para')]")));
//		WebElement wResultado = driver.findElement(By.xpath("//span[contains(text(),'resultados para')]"));
		String sResultado = dsl.retornaConteudo(By.xpath("//span[contains(text(),'resultados para')]/.."));
//		System.out.println("resultao = "+
//				dsl.retornaConteudo(By.xpath("//span[contains(text(),'resultados para')]/..")));
		if (sResultado.contains("1-") && sResultado.contains("mais de ") && sResultado.contains("resultados para")) {
			return true;
		} else {
			return false;
		}
	}

	public void selecionarMarca(String marca) {
		dsl.esperarPresencaElemento(By.xpath("//span[text()='Marca']/../../ul//*[text()='Lenovo']"));
		dsl.clicarComJS(By.xpath("//span[text()='Marca']/../../ul//*[text()='Lenovo']"));
	}

	public String validarPrimeiroProduto() {
		dsl.esperarPresencaElemento(By.xpath("//div[@data-index='1']"));
		String sDescritivo = dsl.retornaConteudo(By.xpath("//div[@data-index='1']"));
		return sDescritivo;
	}

}
