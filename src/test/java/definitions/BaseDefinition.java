package definitions;

import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDefinition {
	WebDriver driver;
	
	
	
	public void launchPage() throws Throwable {
		
		String projectPath = System.getProperty("user.dir");
		System.out.println("Project path is: " + projectPath);
		
		System.setProperty("webdriver.chrome.driver",
				projectPath+"/driver/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
		String url = "http://odoo-test-env.nakivo.com/web/";
		driver.get(url);	
		Common.waitPageLoad();
	}
	
	public void closePage() throws Throwable {
		driver.close();
		driver.quit();
	}
	
	public void login() throws Throwable {
		driver.findElement(By.xpath("//input[@name='login']")).sendKeys("test");
	}
	
}
