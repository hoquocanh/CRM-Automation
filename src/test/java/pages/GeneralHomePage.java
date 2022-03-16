package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import utils.common.Common;
import utils.config.Driver;


public  class GeneralHomePage {
	
	
	WebDriver driver;
	// ============================ Element declaration============================//
		By module_crm 		=By.xpath("//a[div[contains (text(),'CRM')]]/div[1]");
		By btn_expand_menu  =By.xpath("//a[@role= 'button' and @data-toggle='dropdown']/i[@class='fa fa-plus']");
		By sub_menu_leads 	=By.xpath("//a[contains(@data-menu-xmlid,'crm_leads')]/span");
		By module_settings  =By.xpath("//a[div[contains (text(),'Settings')]]/div[1]");
		
		
	// ============================ Constructor declaration============================//
	public GeneralHomePage (WebDriver Driver)
	{
		this.driver = Driver;
	}
	// ============================ Methods============================//
	public void launchPage() throws Throwable {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is: " + projectPath);
		
		System.setProperty("webdriver.chrome.driver",
				projectPath+"/driver/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		
		String url = "http://odoo-test-env.nakivo.com/web/";
//		driver.get(url);	
		
		driver.navigate().to(url);
		Common.waitPageLoad();
	}
	
	public void closePage() throws Throwable {
		driver.close();
		driver.quit();
	}
	
	
	
}
