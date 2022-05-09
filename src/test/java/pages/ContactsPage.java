package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utils.common.Common;
import utils.common.Constants;
import utils.data.dataJsonContact;
import utils.data.dataJsonLead;
import utils.helper.Logger;
import utils.object.objContact;
import utils.object.objLead;

public class ContactsPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	//I. Contacts page
	//1.Menu "Leads"
		
		
	//II. "CREATE" button...
	By btn_create =By.xpath("//button[contains(text(),'Create')]");
	By btn_view_list =By.xpath("//button[contains(@aria-label,'View list')]");
	
	//IV. In Contacts page		
		//1. Detail Contacts page
		
		By btn_save =By.xpath("//button[contains(text(),'Save')]");
		
		By rdb_individual =By.xpath("//input[contains(@data-value,'person')]");
		By rdb_company =By.xpath("//input[contains(@data-value,'company')]");
		
		//Textbox
		By txt_name 	=By.xpath("//input[@name='name']");
		By txt_email 	=By.xpath("(//input[contains(@name,'email')])[3]");
		
		By txt_street =By.xpath("(//input[contains(@name,'street2')])[1]");
		
		By txt_zip  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/following-sibling::input[contains(@name,'zip')]");
		
		
		//Combobox
		By cbb_sales_team =By.xpath("(//div[contains(@name,'team_id')])[1]");
		By cbb_country =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'country_id')][1]/descendant::input");	
		By cbb_state  =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'state_id')][1]/descendant::input");
		By cbb_tags =By.xpath("//div[contains(@class,'clearfix o_form_sheet')]/descendant::div[contains(@name, 'category_id')][1]/descendant::input[contains(@type,'text')]");
		
		
		//2. "Contacts&Addresses" tab....
		By tab_contact_addresses  =By.xpath("//a[contains(text(),'Contacts & Addresses')]");
		By lnk_add  =By.xpath("//div[contains(@name,'child_ids')]/descendant::button[contains(text(),'Add')]");
		
	// ============================ Constructor declaration============================//
	//Login Page continues to use the Driver which created at GeneralHomePage
	public ContactsPage()
	{
		super();
	}
	public ContactsPage(WebDriver Driver)
	{
		super(Driver);
	}
	// ============================ Methods============================//
	public void clickCreateButton() throws Throwable 
	{
		getDriver().findElement(btn_create).click();
		
		Common.waitPageLoad(3);
		//Find textbox "Lead Name" to make sure the "Leads" page displays completely
		getDriver().findElement(txt_name);
		
	}
	/**This method is used to create a Contact
	 * <pre>
	 * Pre-condition: The screen is being Home page
	 * </pre>
	 * This method including below steps
	 * 1. Go to Module CRM
	 * 2. Press "CREATE" button
	 * @throws Throwable
	 */
	public void createContact() throws Throwable
	{
		//1. Go to Module CRM
		super.gotoModuleContacts();				
		//2. Press "CREATE" button
		this.clickCreateButton();
		
	}
	//---------------------------------------CRM page------------------------------------------------
		public void enterContactName(String testFileName, String inputKey) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,inputKey,dataJsonLead.LEADNAME.getValue());
			getDriver().findElement(txt_name).sendKeys(inputText);
			
		}
		public void enterContactChildName(String testFileName, String inputChildName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CHILDCONTACTNAME.getValue(), inputChildName);
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
			if(inputTestCaseType.contains("public domain"))
				randomEmail = Common.getRandomPublicEmail();
			else if(inputTestCaseType.contains("company domain"))
				randomEmail = Common.getRandomCompanyEmail();
			
			System.out.println("Random email:"+ randomEmail);
			Logger.info("Random email:"+ randomEmail);
			//Set random email to the Email address on the Json file
				//objLead.setJsonValue(testFileName,leadType,dataJsonLead.EMAILADDRESS.getValue(), randomEmail);
				//objLead.setJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonLead.EMAILADDRESS.getValue(), randomEmail);
			
			getDriver().findElement(txt_email).sendKeys(randomEmail);
			
			return randomEmail;
		}
}
