package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import utils.common.Common;
import utils.common.Constants;
import utils.data.dataJsonContact;
import utils.data.dataJsonLead;
import utils.data.dataJsonContact;
import utils.helper.Logger;
import utils.object.objContact;
import utils.object.objContact;

/**
 * @author anh.ho
 *
 */
/**
 * @author anh.ho
 *
 */
/**
 * @author anh.ho
 *
 */
public class ContactsPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	//I. Contacts page
	//1.Menu "Leads"
		
		
	//II. "CREATE" button...
	By btn_create =By.xpath("(//button[contains(text(),'Create')])[1]");
	By btn_edit =By.xpath("(//button[contains(text(),'Edit')])[1]");
	By btn_view_list =By.xpath("//button[contains(@aria-label,'View list')]");
	
	//IV. In Contacts page		
		//1. Detail Contacts page
		
		By btn_save =By.xpath("//button[contains(text(),'Save')]");
		
		By rdb_individual =By.xpath("//input[contains(@data-value,'person')]");
		By rdb_company =By.xpath("//input[contains(@data-value,'company')]");
		
		//Textbox
		By txt_name 	=By.xpath("(//input[@name='name'])[1]");
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
		
		//3. Sub Create Contacts page after press "ADD" button
		By txt_child_contact_name  =By.xpath("(//input[@name='name'])[2]");
		By txt_child_contact_email  =By.xpath("(//input[contains(@name,'email')])[4]");
		By btn_child_contact_save_close  =By.xpath("//button/span[contains(text(),'Save & Close')]");
		By btn_child_contact_save  =By.xpath("//button/span[contains(text(),'Save')]");
		By btn_child_cactact_discard  =By.xpath("//button/span[contains(text(),'Discard')]");
		By btn_child_contact_remove  =By.xpath("//button/span[contains(text(),'Remove')]");
						
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
	//---------------------------------------Contacts page------------------------------------------------
		/**enterContactName is used to enter the Contact Name when creating a Contact
		 * @param testFileName
		 * @throws Throwable
		 */
		public void enterContactName(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CONTACTNAME.getValue());
			getDriver().findElement(txt_name).sendKeys(inputText);
			
		}
		
		/**enterContactChildName is used to enter the contact name of a Child of an existing Contact 
		 * @param testFileName
		 * @param inputChildName
		 * @throws Throwable
		 */
		public void enterContactChildName(String testFileName, String inputChildName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CHILDCONTACTNAME.getValue(), inputChildName);
			getDriver().findElement(txt_child_contact_name).sendKeys(inputText);
			
		}
		/**This method is used to generate a random email and create a lead with that email
		 * <pre>
		 * This method is used to create the Target lead
		 * </pre>
		 * @param testFileName
		 * @param leadType
		 * @throws Throwable
		 */
		public String enterEmail(String testFileName) throws Throwable	
		
		{
			String randomEmail = "";
			
			//Get randomEmail
			randomEmail = Common.getRandomCompanyEmail();
			
			System.out.println("Random email:"+ randomEmail);
			Logger.info("Random email:"+ randomEmail);
			//Set random email to the Email address on the Json file
				//objContact.setJsonValue(testFileName,leadType,dataJsonContact.EMAILADDRESS.getValue(), randomEmail);
				//objContact.setJsonValue(testFileName,Constants.SOURCE_LEAD,dataJsonContact.EMAILADDRESS.getValue(), randomEmail);
			
			getDriver().findElement(txt_child_contact_email).sendKeys(randomEmail);
			
			return randomEmail;
		}
		
		//For the kind of Country combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectCountry(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.COUNTRY.getValue());		
			
			if(!inputText.isEmpty())
			{
				getDriver().findElement(cbb_country).click();
				waitForElementResponse();
				getDriver().findElement(cbb_country).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_country).sendKeys(Keys.RETURN);		
				waitForElementResponse();
			}
			
		}
		public void selectState(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = this.refineStateString(temp.getJsonValue(testFileName,dataJsonContact.STATE.getValue()));		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();

				getDriver().findElement(cbb_state).click();
				waitForElementResponse();
				getDriver().findElement(cbb_state).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_state).sendKeys(Keys.RETURN);	
				waitForElementResponse();
			}
			
		}
		public void enterStreetName(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.STREETADDRESS.getValue());
			getDriver().findElement(txt_street).sendKeys(inputText);
			
		}
		public void pressSaveButton() throws Throwable
		{
			
			getDriver().findElement(btn_save).click();
			waitForPageDisplay();
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
