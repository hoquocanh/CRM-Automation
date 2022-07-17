package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.common.Common;
import utils.common.Constants;
import utils.data.dataJsonContact;
import utils.data.dataJsonContact;
import utils.helper.Logger;
import utils.object.objContact;
import utils.object.objContact;

public class ContactsPage extends GeneralHomePage {
	
	// ============================ Element declaration============================//
	//I. Contacts page
	//1.Menu "Leads"
		
		
	//II. "CREATE" button...
	By btn_create =By.xpath("//button[contains(text(),'Create')]");
	By btn_create_in_details_page = By.xpath("(//button[contains(text(),'Create')])[1]");
	By btn_view_list =By.xpath("//button[contains(@aria-label,'View list')]");
	
	//IV. In Contacts page		
		//1. Detail Contacts page
		
		By btn_save =By.xpath("(//button[contains(text(),'Save')])[1]");
		By btn_edit =By.xpath("//button[contains(text(),'Edit')]");
		
		By rdb_individual =By.xpath("//input[contains(@data-value,'person')]");
		By div_rdb_individual =By.xpath("//div/input[contains(@data-value,'person')]");
		By rdb_company =By.xpath("//input[contains(@data-value,'company')]");
		By div_rdb_company =By.xpath("//div/input[contains(@data-value,'company')]");
		
		//Textbox
		By txt_name 	=By.xpath("//input[@name='name'][1]");
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
		
		By txt_child_name 	=By.xpath("(//input[@name='name'])[2]");
		By txt_child_email 	=By.xpath("(//input[contains(@name,'email')])[4]");
		By btn_child_save_close 	=By.xpath("//button/span[contains(text(),'Save & Close')]");
		
		//3. "Partner Assignation" tab....
		By tab_partner_assignation  =By.xpath("//a[contains(text(),'Partner Assignation')]");
		By cbb_level  =By.xpath("//select[contains(@name,'grade_id')]");
		By cbb_activation_date = By.xpath("//input[contains(@name,'activation_date')]");
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
	/**This method is a way to make the element is able to enter Text on Jenkins
	 * <pre>
	 * This method is a way to make the element is  able to enter Text on Jenkins
	 * </pre>
	 * @param elementName	 
	 * @throws Throwable
	 */
	public void specialInput(By elementName,String inputText)
	{
		WebElement elem = getDriver().findElement(elementName);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();
		executor.executeScript("arguments[0].sendKeys(inputText);", elem);
		
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
	/**This method is a way to make the element is able to enter Text on Jenkins
	 * <pre>
	 * This method is a way to make the element is  able to enter Text on Jenkins
	 * </pre>
	 * @param elementName	 
	 * @throws Throwable
	 */
	public String specialGetAttributeID(By elementName)
	{
		WebElement elem = getDriver().findElement(elementName);
		JavascriptExecutor executor = (JavascriptExecutor) getDriver();		
		
		String ID =(String) executor.executeScript("return arguments[0].getAttribute('id')", elem);
				
		return ID;
		
	}
	public void clickCreateButton() throws Throwable 
	{
		specialClick(btn_create);
		
		Common.waitPageLoad(3);
		//Find textbox "Lead Name" to make sure the "Leads" page displays completely
		//getDriver().findElement(txt_name);
		
	}
	public void clickCreateButtonInDetailsPage() throws Throwable 
	{
		Common.waitPageLoad(1);
		specialClick(btn_create_in_details_page);
		
		Common.waitPageLoad(2);
	}
	public void pressSaveButton() throws Throwable
	{
		specialClick(btn_save);
		waitForPageDisplay();
	}
	public void pressEditButton() throws Throwable
	{
		specialClick(btn_edit);
		waitForPageDisplay();
	}
	public void pressTab_ContactAddresses() throws Throwable
	{
		specialClick(tab_contact_addresses);
		waitForPageDisplay();
	}
	public void pressTab_PartnerAssignation() throws Throwable
	{
		specialClick(tab_partner_assignation);
		waitForPageDisplay();
	}
	public void pressTab_ContactAddresses_AddButton() throws Throwable
	{
		specialClick(lnk_add);
		waitForPageDisplay();
	}
	/**This method is used to create a Contact
	 * <pre>
	 * Pre-condition: The screen is being Home page
	 * </pre>
	 * This method including below steps 
	 * 1. Press "CREATE" button
	 * @throws Throwable
	 */
	public void createContact() throws Throwable
	{		
		//1. Press "CREATE" button
		this.clickCreateButton();
	}
	public void selectCreateButtonInDetailsPage() throws Throwable
	{		
		//1. Press "CREATE" button
		this.clickCreateButton();
	}
	
	//---------------------------------------Contact page------------------------------------------------
		public void enterContactName(String testFileName, String fatherContactEmail) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CONTACTNAME.getValue());
			
			//getDriver().findElement(txt_name).sendKeys(inputText);
			specialInput(txt_name,inputText);
			//Old method that generate an unique Contact name as template "Reseller Contact TEST_AUTOMATION_2022_06_10T11_30_0"
				//getDriver().findElement(txt_name).sendKeys(this.generateContactName(testFileName, fatherContactEmail, inputText));
			
		}
		public void enterContactName(String testFileName, int indexOfFatherContact) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValueOfFatherContactByIndex(testFileName,dataJsonContact.CONTACTNAME.getValue(),indexOfFatherContact);
			getDriver().findElement(txt_name).sendKeys(inputText);
			//Old method that generate an unique Contact name as template "Reseller Contact TEST_AUTOMATION_2022_06_10T11_30_0"
				//getDriver().findElement(txt_name).sendKeys(this.generateContactName(testFileName, fatherContactEmail, inputText));
			
		}
		public void enterContactChildName(String testFileName, String inputChildName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValueOfChildContactByContactName(testFileName,dataJsonContact.CHILDCONTACTNAME.getValue(), inputChildName);
			getDriver().findElement(txt_name).sendKeys(inputText);
			
		}
		public void selectContactType(String testFileName) throws Throwable
		{				
			WebDriver dr = super.getDriver();
			JavascriptExecutor Js1 = (JavascriptExecutor) dr;
			
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CONTACTTYPE.getValue());
			String attributeValue ="";
			if(!inputText.isEmpty())
				if(inputText.equalsIgnoreCase("company"))
				{	
					//1. Get value of //input[@id]
					
					//attributeValue = getDriver().findElement(rdb_company).getAttribute("id");
					
					attributeValue= specialGetAttributeID(rdb_company);
					System.out.println("ID of Company: "+ attributeValue);
					//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
					String checkTheRadioButton_Jscript= 
							  "return document.querySelector(\'#" + attributeValue + "\')" +
							  ".checked";
					
					String clickTheRadioButton_Jscript = 
							  "document.querySelector(\'#" + attributeValue + "\')" +
							  ".click()";
					//Logger.info("Checkbox element is "+ checkTheRadioButton);	
					Boolean isChecked = (Boolean) Js1.executeScript(checkTheRadioButton_Jscript);
					
					//3. If the Company radiobutton is UNcheck -> click on it
					if(isChecked == false)
						Js1.executeScript(clickTheRadioButton_Jscript);
				}	
				else if(inputText.equalsIgnoreCase("individual"))
				{
					//1. Get value of //input[@id]
					//attributeValue = getDriver().findElement(rdb_individual).getAttribute("id");
					attributeValue = specialGetAttributeID(rdb_individual);
					//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
					String checkTheRadioButton_Jscript = 
					  "return document.querySelector(\'#" + attributeValue + "\')" +
					  ".checked";
			
					String clickTheRadioButton_Jscript = 
							  "document.querySelector(\'#" + attributeValue + "\')" +
							  ".click()";
					//Logger.info("Checkbox element is "+ checkTheRadioButton);	
					Boolean isChecked = (Boolean) Js1.executeScript(checkTheRadioButton_Jscript);
					
					//3. If the Company radiobutton is UNcheck -> click on it
					if(isChecked == false)
						Js1.executeScript(clickTheRadioButton_Jscript);
				}
			waitForElementResponse();
		}
		public void selectContactType(String testFileName, int indexOfFatherContact) throws Throwable
		{				
			WebDriver dr = super.getDriver();
			JavascriptExecutor Js1 = (JavascriptExecutor) dr;
			
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValueOfFatherContactByIndex(testFileName,dataJsonContact.CONTACTTYPE.getValue(),indexOfFatherContact );
			String attributeValue ="";
			if(!inputText.isEmpty())
				if(inputText.equalsIgnoreCase("company"))
				{	
					//1. Get value of //input[@id]
					
					//attributeValue = getDriver().findElement(rdb_company).getAttribute("id");
					
					attributeValue= specialGetAttributeID(rdb_company);
					//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
					String checkTheRadioButton_Jscript= 
							  "return document.querySelector(\'#" + attributeValue + "\')" +
							  ".checked";
					
					String clickTheRadioButton_Jscript = 
							  "document.querySelector(\'#" + attributeValue + "\')" +
							  ".click()";
					//Logger.info("Checkbox element is "+ checkTheRadioButton);	
					Boolean isChecked = (Boolean) Js1.executeScript(checkTheRadioButton_Jscript);
					
					//3. If the Company radiobutton is UNcheck -> click on it
					if(isChecked == false)
						Js1.executeScript(clickTheRadioButton_Jscript);
				}	
				else if(inputText.equalsIgnoreCase("individual"))
				{
					//1. Get value of //input[@id]
					//attributeValue = getDriver().findElement(rdb_individual).getAttribute("id");
					attributeValue = specialGetAttributeID(rdb_individual);
					//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
					String checkTheRadioButton_Jscript = 
					  "return document.querySelector(\'#" + attributeValue + "\')" +
					  ".checked";
			
					String clickTheRadioButton_Jscript = 
							  "document.querySelector(\'#" + attributeValue + "\')" +
							  ".click()";
					//Logger.info("Checkbox element is "+ checkTheRadioButton);	
					Boolean isChecked = (Boolean) Js1.executeScript(checkTheRadioButton_Jscript);
					
					//3. If the Company radiobutton is UNcheck -> click on it
					if(isChecked == false)
						Js1.executeScript(clickTheRadioButton_Jscript);
				}
			waitForElementResponse();
		}
		/**This method is used to generate a random email and create a Contact with that email
		 * <pre>
		 * This method is used to create the Target lead
		 * </pre>
		 * @param testFileName
		 * @throws Throwable
		 * @return randomEmail
		 */
		public String enterEmail(String testFileName) throws Throwable			
		{
			String randomEmail = "";
			objContact<String, String> temp = new objContact<String, String>();
			//Contact type will be (1) "company" or (2) "individual"
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.CONTACTTYPE.getValue());	
			
			if(inputText.equalsIgnoreCase("company"))
				randomEmail = Common.getRandomCompanyEmail();
			else if (inputText.equalsIgnoreCase("individual"))
				randomEmail = Common.getRandomIndividualEmail();
			System.out.println("Random email:"+ randomEmail);
			Logger.info("Random email:"+ randomEmail);
						
			getDriver().findElement(txt_email).sendKeys(randomEmail);
			
			return randomEmail;
		}
		/**This method is used to generate a random email and create a Contact with that email
		 * <pre>
		 * This method is used to create the Target lead
		 * </pre>
		 * @param testFileName
		 * @throws Throwable
		 * @return randomEmail
		 */
		public String enterEmail(String testFileName, int indexOfFatherContact) throws Throwable			
		{
			String randomEmail = "";
			objContact<String, String> temp = new objContact<String, String>();
			//Contact type will be (1) "company" or (2) "individual"
			String inputText = temp.getJsonValueOfFatherContactByIndex(testFileName,dataJsonContact.CONTACTTYPE.getValue(),indexOfFatherContact);	
			
			if(inputText.equalsIgnoreCase("company"))
				randomEmail = Common.getRandomCompanyEmail();
			else if (inputText.equalsIgnoreCase("individual"))
				randomEmail = Common.getRandomIndividualEmail();
			System.out.println("Random email:"+ randomEmail);
			Logger.info("Random email:"+ randomEmail);
						
			getDriver().findElement(txt_email).sendKeys(randomEmail);
			
			return randomEmail;
		}
		
		//For the kind of Country combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectCountry(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.COUNTRY.getValue());		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();
				
				getDriver().findElement(cbb_country).click();
				waitForElementResponse();
				getDriver().findElement(cbb_country).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_country).sendKeys(Keys.RETURN);		
			}			
		}
		//For the kind of Country combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectCountry(String testFileName, int indexOfFatherContact) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValueOfFatherContactByIndex(testFileName,dataJsonContact.COUNTRY.getValue(),indexOfFatherContact);		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();
				
				getDriver().findElement(cbb_country).click();
				waitForElementResponse();
				getDriver().findElement(cbb_country).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_country).sendKeys(Keys.RETURN);		
			}			
		}
		
		/**THIS METHOD IS NOT COMPLETE YET
		 * @param testFileName
		 * @throws Throwable
		 */
		public void selectState(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = this.refineStateString(temp.getJsonValue(testFileName,dataJsonContact.STATE.getValue()));		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();
				Thread.sleep(5000);
				getDriver().findElement(cbb_state).click();
				waitForElementResponse();
				getDriver().findElement(cbb_state).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_state).sendKeys(Keys.RETURN);		
			}
			
		}
		
		/**THIS METHOD IS NOT COMPLETE YET
		 * @param testFileName
		 * @throws Throwable
		 */
		public void selectState2(String testFileName) throws Throwable
		{				
			WebDriver dr = super.getDriver();
			JavascriptExecutor Js1 = (JavascriptExecutor) dr;
			
			//1. Get value of //input[@id]
			String attributeValue = getDriver().findElement(cbb_state).getAttribute("id");
			//Logger.info("Attribute of ID is "+ attributeValue);	
			
			//2. Compose the Javascript command to check whether the Checkbox is check. Notice: There are the "return" word at the begining of command to return the value of checking
			String checkTheCheckBox = 
					  "return document.querySelector(\'#" + attributeValue + "\')" +
					  ".checked";
			
			//Logger.info("Checkbox element is "+ checkTheCheckBox);	
			Boolean isChecked = (Boolean) Js1.executeScript(checkTheCheckBox);
			
			
		}
		public void enterStreetName(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.STREETADDRESS.getValue());
			getDriver().findElement(txt_street).sendKeys(inputText);
			
		}
		public void enterStreetName(String testFileName, int indexOfFatherContact) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValueOfFatherContactByIndex(testFileName,dataJsonContact.STREETADDRESS.getValue(),indexOfFatherContact);
			getDriver().findElement(txt_street).sendKeys(inputText);
			
		}
		//For the kind of Partner level combobox, that contains the long list of items, we need to enter the name of item -> enter for selecting that item
		public void selectPartnerLevel(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String inputText = temp.getJsonValue(testFileName,dataJsonContact.PARTNERLEVEL.getValue());		
			
			if(!inputText.isEmpty())
			{
				waitForElementResponse();
				
				getDriver().findElement(cbb_level).click();
				waitForElementResponse();
				getDriver().findElement(cbb_level).sendKeys(inputText);	
				waitForElementResponse();
				//Press "Enter" on keyboard
				getDriver().findElement(cbb_level).sendKeys(Keys.RETURN);		
			}
			
		}
		//For the kind of Activation Date combobox, that contains the Calenda
		public void selectActivationDate(String testFileName) throws Throwable
		{
			objContact<String, String> temp = new objContact<String, String>();
			String type = temp.getJsonValue(testFileName,dataJsonContact.ACTIVATIONDATE.getValue());		
			
			if(!type.isEmpty())
				if(type.equalsIgnoreCase("current date"))
				{
				waitForElementResponse();
				
				getDriver().findElement(cbb_activation_date).click();
				waitForElementResponse();
				getDriver().findElement(cbb_activation_date).sendKeys(Common.getCurrentDateAsString());	
				waitForElementResponse();
				//Press "ENTER" on keyboard
				getDriver().findElement(cbb_activation_date).sendKeys(Keys.ENTER);
				//Press "TAB" on keyboard
				getDriver().findElement(cbb_activation_date).sendKeys(Keys.TAB);		
				}
			
		}
		public String refineStateString (String inputState) 
		{
			String outputString = null;
			
			int positionOfIndex = inputState.indexOf("(");
					outputString = inputState.substring(0, positionOfIndex-1);
					System.out.println(outputString);
					return outputString;
					
		}
		
		public String generateContactName(String testFileName, String fatherContactEmail, String fatherContactName) 
		{
			String outputString = null;
			
			int positionOfIndex = fatherContactEmail.indexOf("@");
			//The output father contact name be as "Comany Contact TEST_AUTOMATION_2022_05_13T10_45_06"
			outputString = fatherContactName + " " + fatherContactEmail.substring(0,positionOfIndex-1) ;
			
			System.out.println("Output adjusted contact name: " + outputString);
			return outputString;
					
		}
		//-------------------------Child Contact-----------------------------
		
		public void addAllChildContacts(String testFileName, String fatherContactEmail) throws Throwable
		{
			String childContactName =""; 
			objContact<String, String> temp = new objContact<String, String>();
			
			
			int totalChildContacts = temp.getTotalChildContacs(testFileName);
			
			//Firstly, press EDIT button on father Contact
			this.pressEditButton();
			
			//Secondly, add every child Contact
			for(int i=0; i<totalChildContacts; i++ )
				{
					//Pre-condition:
					
					this.pressTab_ContactAddresses();
					this.pressTab_ContactAddresses_AddButton();
					
					//Implement
					childContactName = temp.getJsonValueOfChildContactByIndex(testFileName, dataJsonContact.CHILDCONTACTNAME.getValue(), i);
					getDriver().findElement(txt_child_name).sendKeys(childContactName);
					getDriver().findElement(txt_child_email).sendKeys(generateChildContactEmail(testFileName,fatherContactEmail,childContactName));
					
					//Press SAVE & CLOSE
					getDriver().findElement(btn_child_save_close).click();				
				}			
		}
		public String addChildContactsByIndex(String testFileName, String fatherContactEmail, int index) throws Throwable
		{
			String childContactName =""; 
			objContact<String, String> temp = new objContact<String, String>();
			
			//Firstly, press EDIT button on father Contact
			this.pressEditButton();
			
			//Secondly, add every child Contact by index			
			//Pre-condition:
			
			this.pressTab_ContactAddresses();
			this.pressTab_ContactAddresses_AddButton();
			
			//Implement
			childContactName = temp.getJsonValueOfChildContactByIndex(testFileName, dataJsonContact.CHILDCONTACTNAME.getValue(), index);
			String randomEmail = generateChildContactEmail(testFileName,fatherContactEmail,childContactName);
			
			getDriver().findElement(txt_child_name).sendKeys(childContactName);
			getDriver().findElement(txt_child_email).sendKeys(randomEmail);
			
			//Press SAVE & CLOSE
			getDriver().findElement(btn_child_save_close).click();	
			waitForElementResponse();
			//Press SAVE button on Contact father
			pressSaveButton();
			
			return randomEmail;
							
		}
		public String generateChildContactEmail(String testFileName, String fatherContactEmail, String childContactName) 
		{
			String outputString = null;
			
			int positionOfIndex = fatherContactEmail.indexOf("@");
			//The outputEmail be as "Bob@company_2022_05_12T17_50_39.com
			outputString = childContactName + fatherContactEmail.substring(positionOfIndex,fatherContactEmail.length()) ;
			
			System.out.println("Output childContactEmail: " + outputString);
			return outputString;
					
		}
		
}
