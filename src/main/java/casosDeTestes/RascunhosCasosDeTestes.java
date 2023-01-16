package casosDeTestes;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class RascunhosCasosDeTestes {
	WebDriver driver;
	String sEmail = "saulohrodrigues@gmail.com";
	String sSenha = "5@123456";

	@Before
	public void inicioPadrao() {
		driver = new FirefoxDriver();
		driver.get("https://www.amazon.com.br/");
		driver.manage().window().maximize();
	}

	@After
	public void finalizacaoPadrao() {
		//driver.quit();
	}

	@Ignore
	@Test
	public void Teste01_LogimAmazon() {
		WebElement wFazerLogin = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		wFazerLogin.click();
		WebElement wCaixaDeEmail = driver.findElement(By.id("ap_email"));
		wCaixaDeEmail.sendKeys(sEmail);
		WebElement wBotaoContinuar = driver.findElement(By.id("continue"));
		wBotaoContinuar.click();
		WebElement wCaixaDeSenha = driver.findElement(By.id("ap_password"));
		wCaixaDeSenha.sendKeys(sSenha);
		WebElement wBotaoFazerLogin = driver.findElement(By.id("signInSubmit"));
		wBotaoFazerLogin.click();
		WebElement wNomeDoUsuario = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		Assert.assertEquals("Olá, Saulo", wNomeDoUsuario.getText());
	}

	// teste 02 - Realizar logout na aplicação

	@Test // Incompleto
	@Ignore
	public void Teste02_LogoutAmazon() {
		WebElement wFazerLogin = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		wFazerLogin.click();
		WebElement wCaixaDeEmail = driver.findElement(By.id("ap_email"));
		wCaixaDeEmail.sendKeys(sEmail);
		WebElement wBotaoContinuar = driver.findElement(By.id("continue"));
		wBotaoContinuar.click();
		WebElement wCaixaDeSenha = driver.findElement(By.id("ap_password"));
		wCaixaDeSenha.sendKeys(sSenha);
		WebElement wBotaoFazerLogin = driver.findElement(By.id("signInSubmit"));
		wBotaoFazerLogin.click();
		WebElement wNomeDoUsuario = driver.findElement(By.id("nav-link-accountList-nav-line-1"));
		WebElement wSairDaConta = driver.findElement(By.id("nav-item-signout"));
		wSairDaConta.click();
		Assert.assertEquals("Olá, Saulo", wNomeDoUsuario.getText());
	}

	/*
	 * Teste 03 - Realizar a consulta pelo item “bicicleta aro 29” no campo de busca
	 * e validar a aparição de ao menos um item no catálogo de resultados de itens.
	 */

	@Test
	@Ignore //ok
	public void teste03_BuscaEValidacao() {
		WebElement wCaixaDePesquisa = driver.findElement(By.id("twotabsearchtextbox"));
		wCaixaDePesquisa.sendKeys("bicicleta aro 29");
		WebElement wLupaDePesquisar = driver.findElement(By.id("nav-search-submit-button"));
		wLupaDePesquisar.click();
		//// div[@class="a-section a-spacing-small a-spacing-top-small"]
		WebElement wResultado = driver
				.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']/span[1]"));

	}
	
	/*Teste 04
	 * Descrição: Realizar a consulta pelo item “ITEM_INEXISTENTE_012345” no campo de busca e 
	 * validar a aparição da mensagem de que não foram encontrados resultados
	 * */
	@Test
	@Ignore //ok
	public void teste04_BuscaEValidacaoConteudoInexistente() {
		WebElement wCaixaDePesquisa = driver.findElement(By.id("twotabsearchtextbox"));
		wCaixaDePesquisa.sendKeys("'ITEMINEXISTENTE012345'");
		WebElement wLupaDePesquisar = driver.findElement(By.id("nav-search-submit-button"));
		wLupaDePesquisar.click();
		WebElement wResultado = driver.findElement(By.xpath("//span[text()='Nenhum resultado para ']"));
		System.out.println("getValue = " + wResultado.getAttribute("value"));
		Assert.assertEquals("Nenhum resultado para", wResultado.getText());
	}
	
	/*	Teste 05.
	 *  Descrição: Realizar a consulta pelo item “frigideira” no campo de busca, clicar no
	 *  primeiro item disponível, digitar o CEP “06010-067” e validar o retorno da(s) 
	 *  informação(ões) de frete/prazo.
	 * */
	@Test  // => REVISAR RESULTADO
	@Ignore
	public void teste05_ValidarRetornoFretePrazo() throws InterruptedException {
		String sCpePesquisado = "06010067";
		WebElement wCaixaDePesquisa = driver.findElement(By.id("twotabsearchtextbox"));
		wCaixaDePesquisa.sendKeys("frigideira");
		WebElement wLupaDePesquisar = driver.findElement(By.id("nav-search-submit-button"));
		wLupaDePesquisar.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@data-component-id='2']"));
		wPrimeiroElemento.click();
		
		WebElement wEndereco = driver.findElement(By.id("cipInsideDeliveryBlock_feature_div"));
		wEndereco.click();//não clica pelo firefox
		WebElement wCampoCEP = driver.findElement(By.id("GLUXZipUpdateInput_0"));
		wCampoCEP.sendKeys(sCpePesquisado);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement wBotaoConfirma = driver.findElement(By.id("GLUXZipUpdate"));
		wBotaoConfirma.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement wResposta = driver.findElement(By.xpath("//div[@id='contextualIngressPtLabel_deliveryShortLine']/span[2]"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		System.out.println("A resposta foi: "+wResposta.getText());
		Assert.assertEquals(sCpePesquisado, wResposta.getText());
		
	}
}
