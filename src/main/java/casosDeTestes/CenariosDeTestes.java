package casosDeTestes;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilitarios.AmazonUtils;
import utilitarios.Constantes;
import utilitarios.DSL;
import utilitarios.DriverFactory;
import utilitarios.ExcelUtils;



public class CenariosDeTestes {
	private WebDriver driver;
//	WebDriverWait wait;
//	DSL dsl;
	AmazonUtils amazon = new AmazonUtils();
//	String sEnderecoDaPagina= Constantes.sEnderecoDaPagina;
//	String sId;
//	ExcelUtils excel = new ExcelUtils();
//	String sAquivoExcel = Constantes.sEnderecoDoArquivoExcel+"\\"+Constantes.sNomeDoArquivoExcel;	
	
//	@Before
//	public void inicioPadrao() {
//		driver = new ChromeDriver();
//		dsl = new DSL(driver);
//		wait = new WebDriverWait(driver, 30);
//		amazon = new AmazonUtils(driver, dsl);
//		boolean bValidaSite = false;
//		while (bValidaSite == false) { 
//
//			driver.get(sEnderecoDaPagina);
//			WebElement wCaixaPesquisa = driver
//					.findElement(By.xpath("//form[@name='site-search']//input[@type='text']"));
//			String sIdentificaPagina = wCaixaPesquisa.getAttribute("id");
//			if (sIdentificaPagina.equalsIgnoreCase("twotabsearchtextbox")) {
//				driver.manage().window().maximize();
//				bValidaSite = true;
//			} else {
//				System.out.println("Site errado!!");
//				driver.navigate().refresh();
//			
//			}
//		}
//	}
//	
//	@Rule
//	public TestName nomeScreenShort = new TestName();
//
////	@After
//	public void finalizacaoPadrao() throws IOException {
//		LocalDateTime data = LocalDateTime.now();
//		int dd = data.getDayOfMonth();
//		int MM = data.getMonthValue();
//		int aaaa = data.getYear();
//		int hh =data.getHour();
//		int mm = data.getMinute();
//		TakesScreenshot screen = (TakesScreenshot) driver;
//		File evidencias= screen.getScreenshotAs(OutputType.FILE);
//		FileUtils.copyFile(evidencias, new File(
//				"src"+File.separator+"main"+File.separator+"java"+File.separator+
//				"evidencias"+File.separator+"ID_"+sId+"_"+dd+"_"+MM+"_"+aaaa+
//				"_"+hh+"_"+mm+".jpg"));
//		
//			driver.close();			
//			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
//	
//		 
//	}	 

//	****************** CENARIO 01 ********************
	@Test
	public void validarLogimAmazon() {
//		sId = "#0001";
		amazon.login();
	}

//	****************** CENARIO 02 ********************
//	@Test
//	public void validarLogaltAmazon() {
//		sId = "#0002";
//		amazon.logout();
//	}
//
////	****************** CENARIO 03 ********************
//	@Test
//	public void validarProdutoExistente() {
//		sId = "#0003";
//		amazon.validarProdutoExistente("#0003");
//	}
//	
////	****************** CENARIO 04 ********************
//	@Test
//	public void validarMsgProdutoNaoEncontrado() {
//		sId = "#0004";
//		amazon.validarMsgProdutoNaoEncontrado("#0004");
//	}
//
////	****************** CENARIO 05 ********************
//	@Test
//	public void validarInformacoesFrete_prazo() {
//		sId = "#0005";
//		String sCpePesquisado = "13091561";
//		amazon.validarRetornoFrete_prazo(sCpePesquisado);
//	} 
//	
////	****************** CENARIO 06 ********************
//	@Test
//	public void validarMensagemCepInvalido() {
//		sId = "#0006";
//		String sCEP = "000000000";
//		amazon.validaMsgCepErrado(sCEP);
//		
//	}
//
////	****************** CENARIO 07 ********************
//	@Test
//	public void conferirIntemNoCarrinho() {
//		sId = "#0007";
//		amazon.validarItemCarrinho();		
//	}
//
////	****************** CENARIO 08 ********************
//	@Test
//	public void validarPrecoDobrado() {
//		sId = "#0008";
//		amazon.validarValorTotalItem();	
//	}
//
////	****************** CENARIO 09 ********************
//	@Test
//	public void confirmarDoisItensNaCesta() {
//		sId = "#0009";
//		amazon.validarDoisItensNaCesta();
//	}
//
////	****************** CENARIO 10 ********************
//	@Test
//	public void excluirItemDoCarrinho()  {
//		sId = "#0010";
//		amazon.validarMensagemCarinhoVazio();
//	}
//
////	****************** CENARIO 11 ********************
//	@Test
//	public void validarQtdeProdCarrinho() {
//		sId = "#0011";
//		amazon.validarQuantidadeItensCarinho();
//	}
//	
////	****************** CENARIO 12 ********************
//	@Test
//	public void validarNescessidadeDeLogim() {
//		sId = "#0012";
//		amazon.msgNecessarioLogin();
//	}
//
////	****************** CENARIO 13 ********************
//	@Test
//	public void validarBuscaCategoriaDepartamento() {
//		sId = "#0013";
//		amazon.validaReornoResultadoBuscaCatehoria();	
//	}
//
////	****************** CENARIO 14 ********************
//	@Test
//	public void validarLenovoDescritivoPrimeiroProduto() {
//		sId = "#0014";
//		amazon.validarPrimeiroProdutoMarcaLenovo();
//	}
////	****************** CENARIO 15 ********************
//	@Test
//	public void validarOrdemMaioMenor() {
//		sId = "#0015";
//		amazon.validarOndenacaoPrecoMaiorMenor();	
//	}	
}
