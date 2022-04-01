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
	By txt_contact_name =By.xpath("(//input[contains(@name,'contact_name')])[1]");
	By txt_street =By.xpath("(//input[contains(@name,'street')])[1]");
	By txt_lead_form =By.xpath("//input[contains(@name,'x_studio_lead_sorce')]");
	By chb_is_create_manual =By.xpath("//div[contains(@name,'is_create_manual')]/input");
	By cbb_sales_team =By.xpath("//select[contains(@name,'team_id')]");
	By cbb_country =By.xpath("(//input[contains(@id,'o_field_input_1761')])[1]");
			
	By tab_crm_developer =By.xpath("//li/a[contains(text(),'CRM Developer')]");
	
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public CRMPage()
	{
		super();
	}
	public CRMPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public CRMPage createNewLead()
	{
		return this;
	}
}
