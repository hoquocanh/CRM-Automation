package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.common.Constants;

public class LoginPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	By txt_username =By.xpath("//input[@name='login']");
	By txt_password =By.xpath("//input[@name='password']");
	By btn_login 	=By.xpath("//button[@type='submit' and contains (text(),'Log in')]");
	
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public LoginPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public void enterUsername(String username) {
		driver.findElement(txt_username).sendKeys(username);
	}
	
	public void enterPassword(String password) {
		driver.findElement(txt_password).sendKeys(password);
	}
	
	public void clikLogin()
	{
		driver.findElement(btn_login).click();
	}
	
	public void loginValidUser(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		clikLogin();
	}
}
