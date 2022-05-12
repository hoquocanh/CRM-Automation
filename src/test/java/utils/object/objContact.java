package utils.object;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import utils.common.Constants;
import utils.data.dataJsonContact;
import utils.data.dataJsonLead;

public class objContact<T, S extends String> {
	
	/**This method get the value of 'father' contact based on the entered "inputKey"
	 * @param testFileName
	 * @param inputKey
	 * @return value of "inputKey"
	 */
	public T getJsonValue(S testFileName, S inputKey)
	{
		T returnValue=null;
		String inputjsonPath = "\\src\\test\\java\\utils\\data\\"+testFileName+".json";
		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
		
		Object obj;
        try {
        	
            obj = new JSONParser().parse(new FileReader(jsonPath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject contactXObject;
            //List of Contacts
			//1. Position of first Contact is 0
            JSONArray contactListArray = (JSONArray) jsonObject.get(dataJsonContact.CONTACTLIST.getValue());
            contactXObject = (JSONObject) contactListArray.get(0);
            
             //2. "address" is another JSONObject
            JSONObject addressObject = (JSONObject) contactXObject.get(dataJsonContact.ADDRESS.getValue());
            //System.out.println("Address:" + JSONValue.toJSONString(addressObject));
            
            if(isAddressKey(inputKey))
            {
            	returnValue = (T) addressObject.get(inputKey);
            	
            }//If not in group of key "address", return the value of "inputKey", return NULL if there is no "inputKey" in JSON
            else //3. If not able to find in key "address"
            {
            	returnValue = (T) contactXObject.get(inputKey);         	
            }
         } 
	    catch (Exception e) 
	    {
	        e.printStackTrace();
	    } 
		return returnValue;	
	}
	
	/**This method get the value of 'child' contact based on the entered "inputKey" and then "inputChildName" from child contact
	 * @param testFileName
	 * @param inputKey
	 * @param inputChildName
	 * @return value of "inputKey"
	 */
	public T getJsonValue(S testFileName, S inputKey, S inputChildName)
	{
		T returnValue=null;
		String inputjsonPath = "\\src\\test\\java\\utils\\data\\"+testFileName+".json";
		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
		
		Object obj;
        try {
        	
            obj = new JSONParser().parse(new FileReader(jsonPath));
            JSONObject jsonObject = (JSONObject) obj;
            JSONObject contactXObject;
            //List of Contacts
			//1. Position of first Contact is 0
            JSONArray contactListArray = (JSONArray) jsonObject.get(dataJsonContact.CONTACTLIST.getValue());
            contactXObject = (JSONObject) contactListArray.get(0);
            
             //2. "address" is another JSONObject
            JSONObject addressObject = (JSONObject) contactXObject.get(dataJsonContact.ADDRESS.getValue());
            //System.out.println("Address:" + JSONValue.toJSONString(addressObject));
            
            if(isAddressKey(inputKey))
            {
            	returnValue = (T) addressObject.get(inputKey);
            	
            }//If not in group of key "address", return the value of "inputKey", return NULL if there is no "inputKey" in JSON
            else if(this.isContactChild(inputKey))
            	{
            	//3. "contactChild" is another JSONObject
                JSONArray contactChildListArray = (JSONArray) contactXObject.get(dataJsonContact.CONTACTCHILD.getValue());
                	//Go through list of contactChildListArray to find out the desired value of "inputKey" using the navigator as "inputChildName"
	                for (int i = 0; i < contactChildListArray.size(); i++) {
	                    JSONObject jobject = (JSONObject) contactChildListArray.get(i);
	                    //Find the desired contactChild using the navigator as "inputChildName"
	                    if(jobject.get(dataJsonContact.CHILDCONTACTNAME.getValue()).equals(inputChildName))
	                    {
	                    	returnValue = (T) jobject.get(inputKey);
	                    	break;
	                    }
	                   
	                }
            	}
            else //4. If not able to find in key "address" or "contactChild"
            {
            	returnValue = (T) contactXObject.get(inputKey);         	
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
//		String inputjsonPath = "\\src\\test\\java\\utils\\data\\MergedLead\\SameCompanyEmail\\"+testFileName+".json";
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
		String inputjsonPath = "\\src\\test\\java\\utils\\data\\MergedLead\\SameCompanyEmail\\"+testFileName+".json";
		String jsonPath = System.getProperty("user.dir") + inputjsonPath;
		//System.out.println("jsonPath: "+ jsonPath);
		
		
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
            JSONObject addressObject = (JSONObject) leadXObject.get(dataJsonLead.STREETADDRESS.getValue());
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
				|| inputKey.equalsIgnoreCase(dataJsonLead.COUNTRY.getValue()) 
				|| inputKey.equalsIgnoreCase(dataJsonLead.STATE.getValue()) 
				|| inputKey.equalsIgnoreCase(dataJsonLead.CITY.getValue())
				|| inputKey.equalsIgnoreCase(dataJsonLead.POSTALCODE.getValue())) 
		{
			return true;
		}
		else return false;
	}
	public static boolean isContactChild(String inputKey)
	{
		if (inputKey.equalsIgnoreCase(dataJsonContact.CHILDCONTACTNAME.getValue()) 
				|| inputKey.equalsIgnoreCase(dataJsonContact.CHILDNO.getValue())
				|| inputKey.equalsIgnoreCase(dataJsonContact.CHILDEMAILADDRESS.getValue())) 
		{
			return true;
		}
		else return false;
	}
}
