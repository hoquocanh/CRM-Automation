package definitions.PanelInformation;

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

public class VerifyDefinition_Panelnfo {

	public String temporaryJsonPath;
	public String getValueByAPI;
	public String positionOfPanel;
	public Response response;
	public String jsonString;
	
	// ============================ GIVEN - for most of scenarios============================//
	@Given("^Print A of (.*)$")
	public void printPanelInforA(String configuration)  {
  		//Pre-condition: Select the correct configuration
		
		System.out.println("111111111111111111111111");
		Logger.info("Print Panel Information");	
		Logger.info("<pre>" + "Data GET: " + "</pre>");

		
		}
	@Given("^Print Panel Information of (.*)$")
	public void printPanelInfor(String configuration)  {
  		//Pre-condition: Select the correct configuration
		PanelInformation.configurationType = configuration;		
			
		//Step 1: Print Panel Information to the Report
		PanelInformation panelInfo = new PanelInformation();
		
		Logger.info("Print Panel Information");	
		Logger.info("<pre>" + "Data GET: " + panelInfo.getGetResponse().prettyPrint() + "</pre>");

		
		}
	// ============================ GIVEN - for checking information from Json file============================//
	
	@Given("^Print Panel Information of (.*) from (.*)$")
	public void printPanelInforFromJsonFile(String configuration, String jsonDataPath)  {
  		//Pre-condition: Select the correct configuration
		PanelInformation.configurationType = configuration;	
		temporaryJsonPath = jsonDataPath;
			
		//Step 1: Print Panel Information to the Report
		PanelInformation panelInfo = new PanelInformation();
		jsonString = panelInfo.getJsonFile(jsonDataPath);
		
		/*
		 * System.out.println("GIVEN : jsonString :" + jsonString);
		 * Logger.info("Print Panel Information"); Logger.info("<pre> \r\n" +
		 * Common.prettyPrintJsonString(jsonString) + "</pre>");
		 */

		}
	// ============================ WHEN collect value of key totalPanels from API request ============================//
	  
	@When("^Send a GET request to collect key totalPanels (.*) infomation$")
		public void getValueOfTotalPanels(String keyOfValue) {
			//Step 1: Show Panel Information
		   PanelInformation panelInfo = new PanelInformation();
			getValueByAPI = panelInfo.getInfoOfKeyTotalPanels(keyOfValue);
			
			
		} 
	 
	// ============================ WHEN - collect value of key totalPanels from Json file ============================//
	
		@When("^Send a request to collect key totalPanels (.*) infomation$")
			public void getValueOfTotalPanelsFromJsonFile(String keyOfValue) {
				//Step 1: Show Panel Information
			   PanelInformation panelInfo = new PanelInformation();
			   
			   getValueByAPI = panelInfo.getInfoOfKeyTotalPanels(keyOfValue,temporaryJsonPath);
				
			} 
		
	// ============================ WHEN ============================//
	
	@When("^Send GET request to collect Panel Information from (.*) to check data of panel (.*)$")
	public void getValueOfKeyAtPosition(String keyOfValue, String panelPosition) {
			Logger.info("GET API request");
			PanelInformation panelInfo = new PanelInformation();
			getValueByAPI = panelInfo.getInfoOfKey(panelPosition, keyOfValue);
			positionOfPanel = panelPosition;
			
	} 	
    // ============================ THEN - check the value of key ============================//
	@Then("^Verify that the value of (.*) meet (.*)$")   
    public void verify_info_is_met(String key,String expectedValue) {
		Logger.info("Check key "+ key );
		Logger.info("Value of key above: "+ getValueByAPI);
		Logger.info("Value need to be compared: "+ expectedValue);
		Assert.assertTrue(getValueByAPI.equals(expectedValue), "Wrong value! Expected value is " + expectedValue + " but verify value is " + getValueByAPI);
	}
	
 
	// =========================Other actions ======================//
 
}

