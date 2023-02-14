package utilitarios;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {
	WebDriver driver;
	WebDriverWait wait;

	public DSL(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, 60);
	}

	public String retornaConteudo(By by) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		WebElement elemento = driver.findElement(by);
		return elemento.getText();
	}

//	***************** Clicar ****************************************

	public void clicar(By by) {
		clicarElemento(by);
	}
	public void clicarElemento(By by) {
		driver.findElement(by).click();
	}
	public WebElement elemento(By by) {
		return driver.findElement(by);
	}
	
	
	public void escrever(By by, String conteudo) {
		WebElement wCaixa = driver.findElement(by);
		wCaixa.sendKeys(conteudo);
	}
	
	public boolean validarElementoVisivel(By by) {
		WebElement wMsg = driver.findElement(by);
		System.out.println("elemento visivel = "+wMsg.isDisplayed());
		if(wMsg.isDisplayed()) {
			return true;
		}else {
			return false;
		}
		
	}
	
//	********************** ESPERAS ***************************************
	
	public void esperarElementoVisivel(By by) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(by));
	}
	
	public void esperarPresencaElemento(By by) {
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}
	
//	********************* COMBOS **********************************
	public void selecionarOpcao(By by, int valor) {
		WebElement element = driver.findElement(by);
		Select combo = new Select(element);
		combo.selectByValue(""+valor);
	}
	
	public String valorSetadoCombo(By by) {
		WebElement element = driver.findElement(by);
		Select combo = new Select(element);
		String valor = combo.getFirstSelectedOption().getText();
		return valor;
		
	}
	
//	********************** CONVERSORES *************************************

		
	public double converterStringToDouble(String texto, String separador) {
		String[] aux = texto.split(separador);
		String sValorTexto = aux[1];

		if(sValorTexto.contains(",")) {
			aux = sValorTexto.split(",");
			sValorTexto= aux[0].concat(".").concat(aux[1]);
		}
		
		Double valorDouble = Double.parseDouble(sValorTexto);
		return valorDouble;
	}
	public int converterStringToInteiro(String texto) {
		Integer numInteiro = Integer.parseInt(texto);
		return numInteiro;
	}
	
//	********** Interação com JS *********************
	
	public void clicarComJS(By by) {
		WebElement elemento = driver.findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", elemento);
	}

}// fim da classe
