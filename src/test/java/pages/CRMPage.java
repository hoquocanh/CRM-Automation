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
	By lnk_dynamic_lead = By.xpath("//tr[contains(@class,'o_data_row')]/td[contains(text(),'TARGET_NAME#1')]");
	//By lnk_dynamic_targe_lead =  By.xpath("//tr[contains(@class,'o_data_row')]/td[contains(text(),'SOURCE_NAME#1')]");
	
	//IV. In CRM page		
	//1. Detail Lead page
	
	By btn_save =By.xpath("//button[contains(text(),'Save')]");
	By btn_discard 	=By.xpath("//button[contains(text(),'Discard')]");
	By btn_paper_plane =By.xpath("//button[contains(@class,'paper-plane')]");
		
	
	//Textbox
	By txt_name 	=By.xpath("//input[@name='name']");
	By txt_email 	=By.xpath("(//input[contains(@name,'email_from')])[2]");
	By txt_contact_name =By.xpath("(//input[contains(@name,'contact_name')])[1]");
	By txt_company_name =By.xpath("(//input[contains(@name,'partner_name')])[1]");
	By txt_street =By.xpath("(//input[contains(@name,'street2')])[1]");
	
	By txt_zip  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/following-sibling::input[contains(@name,'zip')]");
	By txt_lead_form =By.xpath("//input[contains(@name,'x_studio_lead_sorce')]");
	By txt_send_message =By.xpath("//textarea[contains(@class,'composer_text_field')]");
	
	//Combobox
	By cbb_contact =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'partner_id')][1]/descendant::input");
	By cbb_sales_team =By.xpath("//select[contains(@name,'team_id')]");
	By cbb_country =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'country_id')][1]/descendant::input");	
	By cbb_state  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/descendant::input");
	By cbb_tags =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][3]/descendant::input[contains(@type,'text')]");
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
	By lbl_email = By.xpath("//table[contains(@class,'o_group_col_6') and not(contains(@class,'o_invisible_modifier'))]/descendant::a[contains(@name,'email_from')]");
	By lbl_address = By.xpath("(//span[contains(@name,'street2')])[1]");
	By lbl_contact_name = By.xpath("(//span[contains(@name,'contact_name')])[4]");
	By lbl_company_name = By.xpath("(//span[contains(@name,'partner_name')])[1]");
	By lbl_state = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::span[contains(@name, 'state_id')][1]");
	By lbl_country = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::span[contains(@name, 'country_id')][1]");
		//lbl_tag is a special lbl, it will give us the number of tags selected
	By lbl_tag = By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][2]");
	//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'tag_ids')][2]/descendant::span[contains(@role,'img')]
	By lbl_star_1 =By.xpath("(//div[@name='priority']/a[@title='Medium'])[2]");
	By lbl_star_2 =By.xpath("(//div[@name='priority']/a[@title='Medium High'])[2]");
	By lbl_star_3 =By.xpath("(//div[@name='priority']/a[@title='High'])[2]");
	By lbl_star_4 =By.xpath("(//div[@name='priority']/a[@title='Very High'])[2]");
	By lbl_being_checked_star =By.xpath("(//div[@name='priority']/a[@aria-checked='true'])[2]");
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
		getDriver().findElement(sub_menu_leads_leads).click();
		waitForPageDisplay();
		//Find label "Leads" to make sure the "Leads" page displays completely
		getDriver().findElement(lbl_leads);
		
	}
	public void clickArchiveMenu() throws Throwable 
	{
		getDriver().findElement(menu_archive).click();
		waitForElementResponse();
	}
	public void clickArchive_AllSubMenu() throws Throwable 
	{
		getDriver().findElement(sub_menu_archive_all).click();
		waitForPageDisplay();
		//Find label "Leads" to make sure the "Leads" page displays completely
		getDriver().findElement(lbl_all_leads);
		
	}
	public void clickCreateButton() throws Throwable 
	{
		getDriver().findElement(btn_create).click();
		
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
	
	public void goToSub_ArchiveMenu(String subMenu) throws Throwable
	{
		
		//1. Click on "Archive" menu
		this.clickArchiveMenu();
		//2. Click on "All Leads" sub-menu
		this.clickArchive_AllSubMenu();
		
	}

//---------------------------------------CRM page------------------------------------------------
	public void enterLeadName(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADNAME.getValue());
		System.out.println("Lead Name is: " + inputText);
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
		
		//Explanation: If the value of key "testcaseType" in JSON file is "public domain" or "company domain"
		if(inputTestCaseType.equalsIgnoreCase("public domain"))
			randomEmail = Common.getRandomPublicEmail();
		else if(inputTestCaseType.equalsIgnoreCase("test domain"))
			randomEmail = Common.getRandomTestEmail();
		else if(inputTestCaseType.equalsIgnoreCase("company domain"))
			randomEmail = Common.getRandomCompanyEmail();
		else if(inputTestCaseType.equalsIgnoreCase("gmail domain"))
			randomEmail = Common.getRandomGmailEmail(Common.getRandomLocalPartEmail());
		else if(inputTestCaseType.equalsIgnoreCase("yahoo domain"))
			randomEmail = Common.getRandomYahooEmail(Common.getRandomLocalPartEmail());
		
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
		//Set random email to the Email address on the Json file
			//objLead.setJsonValue(testFileName,leadType,dataJsonLead.EMAILADDRESS.getValue(), inputEmail);
		getDriver().findElement(txt_email).sendKeys(inputEmail);
		
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
	public String enterDifferentDomainEmail(String testFileName, String leadType, String inputEmail) throws Throwable	
	
	{
		getDriver().findElement(txt_email).sendKeys(inputEmail);
		return inputEmail;
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

			getDriver().findElement(cbb_contact).click();
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
		
		getDriver().findElement(btn_save).click();
		waitForPageDisplay();
	}
	public void clickCRMDeveloper() throws Throwable
	{
		
		getDriver().findElement(tab_crm_developer).click();
		waitForElementResponse();
	}
	//For the kind of Country combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectCountry(String testFileName, String leadType) throws Throwable
		{
			objLead<String, String> temp = new objLead<String, String>();
			String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.COUNTRY.getValue());		
			
			if(!inputText.isEmpty())
			{
				getDriver().findElement(cbb_country).click();
				waitForElementResponse();
				getDriver().findElement(cbb_country).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_country).sendKeys(Keys.RETURN);		
			}
			
		}
		
		public void selectState(String testFileName, String leadType) throws Throwable
		{
			objLead<String, String> temp = new objLead<String, String>();
			String inputText = this.refineStateString(temp.getJsonValue(testFileName,leadType,dataJsonLead.STATE.getValue()));		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();

				getDriver().findElement(cbb_state).click();
				waitForElementResponse();
				getDriver().findElement(cbb_state).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_state).sendKeys(Keys.RETURN);		
			}
			
		}
	public void selectTag(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText = temp.getJsonValue(testFileName,leadType,dataJsonLead.TAGS.getValue());		
		
		if(!inputText.isEmpty())
		{
			waitForElementResponse();
			
			getDriver().findElement(cbb_tags).click();	
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
			getDriver().findElement(div_chb_is_create_manual).click();
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
		
		//3. If the CreateManual checkbox is check -> click on it
		if(isChecked)
			getDriver().findElement(div_chb_is_create_manual).click();
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
			setPriorityLead(testFileName, leadType);
		}
		
	}
	
	public void setPriorityLead(String testFileName, String leadType) throws Throwable
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputText =  temp.getJsonValue(testFileName,leadType,dataJsonLead.PRIORITY.getValue());	
		
		switch (inputText)
		{
		case "1-star":
			//Only if the attribute "aria-checked" of the target Star is "false", I can click on that Star, otherwise all Star will disappear if clicking on the active level
			if (getDriver().findElement(star_1).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				getDriver().findElement(star_1).click();		
			break;
		case "2-star":
			if (getDriver().findElement(star_2).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				getDriver().findElement(star_2).click();		
			break;
		case "3-star":
			if (getDriver().findElement(star_3).getAttribute("aria-checked").equalsIgnoreCase("false") == true)	
				getDriver().findElement(star_3).click();		
			break;
		case "4-star":
			if (getDriver().findElement(star_4).getAttribute("aria-checked").equalsIgnoreCase("false") == true)
				getDriver().findElement(star_4).click();	
			break;		
		}
	}
	//----------------------Validation area--------------------------------------------------
	public void checkEmail(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_email).getText();
		if(!valueCheck.isEmpty())
			Logger.verify("Verify the Email is " + valueCheck);
		else
			Logger.verify("Verify the Email is " + "EMPTY");
		
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}				
	}
	public void checkEmailTest(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_email).getText();
		if(!valueCheck.isEmpty())
			Logger.verify("Verify the Email is " + valueCheck);
		else
			Logger.verify("Verify the Email is " + "EMPTY");
		
		
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
					
	}
	public void checkStreetAddress(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_address).getText();
		Logger.verify("Verify the Street Address is " + valueCheck);
			
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
	}
	public void checkCountry(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_country).getText();
		Logger.verify("Verify the Country is " + valueCheck);
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		
	}
	public void checkState(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_state).getText();
		Logger.verify("Verify the State is " + valueCheck);
		try {
			Assert.assertTrue(outputvalue.contains(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
	}
	public void checkContactName(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_contact_name).getText();
		Logger.verify("Verify the Contact name is " + valueCheck);
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}		
	}
	public void checkCompanyName(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_company_name).getText();
		Logger.verify("Verify the Company name is " + valueCheck);
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");		
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}		
	}
	public void checkTag(ArrayList<String> valueCheck)
	{
		//NOTICE: The number of tags getting from UI might greater than the number of tags in valueCheck
		ArrayList<String> outputvalue = getTagItems();
		
		Logger.verify("Verify the Tags name are " + valueCheck);
		
			for (String i : valueCheck )
			{
				Assert.assertTrue((outputvalue.contains(i)),"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");	
				
			}
	}
	public void checkPriority(String valueCheck)
	{
		//The value of "outputValue" will be one of [Medium, Medium High, High, Very High]
		String outputValue = (String) getDriver().findElement(lbl_being_checked_star).getAttribute("title");
		String inputValue = convertStarToPriorityLevel(valueCheck);
		Logger.verify("Verify the Priority is " + valueCheck + " equal to " + inputValue);
		try {
			Assert.assertTrue(outputValue.equals(inputValue),
					"output value : " + outputValue + " ; expected value : "+ inputValue + "|");		
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
	}
	public String convertStarToPriorityLevel(String inputText)
	{
		String outputText = "";
		switch (inputText)
		{
		case "1-star":
			outputText= "Medium";		
			break;
		case "2-star":
			outputText= "Medium High";	
			break;
		case "3-star":
			outputText= "High";		
			break;
		case "4-star":
			outputText= "Very High";	
			break;
		
		}
		return outputText;
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
		String inputPriority = null;
		
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
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
			
	}
	
	/**This method is the combo checking on multiple fields on Target Lead. Technically, the value on Target lead fields remain unchanged.
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnTargetLead_NOTMerged(String testFileName, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		
		//1. Check Email
			//1.1. If the value of Email field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!returnRandomEmail.isEmpty())
				inputEmail = returnRandomEmail;
			
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			inputAddress = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue());			
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country			
			inputCountry = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue());			
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State			
			inputState = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue());			
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name			
			inputContactName = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.CONTACTNAME.getValue());			
			//5.2. Check the value on UI
			this.checkContactName(inputContactName);	
			
		//6. Check Tag			
			inputTags.add(temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()));			
			//6.2. Check the value on UI
			this.checkTag(inputTags);		
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
			
	}
	/**This method is the combo checking on multiple fields on Target Lead. Technically, the value on Target lead fields remain unchanged.
	 * <pre>The current fields being check:</pre>
	 * <pre>Email</pre>
	 * <pre>Street name</pre>
	 * <pre>Country</pre>
	 * <pre>State</pre>
	 * <pre>Contact name</pre>
	 * <pre>List of tags</pre>
	 * @param testFileName
	 */
	public void checkValueOfFieldOnTargetLead_NOTMerged(String testFileName, String Contactsfile, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		objContact<String, String> temp2 = new objContact<String, String>();
		
		
		//1. Check Email
			//1.1. If the value of Email field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!returnRandomEmail.isEmpty())
				inputEmail = returnRandomEmail;
			
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name. In this scenario, we will compare with streetAddress that moved from Contact object
			//NOTICE: We assume the 1st Company in input Contactsfile is the Company for the Target Lead
			String streetAddressFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.STREETADDRESS.getValue(),1);			
			//2.2. Check the value on UI
			checkStreetAddress(streetAddressFromConctactObj);	
			
		//3. Check Country			
			inputCountry = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue());			
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State			
			inputState = temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue());			
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name. In this scenario, we will compare with Contact Name that moved from Contact object		
			//NOTICE: We assume the 1st Company in input Contactsfile is the Company for the Target Lead
			String contactNameFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTNAME.getValue(),1);			
			String contactType = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTTYPE.getValue(),1);
			//5.2. Check the value on UI
			if (contactType.equalsIgnoreCase("individual"))
				this.checkContactName(contactNameFromConctactObj);
			else if (contactType.equalsIgnoreCase("company"))
				this.checkCompanyName(contactNameFromConctactObj);
			
		//6. Check Tag			
			inputTags.add(temp.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()));			
			//6.2. Check the value on UI
			this.checkTag(inputTags);		
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
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
	public void checkValueOfFieldOnTargetLead(String testFileName, String Contactsfile, String returnRandomEmail)
	{
		objLead<String, String> temp1 = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		
		objContact<String, String> temp2 = new objContact<String, String>();
		
		//1. Check Email
			//1.1. If the value of Email field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!returnRandomEmail.isEmpty())
				inputEmail = returnRandomEmail;
			
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1. If the value of Street name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue()).isEmpty())
				inputAddress = temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STREETADDRESS.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputAddress = temp1.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1. If the value of Country field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue()).isEmpty())
				inputCountry = temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.COUNTRY.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputCountry = temp1.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1. If the value of State field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue()).isEmpty())
				inputState = temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.STATE.getValue());
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputState = temp1.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name
			//5.1. If the value of Contact name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			String contactNameFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTNAME.getValue(),1);			
			String contactType = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTTYPE.getValue(),1);
			//5.2. Check the value on UI
			if (contactType.equalsIgnoreCase("individual"))
				this.checkContactName(contactNameFromConctactObj);
			else if (contactType.equalsIgnoreCase("company"))
				this.checkCompanyName(contactNameFromConctactObj);

			
		//6. Check Tag
			//6.1. If the value of Tag name field on Target Lead from JSON file is not empty, set the input value to be checked as the value from Target Lead 
			if(!temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()).isEmpty())
			{
				inputTags.add(temp1.getJsonValue(testFileName, Constants.TARGET_LEAD,dataJsonLead.TAGS.getValue()));
				inputTags.add(temp1.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			}
			else //Else, if there is no value at that field on Target Lead, the value from Source Lead will be added to the field on Target lead
				inputTags.add(temp1.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);		
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp1.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp1.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
			
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
		String inputPriority = null;
		
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
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
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
	public void checkValueOfFieldOnSourceLead_NOTMerged(String testFileName, String Contactsfile, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		objContact<String, String> temp2 = new objContact<String, String>();
		//1. Check Email
			//1.1 Get value from input JSON file 
			if(!returnRandomEmail.isEmpty())
			inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//NOTICE: We assume the 2nd Company in input Contactsfile is the Company for the Target Lead
			String streetAddressFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.STREETADDRESS.getValue(),2);			
			//2.2. Check the value on UI
			checkStreetAddress(streetAddressFromConctactObj);	
			
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
			//NOTICE: We assume the 2nd Company in input Contactsfile is the Company for the Target Lead
			String contactNameFromConctactObj = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTNAME.getValue(),2);			
			String contactType = temp2.getJsonValueOfFatherContactByIndex(Contactsfile,dataJsonContact.CONTACTTYPE.getValue(),2);
			//5.2. Check the value on UI
			if (contactType.equalsIgnoreCase("individual"))
				this.checkContactName(contactNameFromConctactObj);
			else if (contactType.equalsIgnoreCase("company"))
				this.checkCompanyName(contactNameFromConctactObj);

			
		//6. Check Tag
			//6.1 Get value from input JSON file 
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
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
	public void checkValueOfFieldOnSourceLead_NOTMerged(String testFileName, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		
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
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
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
	public void checkValueOfFieldOnSecondSourceLead_NOTMerged(String testFileName, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		
		//1. Check Email
			//1.1 Get value from input JSON file 
			if(!returnRandomEmail.isEmpty())
			inputEmail = returnRandomEmail;
			//1.2. Check the value on UI
			checkEmail(inputEmail);
		
		//2. Check Street name
			//2.1 Get value from input JSON file 
				inputAddress = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD_2,dataJsonLead.STREETADDRESS.getValue());
			//2.2. Check the value on UI
			checkStreetAddress(inputAddress);	
			
		//3. Check Country
			//3.1 Get value from input JSON file 
				inputCountry = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD_2,dataJsonLead.COUNTRY.getValue());
			//3.2. Check the value on UI
			this.checkCountry(inputCountry);	
			
		//4. Check State
			//4.1 Get value from input JSON file 
				inputState = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD_2,dataJsonLead.STATE.getValue());
			//4.2. Check the value on UI
			this.checkState(inputState);	
			
		//5. Check Contact name
			//5.1 Get value from input JSON file 
				inputContactName = temp.getJsonValue(testFileName, Constants.SOURCE_LEAD_2,dataJsonLead.CONTACTNAME.getValue());
			//5.2. Check the value on UI
			this.checkContactName(inputContactName);	
			
		//6. Check Tag
			//6.1 Get value from input JSON file 
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD_2,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.SOURCE_LEAD_2,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.SOURCE_LEAD_2,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
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
	public void checkValueOfFieldOnSourceLead(String testFileName, String Contactsfile, String returnRandomEmail)
	{
		objLead<String, String> temp = new objLead<String, String>();
		String inputEmail = null;	
		String inputAddress = null;
		String inputCountry = null;
		String inputState = null;
		String inputContactName = null;
		ArrayList<String> inputTags = new ArrayList<String>();
		String inputPriority = null;
		
		objContact<String, String> temp2 = new objContact<String, String>();
		String contactNameFromConctactObj = temp2.getJsonValue(Contactsfile,dataJsonContact.CONTACTNAME.getValue());
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
			this.checkContactName(contactNameFromConctactObj);	
			
		//6. Check Tag
			//6.1 Get value from input JSON file 
				inputTags.add(temp.getJsonValue(testFileName, Constants.SOURCE_LEAD,dataJsonLead.TAGS.getValue()));
			//6.2. Check the value on UI
			this.checkTag(inputTags);	
			
		//7. Check Priority if required
			//7.1. If the Key "priority" in JSON is existed in Target Lead, the value of "priority" is the remaining no changed 
			if(temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue())!=null)
			{
				inputPriority = temp.getJsonValue(testFileName,Constants.TARGET_LEAD,dataJsonLead.PRIORITY.getValue());
				this.checkPriority(inputPriority);
			}
	}
	public void checkIsWon(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(lbl_is_won).getText();
		if (!valueCheck.isEmpty())
			Logger.verify("Verify the isWon is " + valueCheck);
		else
			Logger.verify("Verify the isWon is " + "EMPTY");
		
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck + "|");
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		
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
		
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		
	}
	public void checkLostReason(String valueCheck)
	{
		String outputvalue = (String) getDriver().findElement(cbb_lost_reason).getText();
		
		if(!valueCheck.isEmpty())
			Logger.verify("Verify the LostReason is " + valueCheck);
		else
			Logger.verify("Verify the LostReason is " + "EMPTY");
		
		try {
			Assert.assertTrue(outputvalue.equals(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		
	}
	public void checkTag(String valueCheck)
	{
		ArrayList<String> outputvalue = this.getTagItems();
		
		Logger.verify("Verify the Tags list contain  " + valueCheck);
		try {
			Assert.assertTrue(outputvalue.contains(valueCheck),
					"output value : " + outputvalue + " ; expected value : "+ valueCheck+ "|");
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		
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
		try {
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_1).isDisplayed());				
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		//2. The system log note on Target Lead will be ["Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead target lead's name"]
		replace_dynamic_control_2_1 = Common.replaceDynamicControl(lbl_dynamic_merge_target_lead_2,"SOURCE_EMAIL#1",returnRandomEmail);
		replace_dynamic_control_2_2 = Common.replaceDynamicControl(replace_dynamic_control_2_1,"TARGET_NAME#1",targetName);
		Logger.verify("Verify the System log note is [\"Another lead from SOURCE_EMAIL#1 has been automatically merged into your lead target lead's name\"]");		
		try {
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_2_2).isDisplayed());				
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
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
		try {
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_1).isDisplayed());	
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
		
		//2. The system log note on Source Lead will be [Your lead  "source lead's name"has been automatically merged into "target lead's name" and closed.]
		replace_dynamic_control_2_1 = Common.replaceDynamicControl(lbl_dynamic_merge_source_lead_2,"SOURCE_NAME#1",sourceLeadName);
		replace_dynamic_control_2_2 = Common.replaceDynamicControl(replace_dynamic_control_2_1,"TARGET_NAME#1",targetLeadName);
		Logger.verify("Verify the System log note is [Your lead  \"source lead's name\"has been automatically merged into \"target lead's name\" and closed.]");		
		try {
			Assert.assertTrue(getDriver().findElement(replace_dynamic_control_2_2).isDisplayed());				
		}catch(AssertionError e)
		{
			System.out.println("Assertion error. ");
		}
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
		By replace_dynamic_control = null;
		
		objLead<String, String> temp = new objLead<String, String>();
		String inputLeadName = temp.getJsonValue(testFileName,leadType,dataJsonLead.LEADNAME.getValue());
		
		replace_dynamic_control = Common.replaceDynamicControl(lnk_dynamic_lead,"TARGET_NAME#1",inputLeadName);
		
		getDriver().findElement(replace_dynamic_control).click();
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
