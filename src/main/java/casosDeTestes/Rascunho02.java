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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Rascunho02 {
	WebDriver driver;
	String sEmail = "saulohrodrigues@gmail.com";
	String sSenha = "5@123456";

	@Before
	public void inicioPadrao() {
		driver = new ChromeDriver();
		driver.get("https://www.amazon.com.br/");
		driver.manage().window().maximize();
		
	}

	@After
	public void finalizacaoPadrao() {
		//driver.quit();
	}
	
	/*	Teste 05.
	 *  Descrição: Realizar a consulta pelo item “frigideira” no campo de busca, clicar no
	 *  primeiro item disponível, digitar o CEP “06010-067” e validar o retorno da(s) 
	 *  informação(ões) de frete/prazo.
	 * */
	@Test
	public void teste05_ValidarRetornoFretePrazo() throws InterruptedException {
		WebElement wCaixaDePesquisa = driver.findElement(By.id("twotabsearchtextbox"));
		wCaixaDePesquisa.sendKeys("frigideira");
		WebElement wLupaDePesquisar = driver.findElement(By.id("nav-search-submit-button"));
		wLupaDePesquisar.click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebElement wPrimeiroElemento = driver.findElement(By.xpath("//div[@data-component-id='2']"));
		wPrimeiroElemento.click();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		WebElement wEndereco = driver.findElement(By.id("cipInsideDeliveryBlock_feature_div"));
		wEndereco.click();//não clica pelo firefox
	}
}
