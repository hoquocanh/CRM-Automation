package definitions;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CRMPage;
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


public class LoginDefinition {
	String returnRandomEmail = "";
	GeneralHomePage homePage = new GeneralHomePage();
	//A LoginPage will be extended from GenralHomePage and use the Driver from there  
	LoginPage loginpage = new LoginPage(homePage.getDriver()); 
	//A SettingPage will continue to use the Driver from LoginPage  
	SettingPage settingpage = new SettingPage(loginpage.getDriver());
	//A CRMPage will continue to use the Driver from LoginPage
	CRMPage crmpage = new CRMPage(loginpage.getDriver());
	//A CRMPage will continue to use the Driver from LoginPage
	OpportunityPage opppage = new OpportunityPage(loginpage.getDriver());
	@Given("^Test$")
	public void Test()  throws Throwable {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("Launch page");
		Logger.info("Launch page");	
		
		Common.getRandomEmail();

		
		}
	// ============================ GIVEN - for most of scenarios============================//
	@Given("^Launch Odoo Page$")
	public void LaunchPage()  throws Throwable {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("Launch page");
		Logger.info("Launch page");	
		

		homePage.launchWebPage();

		
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
	
	@When ("^Create a new Target Opp from (.*)$")
	public void createNewTargetOpp(String Leadsfile)  throws Throwable {
  			
//		Logger.info("Create Target Lead");
//		//homePage.gotoModuleCRM();
//		crmpage.createOpp();
//		
//		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
//		returnRandomEmail = crmpage.enterEmail(Leadsfile, Constants.TARGET_LEAD);
//		
//		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.enterCompanyName(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.selectCountry(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.selectState(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.enterStreetName(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.setCreateManualCheckBox(Leadsfile, Constants.TARGET_LEAD);
//		crmpage.pressSaveButton();
//		crmpage.goToHome();
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
	@When ("^Firstly, create a new Source Opp from (.*)$")
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
		//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.TARGET_LEAD);
		
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnTargetLead(Leadsfile);	
		crmpage.checkMergeMessageOnTargetLead(Leadsfile);
		
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
		//crmpage.searchOnArchive("2022_04_13T17_10_28@test.com");
		crmpage.searchOnArchive(returnRandomEmail);
		//1. Check Target lead first
		//1.1. Go to CRM page of Target Lead first
		crmpage.clickOnItemLead(Leadsfile, Constants.SOURCE_LEAD);
		crmpage.clickCRMDeveloper();
		
		//*1.2. Check the fields after doing merging action
		
		crmpage.checkValueOfFieldOnSourceLead(Leadsfile);	
		crmpage.checkMergeMessageOnSourceLead(Leadsfile);
		
		crmpage.clickCRMDeveloper();
		crmpage.checkIsWon("Lost");
		crmpage.checkActive(false);
		crmpage.checkLostReason("Duplicate");
		
		//1.3. Get back to Home screen
		crmpage.goToHome();
		
		}	
	@Then("^Close$")   
	    public void ClosePage() throws Throwable {
			Logger.info("Close page");	
			loginpage.closePage();
		}
	// =========================Other actions ======================//
 
}

