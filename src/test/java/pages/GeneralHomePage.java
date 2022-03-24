package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import utils.common.Common;
import utils.common.Constants;
import utils.config.Driver;

import utils.data.dataGenralHomePage;


abstract  class GeneralHomePage {
	
	
	WebDriver driver;
	private dataGenralHomePage _homeInfo;
	// ============================ Element declaration============================//
	
		By module_crm 		=By.xpath("//div[contains (text(),'CRM')]");
		By module_settings  =By.xpath("//div[contains (text(),'Settings')]");
		By btn_application  =By.xpath("//a[contains (@title,'Applications')]");
		
		By btn_expand_menu  =By.xpath("//a[@role= 'button' and @data-toggle='dropdown']/i[@class='fa fa-plus']");
		By sub_menu_leads 	=By.xpath("//a[contains(@data-menu-xmlid,'crm_leads')]/span");
		
		
		
	// ============================ Constructor declaration============================//
	public GeneralHomePage (WebDriver Driver)
	{
		this.driver = Driver;
	}
	// ============================ Methods============================//
	public void launchWebPage() throws Throwable {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is: " + projectPath);
		
		System.setProperty("webdriver.chrome.driver",
				projectPath+"/driver/chromedriver.exe");
		
		driver = new ChromeDriver();
		//driver.manage().timeouts().pageLoadTimeout(TimeUnit.SECONDS);
		
		
		driver.manage().window().maximize();
		
		String url = Constants.UAT_LINK;

		
		driver.navigate().to(url);		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		//Common.waitPageLoad();
	}
	
	public void closePage() throws Throwable {
		driver.close();
		driver.quit();
	}
	
	public void gotoModuleCRM() {
		
		driver.findElement(module_crm).click();
		waitForPageDisplay();
	}
	public void gotoModuleSettings() {
		
		try {
		driver.findElement(module_settings).click();
		waitForPageDisplay();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	public void backToHome() throws Throwable {
		
		driver.findElement(btn_application).click();
		waitForHomePageDisplay();
	}
	public void waitForHomePageDisplay() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(module_crm);
		
	}
	
	public void waitForPageDisplay()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
}
