package definitions;

import org.testng.Assert;
import org.testng.annotations.Test;


import pages.LoginPage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;



import org.openqa.selenium.WebDriver;


public class LoginDefinition extends BaseDefinition{

	WebDriver driver = null;
	
	// A new class will continue to use the "driver" in class BaseDefinition
	
	
	LoginPage loginpage = new LoginPage(driver); 
	
	
	// ============================ GIVEN - for most of scenarios============================//
	@Given("^Print A of (.*)$")
	public void LaunchPage(String configuration)  throws Throwable {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("1111111114441111111111");
		Logger.info("Print Panel Information");	
		Logger.info("<pre>" + "Data GET: " + "</pre>");

		loginpage.launchPage();

		
		}
	
	// ============================ WHEN - Login ============================//
	  
		@When("^Login$")
			public void Login() throws Throwable {
			loginpage.loginValidUser("anh.ho@nakivo.com", "QATest@123");
			} 
	
	
	// ============================ THEN - close page ============================//
		@Then("^Close$")   
	    public void ClosePage() throws Throwable {
			loginpage.closePage();
		}
	// =========================Other actions ======================//
 
}

