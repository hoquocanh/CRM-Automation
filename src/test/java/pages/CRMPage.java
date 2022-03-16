package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.common.Constants;

public class CRMPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	By btn_create =By.xpath("//button[contains(text(),'Create')]");
	By btn_save =By.xpath("//button[contains(text(),'Discard')]");
	By btn_discard 	=By.xpath("//button[contains(text(),'Discard')]");
	By txt_name 	=By.xpath("//input[@name='name']");
	By txt_email 	=By.xpath("(//input[@name='email_from'])[1]");
	
	
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public CRMPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	
}
