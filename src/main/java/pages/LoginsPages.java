package pages;

import org.openqa.selenium.WebDriver;

import utilitarios.AmazonUtils;
import utilitarios.DSL;

public class LoginsPages {
	AmazonUtils amazon;
	DSL dsl;
	public LoginsPages(WebDriver driver) {
		this.dsl = new DSL(driver);
		this.amazon = new AmazonUtils(driver, dsl);
	}
	
	public void setEmail(String email) {
		
	}
	
	
}
