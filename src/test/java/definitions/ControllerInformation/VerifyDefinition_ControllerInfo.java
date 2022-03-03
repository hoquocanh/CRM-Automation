package definitions.ControllerInformation;


import static org.junit.Assert.assertFalse;

import org.testng.Assert;

import FirmwareObject.ControllerInformation;
import FirmwareObject.RackInformation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;


public class VerifyDefinition_ControllerInfo {

	public String getValueByAPI;
	public String positionOfRack;
	public Response response;
	public String jsonString;
	
	
	// ============================ GIVEN ============================//
	@Given("^Print Rack Information of (.*)$")
	public void resetData(String configuration)  {
			//Select the correct configuration
			ControllerInformation.configurationType = configuration;	
			
			Logger.info("Print Controller Information");	
			
			ControllerInformation controllerInfo = new ControllerInformation();
			Logger.info("Status code: " + controllerInfo.getGetResponse().statusCode());
			Logger.info("<pre>" + "Data GET: " + controllerInfo.getGetResponse().prettyPrint() + "</pre>");
			
		}
	
	// ============================ WHEN ============================//
	
    @When("^Send a GET request to collect ([^\\\\\\\"]*)infomation from Controller to check data of rack ([^\\\\\\\"]*)$")
	public void get_new_infomation(String keyOfValue, String rackPosition) {
		Logger.info("GET API request");
		ControllerInformation controllerInfo = new ControllerInformation();
		getValueByAPI = controllerInfo.getInfoOfKey(rackPosition, keyOfValue);
		positionOfRack = rackPosition;
		
	} 

   
	// ============================ THEN 1 ============================//
    @Then("^Verify the value of ([^\\\\\\\"]*) in Controller information meet ([^\\\\\\\"]*)$")
	public void verify_info_is_met(String key, String expectedValue) {
		Logger.info("Check key "+ key + " of rack position " + positionOfRack);
		Logger.info("Value of key above: "+ getValueByAPI);
		Logger.info("Value need to be compared: "+ expectedValue);
		
		Assert.assertTrue(getValueByAPI.equals(expectedValue), "Wrong value! Expected value is " + expectedValue + " but verify value is " + getValueByAPI);
	}
	
     
    // ============================ THEN 2 ============================//
    @Then("^Verify the ([^\\\\\\\"]*) existed in Controller information$")
	public void verify_key_existed(String key) {
		Logger.info("Check key "+ key + " of rack position " + positionOfRack);
		Logger.info("Value of key above: "+ getValueByAPI);
		
		Assert.assertTrue(getValueByAPI.equals("display exist"), "Wrong value! Expected value is " + "display exist" + " but verify value is " + getValueByAPI);
	}
	// =========================Other actions ======================//
    //This method call sending POST request with the body comes from JsonPath
  	public void updateDataByAPI(String jsonPath) {
  		RackInformation rackInformation = new RackInformation();
  		if (jsonPath.contains("/")) {
  			response = rackInformation.changeWithFileInput(jsonPath);
  			
  		}
  		else {
  			response = rackInformation.change(jsonPath);
  		}
  		
  	}
}
