package utils.config;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.common.Common;

public class Driver {
	static WebDriver driver;
	public static void main(String[] args) throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"D:\\II. Automation\\Workspace\\.metadata\\CRM-Automation\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
	}
	
	
	public static void launchPage() throws Throwable {
		
		
		driver.manage().window().maximize();
		String url = "http://odoo-test-env.nakivo.com/web/";
		driver.get(url);	
		Common.waitPageLoad();
		driver.manage().window().maximize();
		String urlLink = "http://odoo-test-env.nakivo.com/web/";
		driver.get(urlLink);	
		Common.waitPageLoad();
	}
	
	public static void closePage() throws Throwable {
		driver.close();
	
	}
}
