package definitions.RackInformation;

import org.testng.Assert;

import FirmwareObject.RackInformation;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import utils.common.Common;
import utils.common.Constants;
import utils.helper.Logger;

public class VerifyDefinition_RackInfo {

	public String temporaryJsonPath;
	public String getValueByAPI;
	public String originalValueOfKey;
	public String originalPositionOfRack = "1";
	public String enteredValue;
	public String idOfMasterRack;
	public String keyOfId = "id";
	public Response response;
	public String jsonString;
	public String outputStatusCode;
	
	// ============================ GIVEN - GET request ============================//
	
		@Given("^Reset data using Json file (.*) and print Rack Information of (.*)$")
		public void resetData(String jsonDataPath, String configuration)  {
				//Select the correct configuration
				
				RackInformation.configurationType = configuration;	
				Logger.info("Reset data to default value");
				//updateDataByAPI(jsonDataPath);
				
				Logger.info("Print Rack Information");	
				RackInformation rackInfo = new RackInformation();
				
				Logger.info("Status code: " + rackInfo.getGetResponse().statusCode());
				Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");
				
				
			}
	
	
	// ============================ GIVEN - for most of scenarios============================//
		
		@Given("^Reset data of Rack Information using Json file (.*) and show that information of (.*) and save that data to (.*)$")
	public void resetData(String jsonInitialPath, String configuration, String jsonDataPath)  {
  		//Pre-condition: Select the correct configuration
  		RackInformation rackInfo = new RackInformation();
  		RackInformation.configurationType = configuration;	
		temporaryJsonPath = jsonDataPath;
		
  		//Step 1: Reset data to initial state	
  		Logger.info("Reset data");
		updateDataByAPI(jsonInitialPath);
		
		//Step 2: Print initial Rack Information to the Report
		
		
		Logger.info("Print Rack Information");	
		Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");

		//Step 3: Save data of Rack Information to a temporary Json file according to "jsonDataPath"
		//This step is necessary to generate latest Rack Information data
		  Logger.info("Copy the body of initial Rack Information to Json file");
		 jsonString = rackInfo.getGetResponse().asString();
		 Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
		 
		
		}
	// ============================ GIVEN - for incorrect authentication credentials============================//
  	
  	@Given("^Use incorrect authentication credentials (.*) and (.*), then reset data using Json file (.*) and print Rack Information of (.*) and save that data to (.*)$")
	public void incorrect_authentiaction(String authenUsername, String authenPassword, String jsonInitialPath, String configuration, String jsonDataPath)  {
  		//Pre-condition: Select the correct configuration
		
		  RackInformation.configurationType = configuration;
		  RackInformation.authentication_username = authenUsername;
		  RackInformation.authentication_password = authenPassword; temporaryJsonPath =
		  jsonDataPath;
		 
		
  		//Step 1: Reset data to initial state	
  		Logger.info("Reset data");
		updateDataByAPI(jsonInitialPath);
		
		//Step 2: Print initial Rack Information to the Report
		RackInformation rackInfo = new RackInformation();
		
		Logger.info("Print Rack Information");	
		Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");

		//Step 3: Save data of Rack Information to a temporary Json file according to "jsonDataPath"
		Logger.info("Copy the body of initial Rack Information to Json file");
		jsonString = rackInfo.getGetResponse().asString();
		Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
		
		}
  	
 // ============================ WHEN - GET request ============================//
 	@When("^Send a GET request to collect ([^\\\\\\\"]*) infomation to check data of rack ([^\\\\\\\"]*)$")
 	public void get_new_infomation(String keyOfValue, String rackPosition) {
 		Logger.info("GET API request");
 		RackInformation rackInfo = new RackInformation();
 		getValueByAPI = rackInfo.getInfoOfKey(rackPosition, keyOfValue);
 		originalPositionOfRack = rackPosition;
 		
 	} 
 	
 
	// ============================ WHEN 1 ============================//
    
    @When("^Update the value of the key (.*) at rack (.*) to (.*) in (.*)$")
	public void update_value_json(String keyOfValue,String rackPosition, String inputValue, String jsonDataPath) 
    {
    	RackInformation rackInfo = new RackInformation();
    	//Get the value of inputed Key before making update
    	originalValueOfKey = rackInfo.getInfoOfKey(rackPosition,keyOfValue);
    	//Get the inputValue
    	enteredValue = inputValue;
    	//Get the original Rack Position before making update
    	originalPositionOfRack = rackInfo.getInfoOfKey(rackPosition,"rackNumber");
    	
    	Logger.info("Update json file");
		//Step 1: Update the temporary Json file "jsonDataPath" by changing the value of "keyOfValue" according to rackPosition   	
    	jsonString=rackInfo.updateKeyValueAtRack(keyOfValue,inputValue,rackPosition,Constants.DATA_SOURCE + jsonDataPath);
    	
    	
    	//Step 2: Save the updated value to temporary Json file according to "jsonDataPath"
    	Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
    	System.out.println("keyOfValue: " + keyOfValue);
    	System.out.println("inputValue: " + inputValue);
    	
    	//Step 3: Print the updated json body to the Report
    	Logger.info("<pre>" + "Show the updated json body before POST: \r\n " + Common.prettyPrintJsonString(jsonString) + "</pre>");
    	
	} 
    
// ============================ WHEN - update the totalRacks ============================//
    
    @When("^Update the value of the key totalRacks (.*) to (.*) in (.*)$")
  	public void update_value_total_racks(String keyOfValue, String inputValue, String jsonDataPath) 
      {
      	RackInformation rackInfo = new RackInformation();
      	//Get the value of inputed Key before making update
      	originalValueOfKey = rackInfo.getInfoOfKeyTotalRacks(keyOfValue);
      	//Get the inputValue
      	enteredValue = inputValue;
      	
      	
      	Logger.info("Update json file");
  		//Step 1: Update the temporary Json file "jsonDataPath" by changing the value of "keyOfValue" according to rackPosition   	
      	jsonString=rackInfo.updateKeyValueAtTotalRack(keyOfValue,inputValue,Constants.DATA_SOURCE + jsonDataPath);
      	
      	
      	//Step 2: Save the updated value to temporary Json file according to "jsonDataPath"
      	Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
      	System.out.println("keyOfValue: " + keyOfValue);
      	System.out.println("inputValue: " + inputValue);
      	
      //Step 3: Print the updated json body to the Report
    	Logger.info("<pre>" + "Show the updated json bodybefore POST: \r\n " + Common.prettyPrintJsonString(jsonString) + "</pre>");
  	}
    
    
// ============================ WHEN - remove a key ============================//
    
    @When("^Remove key (.*) at rack (.*) and update to (.*)$")
	public void remove_key(String keyOfValue,String rackPosition, String jsonDataPath) 
    {
    	RackInformation rackInfo = new RackInformation();
    	Logger.info("Update json file");
    	//Get the value of inputed Key before making update
    	originalValueOfKey = rackInfo.getInfoOfKey(rackPosition,keyOfValue);
    	
    	System.out.println("originalValueOfKey :"+ originalValueOfKey);
    	//Step 1: Update the temporary Json file "jsonDataPath" by changing the value of "keyOfValue" according to rackPosition    	
    	jsonString=rackInfo.removeKeyAtRack(keyOfValue,rackPosition,Constants.DATA_SOURCE + jsonDataPath);
    	originalPositionOfRack = rackPosition;
    	//Step 2: Save the updated value to temporary Json file according to "jsonDataPath"
    	Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
    	
    	//Step 3: Print the updated json body to the Report
    	Logger.info("<pre>" + "Show the updated json body before POST: \r\n" + Common.prettyPrintJsonString(jsonString) + "</pre>");
	} 
	
// ============================ WHEN - remove totalRacks key ============================//
    
    @When("^Remove totalRacks key (.*) and update to (.*)$")
	public void remove_total_rack_key(String keyOfValue, String jsonDataPath) 
    {
    	RackInformation rackInfo = new RackInformation();
    	Logger.info("Update json file");
    	//Get the value of inputed Key before making update
    
    	originalValueOfKey = rackInfo.getInfoOfKeyTotalRacks(keyOfValue);
    	System.out.println("originalValueOfKey :"+ originalValueOfKey);
    	//Step 1: Update the temporary Json file "jsonDataPath" by changing the value of "keyOfValue" according to rackPosition    	
    	jsonString=rackInfo.removeKeyAtTotalRack(keyOfValue,Constants.DATA_SOURCE + jsonDataPath);
    	
    	//Step 2: Save the updated value to temporary Json file according to "jsonDataPath"
    	Common.saveToJsonFile(Constants.DATA_SOURCE + jsonDataPath,jsonString);
    	
    	//Step 3: Print the updated json body to the Report
    	Logger.info("<pre>" + "Show the updated json body before POST: \r\n" + Common.prettyPrintJsonString(jsonString) + "</pre>");
	} 
//================== AND - For most of scenarios ============================//
    
    @And("^Send a POST request to update server information with data updated$")
    public void send_post_request() {
		//Step 1: Send POST request using the temporary Json file at GIVEN
    	
    	Logger.info("POST API request");
		updateDataByAPI(jsonString);
	} 
//================== AND - For POST request using No Auth ============================//
    
    @And("^Send a POST request to update server information with data updated using No Auth$")
    public void send_post_request_using_NoAuth() {
		//Step 1: Send POST request using the temporary Json file at GIVEN
    	
    	Logger.info("POST API request");
		updateDataByAPI(jsonString,"No Auth");
	} 
//================== AND - For POST request to an incorrect URL ============================//
    
    @And("^Send a POST request to an incorrect URL$")
    public void send_post_request_incorrect() {
		//Step 1: Send POST request using the temporary Json file
    	
    	Logger.info("POST API request");
		updateDataByAPI_incorrect(jsonString);
	}
    
//================== AND - For PUT request============================//
    
    @And("^Send a PUT request to update server information with data updated$")
    public void send_put_request() {
		//Step 1: Send POST request using the temporary Json file
    	
    	Logger.info("POST API request");
		putDataByAPI(jsonString);
	}
  //================== AND - Get value of others keys ============================//
    @And("^Send a GET request to collect (.*) info.$")
	public void get_new_info(String keyOfValue) {
		//Step 1: Show Rack Information after POST
		RackInformation rackInfo = new RackInformation();
		getValueByAPI = rackInfo.getInfoOfKey(originalPositionOfRack,keyOfValue);
		Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");
		
	} 
  //================== AND - Get value of totalRacks key ============================//
    @And("^Send a GET request and collect totalRacks (.*) info.$")
	public void get_total_racks_info(String keyOfValue) {
		//Step 1: Show Rack Information after POST
		RackInformation rackInfo = new RackInformation();
		getValueByAPI = rackInfo.getInfoOfKeyTotalRacks(keyOfValue);
		Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");
		
	} 
  //================== AND 3 - Collect new rack number ============================//
    @And("^Send a GET and collect new Rack Number (.*) info.$")
	public void get_new_rack_number(String keyOfValue) {
		
    	
    	//Step 1: Show Rack Information after POST
		RackInformation rackInfo = new RackInformation();
		
		System.out.println("Correct Rack Number 1: " + select_current_rack_number(outputStatusCode));
		getValueByAPI = rackInfo.getInfoOfKey(select_current_rack_number(outputStatusCode),keyOfValue);
		
		System.out.println("Correct rack number 2: " + select_current_rack_number(outputStatusCode));
		Logger.info("<pre>" + "Data GET: " + rackInfo.getGetResponse().prettyPrint() + "</pre>");
		
	} 
    
 // ============================ THEN - GET request ============================//
 	@Then("^Verify that value of ([^\\\\\\\"]*) meet ([^\\\\\\\"]*)$")
 	public void verify_info_is_met(String key, String expectedValue) {
 		Logger.info("Check key "+ key + " of rack position " + originalPositionOfRack);
 		Logger.info("Value of key above: "+ getValueByAPI);
 		Logger.info("Value need to be compared: "+ expectedValue);
 		Assert.assertTrue(getValueByAPI.equals(expectedValue), "Wrong value! Expected value is " + expectedValue + " but verify value is " + getValueByAPI);
 	}
 	
	@Then("^Check this rack is managed by rack position ([^\\\\\\\"]*)$")
	public void verify_info_is_met_managedBy(String masterRackPosition) {
		Logger.info("Current rack position " + originalPositionOfRack);
		Logger.info("Master rack position " + masterRackPosition);
		String correctManagedByID = getCorrectManagedByID(originalPositionOfRack,masterRackPosition);
		System.out.println("getValueByAPI: "+ getValueByAPI);
		Logger.info("Value of managedBy of current rack: "+ getValueByAPI);
		Logger.info("Value of rack id of master rack: "+ idOfMasterRack);
		Assert.assertTrue(getValueByAPI.equals(correctManagedByID), "Wrong value! Expected value is " + idOfMasterRack + " but verify value is " + getValueByAPI);
	}
    // ============================ THEN - check POST status code============================//
    
    @Then("^Verify that the POST status code is (.*)$")
    public void verify_Post_code_is_met(String expectedCode) 
    {
    	
    	//Step 1: Check the Status Code of POST
    	Logger.info("Check Status code of POST: ");
    	Logger.info("Expected Status Code: " + expectedCode);
    	Logger.info("Collected Status Code: " + outputStatusCode);
    	Assert.assertTrue(expectedCode.equals(outputStatusCode), "Wrong value! Expected value is " + expectedCode + " but verify value is " + outputStatusCode);
    	
    }
    // ============================ THEN - check PUT status code============================//
    
    @Then("^Verify that the PUT status code is (.*)$")
    public void verify_Put_code_is_met(String expectedCode) 
    {
    	
    	//Step 1: Check the Status Code of PUT
    	Logger.info("Check Status code of PUT: ");
    	Logger.info("Expected Status Code: " + expectedCode);
    	Logger.info("Collected Status Code: " + outputStatusCode);
    	Assert.assertTrue(expectedCode.equals(outputStatusCode), "Wrong value! Expected value is " + expectedCode + " but verify value is " + outputStatusCode);
    	
    }
    
    // ============================ THEN - check the value of key ============================//
    @Then("^Verify that (.*) is changed to (.*) and meet (.*)$")   
    public void verify_info_is_met(String key,String inputValue, String expectedValue) {
		Logger.info("Check key "+ key + " of rack at original position " + originalPositionOfRack);
		Logger.info("Value of key above: "+ getValueByAPI);
		Logger.info("Value need to be compared: "+ expectedValue);
		Assert.assertTrue(getValueByAPI.equals(expectedValue), "Wrong value! Expected value is " + expectedValue + " but verify value is " + getValueByAPI);
	}
	
  // ============================ THEN - check the value is the same as old value ============================//
    
    @Then("^Verify that the value of (.*) is show the old value$")    
    public void verify_key_show_old_value(String key) {
		Logger.info("Check key "+ key + " of rack at original position " + originalPositionOfRack);
		Logger.info("Value of key above: "+ getValueByAPI);
		Logger.info("Value need to be compared: "+ originalValueOfKey);
		Assert.assertTrue(getValueByAPI.equals(originalValueOfKey), "Wrong value! Expected value is " + originalValueOfKey + " but verify value is " + getValueByAPI);
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
  		int temp= response.statusCode();
  		outputStatusCode = String.valueOf(temp);
  		System.out.println("outputStatusCode: " + outputStatusCode);
  	}
  	public void updateDataByAPI(String jsonPath, String authType) {
  		RackInformation rackInformation = new RackInformation();
  		if (jsonPath.contains("/")) {
  			response = rackInformation.changeWithFileInput(jsonPath, authType);
  			
  		}
  		else {
  			response = rackInformation.change(jsonPath, authType);
  		}
  		int temp= response.statusCode();
  		outputStatusCode = String.valueOf(temp);
  		System.out.println("outputStatusCode: " + outputStatusCode);
  	}
  //This method call sending POST request to an invalid URL
  	public void updateDataByAPI_incorrect(String jsonPath) {
  		RackInformation rackInformation = new RackInformation();
  		if (jsonPath.contains("/")) {
  			response = rackInformation.changeWithFileInput_incorrect(jsonPath);
  			
  		}
  		else {
  			response = rackInformation.change_incorrect(jsonPath);
  		}
  		int temp= response.statusCode();
  		outputStatusCode = String.valueOf(temp);
  		System.out.println("outputStatusCode" + outputStatusCode);
  	}
  	
  //This method call sending PUT request with the body comes from JsonPath
  	public void putDataByAPI(String jsonPath) {
  		RackInformation rackInformation = new RackInformation();
  		if (jsonPath.contains("/")) {
  			response = rackInformation.putWithFileInput(jsonPath);
  			
  		}
  		else {
  			response = rackInformation.put(jsonPath);
  		}
  		int temp= response.statusCode();
  		outputStatusCode = String.valueOf(temp);
  		System.out.println("outputStatusCode" + outputStatusCode);
  	}
  	//This method support to select the correct rack number after POST to change the value of rack number
  	//If POST got code 200, new rack number will then enteredValue 
  	//Otherwise, new rack number will be the original one
  	public String select_current_rack_number(String statusCodeAfterPOST)
  	{
  		String correctRackNumber ;
		
		if (statusCodeAfterPOST.equalsIgnoreCase("200"))
				{
					
					correctRackNumber = enteredValue;			
				}
		else
			correctRackNumber = originalPositionOfRack;
		
		return correctRackNumber;
  	}
  	
  	public String getCorrectManagedByID(String currentRackPosition, String masterRackPosition)
	{
		
		if (currentRackPosition.compareTo("-1")==0)
			return "";
		else
		{
			RackInformation rackInfo = new RackInformation();
			idOfMasterRack = rackInfo.getInfoOfKey(masterRackPosition, keyOfId);
			return idOfMasterRack;
		}
	}
}

