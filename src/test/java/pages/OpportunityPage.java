package pages;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import utils.common.Common;
import utils.common.Constants;
import utils.data.dataJsonContact;
import utils.data.dataJsonLead;
import utils.helper.Logger;
import utils.object.objContact;
import utils.object.objLead;

/**
 * @author anh.ho
 *
 */
/**
 * @author anh.ho
 *
 * @param <T>
 * @param <S>
 */
public class OpportunityPage<T, S extends String> extends GeneralHomePage {
	/**
	 * Flow to create Source lead
	 * 1. Fill all fields except Email
	 * 2. Press "SAVE"
	 * 3. Send Public and Log message
	 * 4. Enter email same as Target email
	 * 
	 */
	
	// ============================ Element declaration============================//
	//I. Pipeline page
	//1.Menu "Leads"
	By menu_leads		=By.xpath("//a[@role= 'button' and contains(@ data-menu-xmlid,'menu_leads')]");
	By sub_menu_leads_leads 	=By.xpath("//a[contains(@data-menu-xmlid,'crm_leads')]/span");
	
	By menu_archive		=By.xpath("//a[@role= 'button' and contains(@ data-menu-xmlid,'menu_archive_leads')]");
	By sub_menu_archive_all =By.xpath("//a[contains(@data-menu-xmlid,'archive_leads')]/span[contains(text(),'All')]");
	By btn_create =By.xpath("//button[contains(text(),'Create')]");
	//2. Textbox
	By txt_search 		=By.xpath("//input[contains(@role,'searchbox')]");
	
	//3. Title
	By lbl_leads = By.xpath("//li[contains(text(),'Leads')]");
	By lbl_all_leads = By.xpath("//li[contains(text(),'All Leads')]");
	
	//II. All leads page
	By lnk_dynamic_source_lead = By.xpath("//tr[contains(@class,'o_data_row')]/td[contains(text(),'REPLACE#1')]");
	By lnk_dynamic_targe_lead = By.xpath("//tr[contains(@class,'o_data_row')]/td[contains(text(),'REPLACE#1')]");
	
	//III. In Pipeline page
	
	By btn_view_list =By.xpath("//button[contains(@aria-label,'View list')]");
		///1. Stage on Opp page 
	By tab_stage_new =By.xpath("(//button[contains(text(),'New')])[2]");			
	By tab_stage_in_process =By.xpath("//button[contains(text(),'In Process')]");
	By tab_stage_qualified =By.xpath("//button[contains(text(),'Qualified')]");
	By tab_stage_active_interest =By.xpath("//button[contains(text(),'Active interest')]");
	By tab_stage_hot_deal =By.xpath("//button[contains(text(),'Hot Deal')]");
	By tab_stage_purchase_approval =By.xpath("//button[contains(text(),'Purchase Approval')]");
	By tab_stage_won =By.xpath("//button[contains(text(),'Won')]");
	
	
		
	//1. Detail Lead page
	
	By btn_save =By.xpath("//button[contains(text(),'Save')]");
	By btn_discard 	=By.xpath("//button[contains(text(),'Discard')]");
	By btn_paper_plane =By.xpath("//button[contains(@class,'paper-plane')]");
		
	
	//Textbox
	By txt_name 	=By.xpath("//input[@name='name']");
	By txt_email 	=By.xpath("(//input[contains(@name,'email_from')])[1]");
	By txt_contact_name =By.xpath("(//input[contains(@name,'contact_name')])[2]");
	By txt_company_name =By.xpath("(//input[contains(@name,'partner_name')])[3]");
	By txt_street =By.xpath("(//input[contains(@name,'street2')])[2]");
	
	By txt_zip  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][2]/following-sibling::input[contains(@name,'zip')]");
	By txt_lead_form =By.xpath("(//input[contains(@name,'x_studio_lead_sorce')])[2]");
	
	//NOT DOUBLE CHECK YET
	By txt_send_message =By.xpath("//textarea[contains(@class,'composer_text_field')]");
	
	//Combobox
	By cbb_sales_team =By.xpath("//select[contains(@name,'team_id')]");
	By cbb_contact =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'partner_id')][2]/descendant::input");
	By cbb_country =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'country_id')][2]/descendant::input");	
	By cbb_state  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][2]/descendant::input");
	By cbb_tags =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][1]/descendant::input[contains(@type,'text')]");
	By cbb_lost_reason =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::a[contains(@name,'lost_reason')][2]");
	
	//Checkbox
	By chb_is_create_manual =By.xpath("//div[contains(@name,'is_create_manual')]/input");
	By div_chb_is_create_manual =By.xpath("//div[contains(@name,'is_create_manual')]");
	By chb_active =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name,'active')][2]/input[@type='checkbox']");
	
	//Star
	By star_1 =By.xpath("(//div[@name='priority']/a[@title='Medium'])[2]");
	By star_2 =By.xpath("(//div[@name='priority']/a[@title='Medium High'])[2]");
	By star_3 =By.xpath("(//div[@name='priority']/a[@title='High'])[2]");
	By star_4 =By.xpath("(//div[@name='priority']/a[@title='Very High'])[2]");
	
	//label
	By lbl_is_won =By.xpath("//span[contains(@name,'won_status')]");
		//The system log note on Source Lead will be [This lead has been merged into "target lead's name"]
		By lbl_dynamic_merge_source_lead_1 = By.xpath("//p[contains(text(),'This lead has been merged into')]/a[contains(text(),'TARGET_NAME#1')]");
		//The system log note on Source Lead will be [Your lead  "source lead's name"has been automatically merged into "target lead's name" and closed.]
		By lbl_dynamic_merge_source_lead_2 = By.xpath("//p[contains(text(),'Your lead')]/a[contains(text(),'SOURCE_NAME#1')] | //p[contains(text(),'has been automatically merged into ')]/a[contains(text(),'TARGET_NAME#1')] | //p[contains(text(),' and closed.')]");
		//The system log note on Target Lead will be ["source lead's name",has been merged into this lead]
		By lbl_dynamic_merge_target_lead_1 = By.xpath("//a[contains(text(),'SOURCE_NAME#1')]/ancestor::span[contains(text(),'has been merged into this lead')]");
		//The system log note on Target Lead will be ["Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead target lead's name"]
		By lbl_dynamic_merge_target_lead_2 = By.xpath("//a[contains(text(),'TARGET_NAME#1')]/ancestor::p[contains(text(),'Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead ')]");
	
	
	
	By lbl_email_form = By.xpath("//table[contains(@class,'o_group_col_6') and not(contains(@class,'o_invisible_modifier'))]/descendant::a[contains(@name,'email_from')]");
	By lbl_email_partner = By.xpath("//table[contains(@class,'o_group_col_6') and not(contains(@class,'o_invisible_modifier'))]/descendant::a[contains(@name,'partner_address_email')]");
		
	By lbl_address = By.xpath("(//span[contains(@name,'street2')])[2]");
	By lbl_contact_name = By.xpath("(//span[contains(@name,'contact_name')])[3]");
	By lbl_combobox_contact = By.xpath("(//a[contains(@name,'partner_id')])[2]");
	By lbl_company_name = By.xpath("(//span[contains(@name,'partner_name')])[3]");
	By lbl_state = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::span[contains(@name, 'state_id')][2]");
	By lbl_country = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::span[contains(@name, 'country_id')][2]");
		//lbl_tag is a special lbl, it will give us the number of tags selected
	By lbl_tag = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][1]");
	//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][2]/descendant::span[contains(@role,'img')]
	By lbl_star_1 =By.xpath("(//div[@name='priority']/a[@title='Medium'])[1]");
	By lbl_star_2 =By.xpath("(//div[@name='priority']/a[@title='Medium High'])[1]");
	By lbl_star_3 =By.xpath("(//div[@name='priority']/a[@title='High'])[1]");
	By lbl_star_4 =By.xpath("(//div[@name='priority']/a[@title='Very High'])[1]");
	//Footer tabs
	//tab_send_message		
		By btn_send_messsage =By.xpath("//button[contains(text(),'Send message')]");
		By btn_log_note =By.xpath("//button[contains(text(),'Log note')]");
		By btn_activity =By.xpath("//button[contains(@title,'activity')]");
	By tab_crm_developer =By.xpath("//li/a[contains(text(),'CRM Developer')]");
	
	
	//CHECK public content with text = 'Send message' //div[contains(@class,'mail_thread_content')]/descendant::div[contains(@class,'mail_discussion')]/descendant::p[contains(text(),'Send message')]
		//CHECK log note content with text = "Send log note" /div[contains(@class,'mail_thread_content')]/descendant::div[contains(@class,'mail_not_discussion')]/descendant::p[contains(text(),'Send log note')]
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public OpportunityPage()
	{
		super();
	}
	public OpportunityPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public OpportunityPage createNewLead()
	{
		return this;
	}
	
	/**This method is a way to make the element is clickable on Jenkins
	 * <pre>
	 * This method is a way to make the element is clickable on Jenkins
	 * </pre>
	 * @param elementName	 
	 * @throws Throwable
	 */
	public void specialClick(By elementName)
	{
		WebElement elem = getDriver().findElement(elementName);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].click();", elem);
	}
	
	/**This method is a way to make the element is able to get text on Jenkins
	 * <pre>
	 * This method is a way to make the element is able to get text  on Jenkins
	 * </pre>
	 * @param elementName	 
	 * @throws Throwable
	 */
	public String specialGetText(By elementName)
	{
		WebElement elem = getDriver().findElement(elementName);
		
		System.out.println("elem: "+ elem);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		String text = (String ) executor.executeScript("return arguments[0].value", elem);
		
		System.out.println("text in Special Get Text: "+ text);
		return text;
	}
	/**This method is a way to make the element is able to get the value of an attribute on Jenkins
	 * <pre>
	 * This method is a way to make the element is  able to get the value of an attribute on Jenkins
	 * </pre>
	 * @param elementName	 
	 * @throws Throwable
	 */
	public String specialGetAttributetextContent(By elementName)
	{
		waitForSecond(2);
		WebElement elem = getDriver().findElement(elementName);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();		
		
		executor.executeScript("arguments[0].scrollIntoView(true);", elem);
		
		String text =(String) executor.executeScript("return arguments[0].getAttribute('textContent')", elem);
		System.out.println("text in textContent: "+ text);	
		
		String text2 =(String) executor.executeScript("return arguments[0].getAttribute('value')", elem);
		System.out.println("text in Special value: "+ text2);	
		
		String text3 =(String) executor.executeScript("return arguments[0].getAttribute('innerHTML')", elem);
		System.out.println("text in Special innerHTML: "+ text3);	
		
		
		
		
		return text;
		
	}
	public void clickLeadsMenu() throws Throwable 
	{
		//getDriver().findElement(menu_leads).click();
		
		specialClick(menu_leads);
		waitForElementResponse();		
	}
	public void clickLeadsSubMenu() throws Throwable 
	{
		specialClick(sub_menu_leads_leads);
		waitForPageDisplay();
		//Find label "Leads" to make sure the "Leads" page displays completely
		getDriver().findElement(lbl_leads);
		
	}
	
	public void clickArchiveMenu() throws Throwable 
	{
		specialClick(menu_archive);
		waitForElementResponse();
	}
	public void clickArchive_AllSubMenu() throws Throwable 
	{
		specialClick(sub_menu_archive_all);
		waitForPageDisplay();
		//Find label "Leads" to make sure the "Leads" page displays completely
		getDriver().findElement(lbl_all_leads);
		
	}
	public void clickCreateButton() throws Throwable 
	{
		specialClick(btn_create);
		
		Common.waitPageLoad(3);
		//Find textbox "Lead Name" to make sure the "Leads" page displays completely
		getDriver().findElement(txt_name);
		
	}
	/**This method is used to create a Lead
	 * <pre>
	 * Pre-condition: The screen is being Home page
	 * </pre>
	 * This method including below steps
	 * 1. Go to Module CRM
	 * 2. Click on "Leads" menu
	 * 3. Click on "Leads" sub-menu
	 * 4. Press "CREATE" button
	 * @throws Throwable
	 */
	public void createLead() throws Throwable
	{
		//1. Go to Module CRM
		this.gotoModuleCRM();
		//2. Click on "Leads" menu
		this.clickLeadsMenu();
		//3. Click on "Leads" sub-menu
		this.clickLeadsSubMenu();
		//4. Press "CREATE" button
		this.clickCreateButton();
		
		
	}
	public void createOpp() throws Throwable
	{
		//1. Go to Module CRM
		this.gotoModuleCRM();
		//2. Click on View List button to be able to create new Opp
		this.clickViewList();
		//3. Press "CREATE" button	
		this.clickCreateButton();
		
		
	}
	public void goToSub_ArchiveMenu(String subMenu) throws Throwable
	{
		
		//1. Click on "Archive" menu
		this.clickArchiveMenu();
		//2. Click on "All Leads" sub-menu
		this.clickArchive_AllSubMenu();
		
	}
//---------------------------------------Pipeline page------------------------------------------------	
	public void clickViewList() throws Throwable 
	{
		//getDriver().findElement(btn_view_list).click();
		specialClick(btn_view_list);
		Common.waitPageLoad(3);
		
	}
//---------------------------------------CRM page------------------------------------------------
	public void enterLeadName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADNAME.getValue());
		getDriver().findElement(txt_name).sendKeys(inputText);
		
	}
	
	/**This method is used to generate a random email and create a lead with that email
	 * <pre>
	 * This method is used to create the Target lead
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @throws Throwable
	 */
public String enterEmail(String testFileName, String leadType) throws Throwable	
	
	{
	String randomEmail = "";
	objLead<String, String> temp = new objLead<String, String>();
	String inputTestCaseType = temp.getJsonValue(testFileName,leadType,dataJsonLead.TESTCASETYPE.getValue());
	
	if(inputTestCaseType.contains("public domain"))
		randomEmail = Common.getRandomPublicEmail();
	else 
		randomEmail = Common.getRandomCompanyEmail();
	System.out.println("Random email:"+ randomEmail);
	Logger.info("Random email:"+ randomEmail);
	//Set random email to the Email address on the Json file
		//objLead.setJsonValue(testFileName,leadType,dataJsonLead.EMAILADDRESS.getValue(), randomEmail);
		//objLead.setJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.EMAILADDRESS.getValue(), randomEmail);
	
	getDriver().findElement(txt_email).sendKeys(randomEmail);
	
	return randomEmail;
	}
	/**This method is used to enter an existing email
	 * <pre>
	 * This method is used to create the Source lead
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @param inputEmail
	 * @throws Throwable
	 */
	public void enterEmail(String testFileName, String leadType, String inputEmail) throws Throwable	
	
	{
		//objLead.setJsonValue(testFileName,leadType,dataJsonLead.EMAILADDRESS.getValue(), inputEmail);
		getDriver().findElement(txt_email).sendKeys(inputEmail);
		
	}
	/**This method is used to enter an existing email to the "Contact" dropdownlist
	 * <pre>
	 * 
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @param inputChildContactEmail 
	 * @throws Throwable
	 */
	public void selectContact(String testFileName, String leadType, String inputChildContactEmail) throws Throwable	
	{
		if(!inputChildContactEmail.isEmpty())
		{
			waitForElementResponse();
			specialClick(cbb_contact);			
			waitForElementResponse();
			getDriver().findElement(cbb_contact).sendKeys(inputChildContactEmail);	
			waitForElementResponse();
			//Press "Enter" on keyboard
			getDriver().findElement(cbb_contact).sendKeys(Keys.RETURN);		
		}
				
	}
	public void enterStreetName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.STREETADDRESS.getValue());
		getDriver().findElement(txt_street).sendKeys(inputText);
		
	}
	public void selectCRMDeveloperTab() throws Throwable		
	{		
		specialClick(tab_crm_developer);		
		waitForElementResponse();
		
	}
	public void enterLeadForm(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADFORM.getValue());
		//1. Select CRM Developer tab first
		selectCRMDeveloperTab();
		//2. Enter Lead Form
		getDriver().findElement(txt_lead_form).sendKeys(inputText);	
	}
	public void enterCompanyName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.COMPANYNAME.getValue());
		getDriver().findElement(txt_company_name).sendKeys(inputText);	
	}
	public void enterContactName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.CONTACTNAME.getValue());
		getDriver().findElement(txt_contact_name).sendKeys(inputText);	
	}
	
	public void selectSalesTeam(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.SALESTEAM.getValue());	
		getDriver().findElement(cbb_sales_team).sendKeys(inputText);
	}
	public void pressSaveButton() throws Throwable
	{
		specialClick(btn_save);		
		waitForPageDisplay();
	}
	public void clickCRMDeveloper() throws Throwable
	{
		specialClick(tab_crm_developer);		
		waitForElementResponse();
	}
	//For the kind of Country combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectCountry(String testFileName, String leadType) throws Throwable
		{
			objLead<String, String> temp = new objLead<String, String>();
			String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.COUNTRY.getValue());		
			
			if(!inputText.isEmpty())
			{
				specialClick(cbb_country);
				waitForElementResponse();
				getDriver().findElement(cbb_country).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_country).sendKeys(Keys.RETURN);		
				waitForElementResponse();
			}
			
		}
		
		public void selectState(String testFileName, String leadType) throws Throwable
		{
			objLead<String, String> temp = new objLead<String, String>();
			String inputText = this.refineStateString(temp.getJsonValue(testFileName,leadType,dataJsonLead.STATE.getValue()));		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();

				specialClick(cbb_state);
				waitForElementResponse();
				getDriver().findElement(cbb_state).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_state).sendKeys(Keys.RETURN);	
				waitForElementResponse();
			}
			
		}
	public void selectTag(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.TAGS.getValue());		
		
		if(!inputText.isEmpty())
		{
			waitForElementResponse();
			
			specialClick(cbb_tags);	
			waitForElementResponse();
			getDriver().findElement(cbb_tags).sendKeys(inputText);	
			waitForElementResponse();
			//Press "Enter" on keyboard
			getDriver().findElement(cbb_tags).sendKeys(Keys.RETURN);
		}
		}
	public void setCreateManualCheckBox(String testFileName, String leadType) throws Throwable
	{
		objLead<Boolean, String> temp = new objLead<Boolean, String>();
		Boolean inputBoolean =  temp.getJsonValue(testFileName,leadType,dataJsonLead.CREATEMANUAL.getValue());	
		System.out.println("inputBoolean for " + leadType +" : " + inputBoolean);
		if(inputBoolean == true)
			setOnCreateManual(testFileName,leadType);
		else
				setOffCreateManual(testFileName,leadType);
	}
	public void setOnCreateManual(String testFileName, String leadType) throws Throwable
	{				
		WebDriver dr = super.getDriver();
		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
		
		//1. Get value of //input[@id]
		String attributeValue = getDriver().findElement(chb_is_create_manual).getAttribute("id");
		//Logger.info("Attribute of ID is "+ attributeValue);	
		
		//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
		String checkTheCheckBox = 
				  "return document.querySelector(\'#" + attributeValue + "\')" +
				  ".checked";
		
		//Logger.info("Checkbox element is "+ checkTheCheckBox);	
		Boolean isChecked = (Boolean) Js1.executeScript(checkTheCheckBox);
		
		//3. If the CreateManual checkbox is UNcheck -> click on it
		if(!isChecked)
			specialClick(chb_is_create_manual);
			
	}
	public Boolean isChecked(By elementName)
	{
		WebDriver dr = super.getDriver();
		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
		
		//1. Get value of //input[@id]
				String attributeValue = getDriver().findElement(elementName).getAttribute("id");
		//1. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
				String checkTheCheckBox = 
						  "return document.querySelector(\'#" + attributeValue + "\')" +
						  ".checked";
				
				return (Boolean) Js1.executeScript(checkTheCheckBox);
	}
	public void setOffCreateManual(String testFileName, String leadType) throws Throwable
	{				
		WebDriver dr = super.getDriver();
		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
		
		//1. Get value of //input[@id]
		String attributeValue = getDriver().findElement(chb_is_create_manual).getAttribute("id");
		//Logger.info("Attribute of ID is "+ attributeValue);	
		
		//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
		String checkTheCheckBox = 
				  "return document.querySelector(\'#" + attributeValue + "\')" +
				  ".checked";
		
		//Logger.info("Checkbox element is "+ checkTheCheckBox);	
		Boolean isChecked = (Boolean) Js1.executeScript(checkTheCheckBox);
		System.out.println("attributeValue: " + attributeValue);
		System.out.println("checkTheCheckBox: " +checkTheCheckBox );
		System.out.println("isChecked: " + isChecked);
		//3. If the CreateManual checkbox is check -> click on it
		if(isChecked)
			specialClick(chb_is_create_manual);		
		System.out.println("isChecked: " + isChecked);
		
		
		isChecked = isChecked(chb_is_create_manual);
		System.out.println("isChecked again: " + isChecked);
	}
	public void setStageOrPriority(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputTextOfStageOpp =  temp.getJsonValue(testFileName,leadType,dataJsonLead.STAGEOPP.getValue());	
		String inputTextOfPriority =  temp.getJsonValue(testFileName,leadType,dataJsonLead.PRIORITY.getValue());
		
		if(inputTextOfStageOpp!=null)
		{
			setStageOpp(testFileName, leadType);
		}
		
		if(inputTextOfPriority!=null)
		{
			setPriorityOpp(testFileName, leadType);
		}
		
	}
	public void setStageOpp(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText =  temp.getJsonValue(testFileName,leadType,dataJsonLead.STAGEOPP.getValue());	
		
		waitForElementResponse();
		switch (inputText)
		{
		case "new":
			//Only if the attribute "aria-checked" of the target Tab is "false", I can click on that tab, otherwise I could not
			if (getDriver().findElement(tab_stage_new).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_new);				}	
			break;
		case "in process":
			if (getDriver().findElement(tab_stage_in_process).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_in_process);}			
			break;
		case "qualified":
			if (getDriver().findElement(tab_stage_qualified).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_qualified);}						
			break;
		case "active interest":
			if (getDriver().findElement(tab_stage_active_interest).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_active_interest);}		
			break;
		case "hot deal":
			if (getDriver().findElement(tab_stage_hot_deal).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_hot_deal);}	
			break;
		case "purchase approval":
			if (getDriver().findElement(tab_stage_purchase_approval).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_purchase_approval);}				
			break;
		case "won":
			if (getDriver().findElement(tab_stage_won).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
			{specialClick(tab_stage_won);}		
			break;		
		}
			
	}
	
	
	
	/**This method is used to select the Priority of Lead if having that Key in JSON file
	 * @param testFileName
	 * @param leadType 
	 * @throws Throwable
	 */
	public void setPriorityIfRequired(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
			
		String inputTextOfPriority =  temp.getJsonValue(testFileName,leadType,dataJsonLead.PRIORITY.getValue());
		
		if(inputTextOfPriority!=null)
		{
			setPriorityOpp(testFileName, leadType);
		}
		
	}
	public void setPriorityOpp(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText =  temp.getJsonValue(testFileName,leadType,dataJsonLead.PRIORITY.getValue());	
		
		switch (inputText)
		{
		case "1-star":
			//Only if the attribute "aria-checked" of the target Star is "false", I can click on that Star, otherwise all Star will disappear if clicking on the active level
			if (getDriver().findElement(star_1).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				specialClick(star_1);						
			break;
		case "2-star":
			if (getDriver().findElement(star_2).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				specialClick(star_2);	
			break;
		case "3-star":
			if (getDriver().findElement(star_3).getAttribute("aria-checked").equalsIgnoreCase("false") == true)	
				specialClick(star_3);		
			break;
		case "4-star":
			if (getDriver().findElement(star_4).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				specialClick(star_4);
			break;		
		}
			
	}
	public Boolean isComboxContactEmpty()
	{
		String outputvalue = (String) getDriver().findElement(lbl_combobox_contact).getText();
		
		return outputvalue.isEmpty();
	}
	//----------------------Validation area--------------------------------------------------
	public void checkEmail(String valueCheck)
	{
		String outputvalue = null;
		if(isComboxContactEmpty() ==true)
		{
			System.out.println("lbl_email_form : " + lbl_email_form + "-------");
			outputvalue = (String) getDriver().findElement(lbl_email_form).getText();
		}
		else
		{
			System.out.println("lbl_email_partner : " + lbl_email_partner + "-------");
			outputvalue = (String) getDriver().findElement(lbl_email_partner).getText();			
		}
		
		
		System.out.println("outputvalue : " + outputvalue + "-------");
		Logger.verify("Verify the Email is " + valueCheck);
		
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"Email output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
	}
	
	public void checkStreetAddress(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_address).getText();
		Logger.verify("Verify the Street Address is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"Street output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
		
	}
	public void checkCountry(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_country).getText();
		Logger.verify("Verify the Country is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"Country output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
	}
	public void checkState(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_state).getText();
		Logger.verify("Verify the State is " + valueCheck);
		
			Assert.assertTrue(outputvalue.contains(valueCheck),
					"State output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		
	}
	public void checkContactName(String valueCheck)
	{
		waitForSecond(3);
		String outputvalue = (String) getDriver().findElement(lbl_contact_name).getText();
		String outputvalue1 = (String) specialGetText(lbl_contact_name);
		String outputvalue2 = (String) specialGetAttributetextContent(lbl_contact_name);
		System.out.println("lbl_contact_name : " + lbl_contact_name + "-------");
		System.out.println("outputvalue : " + outputvalue + "-------");
		System.out.println("outputvalue2 : " + outputvalue2 + "-------");
		
		
		String outputvalue3 = (String) getDriver().findElement(lbl_contact_name).getAttribute("textContent");
		System.out.println("outputvalue3 : " + outputvalue3 + "-------");
		
		String outputvalue4 = (String) getDriver().findElement(lbl_contact_name).getAttribute("innerText");
		System.out.println("outputvalue4 : " + outputvalue4 + "-------");
		
		String outputvalue5 = (String) getDriver().findElement(lbl_contact_name).getAttribute("innerHTML");
		System.out.println("outputvalue5 : " + outputvalue5 + "-------");
		
		String outputvalue6 = (String) getDriver().findElement(lbl_contact_name).getAttribute("value");
		System.out.println("outputvalue6 : " + outputvalue6 + "-------");
		
		Logger.verify("Verify the Contact name is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"Contact Name output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
	}
	public void checkCompanyName(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_company_name).getText();
		Logger.verify("Verify the Company name is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"Company name output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		
	}
	public void checkTag(ArrayList<String> valueCheck)
	{
		//NOTICE: The number of tags getting from UI might greater than the number of tags in valueCheck
		ArrayList<String> outputvalue = getTagItems();
		
		Logger.verify("Verify the Targs name are " + valueCheck);
		
			for (String i : valueCheck )
			{
				Assert.assertTrue((outputvalue.contains(i)),
						"Tag output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
			}
		
	}
	/**This method is the combo checking on multiple fields on Target Lead. Technically, only if the value on fields of Target lead is empty AND the value on the same fields of Source lead is not empty. These value will copy from Source Lead fields to Target lead fields.
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnTargetLead(String testFileName, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		
		//1. Check Email
			//1.1. If the value of Email field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!returnRandomEmail.isEmpty())
				inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1. If the value of Street name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue()).isEmpty())
				inputAddress = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputAddress = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1. If the value of Country field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue()).isEmpty())
				inputCountry = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputCountry = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1. If the value of State field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue()).isEmpty())
				inputState = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputState = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name
			//5.1. If the value of Contact name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.CONTACTNAME.getValue()).isEmpty())
				inputContactName = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.CONTACTNAME.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputContactName = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.CONTACTNAME.getValue());
			//5.2. Check the value on UI
			this.checkContactName(inputContactName);	
			
		//6. Check Tag
			//6.1. If the value of Tag name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()).isEmpty())
			{
				inputTags.add(temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()));
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			}
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
			
		
	}
	/**This method is the combo checking on multiple fields on Target Lead. Technically, only if the value on fields of Target lead is empty AND the value on the same fields of Source lead is not empty. These value will copy from Source Lead fields to Target lead fields.
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnTargetLead(String testFileName, String Contactsfile, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		String contactNameFromConctactObj = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		objContact<String, String> temp2 = new objContact<String, String>();
		
		//1. Check Email
			//1.1. If the value of Email field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!returnRandomEmail.isEmpty())
				inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1. If the value of Street name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue()).isEmpty())
				inputAddress = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputAddress = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1. If the value of Country field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue()).isEmpty())
				inputCountry = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputCountry = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1. If the value of State field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue()).isEmpty())
				inputState = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputState = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name						
			///If the Lead/ Opp is using the Contact from separate Contact file
			if (!Contactsfile.isEmpty() )
				//If having no any Contact child, meaning the Json Contact file need to treat as Reseller or only Company or Individual
				System.out.println("Number of Contact childs in json Contact: " + temp2.getTotalChildContacs(Contactsfile));
				if (temp2.getTotalChildContacs(Contactsfile) == 0)
					contactNameFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile, dataJsonContact.CONTACTNAME.getValue(),1);								 
				else
					contactNameFromConctactObj = temp2.getJsonValueOfChildContactByIndex(Contactsfile, dataJsonContact.CHILDCONTACTNAME.getValue(),1);	
			//5.2. Check the value on UI
				this.checkContactName(contactNameFromConctactObj);
		
		//6. Check Tag
			//6.1. If the value of Tag name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()).isEmpty())
			{
				inputTags.add(temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()));
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			}
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
			
		
	}
	
	/**This method is the combo checking on multiple fields on Source Lead. Technically, the value on fields of Source remain unchanged. 
	 * 
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnSourceLead(String testFileName, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		
		//1. Check Email
			//1.1 Get value from input JSON file 
			if(!returnRandomEmail.isEmpty())
			inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1 Get value from input JSON file 
				inputAddress = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1 Get value from input JSON file 
				inputCountry = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1 Get value from input JSON file 
				inputState = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name
			//5.1 Get value from input JSON file 
				inputContactName = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.CONTACTNAME.getValue());
			//5.2. Check the value on UI
			this.checkContactName(inputContactName);	
			
		//6. Check Tag
			//6.1 Get value from input JSON file 
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
		
	}
	/**This method is the combo checking on multiple fields on Source Lead. Technically, the value on fields of Source remain unchanged. 
	 * 
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnSourceLead(String testFileName, String Contactsfile,String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		String contactNameFromConctactObj = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		objContact<String, String> temp2 = new objContact<String, String>();
		//String contactNameFromConctactObj = temp2.getJsonValue(Contactsfile,dataJsonContact.CONTACTNAME.getValue());
		//1. Check Email
			//1.1 Get value from input JSON file 
			if(!returnRandomEmail.isEmpty())
			inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1 Get value from input JSON file 
				inputAddress = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1 Get value from input JSON file 
				inputCountry = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1 Get value from input JSON file 
				inputState = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name						
			///If the Lead/ Opp is using the Contact from separate Contact file
			if (!Contactsfile.isEmpty() )
				//If having no any Contact child, meaning the Json Contact file need to treat as Reseller or only Company or Individual
				System.out.println("Number of Contact childs in json Contact: " + temp2.getTotalChildContacs(Contactsfile));
				if (temp2.getTotalChildContacs(Contactsfile) == 0)
					contactNameFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile, dataJsonContact.CONTACTNAME.getValue(),1);								 
				else
					contactNameFromConctactObj = temp2.getJsonValueOfChildContactByIndex(Contactsfile, dataJsonContact.CHILDCONTACTNAME.getValue(),1);	
			//5.2. Check the value on UI
				this.checkContactName(contactNameFromConctactObj);
			
		//6. Check Tag
			//6.1 Get value from input JSON file 
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
		
	}
	public void checkIsWon(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_is_won).getText();
		Logger.verify("Verify the isWon is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");
	}
	public void checkActive(Boolean valueCheck)
	{
		WebDriver dr = super.getDriver();
		JavascriptExecutor Js1 = (JavascriptExecutor) dr;
		
		//1. Get value of //input[@id]
		String attributeValue = getDriver().findElement(chb_active).getAttribute("id");
		//Logger.info("Attribute of ID is "+ attributeValue);	
		
		//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the beginning of command to return the value of checking
		String checkTheCheckBox = 
				  "return document.querySelector(\'#" + attributeValue + "\')" +
				  ".checked";
		
		//Logger.info("Checkbox element is "+ checkTheCheckBox);	
		Boolean isChecked = (Boolean) Js1.executeScript(checkTheCheckBox);
		
		
		//3. Now start to check
		Boolean outputvalue = isChecked;
		Logger.verify("Verify the checkbox Active is " + valueCheck);
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
	}
	public void checkLostReason(String valueCheck)
	{
		
		String outputvalue = (String) getDriver().findElement(cbb_lost_reason).getText();
		
		if(!valueCheck.isEmpty())
			Logger.verify("Verify the LostReason is " + valueCheck);
		else
			Logger.verify("Verify the LostReason is " + "EMPTY");
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
	}
	public void checkTag(String valueCheck)
	{
		ArrayList<String> outputvalue = this.getTagItems();
		
		Logger.verify("Verify the Tags list contain  " + valueCheck);
		
			Assert.assertTrue(outputvalue.contains(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
	}
	/**This method is used to check the message on Target Lead like ["target lead's name", has been merged into this lead]
	 * <pre>
	 * This method is used to create the Source lead
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @param inputEmail
	 * @throws Throwable
	 */
	public void checkMergeMessageOnTargetLead(String testFileName, String returnRandomEmail)
	{
		By replace_dynamic_control_1 = null;
		By replace_dynamic_control_2_1 = null;
		By replace_dynamic_control_2_2 = null;
		
		objLead<String, String> temp = new objLead<String, String>();
		//Get the Lead name of Source Lead
		String sourceName = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.LEADNAME.getValue());
		String targetName = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.LEADNAME.getValue());
		
		//1. The system log note on Target Lead will be ["source lead's name",has been merged into this lead]
		replace_dynamic_control_1 = Common.replaceDynamicControl(lbl_dynamic_merge_target_lead_1,"SOURCE_NAME#1",sourceName);
		
		
		Logger.verify("Verify the System log note is [\"target lead's name\",has been merged into this lead]");		
		
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_1).isDisplayed());				
		
		//2. The system log note on Target Lead will be ["Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead target lead's name"]
		replace_dynamic_control_2_1 = Common.replaceDynamicControl(lbl_dynamic_merge_target_lead_2,"SOURCE_EMAIL#1",returnRandomEmail);
		replace_dynamic_control_2_2 = Common.replaceDynamicControl(replace_dynamic_control_2_1,"TARGET_NAME#1",targetName);
		Logger.verify("Verify the System log note is [\"Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead target lead's name\"]");		
		
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_2_2).isDisplayed());				
		
	}
	
	/**This method is used to check the message on Source Lead like [This lead has been merged into target lead]
	 * <pre>
	 * This method is used to create the Source lead
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @param inputEmail
	 * @throws Throwable
	 */
	public void checkMergeMessageOnSourceLead(String testFileName, String returnRandomEmail)
	{
		By replace_dynamic_control_1 = null; 
		By replace_dynamic_control_2_1 = null;
		By replace_dynamic_control_2_2 = null;
		objLead<String, String> temp = new objLead<String, String>();
		//Get the Lead name of Target Lead
		String targetLeadName = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.LEADNAME.getValue());
		String sourceLeadName = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.LEADNAME.getValue());
		
		//1. The system log note on Source Lead will be [This lead has been merged into "target lead's name"]
		replace_dynamic_control_1 = Common.replaceDynamicControl(lbl_dynamic_merge_source_lead_1,"TARGET_NAME#1",targetLeadName);
				
		Logger.verify("Verify the System log note is [This lead has been merged into \"target lead's name\"]");		
		
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_1).isDisplayed());	
		
		//2. The system log note on Source Lead will be [Your lead  "source lead's name"has been automatically merged into "target lead's name" and closed.]
		replace_dynamic_control_2_1 = Common.replaceDynamicControl(lbl_dynamic_merge_source_lead_2,"SOURCE_NAME#1",sourceLeadName);
		replace_dynamic_control_2_2 = Common.replaceDynamicControl(replace_dynamic_control_2_1,"TARGET_NAME#1",targetLeadName);
		Logger.verify("Verify the System log note is [Your lead  \"source lead's name\"has been automatically merged into \"target lead's name\" and closed.]");		
		
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_2_2).isDisplayed());				
		
	}
	//---------------------------------------Archive page------------------------------------------------
	
	
	public void searchOnArchive(String textSearch) throws Throwable
	{
		
		getDriver().findElement(txt_search).sendKeys(textSearch);
		waitForElementResponse();
		//Press "Enter" on keyboard
		getDriver().findElement(txt_search).sendKeys(Keys.RETURN);
		
	}
	/**This method is used to click on Target Lead or Source Lead (after creating these 2 leads completely)
	 * <pre>
	 * 
	 * </pre>
	 * @param testFileName
	 * @param leadType
	 * @throws Throwable
	 */
	public void clickOnItemLead(String testFileName,String leadType) throws Throwable
	{
		By replace_dynamic_controll = null;
		
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADNAME.getValue());
		
		if (leadType.equalsIgnoreCase(Constants.TARGET_LEAD))
			replace_dynamic_controll = Common.replaceDynamicControl(lnk_dynamic_targe_lead,"REPLACE#1",inputText);
		else if (leadType.equalsIgnoreCase(Constants.SOURCE_LEAD))
			replace_dynamic_controll = Common.replaceDynamicControl(lnk_dynamic_source_lead,"REPLACE#1",inputText);				
		specialClick(replace_dynamic_controll);
		waitForPageDisplay();
	}
	/**This method is used to get list of Tags on Target Lead or Source Lead (after creating these 2 leads completely)
	 * <pre>
	 * 
	 * </pre>	 
	 */
	public ArrayList<String> getTagItems()
	{
		ArrayList<String> list = new ArrayList<String>();
		
		WebElement tag_items_parent =  getDriver().findElement(lbl_tag);
		
		//Get list of child elements
		List<WebElement> tag_items_childs = tag_items_parent.findElements(By.xpath("./child::*"));
	      // iterate child nodes
	      for ( WebElement i : tag_items_childs ) {
	      //getText() to get text for child nodes
	      
	      list.add(i.getText());
	      }
	      
		return list;
	}
	public String refineStateString (String inputState) 
	{
		String outputString = null;
		
		int positionOfIndex = inputState.indexOf("(");
		outputString = inputState.substring(0, positionOfIndex-1);
		System.out.println(outputString);
		return outputString;
		
	}
	
	
}
