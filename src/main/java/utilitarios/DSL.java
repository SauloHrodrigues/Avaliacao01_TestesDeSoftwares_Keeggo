package utilitarios;

import static utilitarios.DriverFactory.getDriver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DSL {
//	WebgetDriver() getDriver();
	WebDriverWait wait;


//	public DSL(WebgetDriver() getDriver()) {
//		this.getDriver() = getDriver();
//		this.wait = new WebgetDriver()Wait(getDriver(), 60);
//	}
	public DSL() {
		this.wait = new WebDriverWait(getDriver(), 60);
	}

	public String retornaConteudo(By by) {
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
		WebElement elemento = getDriver().findElement(by);
		return elemento.getText();
	}

	//novo
	public String retornaConteudo(WebElement elemento) {
		wait.until(ExpectedConditions.elementToBeClickable(elemento));
		return elemento.getText();
	}

//	***************** Clicar ****************************************

//	public void clicar(By by) {
//		clicarElemento(by);
//	}
	//novo
	public void clicar(WebElement elemento) {
		elemento.click();
	}
	
//	public void clicarElemento(By by) {
//		getDriver().findElement(by).click();
//	}
//	public WebElement elemento(By by) {
//		return getDriver().findElement(by);
//	}
	
//	******************** Escrever **********************************
	public void escrever(By by, String conteudo) {
		WebElement wCaixa = getDriver().findElement(by);
		wCaixa.sendKeys(conteudo);
	}
	
	public void escrever(WebElement elemento, String conteudo) {
		wait.until(ExpectedConditions.elementToBeClickable(elemento));
		elemento.sendKeys(conteudo);
	}
	
	public boolean validarElementoVisivel(By by) {
		WebElement wMsg = getDriver().findElement(by);
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
		WebElement element = getDriver().findElement(by);
		Select combo = new Select(element);
		combo.selectByValue(""+valor);
	}
	
	public String valorSetadoCombo(By by) {
		WebElement element = getDriver().findElement(by);
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
		WebElement elemento = getDriver().findElement(by);
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", elemento);
	}

	public void clicarComJS(WebElement elemento) {
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", elemento);
	}

}// fim da classe
