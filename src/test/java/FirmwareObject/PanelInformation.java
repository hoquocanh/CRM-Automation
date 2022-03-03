package FirmwareObject;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utils.common.Constants;
import utils.data.dataPanelInformation;

import static io.restassured.RestAssured.*;

public class PanelInformation {
	public Response response;
	public static String configurationType;
	public static String config_rackID;
	public static String theFullyURL;
	public static String authentication_username = Constants.AUTHENTICATION_USERNAME;
	public static String authentication_password = Constants.AUTHENTICATION_USERNAME;
	
	private dataPanelInformation _panelInfo;
	public void setup() {
		//Step 1: Choose correct Static RM LAN
		switch (configurationType) {
		case "configurationA":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_A;	
			config_rackID =Constants.RACKID_CONFIG_A;
			break;
		case "configurationB":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_B;	
			config_rackID =Constants.RACKID_CONFIG_B;
			break;
		case "configurationC":
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_C;	
			config_rackID =Constants.RACKID_CONFIG_C;
			break;
		default:
			RestAssured.baseURI = Constants.URL_HTTP_CONFIG_A;
			config_rackID =Constants.RACKID_CONFIG_A;
		}	
		
		//Step 2: combine each part of component to be a fully URL
		theFullyURL = Constants.APIVER_ALL_PANELS + Constants.APIVER_PANELS_PARAMETER_RACKID + config_rackID;
		
		//Step 3: Go through notification
		RestAssured.authentication = preemptive().basic(authentication_username,authentication_password);
	}

	
			//This method send POST request with the body comes from Json file "path" if the path contains "/" to separate folders
			public Response changeWithFileInput(String path) {
				setup();		
				return response = given().body(dataForAPI(path)).with().contentType(ContentType.JSON).when().post(theFullyURL).then().extract().response();		
			}
			//This method send POST request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
			public Response change(String jsonFile) {
				setup();
				return response = given().body(jsonFile).with().contentType(ContentType.JSON).when().post(theFullyURL).then()
						.extract().response();
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
				return response = given().body(dataForAPI(path)).with().contentType(ContentType.JSON).when().put(theFullyURL).then().extract().response();		
			}
			//This method send PUT request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
			public Response put(String jsonFile) {
				setup();
				return response = given().body(jsonFile).with().contentType(ContentType.JSON).when().put(theFullyURL).then()
						.extract().response();
			}
		public File dataForAPI(String path) {
			setup();
			return new File(Constants.DATA_SOURCE + path);
		}
		
		//This method send GET request
		public Response getGetResponse() {
			setup();
			 
			return given().when().get(theFullyURL).then().extract().response();
		}
		
		//This method used to print panel information from json file
				@SuppressWarnings("unchecked")
				public String getJsonFile(String jsonPath)
				{
					String jsonString = "";
					JSONObject jsonObject = new JSONObject();
					JSONParser parser = new JSONParser();
					
					try {
						// Get the data from Json file following the path "jsonPath"
						Object obj = parser.parse(new FileReader(jsonPath));
						
						jsonObject = (JSONObject) obj;
						System.out.println("AAVVV" + jsonObject.toString());
						jsonString = jsonObject.toString();
						 
						 System.out.println("AAA1  " + jsonString);
								 //get(_panelInfo.PANELLIST.getValue()).toString();
					} catch (Exception e) {
					}
					 System.out.println("AAA 2 " + jsonString);
					return jsonString;
				}
		//This method will define the value of inputed key will be a number or string
		public Boolean isValueOfKeyString( String key)
		{
			//Step 1: create list of keys which their value is a string
			List<String> listKeyIsString = new ArrayList<String>();
			
			listKeyIsString.add(_panelInfo.PANELLIST.getValue());
			listKeyIsString.add(_panelInfo.RACKID.getValue());
			listKeyIsString.add(_panelInfo.ID.getValue());
			listKeyIsString.add(_panelInfo.KITS_TYPE.getValue());
			listKeyIsString.add(_panelInfo.STATUS.getValue());
			listKeyIsString.add(_panelInfo.FIRMWARETIMESTAMP.getValue());
			listKeyIsString.add(_panelInfo.UPDATABLE.getValue());
			listKeyIsString.add(_panelInfo.POSTION.getValue());
			listKeyIsString.add(_panelInfo.MODULE_TYPE.getValue());
			
			//step 2: identify the inputed key is a string or not
			if (listKeyIsString.contains(key)== true)
				return true;
			else return false; 
					
		}
		
		//This method get the value of input Key "keyOfInfo" if the key included in an Array[]
		public String getInfoOfKey(String panelPosition,String key) {
			setup();
			
			System.out.println("// ===============================================//");
			System.out.println("We are checking information of panel position : " +panelPosition);
			
			//Step1: Get the list of value of the key "panelNumber" in Panel List of Json file to identify list of panel position in Panel List
			//The output list return as [[1,2,3...]]
			List<List<Integer>> listOfPanelPosition = getGetResponse().jsonPath().getList(Constants.ROOT_OF_PANEL_LIST+_panelInfo.PANELS.getValue()+"."+_panelInfo.PANELNUMBER.getValue() );
			System.out.println("list of Panel Position: " + listOfPanelPosition);
			//Step2: Get the index of panel which have the key value qualify the value of "panelNumber" available
			List<Integer> temp = listOfPanelPosition.get(0);
			
			
			Integer indexOfExpectedPanelNumber = temp.indexOf(Integer.parseInt(panelPosition)+1);
			
			System.out.println("indexOfExpectedPanelNumber in list:" + indexOfExpectedPanelNumber);
			//Step3: Get the list of value of the key given by value of "keyOfInfo" variable in Panel List of Json file
			System.out.println("key : "+key);

			//Since the response of Rack Information API will return 2 kinds of key value. (1) The number  and (2) The string
			//Therefore, i have to declare 2 kinds of List correspondingly 
			String tempString ;
			
			//If the key is not a String
			if (isValueOfKeyString(key) == false)
			{
				List<Integer> itemList1 = getGetResponse().jsonPath().getList(Constants.ROOT_OF_RACK_LIST+key);
				Integer newValue1 = itemList1.get(indexOfExpectedPanelNumber);
				tempString = newValue1.toString();
			}
			else
			{	
				List<String> itemList2 = getGetResponse().jsonPath().getList(Constants.ROOT_OF_RACK_LIST+key);
				String newValue2 = itemList2.get(indexOfExpectedPanelNumber);
				tempString = newValue2;
			}
			System.out.println("The value of key " +key + " of rack position "+panelPosition + " is :" + tempString);

			return tempString;
		}
		
		//This method get the value of input Key "keyOfInfo" if the key is outside the Array Panels
		public String getInfoOfKeyTotalPanels(String key) {
					setup();
					
					System.out.println("// ===============================================//");
					System.out.println("We are checking information of key : "+key);
					String tempString = getGetResponse().jsonPath().get(Constants.ROOT_OF_PANEL_LIST+key).toString();
					int lastWordPosition = tempString.length()-1;
					String correctValue =tempString.substring(1, lastWordPosition); 

					return correctValue; 
				}
		//This method get the value of input Key "keyOfInfo" if the key is outside the Array Panels using Json file
		public String getInfoOfKeyTotalPanels(String key, String jsonPath) {
			setup();
			
			System.out.println("// ===============================================//");
			System.out.println("We are checking information of key : "+key);
			Integer tempString = getTotalPanels(jsonPath);
		/*
		 * int lastWordPosition = tempString.length()-1; String correctValue
		 * =tempString.substring(1, lastWordPosition);
		 */ 

			return tempString.toString(); 
		}
		//This method used to get the total panels of Rack in RM LAN
		@SuppressWarnings("unchecked")
		public Integer getTotalPanels(String jsonPath)
		{
			
			Integer totalPanels = 0;
			JSONObject jsonObject = null;
			JSONParser parser = new JSONParser();
			
			try {
				// Get the data from Json file following the path "jsonPath"
				Object obj = parser.parse(new FileReader(jsonPath));
				jsonObject = (JSONObject) obj;
				
				//Get the value of key "totalPanels"
				totalPanels = Integer.parseInt(jsonObject.get(_panelInfo.TOTALPANELS.getValue()).toString());
					
			} catch (Exception e) {
			}
			
		
			return totalPanels;
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
