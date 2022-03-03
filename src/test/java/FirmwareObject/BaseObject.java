package FirmwareObject;

import static io.restassured.RestAssured.preemptive;

import io.restassured.RestAssured;
import utils.common.Constants;

public class BaseObject {
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
		
		
		//Step 2: Go through notification
		RestAssured.authentication =  preemptive().basic(authentication_username,authentication_password);
		
	}
}
