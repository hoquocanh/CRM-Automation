package FirmwareObject;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import io.restassured.RestAssured;
import io.restassured.authentication.NoAuthScheme;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.common.Constants;


import static io.restassured.RestAssured.*;

public class RackInformation {
	public Response response;
	public RequestSpecification request ;
	public static String configurationType;
	public static String authentication_username = Constants.AUTHENTICATION_USERNAME;
	public static String authentication_password = Constants.AUTHENTICATION_PASSWORD;
	
	public void setup() {
		//Step 1: Choose correct Static RM LAN
		switch (configurationType) {
		case "configurationA":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_A;	
			break;
		case "configurationB":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_B;	
			break;
		case "configurationC":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_C;	
			break;
		default:
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_A;
		}	
	
		//Step 2: Go through notification Basic Auth
		RestAssured.authentication =  preemptive().basic(authentication_username,authentication_password);
		
	}
			//This method send POST request with the body comes from Json file "path" if the path contains "/" to separate folders
			public Response changeWithFileInput(String path) {
				setup();
					
				
				return response = given().contentType(ContentType.JSON).body(dataForAPI(path)).when().post(Constants.APIVER_ALL_RACKS).then().extract().response();
			}
			
			//This method send POST request with the body comes from Json file "path" if the path contains "/" to separate folders
			public Response changeWithFileInput(String path, String otherAuthType) {
				setup();
				
				switch (otherAuthType) {
				case "No Auth":	
					request = RestAssured.given().auth().none();
					break;
				default:
					request =  RestAssured.given().auth().basic(authentication_username,authentication_password);
				}	
				
				return response = request.contentType(ContentType.JSON).body(dataForAPI(path)).when().post(Constants.APIVER_ALL_RACKS).then().extract().response();
			}
			//This method send POST request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
			public Response change(String jsonFile) {
				setup();
				return response = given().contentType(ContentType.JSON).body(jsonFile).with().contentType(ContentType.JSON).when().post(Constants.APIVER_ALL_RACKS).then().extract().response();
				
			}
			//This method send POST request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
			public Response change(String jsonFile, String otherAuthType) {
				setup();
				
				switch (otherAuthType) {
				case "No Auth":	
					request = RestAssured.given().auth().none();
					break;
				default:
					request =  RestAssured.given().auth().basic(authentication_username,authentication_password);
				}	
				//return response = given().contentType(ContentType.JSON).body(jsonFile).with().contentType(ContentType.JSON).when().post(Constants.APIVER_ALL_RACKS).then().extract().response();
				return response = request.contentType(ContentType.JSON).body(jsonFile).with().contentType(ContentType.JSON).when().post(Constants.APIVER_ALL_RACKS).then().extract().response();
			}
			//This method send POST request to an incorrect URL
			public Response changeWithFileInput_incorrect(String path) {
				setup();		
				return response = given().body(dataForAPI(path)).with().contentType(ContentType.JSON).when().post(Constants.APIVER_INCORRECT_RACKS).then().extract().response();
				
			}
			//This method send POST request to an incorrect URL
			public Response change_incorrect(String jsonFile) {
				setup();
				return response = given().body(jsonFile).with().contentType(ContentType.JSON).when().post(Constants.APIVER_INCORRECT_RACKS).then()
						.extract().response();
			}
			
			//This method send PUT request with the body comes from Json file "path" if the path contains "/" to separate folders
			public Response putWithFileInput(String path) {
				setup();		
				return response = given().body(dataForAPI(path)).with().contentType(ContentType.JSON).when().put(Constants.APIVER_ALL_RACKS).then().extract().response();		
			}
			//This method send PUT request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
			public Response put(String jsonFile) {
				setup();
				return response = given().body(jsonFile).with().contentType(ContentType.JSON).when().put(Constants.APIVER_ALL_RACKS).then()
						.extract().response();
			}
		public File dataForAPI(String path) {
			setup();
			return new File(Constants.DATA_SOURCE + path);
		}
		
		//This method send GET request
		public Response getGetResponse() {
			setup();
			return given().when().get(Constants.APIVER_ALL_RACKS).then().extract().response();
			
		}
			
		//This method get the value of input Key "keyOfInfo" if the key included in an Array[]
		public String getInfoOfKey(String rackPosition,String key) {
			setup();
			
			System.out.println("// ===============================================//");
			System.out.println("We are checking information of rack position : "+rackPosition);
			//Step1: Get the list of value of the key "rackNumber" in Rack List of Json file to identify list of rack position in Rack List
			List<Integer> listOfRackPosition = getGetResponse().jsonPath().getList(Constants.ROOT_OF_RACK_LIST+"rackNumber");
			System.out.println("list of Rack Position: " + listOfRackPosition);
			//Step2: Get the index of rack which have the key value qualify the value of "customerRackNumber" available
			int indexOfExpectedRackNumber = listOfRackPosition.indexOf(Integer.parseInt(rackPosition));
			System.out.println("indexOfExpectedRackNumber in list:" + indexOfExpectedRackNumber);
			//Step3: Get the list of value of the key given by value of "keyOfInfo" variable in Rack List of Json file
			System.out.println("key : "+key);

			//Since the response of Rack Information API will return 2 kinds of key value. (1) It is a number if key is "powerUsagePercent" or "iPatchRackUnitsAvailable" or "customerRackNumber". (2) It is a string for other keys
			//Therefore, i have to declare 2 kinds of List correspondingly 
			String tempString ;
			switch (key) {
			case "powerUsagePercent":
			case "iPatchRackUnitsAvailable":
			case "rackNumber" :
				
				List<Integer> itemList1 = getGetResponse().jsonPath().getList(Constants.ROOT_OF_RACK_LIST+key);
				Integer newValue1 = itemList1.get(indexOfExpectedRackNumber);
				tempString = newValue1.toString();
				break;
			default:
				List<String> itemList2 = getGetResponse().jsonPath().getList(Constants.ROOT_OF_RACK_LIST+key);
				String newValue2 = itemList2.get(indexOfExpectedRackNumber);
				tempString = newValue2;
			}
			System.out.println("The value of key " +key + " of rack position "+rackPosition + " is :" + tempString);

			return tempString;
		}
		
		//This method get the value of input Key "keyOfInfo" if the key is outside the Array
		public String getInfoOfKeyTotalRacks(String key) {
					setup();
					
					System.out.println("// ===============================================//");
					System.out.println("We are checking information of key : "+key);
					
					return getGetResponse().jsonPath().get(key).toString();
					
				}
		//This method used to get the total number of Rack in RM LAN
		@SuppressWarnings("unchecked")
		public Integer getTotalRack(String jsonPath)
		{
			
			Integer totalRack = 1;
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try {
				// Get the data from Json file following the path "jsonPath"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				
				//Get the value of key "totalRacks"
				totalRack = Integer.parseInt(jsonObject.get("totalRacks").toString());
					
			} catch (Exception e) {
			}
			
		
			return totalRack;
		}
		
		//This method used to get list of Racks presented in Json object
		@SuppressWarnings("unchecked")
		public JSONArray getRackList(String jsonPath)
		{
			
			JSONArray rackList = new JSONArray();
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try {
				// Get the data from Json file following the path "jsonPath"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				
				//Get an array of Racks list that presented in "rackList" key
				String stringRackList = jsonObject.get("rackList").toString();	
				rackList = new JSONArray(stringRackList);
				
			} catch (Exception e) {
			}
			
		
			return rackList;
		}
		
		@SuppressWarnings("unchecked")
		public JSONObject putRackList(String jsonPath, JSONArray inputArray)
		{
			
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try {
				// Get the data from Json file following the path "jsonPath"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				//Remove the old Array "rackList"
				jsonObject.remove("rackList");
				//Add new "rackList" to the same jsonObject
				jsonObject.put("rackList",inputArray);
			
				
			} catch (Exception e) {
			}
			
			return jsonObject;
		}
		
		//This method used to update value at key outside the Array rackList
		public String updateKeyValueAtTotalRack(String key, String value, String jsonPath)
		{
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try 
				{
				// Get the data from Json file following the path "path"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				jsonObject.put(key, Integer.parseInt(value));}
				catch (Exception ex)
			{
				System.out.println("Inputed value: " + value + " is not an integer" );
								
			}
						
			
			return jsonObject.toString();
		}
		
		//This method used to update value at key inside the Array rackList
		public String updateKeyValueAtRack(String key, String value,String rackPosition, String jsonPath)
		{
			JSONArray rackLists = getRackList(jsonPath);
			int rackPos = Integer.parseInt(rackPosition);
			int arrayIndex = 0;
			
			//Step 1: check to see if the inputed rackPosition = -1 (rack not communicating). 
			//If yes, the desired rack need to use is at the top of Array
			if (rackPos == -1)
			{
				arrayIndex = 0;
			}
			else arrayIndex = rackPos-1;
			
			//Step 2: Navigate to the correct Array index according to inputed rack position
			org.json.JSONObject jsonObject =rackLists.getJSONObject(arrayIndex);
			
			//Step 3: Update value at inputed key
			// If the input "key" is one of "customerRackNumber" or "iPatchRackUnitsAvailable" or
			// "powerUsagePercent", then the input "value" should force to Integer
						switch (key) {
						case "rackNumber":
						case "iPatchRackUnitsAvailable":
						case "powerUsagePercent":
							try 
							{
								Integer newValue1 = Integer.parseInt(value);
								jsonObject.put(key, newValue1);}
							catch (Exception ex)
							{
								System.out.println("Inputed value: " + value + " is not an integer" );
								String newValue2 = value;
								jsonObject.put(key, newValue2);
							}
						
							
							break;
						default:
							String newValue2 = value;
							jsonObject.put(key, newValue2);
							
						}
					
			//Step 4: Update the modified jsonObject to the current array according to "arrayIndex"
			rackLists.put(arrayIndex, jsonObject);
			
			return putRackList(jsonPath,rackLists).toString();
		}
		
		public String removeKeyAtRack(String key,String rackPosition, String jsonPath)
		{
			JSONArray rackLists = getRackList(jsonPath);
			int rackPos = Integer.parseInt(rackPosition);
			int arrayIndex = 0;
			
			//Step 1: check to see if the inputed rackPosition = -1 (rack not communicating). 
			//If yes, the desired rack need to use is at the top of Array
			if (rackPos == -1)
			{
				arrayIndex = 0;
			}
			else arrayIndex = rackPos-1;
			
			//Step 2: Navigate to the correct Array index according to inputed rack position
			org.json.JSONObject jsonObject =rackLists.getJSONObject(arrayIndex);
			
			//Step 3: Remove at inputed key
			jsonObject.remove(key);
			
						
			//Step 4: Update the modified jsonObject to the current array according to "arrayIndex"
			rackLists.put(arrayIndex, jsonObject);
			
			return putRackList(jsonPath,rackLists).toString();
		}
		
		public String removeKeyAtTotalRack(String key,String jsonPath)
		{
			
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			//Step 1: Get all json body
			try 
				{
				// Get the data from Json file following the path "path"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				}
				catch (Exception ex)
			{
				System.out.println("Inputed key: " + key + " is invalid" );
								
			}
				
			//Step 2: Remove at inputed key
			jsonObject.remove(key);
			
			return jsonObject.toString();
		}
		@SuppressWarnings("unchecked")
		public String updateJsonRackByKey(String key, String value, String path) 
		{
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try {
				// Get the data from Json file following the path "path"
				Object obj = parser.parse(new FileReader(path));
				jsonObject = (JSONObject) obj;

				// If the input "key" is one of "customerRackNumber" or "iPatchRackUnitsAvailable" or
				// "powerUsagePercent", then the input "value" should force to Integer
				switch (key) {
				case "rackNumber":
				case "iPatchRackUnitsAvailable":
				case "powerUsagePercent":
					Integer newValue1 = Integer.parseInt(value);
					jsonObject.put(key, newValue1);
					
					break;
				default:
					String newValue2 = value;
					jsonObject.put(key, newValue2);
					
				}

			} catch (Exception e) {
			}
			return jsonObject.toString();
		}
}
