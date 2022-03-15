package definitions;

import org.testng.Assert;
import org.testng.annotations.Test;

import FirmwareObject.PanelInformation;
import FirmwareObject.RackInformation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginDefinition extends BaseDefinition{
//	WebDriver driver;
	
	
	public String temporaryJsonPath;
	public String getValueByAPI;
	public String positionOfPanel;
	public Response response;
	public String jsonString;
	
	// ============================ GIVEN - for most of scenarios============================//
	@Given("^Print A of (.*)$")
	public void LaunchPage(String configuration)  throws Throwable {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("1111111114441111111111");
		Logger.info("Print Panel Information");	
		Logger.info("<pre>" + "Data GET: " + "</pre>");

		launchPage();

		
		}
	
	// ============================ WHEN - Login ============================//
	  
		@When("^Login$")
			public void Login() throws Throwable {
			login();
			} 
	
	
	// ============================ THEN - close page ============================//
		@Then("^Close$")   
	    public void ClosePage() throws Throwable {
			closePage();
		}
	// =========================Other actions ======================//
 
}

