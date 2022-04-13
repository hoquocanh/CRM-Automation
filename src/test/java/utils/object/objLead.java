package utils.object;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import utils.common.Constants;
import utils.data.dataJsonLead;

public class objLead<T, S extends String> {
	
	public T getJsonValue(S testFileName, S leadType, S inputKey)
	{
		T returnValue=null;
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
            	returnValue = (T) addressObject.get(inputKey);
            	
            }//If not in group of key "address"
            else
            {
            	returnValue = (T) leadXObject.get(inputKey);
            	
            }
         } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    } 
		
		return returnValue;
		
	}
	
//	public static String getJsonValue(String testFileName, String leadType, String inputKey)
//	{
//		String returnValue ="";
//		String inputjsonPath = "\\src\\test\\java\\utils\\data\\"+testFileName+".json";
//		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
//		System.out.println("jsonPath: "+ jsonPath);
//		
//		
//		Object obj;
//        try {
//        	
//            obj = new JSONParser().parse(new FileReader(jsonPath));
//            JSONObject jsonObject = (JSONObject) obj;
//            JSONObject leadXObject;
//            //List of Leads
//			//1. Iterator itr1 = leadList.iterator();
//            JSONArray leadListArry = (JSONArray) jsonObject.get(dataJsonLead.LEADLIST.getValue());
//            
//            //2. Position of Target lead is 0; position of Source lead is 1
//            if(leadType.equalsIgnoreCase(Constants.TARGET_LEAD))
//            {
//            	leadXObject = (JSONObject) leadListArry.get(0);
//            }
//            else
//            {
//            	leadXObject = (JSONObject) leadListArry.get(1);
//            }
//             //3. "address" is another JSONObject
//            JSONObject addressObject = (JSONObject) leadXObject.get(dataJsonLead.ADDRESS.getValue());
//            System.out.println("Address:" + JSONValue.toJSONString(addressObject));
//            
//            if(isAddressKey(inputKey))
//            {
//            	returnValue = (String) addressObject.get(inputKey);
//            	
//            }//If not in group of key "address"
//            else
//            {
//            	returnValue = (String) leadXObject.get(inputKey);
//            	
//            }
//         } 
//	    catch (Exception e) 
//	    {
//	        e.printStackTrace();
//	    } 
//		
//		return returnValue;
//		
//	}
	public static void setJsonValue(String testFileName, String leadType, String inputKey, String inputValue)
	{
		String returnValue ="";
		String inputjsonPath = "\\src\\test\\java\\utils\\data\\"+testFileName+".json";
		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
		System.out.println("jsonPath: "+ jsonPath);
		
		
		Object obj = null;
        //I. Find out the inputKey we want to replace the value
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
            //System.out.println("Address:" + JSONValue.toJSONString(addressObject));
            
            if(isAddressKey(inputKey))
            {            	
            	addressObject.remove(inputKey);
            	addressObject.put(inputKey, inputValue);
            	
            }//If not in group of key "address"
            else
            {            	
            	leadXObject.remove(inputKey);
            	leadXObject.put(inputKey, inputValue);
            }
         } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    } 
		        
        
        //II. Write JSON file after replace the value for the inputKey
		try (FileWriter file = new FileWriter(jsonPath) )
		{
			file.write(obj.toString());
			file.flush();			
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
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

}
