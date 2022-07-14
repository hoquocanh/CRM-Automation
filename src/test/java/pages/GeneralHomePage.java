package pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;

import java.util.concurrent.TimeUnit;

import utils.common.Common;
import utils.common.Constants;
import utils.config.Driver;

import utils.data.dataGenralHomePage;


public class GeneralHomePage implements WebDriverEventListener{
	
	//A variable "driver" is a static variable which is created at beginning and being used through the session
	private static WebDriver driver;
	private dataGenralHomePage _homeInfo;
	// ============================ Element declaration============================//
		//Modules
		By module_crm 		=By.xpath("//div[contains (text(),'CRM')]");
		By module_settings  =By.xpath("//div[contains (text(),'Settings')]");
		By module_contacts  =By.xpath("//div[contains (text(),'Contacts')]");
		By btn_application  =By.xpath("//a[contains (@title,'Applications')]");
		
		
		
		
		By btn_expand_menu  =By.xpath("//a[@role= 'button' and @data-toggle='dropdown']/i[@class='fa fa-plus']");
	// ============================ Constructor declaration============================//
		public GeneralHomePage ()
		{
			
			String projectPath = System.getProperty("user.dir");
			System.out.println("Project path is: " + projectPath);
			
			System.setProperty("webdriver.chrome.driver",
					projectPath+"/driver/chromedriver.exe");
			
			this.driver = new ChromeDriver();
		}
		public GeneralHomePage (WebDriver Driver)
	{
		this.driver = Driver;
		
		
	}
		// ============================ Generate Getter and Setter============================//	
		public static WebDriver getDriver() {
			return driver;
		}
		public static void setDriver(WebDriver driver) {
			GeneralHomePage.driver = driver;
		}
	// ============================ Methods============================//
	public void launchWebPage() throws Throwable {
		
		String url = Constants.UAT_LINK;
		
		Dimension d = new Dimension(1382,744); 
		//Resize the current window to the given dimension
		 
		
		driver.navigate().to(url);		
		driver.manage().window().setSize(d);
		//driver.manage().window().maximize();
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
	public void gotoModuleContacts() {
		
		try {
		driver.findElement(module_contacts).click();
		waitForPageDisplay();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	public void goToHome() throws Throwable {
		
		driver.findElement(btn_application).click();
		waitForHomePageDisplay();
	}
	public void waitForHomePageDisplay() throws InterruptedException {
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(17));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(module_crm);
		
	}
	
	public void waitForPageDisplay()
	{
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
	}
	public void waitForElementResponse()
	{
		try {
			Thread.sleep(1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	}
	public void waitForLeadMerging()
	{
		try {
			Thread.sleep(4000);
			driver.navigate().refresh();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}
	@Override
	public void beforeNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateTo(String url, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateBack(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterNavigateForward(WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterFindBy(By by, WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterClickOn(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterChangeValueOf(WebElement element, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void beforeScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void afterScript(String script, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onException(Throwable throwable, WebDriver driver) {
		// TODO Auto-generated method stub
		
	}
}
