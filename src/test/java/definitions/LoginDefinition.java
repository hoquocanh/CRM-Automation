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
	String varContactsfile="";
	String returnRandomEmail = "";
	String returnDifferentEmail = "";
	String returnRandomContactEmail ="";
	String returnRandomContactEmail_1 ="";
	String returnRandomContactEmail_2 ="";
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
  		//Take out the value of "Contactsfile"	
		varContactsfile = Contactsfile;
		
		//Start:
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
	@Given ("^Create 2 contacts from (.*)$")
	public void create2Contacts(String Contactsfile)  throws Throwable {
  		//Take out the value of "Contactsfile"	
		varContactsfile = Contactsfile;
		
		Logger.info("Create Contact");
		//Start to go to Contact page:		
		homePage.gotoModuleContacts();		
		
		//Start to create Company 1:
		contactspage.createContact();
		contactspage.selectContactType(Contactsfile, 1);
		returnRandomContactEmail_1 = contactspage.enterEmail(Contactsfile, 1);
		contactspage.enterContactName(Contactsfile,1);
		contactspage.enterStreetName(Contactsfile, 1);
		
		contactspage.pressSaveButton();
		
		//Start to create Company 2:
		contactspage.clickCreateButtonInDetailsPage();
		contactspage.selectContactType(Contactsfile, 2);
		returnRandomContactEmail_2 = contactspage.enterEmail(Contactsfile, 2);
		contactspage.enterContactName(Contactsfile,2);
		contactspage.enterStreetName(Contactsfile, 2);
		
		contactspage.pressSaveButton();
		
		//Return HOME
		contactspage.goToHome();
		}
	@Given ("^Create 1 contact from (.*)$")
	public void create1Contact(String Contactsfile)  throws Throwable {
  		//Take out the value of "Contactsfile"	
		varContactsfile = Contactsfile;
		
		Logger.info("Create Contact");
		//Start to go to Contact page:		
		homePage.gotoModuleContacts();		
		
		//Start to create Company 1:
		contactspage.createContact();
		contactspage.selectContactType(Contactsfile, 1);
		returnRandomContactEmail_1 = contactspage.enterEmail(Contactsfile, 1);
		contactspage.enterContactName(Contactsfile,1);
		contactspage.enterStreetName(Contactsfile, 1);
		
		contactspage.pressSaveButton();
		
		//Return HOME
		contactspage.goToHome();
		}
	@Given ("^Create a Reseller from (.*)$")
	public void createNewReseller(String Contactsfile)  throws Throwable {
  		
		//Take out the value of "Contactsfile"	
		varContactsfile = Contactsfile;
				
		//Start:
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
		crmpage.waitForSecond(1);
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.TARGET_LEAD, returnRandomEmail_ContactChild1);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.waitForSecond(1);
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
		
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@Given ("^Create a new Target Lead using contact from (.*)$")
	public void createNewTargetLeadUsingContact(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.TARGET_LEAD, returnRandomContactEmail_1);
		
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		
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
		
		opppage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.TARGET_LEAD);
		
		opppage.pressSaveButton();
		opppage.goToHome();
		}   
	@Given ("^Secondly, setup a new Target Opportunity using Reseller contact from (.*)$")
	public void createNewTargetOppUsingResellerContact(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Opportunity");
		
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		opppage.enterEmail(Leadsfile, Constants.TARGET_LEAD,returnRandomContactEmail);
		
		opppage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		
		opppage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		opppage.selectState(Leadsfile, Constants.TARGET_LEAD);
		
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
		
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	@Given ("^Create a new Source Lead using contact from (.*)$")
	public void createNewSourceLeadUsingContact(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		crmpage.selectContact(Leadsfile, Constants.SOURCE_LEAD, returnRandomContactEmail_2);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);		
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		
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
		
		crmpage.enterContactName(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		
		}
	@Given ("^Firstly, setup a new Source Opportunity using Reseller contact from (.*)$")
	public void firstlycreateNewSourceOppUsingResellerContact(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Opportunity");
		
		opppage.createOpp();
		
		opppage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		//Slecting an existing Contact will make Email, Contact Name, Company Name, Address be auto filled
		opppage.selectContact(Leadsfile, Constants.SOURCE_LEAD, returnRandomContactEmail);
		
		opppage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.selectCountry(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectState(Leadsfile, Constants.SOURCE_LEAD);
		
		
		opppage.selectTag(Leadsfile, Constants.SOURCE_LEAD);
		opppage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD);
		opppage.setStageOpp(Leadsfile, Constants.SOURCE_LEAD);
		
		opppage.pressSaveButton();		
		opppage.goToHome();
		
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
		crmpage.enterContactName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
		
		crmpage.pressSaveButton();
		crmpage.goToHome();
		}
	
	@When ("^Create a new Target Lead using different email domain from (.*)$")
	public void createNewTargetLeadUsingDifferentEmail(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Target Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		returnRandomContactEmail_1 = crmpage.enterEmail(Leadsfile, Constants.TARGET_LEAD);
		
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
  			
		Logger.info("Create Target Opp");
		
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
	@When ("^Create a new second Source Lead from (.*)$")
	public void createNewSourceLead2nd(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.enterEmail(Leadsfile, Constants.SOURCE_LEAD_2,returnRandomEmail);
		
		crmpage.enterLeadForm(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.enterCompanyName(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.selectCountry(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.selectState(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.enterStreetName(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.enterContactName(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.selectTag(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.selectSalesTeam(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.setCreateManualCheckBox(Leadsfile, Constants.SOURCE_LEAD_2);
		
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
	@When ("^Create a new Source Lead using different email domains from (.*)$")
	public void createNewSourceLeadUsingDifferentEmailDomains(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		//homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		returnRandomContactEmail_2 = crmpage.enterEmail(Leadsfile, Constants.SOURCE_LEAD);
		
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
	@When ("^Create a new Source Lead using different email domain from (.*)$")
	public void createNewSourceLeadUsingDifferentEmailDomain(String Leadsfile)  throws Throwable {
  			
		Logger.info("Create Source Lead");
		homePage.gotoModuleCRM();
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.SOURCE_LEAD);
		returnRandomContactEmail_2= crmpage.enterDifferentDomainEmail(Leadsfile, Constants.SOURCE_LEAD,Common.getSameLocalPartEmail(returnRandomContactEmail_1, Constants.YAHOOMAIL_DOMAIN_EMAIL));
		
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
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile,varContactsfile,returnRandomEmail_ContactChild1);	
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
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile,varContactsfile,returnRandomContactEmail);	
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
		
		opppage.checkValueOfFieldOnTargetLead(Leadsfile,varContactsfile,returnRandomEmail_ContactChild1);	
		opppage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomEmail_ContactChild1);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Pending");
		opppage.checkActive(true);
		opppage.checkLostReason("");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}
	@Then ("^Check Target Opportunity after merged with Source Opportunity using Reseller contact from (.*)$")
	public void checkTargetOppUsingResellerContact(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Opportunity");
		homePage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//opppage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomContactEmail);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnTargetLead(Leadsfile,varContactsfile,returnRandomContactEmail);	
		opppage.checkMergeMessageOnTargetLead(Leadsfile,returnRandomContactEmail);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Pending");
		opppage.checkActive(true);
		opppage.checkLostReason("");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}
	@Then ("^Check Target Lead NOT merged with Source Lead from (.*)$")
	public void checkTargetLead_NOTMerged(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail_1);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
				
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead_NOTMerged(Leadsfile,returnRandomContactEmail_1);	
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Check Target Lead NOT merged with Source Lead using same email from (.*)$")
	public void checkTargetLead_NOTMergedUsingSameEmail(String Leadsfile)  throws Throwable {
		
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
		
		crmpage.checkValueOfFieldOnTargetLead_NOTMerged(Leadsfile,returnRandomEmail);	
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Check Target Lead NOT merged with Source Lead using contact from (.*)$")
	public void checkTargetLeadUsingContact_NOTMerged(String Leadsfile)  throws Throwable {
		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Target Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail_1);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
				
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead_NOTMerged(Leadsfile,varContactsfile,returnRandomContactEmail_1);	
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
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
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile,varContactsfile,returnRandomEmail_ContactChild2);	
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
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile,varContactsfile,returnRandomContactEmail);	
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
		
		opppage.checkValueOfFieldOnSourceLead(Leadsfile,varContactsfile,returnRandomEmail_ContactChild2);	
		opppage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomEmail_ContactChild2);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Lost");
		opppage.checkActive(false);
		opppage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}	
	@Then ("^Check Source Opportunity after merged with Target Opportunity using Reseller contact from (.*)$")
	public void checkSourceOpportunityUsingResellerContact(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		opppage.gotoModuleCRM();
		opppage.goToSub_ArchiveMenu("All");
			//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		opppage.searchOnArchive(returnRandomContactEmail);
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		opppage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		opppage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		opppage.checkValueOfFieldOnSourceLead(Leadsfile,varContactsfile,returnRandomContactEmail);	
		opppage.checkMergeMessageOnSourceLead(Leadsfile,returnRandomContactEmail);
		
		opppage.clickCRMDeveloper();
		opppage.checkIsWon("Lost");
		opppage.checkActive(false);
		opppage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		opppage.goToHome();
		
		}	
	
	@Then ("^Check Source Lead NOT merged with Target Lead from (.*)$")
	public void checkSourceLead_NOTMerged(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail_2);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead_NOTMerged(Leadsfile,returnRandomContactEmail_2);						
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	 
	@Then ("^Check Source Lead NOT merged with Target Lead using same email from (.*)$")
	public void checkSourceLead_NOTMergedUsingSameEmail(String Leadsfile)  throws Throwable {
  		
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
		
		crmpage.checkValueOfFieldOnSourceLead_NOTMerged(Leadsfile,returnRandomEmail);						
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then ("^Check second Source Lead NOT merged with Target Lead using same email from (.*)$")
	public void checkSecondSourceLead_NOTMergedUsingSameEmail(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead 2");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD_2);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSecondSourceLead_NOTMerged(Leadsfile,returnRandomEmail);						
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then ("^Check Source Lead NOT merged with Target Lead using contact from (.*)$")
	public void checkSourceLeadUsingContact_NOTMerged(String Leadsfile)  throws Throwable {
  		
		//Pre-condition: Wait for a certain time to make System merge leads
		homePage.waitForLeadMerging();
		
		Logger.info("Check on Source Lead");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomContactEmail_2);
		
		
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead_NOTMerged(Leadsfile,varContactsfile,returnRandomContactEmail_2);						
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Pending");
		crmpage.checkActive(true);
		crmpage.checkLostReason("");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	
	@Then ("^Set Target Lead to archived (.*)$")
	public void setTargetLeadToArchived(String Leadsfile)  throws Throwable {
		
		Logger.info("Set Target Lead to archived");
		homePage.gotoModuleCRM();
		crmpage.goToSub_ArchiveMenu("All");
		Logger.info("Set goToSub_ArchiveMenu");
			//crmpage.searchOnArchive("TEST_AUTOMATION_2022_04_29T15_35_15@test.com");
		crmpage.searchOnArchive(returnRandomEmail_ContactChild1);
		
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		//*1.2. Check the fields after doing merging action
		crmpage.setLeadToActive();
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}
	@Then ("^Close$")   
	    public void ClosePage() throws Throwable {
			Logger.info("Close page");	
			loginpage.closePage();
		}
	// =========================Other actions ======================//
 
}

