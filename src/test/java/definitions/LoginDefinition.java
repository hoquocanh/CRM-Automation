package definitions;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CRMPage;
import pages.ContactsPage;
import pages.GeneralHomePage;
import pages.LoginPage;
import pages.OpportunityPage;
import pages.SettingPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;



import org.openqa.selenium.WebDriver;


public class LoginDefinition  {
	String returnRandomEmail = "";
	String returnRandomContactEmail ="";
	String returnRandomEmail_ContactChild1="";
	String returnRandomEmail_ContactChild2="";
	GeneralHomePage homePage = new GeneralHomePage();
	//A LoginPage will be extended from GenralHomePage and use the Driver from there  
	LoginPage loginpage = new LoginPage(homePage.getDriver()); 
	//A SettingPage will continue to use the Driver from LoginPage  
	SettingPage settingpage = new SettingPage(loginpage.getDriver());
	//A CRMPage will continue to use the Driver from LoginPage
	CRMPage crmpage = new CRMPage(loginpage.getDriver());
	//A CRMPage will continue to use the Driver from LoginPage
	OpportunityPage opppage = new OpportunityPage(loginpage.getDriver());
	//A ContactsPage will continue to use the Driver from LoginPage
	ContactsPage contactspage = new ContactsPage(loginpage.getDriver());
	@Given("^Test$")
	public void Test()  throws Throwable {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("Launch page");
		Logger.info("Launch page");	
		
		Common.getRandomPublicEmail();
	
		}
	
	@Given("^Test (.*) and (.*)$")
	public void Test(String Leadsfile, String Contactsfile)  throws Throwable {
  		//Pre-condition: Select the correct configuration
		//Test
		System.out.println("Launch page");
		Logger.info("Launch page");	
		
		contactspage.enterContactChildName(Contactsfile,"Bob");

		
		}
	// ============================ GIVEN - for most of scenarios============================//
	@Given("^Launch Odoo Page$")
	public void LaunchPage()  throws Throwable {
  		//Pre-condition: Select the correct configuration
		homePage.launchWebPage();
		System.out.println("Launch page");
		Logger.info("Launch page");	

		
		}
	// ============================ GIVEN - Login ============================//
	  
	@Given("^Login successfully$")
		public void Login() throws Throwable {
		Logger.info("Login successfully");	
		loginpage.loginValidUser(Constants.UAT_USERNAME, Constants.UAT_PASSWORD);
		} 
			
	@Given("^Go to (.*)$")
	public void goTo(String page)  throws Throwable {
  		//Pre-condition: Select the correct configuration
		System.out.println("Go to "+ page +" page");
		Logger.info("Go to "+ page +" page");	
		homePage.gotoModuleCRM();
		
		}
	
	@Given("^Active developer mode$")
	public void activeDeveloperMode()  throws Throwable {
		Logger.info("Active developer mode");
		settingpage.activateDeveloperMode();
		//NOTICE: needn't to back Home
		
		}
	
	@Given ("^Create a Contact and its child contacts from (.*)$")
	public void createNewContactandChildContacts(String Contactsfile)  throws Throwable {
  			
		Logger.info("Create Contact");
		homePage.gotoModuleContacts();
		contactspage.createContact();
		
		contactspage.selectContactType(Contactsfile);
		returnRandomContactEmail = contactspage.enterEmail(Contactsfile);
		contactspage.enterContactName(Contactsfile, returnRandomContactEmail);
		
				
		contactspage.enterStreetName(Contactsfile);
		
		contactspage.pressSaveButton();
		
		//Operate on Child Contacts
		Logger.info("Create Contact child");
		//Add 2 child contacts
		returnRandomEmail_ContactChild1 = contactspage.addChildContactsByIndex(Contactsfile,returnRandomContactEmail, 0);
		returnRandomEmail_ContactChild2 = contactspage.addChildContactsByIndex(Contactsfile,returnRandomContactEmail, 1);
		
		//Return HOME
		contactspage.goToHome();
		}
	
	@Given ("^Create a Reseller from (.*)$")
	public void createNewReseller(String Contactsfile)  throws Throwable {
  			
		Logger.info("Create Reseller");
		homePage.gotoModuleContacts();
		contactspage.createContact();
		
		contactspage.selectContactType(Contactsfile);
		returnRandomContactEmail = contactspage.enterEmail(Contactsfile);
		contactspage.enterContactName(Contactsfile, returnRandomContactEmail);
		contactspage.enterStreetName(Contactsfile);
		
		//For particular behavior, we need to SAVE the Contact before changing the Partner Level
		contactspage.pressSaveButton();
		//Press "EDIT" button to start to change Partner Level
		contactspage.pressEditButton();
		
		//Press tab Partner Assignation 
		contactspage.pressTab_PartnerAssignation();
		
		//Change Partner Level
		contactspage.selectPartnerLevel(Contactsfile);
		//Select Activation Date
		contactspage.selectActivationDate(Contactsfile);
		
		//Finally, press SAVE
		contactspage.pressSaveButton();
		
		//Return HOME
		contactspage.goToHome();
		}
	@Given ("^Create a new Target Lead using Contact child from (.*)$")
	public void createNewTargetLeadUsingContactChild(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.TARGET_LEAD, returnRandomEmail_ContactChild1);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@Given ("^Create a new Target Lead using Reseller from (.*)$")
	public void createNewTargetLeadUsingReseller(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.TARGET_LEAD, returnRandomContactEmail);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@Given ("^Secondly, create a new Target Opportunity using Contact child from (.*)$")
	public void createNewTargetOppUsingContactChild(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Opportunity");
		
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterEmail(Leadsfile, Constants.TARGET_LEAD,returnRandomEmail_ContactChild1);
		
		opppage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		
		opppage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectState(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.TARGET_LEAD);
		
		opppage.pressSaveButton();
		opppage.goToHome();
		}   
	
	@Given ("^Create a new Source Lead using Contact child from (.*)$")
	public void createNewSourceLeadUsingContactChild(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.SOURCE_LEAD, returnRandomEmail_ContactChild2);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);		
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	@Given ("^Create a new Source Lead using Reseller from (.*)$")
	public void createNewSourceLeadUsingReseller(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.SOURCE_LEAD, returnRandomContactEmail);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);		
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	@Given ("^Firstly, create a new Source Opportunity using Contact child from (.*)$")
	public void firstlyCreateNewSourceOppUsingContactChild(String Leadsfile)  throws Throwable {
  		
		Logger.info("Create Source Opportunity");
		
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		opppage.selectContact(Leadsfile, Constants.SOURCE_LEAD, returnRandomEmail_ContactChild2);
		
		opppage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		opppage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.pressSaveButton();		
		opppage.goToHome();
		
		}
	@Given ("^Firstly, setup a new Source Lead using Contact child from (.*)$")
	public void firstlycreateNewSourceLeadUsingContactChild(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		//homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		returnRandomEmail = crmpage.enterEmail(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterContactName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	// ============================ WHEN -  ============================//
	@When ("^Create a new Target Lead from (.*)$")
	public void createNewTargetLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		returnRandomEmail = crmpage.enterEmail(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@When ("^Set Priority on Target Lead from (.*)$")
	public void setPriorityTargetLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Set Priority for Target Lead");
		
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		//1. Click on Source lead first
		//1.1. Go to CRM page of Source Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
				
		//*1.2. Set priority for Source Lead
		//crmpage.setPriorityIfRequired(Leadsfile, Constants.TARGET_LEAD);	
		
		//1.3. Go Home
		crmpage.goToHome();
		}
	@When ("^Secondly, setup a new Target Lead from (.*)$")
	public void secondlycreateNewTargetLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterEmail(Leadsfile, Constants.TARGET_LEAD,returnRandomEmail);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@When ("^Secondly, create a new Target Opportunity from (.*)$")
	public void createNewTargetOpp(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterEmail(Leadsfile, Constants.TARGET_LEAD,returnRandomEmail);
		
		opppage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterCompanyName(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectState(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.TARGET_LEAD);
		
		opppage.pressSaveButton();
		opppage.goToHome();
		}   
	
	
	@When ("^Create a new Source Lead from (.*)$")
	public void createNewSourceLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterEmail(Leadsfile, Constants.SOURCE_LEAD,returnRandomEmail);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterContactName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	@When ("^Firstly, setup a new Source Lead from (.*)$")
	public void firstlycreateNewSourceLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		//homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		returnRandomEmail = crmpage.enterEmail(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.enterContactName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	@When ("^Set Priority on Source Lead from (.*)$")
	public void setPrioritySourceLead(String Leadsfile)  throws Throwable {
  			
		Logger.info("Set Priority for Source Lead");
		
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		//1. Click on Source lead first
		//1.1. Go to CRM page of Source Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
				
		//*1.2. Set priority for Source Lead
		//crmpage.setPriorityIfRequired(Leadsfile, Constants.SOURCE_LEAD);	
		
		//1.3. Go Home
		crmpage.goToHome();
		}
	@When ("^Firstly, create a new Source Opportunity from (.*)$")
	public void firstlyCreateNewSourceOpp(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Opp");
		//homePage.gotoModuleCRM();
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		returnRandomEmail = opppage.enterEmail(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		opppage.enterCompanyName(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		opppage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD);
		opppage.enterContactName(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.pressSaveButton();		
		opppage.goToHome();
		
		}
	
	
	// ============================ THEN - close page ============================//
	@Then ("^Check Target Lead after merged with Source Lead from (.*)$")
	public void checkTargetLead(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile,returnRandomEmail);	
			//crmpage.checkMergeMessageOnTargetLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomEmail);
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Check Target Lead after merged with Source Lead using Contact child from (.*)$")
	public void checkTargetLeadUsingContactChild(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail_ContactChild1);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile,returnRandomEmail_ContactChild1);	
			//crmpage.checkMergeMessageOnTargetLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomEmail_ContactChild1);
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Check Target Lead after merged with Source Lead using Reseller from (.*)$")
	public void checkTargetLeadUsingReseller(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile,returnRandomContactEmail);	
			//crmpage.checkMergeMessageOnTargetLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomContactEmail);
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Check Target Opportunity after merged with Source Opportunity from (.*)$")
	public void checkTargetOpp(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Opportunity");
		homePage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//opppage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnTargetLead(Leadsfile,returnRandomEmail);	
		opppage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomEmail);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Pending");
		opppage.checkActive(true);
		opppage.checkLostReason("");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}
	@Then ("^Check Target Opportunity after merged with Source Opportunity using Contact child from (.*)$")
	public void checkTargetOppUsingContactChild(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Opportunity");
		homePage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//opppage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomEmail_ContactChild1);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnTargetLead(Leadsfile,returnRandomEmail_ContactChild1);	
		opppage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomEmail_ContactChild1);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Pending");
		opppage.checkActive(true);
		opppage.checkLostReason("");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}
	
	@Then ("^Check Source Lead after merged with Target Lead from (.*)$")
	public void checkSourceLead(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile,returnRandomEmail);	
			//crmpage.checkMergeMessageOnSourceLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomEmail);
		
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Lost");
		crmpage.checkActive(false);
		crmpage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then ("^Check Source Lead after merged with Target Lead using Contact child from (.*)$")
	public void checkSourceLeadUsingContactChild(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail_ContactChild2);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile,returnRandomEmail_ContactChild2);	
			//crmpage.checkMergeMessageOnSourceLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomEmail_ContactChild2);
		
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Lost");
		crmpage.checkActive(false);
		crmpage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then ("^Check Source Lead after merged with Target Lead using Reseller from (.*)$")
	public void checkSourceLeadUsingReseller(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile,returnRandomContactEmail);	
			//crmpage.checkMergeMessageOnSourceLead(Leadsfile,"TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomContactEmail);
		
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Lost");
		crmpage.checkActive(false);
		crmpage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then ("^Check Source Opportunity after merged with Target Opportunity from (.*)$")
	public void checkSourceOpportunity(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		opppage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomEmail);
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		opppage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnSourceLead(Leadsfile,returnRandomEmail);	
		opppage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomEmail);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Lost");
		opppage.checkActive(false);
		opppage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}	
	
	@Then ("^Check Source Opportunity after merged with Target Opportunity using Contact child from (.*)$")
	public void checkSourceOpportunityUsingContactChild(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		opppage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomEmail_ContactChild2);
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		opppage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnSourceLead(Leadsfile,returnRandomEmail_ContactChild2);	
		opppage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomEmail_ContactChild2);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Lost");
		opppage.checkActive(false);
		opppage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}	
	
	@Then("^Close$")   
	    public void ClosePage() throws Throwable {
			Logger.info("Close page");	
			loginpage.closePage();
		}
	// =========================Other actions ======================//
 
}

