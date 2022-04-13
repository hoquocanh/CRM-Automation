package definitions;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.CRMPage;
import pages.GeneralHomePage;
import pages.LoginPage;
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
	GeneralHomePage homePage = new GeneralHomePage();
	//A LoginPage will be extended from GenralHomePage and use the Driver from there  
	LoginPage loginpage = new LoginPage(homePage.getDriver()); 
	//A SettingPage will continue to use the Driver from LoginPage  
	SettingPage settingpage = new SettingPage(loginpage.getDriver());
	//A CRMPage will continue to use the Driver from LoginPage
	CRMPage crmpage = new CRMPage(loginpage.getDriver());
	
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
  			
		settingpage.activateDeveloperMode();
		
		}
	@Given("^Create a new Target Lead from (.*)$")
	public void createNewLead(String Leadsfile)  throws Throwable {
  			
		String returnRandomEmail = "";
		crmpage.createLead();
		
		crmpage.enterLeadName(Leadsfile, Constants.TARGET_LEAD);
		returnRandomEmail = crmpage.enterEmail(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterLeadForm(Leadsfile, Constants.TARGET_LEAD);
		crmpage.enterCompanyName(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectTag(Leadsfile, Constants.TARGET_LEAD);
		crmpage.selectSalesTeam(Leadsfile, Constants.TARGET_LEAD);
		crmpage.setOffCreateManual(Leadsfile, Constants.TARGET_LEAD);
		}
	
	
	// ============================ THEN - close page ============================//
		@Then("^Close$")   
	    public void ClosePage() throws Throwable {
			Logger.info("Close page");	
			loginpage.closePage();
		}
	// =========================Other actions ======================//
 
}

