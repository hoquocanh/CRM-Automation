package pages;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import utils.common.Constants;
import utils.data.dataJsonLead;
import utils.object.objLead;
public class TEST {
	
	public static boolean isAddressKey(String inputKey)
	{
		
		if (inputKey.equalsIgnoreCase(dataJsonLead.STREETADDRESS.getValue()) 
				|| inputKey.equalsIgnoreCase(dataJsonLead.DISTRICT.getValue()) 
				|| inputKey.equalsIgnoreCase(dataJsonLead.CITY.getValue())
				|| inputKey.equalsIgnoreCase(dataJsonLead.POSTALCODE.getValue())) 
		{
			return true;
		}
		else return false;
	}
	
	public static String getJsonValue(String testFileName, String leadType, String inputKey)
	{
		String returnValue ="";
		String inputjsonPath = "\\src\\test\\java\\utils\\data\\"+testFileName+".json";
		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
		System.out.println("jsonPath: "+ jsonPath);
		
		
		Object obj;
        try {
        	
            obj = new JSONParser().parse(new FileReader(jsonPath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject leadXObject;
            //List of Leads
			//1. Iterator itr1 = leadList.iterator();
            JSONArray leadListArry = (JSONArray) jsonObject.get(dataJsonLead.LEADLIST.getValue());
            
            //2. Position of Target lead is 0; position of Source lead is 1
            if(leadType.equalsIgnoreCase(Constants.TARGET_LEAD))
            {
            	leadXObject = (JSONObject) leadListArry.get(0);
            }
            else
            {
            	leadXObject = (JSONObject) leadListArry.get(1);
            }
             //3. "address" is another JSONObject
            JSONObject addressObject = (JSONObject) leadXObject.get(dataJsonLead.ADDRESS.getValue());
            System.out.println("Address:" + JSONValue.toJSONString(addressObject));
            
            if(isAddressKey(inputKey))
            {
            	returnValue = (String) addressObject.get(dataJsonLead.STREETADDRESS.getValue());
            	
            }
            else
            {
            	returnValue = (String) leadXObject.get(dataJsonLead.STREETADDRESS.getValue());
            	
            }
         } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    } 
		
		return returnValue;
		
	}
	
	public static void main(String[] args) {
		
		String inputjsonPath = "defaultLead";
		String outputValue ="";

		System.out.println("Company name: "+ getJsonValue(inputjsonPath,Constants.SOURCE_LEAD,dataJsonLead.STREETADDRESS.getValue()));
		System.out.println("Is Address:" + isAddressKey(dataJsonLead.STREETADDRESS.getValue()));
		
		
		
        
    }
}
