package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import utils.common.Common;
import utils.common.Constants;
import utils.data.dataJsonLead;
import utils.helper.Logger;
import utils.object.objLead;

/**
 * @author anh.ho
 *
 */
public class CRMPage<T, S extends String> extends GeneralHomePage {
	/**
	 * Flow to create Source lead
	 * 1. Fill all fields except Email
	 * 2. Press "SAVE"
	 * 3. Send Public and Log message
	 * 4. Enter email same as Target email
	 * 
	 */
	
	// ============================ Element declaration============================//
	//Menu "Leads"
	By menu_leads		=By.xpath("//a[@role= 'button' and contains(@ data-menu-xmlid,'menu_leads')]");
	By sub_menu_leads 	=By.xpath("//a[contains(@data-menu-xmlid,'crm_leads')]/span");
	
	//Title
	By lbl_leads = By.xpath("//li[contains(text(),'Leads')]");
	
	//In CRM page
	By btn_create =By.xpath("//button[contains(text(),'Create')]");
	By btn_save =By.xpath("//button[contains(text(),'Discard')]");
	By btn_discard 	=By.xpath("//button[contains(text(),'Discard')]");
	By btn_paper_plane =By.xpath("//button[contains(@class,'paper-plane')]");
		//tab_send_message		
	By btn_send_messsage =By.xpath("//button[contains(text(),'Send message')]");
	By btn_log_note =By.xpath("//button[contains(text(),'Log note')]");
	By btn_activity =By.xpath("//button[contains(@title,'activity')]");
	
	//Textbox
	By txt_name 	=By.xpath("//input[@name='name']");
	By txt_email 	=By.xpath("(//input[contains(@name,'email_from')])[2]");
	By txt_contact_name =By.xpath("(//input[contains(@name,'contact_name')])[1]");
	By txt_company_name =By.xpath("(//input[contains(@name,'partner_name')])[1]");
	By txt_street =By.xpath("(//input[contains(@name,'street')])[1]");
	By txt_street2 =By.xpath("(//input[contains(@name,'street2')])[1]");
	By txt_zip  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/following-sibling::input[contains(@name,'zip')]");
	By txt_lead_form =By.xpath("//input[contains(@name,'x_studio_lead_sorce')]");
	By txt_send_message =By.xpath("//textarea[contains(@class,'composer_text_field')]");
	
	//Combobox
	By cbb_sales_team =By.xpath("//select[contains(@name,'team_id')]");
	By cbb_country =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'country_id')][1]/descendant::input");	
	By cbb_state  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/descendant::input");
	By cbb_tags =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][3]/descendant::input[contains(@type,'text')]");
	By cbb_lost_reason =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::a[contains(@name,'lost_reason')][2]");
	
	//Checkbox
	By chb_is_create_manual =By.xpath("//div[contains(@name,'is_create_manual')]/input");
	By div_chb_is_create_manual =By.xpath("//div[contains(@name,'is_create_manual')]");
	By chb_active =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name,'active')][2]/input[@type='checkbox']");
	
	//document.querySelector('#o_field_input_1691').checked;
	
	//label
	By lbl_is_won =By.xpath("//span[contains(@name,'won_status')]");
	
	//Footer tabs
	
	By tab_crm_developer =By.xpath("//li/a[contains(text(),'CRM Developer')]");
	
	
	//CHECK public content with text = 'Send message' //div[contains(@class,'mail_thread_content')]/descendant::div[contains(@class,'mail_discussion')]/descendant::p[contains(text(),'Send message')]
		//CHECK log note content with text = "Send log note" /div[contains(@class,'mail_thread_content')]/descendant::div[contains(@class,'mail_not_discussion')]/descendant::p[contains(text(),'Send log note')]
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
	public void clickLeadsMenu() throws Throwable 
	{
		getDriver().findElement(menu_leads).click();
		waitForElementResponse();
	}
	public void clickLeadsSubMenu() throws Throwable 
	{
		getDriver().findElement(sub_menu_leads).click();
		waitForPageDisplay();
		//Find label "Leads" to make sure the "Leads" page displays completely
		getDriver().findElement(lbl_leads);
		
	}
	
	public void clickCreateButton() throws Throwable 
	{
		getDriver().findElement(btn_create).click();
		
		Common.waitPageLoad(3);
		//Find textbox "Lead Name" to make sure the "Leads" page displays completely
		getDriver().findElement(txt_name);
		
	}
	
	public void createLead() throws Throwable
	{
		//1. Click on "Leads" menu
		this.clickLeadsMenu();
		//2. Click on "Leads" sub-menu
		this.clickLeadsSubMenu();
		//3. Press "CREATE" button
		this.clickCreateButton();
		
		
	}
	
	public void enterLeadName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADNAME.getValue());;
		getDriver().findElement(txt_name).sendKeys(inputText);
		
	}
	
	/**This method is used to generate a random email
	 * @param testFileName
	 * @param leadType
	 * @throws Throwable
	 */
	public String enterEmail(String testFileName, String leadType) throws Throwable	
	
	{
		
		String randomEmail = Common.getRandomEmail();
		System.out.println("Random email:"+ randomEmail);
		//Set random email to the Email address on the Json file
		objLead.setJsonValue(testFileName,leadType,dataJsonLead.EMAILADDRESS.getValue(), randomEmail);
		
		getDriver().findElement(txt_email).sendKeys(randomEmail);
		
		return randomEmail;
	}
	public void enterLeadForm(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADFORM.getValue());
		getDriver().findElement(txt_lead_form).sendKeys(inputText);	
	}
	public void enterCompanyName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.COMPANYNAME.getValue());
		getDriver().findElement(txt_contact_name).sendKeys(inputText);	
	}
	public void selectSalesTeam(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.SALESTEAM.getValue());	
		getDriver().findElement(cbb_sales_team).sendKeys(inputText);
	}
	public void selectTag(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.TAGS.getValue());		
		//Zoom out
//		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
//		waitForElementResponse();
//		Js1.executeScript("document.body.style.zoom = '0.3'");
//		//Scroll down
//		waitForElementResponse();
//		Js1.executeScript("window.scrollBy(0,1000)");  
		
		waitForElementResponse();
		//getDriver().findElement(cbb_tags).click();
		//waitForElementResponse();
		getDriver().findElement(cbb_tags).click();;	
		getDriver().findElement(cbb_tags).sendKeys(inputText);	
		getDriver().findElement(cbb_tags).sendKeys(Keys.RETURN);

	}
	public void setOnCreateManual(String testFileName, String leadType) throws Throwable
	{				
		//If the CreateManual checkbox is UNcheck -> click on it
		if(!getDriver().findElement(chb_is_create_manual).isSelected())
			getDriver().findElement(chb_is_create_manual).click();
	}
	public void setOffCreateManual(String testFileName, String leadType) throws Throwable
	{				
		WebDriver dr = super.getDriver();
		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
		
		
		
		//Get value of //input[@id]
		String attributeValue = getDriver().findElement(chb_is_create_manual).getAttribute("id");
		Logger.info("Attribute of ID is "+ attributeValue);	
		
		String checkTheCheckBox = 
				  "return document.querySelector(\'#" + attributeValue + "\')" +
				  ".checked";
		
		Logger.info("Checkbox element is "+ checkTheCheckBox);	
		Boolean isChecked = (Boolean) Js1.executeScript(checkTheCheckBox);
		
		//If the CreateManual checkbox is check -> click on it
		if(isChecked)
			getDriver().findElement(div_chb_is_create_manual).click();
	}
	
	
	

	
	
	
	
}
