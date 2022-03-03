package FirmwareObject;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.Collection;
import java.util.List;




import io.restassured.http.ContentType;
import io.restassured.response.Response;

import utils.common.Constants;



public class ControllerInformation extends BaseObject{
	public Response response;
	
	
	//This method send POST request with the body comes from Json file "path" if the path contains "/" to separate folders
		public Response changeWithFileInput(String path) {
			setup();		
			return response = given().body(dataForAPI(path)).with().contentType(ContentType.JSON).when().post(Constants.APIVER).then().extract().response();		
		}
		//This method send POST request with the body comes from Json file "jsonFile" if the path does NOT contain "/" to separate folders
		public Response change(String jsonFile) {
			setup();
			return response = given().body(jsonFile).with().contentType(ContentType.JSON).when().post(Constants.APIVER).then()
					.extract().response();
		}
		
			
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
			
			
			//Step 2.1. If the inputed key is "display", check to see whether having "display" key in API response
			if(key.compareToIgnoreCase("display") == 1) 
			{
			
				//If existing a fist value in the List, show "display exist", otherwise show "display not exist" 
				if ( itemList.get(0)!= null)
				{
					tempString = "display exist";
				System.out.println("vo exist");
				}
				else
				{
				tempString = "display not exist";
				System.out.println("vo  Null");
				}
			}
			//step2.2: if the inputed key is NOT "display"
			//get a value from the inputed key following inputed rackPosition (Notice: the gotten list beginning at position 0, that while i have to subtract 1
			else	
			{	

				String newValue = itemList.get(Integer.parseInt(rackPosition)-1);
				tempString = newValue;
					
			}
			
			System.out.println("The value of key " +key + " of rack position "+rackPosition + " is :" + tempString);
			return tempString;
		}
		
		//-------------------------external method---------------------------//
		public static boolean isNullOrEmpty( final Collection< ? > c ) {
		    return c == null || c.isEmpty();
		}
}
