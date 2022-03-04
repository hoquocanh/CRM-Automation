package definitions;

import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseDefinition {
	WebDriver driver;
	
	
	
	public void launchPage() throws Throwable {
		System.setProperty("webdriver.chrome.driver",
				"D:\\II. Automation\\Workspace\\.metadata\\CRM-Automation\\driver\\chromedriver.exe");
		
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		String url = "http://odoo-test-env.nakivo.com/web/";
		driver.get(url);	
		Common.waitPageLoad();
	}
	
	public void closePage() throws Throwable {
		driver.close();
	
	}
	
}
