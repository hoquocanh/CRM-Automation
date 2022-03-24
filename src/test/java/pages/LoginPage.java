package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.common.Common;
import utils.common.Constants;

public class LoginPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	By txt_username =By.xpath("//input[@name='login']");
	By txt_password =By.xpath("//input[@name='password']");
	By btn_login 	=By.xpath("//button[@type='submit' and contains (text(),'Log in')]");
	
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public LoginPage()
	{
		super();
	}
	public LoginPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public void enterUsername(String username) {
		getDriver().findElement(txt_username).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		getDriver().findElement(txt_password).sendKeys(password);
	}
	
	public void clickLogin() throws Throwable 
	{
		getDriver().findElement(btn_login).click();
		waitForHomePageDisplay();
	}
	
	public void loginValidUser(String username, String password) throws Throwable {
		enterUsername(username);
		enterPassword(password);
		clickLogin();
		//Common.waitPageLoad();
		
		waitForHomePageDisplay();
	}
}
