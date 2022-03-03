package FirmwareObject;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Collection;
import java.util.List;


import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utils.common.Constants;

import static io.restassured.RestAssured.*;

public class PatchingMode extends BaseObject{
	public Response response;
	

			
		public File dataForAPI(String path) {
			setup();
			return new File(Constants.DATA_SOURCE + path);
		}
		
		//This method send GET request
		public Response getGetResponse() {
			setup();
			return given().when().get(Constants.APIVER_ALL_CONTROLLERS).then().extract().response();
		}
			
		//This method get the value of input Key "keyOfInfo"
		//Notice: In Controllers page, the input variable "rackPosition" is an positive number >=1, because only Controllers are being available showed, the "Not Communicating" Controllers will not show
		public String getInfoOfKey(String rackPosition,String key) {
			String tempString ;
			
			setup();
			
			System.out.println("// ===============================================//");
			System.out.println("We are checking information of rack position : " +rackPosition);
			
			//Check to see whether the inputed key exist
			//Step1: get list of value from the inputed keys beginning at the "controllers" key  
			List<String> itemList = getGetResponse().jsonPath().getList(Constants.ROOT_OF_CONTROLLER_LIST+key);
			System.out.println("itemList :"+ itemList);
			
			//step2: get a value from the inputed key following inputed rackPosition (Notice: the gotten list beginning at position 0, that while i have to subtract 1
			
				String newValue = itemList.get(Integer.parseInt(rackPosition)-1);
				tempString = newValue;
				
			System.out.println("The value of key " +key + " of rack position "+rackPosition + " is :" + tempString);
			return tempString;
		}
		
		//-------------------------external method---------------------------//
		public static boolean isNullOrEmpty( final Collection< ? > c ) {
		    return c == null || c.isEmpty();
		}
}
