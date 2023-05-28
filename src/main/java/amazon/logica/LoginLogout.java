package amazon.logica;

import amazon.gerenciadores.GerenciadorPages;
import amazon.pages.HomeAmazonPage;
import amazon.pages.LoginAmazonPage;
import utilitarios.DriverFactory;

public class LoginLogout {
	
	private GerenciadorPages pages = new GerenciadorPages();
	private HomeAmazonPage homePage;
	private LoginAmazonPage loginPage;
	
	private void entrarSiteSamazon() {
		DriverFactory.getDriver().get("https://www.amazon.com.br/");
	}

	public void logarAmazon() {
		entrarSiteSamazon();
//		String[] credencial = excel.credenciais();
		homePage = pages.getHomePage();
		loginPage =pages.getLoginPage();
		homePage.clicarFazerLogin(); 
		loginPage.entrarEmail("saulohrodrigues@gmail.com");
		loginPage.clicarContinuar();
		loginPage.entrarSenha("#!S@ulo1978");
		loginPage.clicarFazerLogin();
	}
	
	public void deslogarAmazon() {
		logarAmazon();
//		dsl.esperarPresencaElemento(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
//		WebElement sair = driver.findElement(By.xpath("//a[@id='nav-item-signout']//span[text()='Sair da conta']"));
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].click();", sair);
//		Assert.assertEq
	}
	
	

	

}
