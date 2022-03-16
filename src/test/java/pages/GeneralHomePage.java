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
		
		General:
			module_settings  //a[div[contains (text(),'Settings')]]/div[1]

			CRM:
			txt_contact_name (//input[contains(@name,'contact_name')])[1]
			txt_street (//input[contains(@name,'street')])[1]
			txt_lead_form //input[contains(@name,'x_studio_lead_sorce')]
			chb_is_create_manual //div[contains(@name,'is_create_manual')]/input
			cbb_sales_team //select[contains(@name,'team_id')]
			cbb_country (//input[contains(@id,'o_field_input_1761')])[1]
			->CM_Channel Management
			//li/a[contains(text(),'CRM Developer')]

			Setting:
			(//a[contains(text(),'Activate the developer mode')])[1]
		
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
