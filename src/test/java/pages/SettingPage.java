package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.common.Constants;

public class SettingPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	By lnk_activate_developer_mode =By.xpath("(//a[contains(text(),'Activate the developer mode')])[1]");
	
	
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public SettingPage()
	{
		super();
	}
	public SettingPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public void activateDeveloperMode() throws Throwable 
	{
		
		super.gotoModuleSettings();
		getDriver().findElement(lnk_activate_developer_mode).click();
		//backToHome();
		
	}
}
