package utils.common;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Common {
	public static void waitPageLoad() throws Throwable
	{
		
		Thread.sleep(Constants.WAIT_PAGE_LOAD);
	}
	public static void waitPageLoad(int seconds) throws Throwable
	{
		
		Thread.sleep(seconds*1000);
	}
	public static String dateToString(Date date) {
		return dateToString(date, "MM/dd/yy");
	}

	public static String dateToString(Date date, String format) {
		SimpleDateFormat myFormat = new SimpleDateFormat(format);
		return myFormat.format(date);
	}

	public static Date convertStringToDate(String input, String format) {
		Date date;
		try {
			date = new SimpleDateFormat(format).parse(input);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static Date setTimeValue(int hours, int minutes) {
		return setTimeValue(hours, minutes, 0);
	}

	public static Date setTimeValue(int hours, int minutes, int seconds) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, hours);
		cal.set(Calendar.MINUTE, minutes);
		cal.set(Calendar.SECOND, seconds);
		return cal.getTime();
	}

	@SuppressWarnings("deprecation")
	public static int convertTimeToSeconds(Date input) {
		return input.getHours() * 3600 + input.getMinutes() * 60 + input.getSeconds();
	}

	public static Date getCurrentDate() {
		Date curDate = new Date();
		return curDate;
	}
	
	
	public static Date addDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, days);
		return cal.getTime();
	}

	public static Date getStartOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

	public static Date getEndOfDay(Date date) {
		if (date == null) {
			return null;
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Date getRandomDateBefore(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int rnd = Common.getRandomNumberFromRange(1, cal.get(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.DAY_OF_MONTH, rnd);
		return cal.getTime();
	}
	/**
	 * Generate a random company email based on the current time. 
	 * The output email will be as "TEST_AUTOMATION_2022_05_12T11_10_23@test.com"
	 * @author anh.ho	 
	 * @return a random email
	 */
	public static String getRandomTestEmail() {
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyy_MM_dd'T'HH_mm_ss");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		System.out.println(timestamp.getTime());
		
		String randomEmail =  "TEST_AUTOMATION" + sdf.format(timestamp) + Constants.TEST_DOMAIN_EMAIL;
		System.out.println(randomEmail);
		return randomEmail;
	}
	/**
	 * Generate a random public email based on the current time
	 * The output email will be as "TEST_AUTOMATION_2022_05_05T10_46_37@gmail.com" 
	 * @author anh.ho	 
	 * @return a random email
	 */
	public static String getRandomPublicEmail() {
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyy_MM_dd'T'HH_mm_ss");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		System.out.println(timestamp.getTime());
		
		String randomEmail =  "TEST_AUTOMATION" + sdf.format(timestamp) + Constants.GMAIL_DOMAIN_EMAIL;
		System.out.println(randomEmail);
		return randomEmail;
	}
	/**
	 * Generate a random public email based on the current time
	 * The output email will be as "TEST_AUTOMATION_2022_05_05T10_46_37@company_2022_05_05T10_46_37.com" 
	 * @author anh.ho	 
	 * @return a random email
	 */
	public static String getRandomCompanyEmail() {
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyy_MM_dd'T'HH_mm_ss");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		//Change the domain of Company to be as "@company_2022_05_05T10_46_37.com
		String refineCompanyDomain = Constants.COMPANY_DOMAIN_EMAIL.replace("REPLACE",(String) sdf.format(timestamp));		
		
		String randomEmail =  "TEST_AUTOMATION" + sdf.format(timestamp) + refineCompanyDomain;
		System.out.println(randomEmail);
		return randomEmail;
	}
	
	/**
	 * Generate a random public email based on the current time
	 * The output email will be as "TEST_AUTOMATION_2022_05_05T10_46_37@company_2022_05_05T10_46_37.com" 
	 * @author anh.ho	 
	 * @return a random email
	 */
	public static String getRandomIndividualEmail() {
		SimpleDateFormat sdf = new SimpleDateFormat("_yyyy_MM_dd'T'HH_mm_ss");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		//Change the domain of Company to be as "@individual_2022_05_05T10_46_37.com
		String refineDomain = Constants.INDIVIDUAL_DOMAIN_EMAIL.replace("REPLACE",(String) sdf.format(timestamp));		
		
		String randomEmail =  "TEST_AUTOMATION" + sdf.format(timestamp) + refineDomain;
		System.out.println(randomEmail);
		return randomEmail;
	}
	/**
	 * Generate a current date in template MM/dd/yyyy
	 * @author anh.ho	 
	 * @return current data in template MM/dd/yyyy
	 */
	public static String getCurrentDateAsString() 
	{	
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		
		Timestamp timestamp = new Timestamp(date.getTime());
		
		String returnString = (String) sdf.format(timestamp);	
		return returnString;
	}
	/**
	 * Replace a dynamic controll
	 * @author anh.ho	
	 * @param inputElement
	 * @param replaceString
	 * @param replaceBy
	 * @return a selenium.by
	 */
	public static By replaceDynamicControl (By inputElement, String replaceString, String replaceBy)
	{
		By dynamicElement=null;
		
		String x = inputElement.toString().replaceAll("By.xpath: ", "");
        String y = x.replaceAll(replaceString, replaceBy);
		
        dynamicElement= By.xpath(y);
        
		return dynamicElement;
		
	}
	public static String randomHeight() {
		return String.format("%.2f",
				(float) new Random()
						.nextInt(new Float(Constants.MAX_HEIGHT * 100 - Constants.MIN_HEIGHT * 100).intValue())
						/ (float) 100 + Constants.MIN_HEIGHT);
	}

	public static String randomWeight() {
		return String.format("%.2f",
				(float) new Random()
						.nextInt(new Float(Constants.MAX_WEIGHT * 100 - Constants.MIN_WEIGHT * 100).intValue())
						/ (float) 100 + Constants.MIN_WEIGHT);
	}

	public static String randomBirthDate() {
		return String.format("%02d/%02d/%04d", LocalDate.now().getDayOfMonth() - 1, LocalDate.now().getMonthValue(),
				LocalDate.now().getYear()
						- (new Random().nextInt(Constants.MAX_OLD - Constants.MIN_OLD) + Constants.MIN_OLD));
	}

	public static String getStringByKey(String src, String key) {
		Boolean isCorrect = true;
		String[] splitSrc = src.split("\\s+");
		String[] splitCondition = key.split("\\s+");

		for (int i = 0; i < splitSrc.length; i++)
			if (splitSrc[i].equals(splitCondition[0])) {

				for (int j = 1; j < splitCondition.length; j++) {
					if (!splitSrc[i + j].equals(splitCondition[j])) {
						isCorrect = false;
						break;
					}
				}

				if (isCorrect)
					return splitSrc[i + splitCondition.length];

				isCorrect = true;
			}

		return "";
	}

	public static String randomName() {
		String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String string = "";
		Random rd = new Random();
		int indx = rd.nextInt(5) + 5;

		for (int i = 0; i < indx; i++) {
			int lt = rd.nextInt(52);
			string += Character.toString(letter.charAt(lt));
		}

		return string;
	}

	@SuppressWarnings("null")
	public static String getStringFromJSON(JSONObject data, String key) {
		if (data == null)
			return "";

		// return String.valueOf(data.get(key));
		return String.valueOf(data.get(key));
	}

	public static JSONObject readFileJson(String link) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {
			String fileContent = new String(Files.readAllBytes(Paths.get(link)));
			jsonObject = (JSONObject) parser.parse(fileContent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String convertImageToURI(String imagePath) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		BufferedImage img;
		File image = new File(imagePath);
		try {
			img = ImageIO.read(image);
			ByteArrayOutputStream convert = new ByteArrayOutputStream();
			ImageIO.write(img, "png", convert);
			String data = DatatypeConverter.printBase64Binary(convert.toByteArray());
			return data;
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public static String randomString() {
		String letter = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String number = "0123456789";
		String string = "";
		Random rd = new Random();
		int indx = rd.nextInt(20);

		for (int i = 0; i < indx; i++) {
			int kt = rd.nextInt(2);
			if (kt == 0)// string will have letter(s)
			{
				int lt = rd.nextInt(52);
				string += Character.toString(letter.charAt(lt));
			} else // password will have number(s)
			{
				int lt = rd.nextInt(9);
				string += Character.toString(number.charAt(lt));
			}
		}
		return string;
	}

	public static String screenshotURI(String imagePath) {
		String randomPopUpId = "id" + UUID.randomUUID().toString();
		String randomButtonId = randomPopUpId + "button";
		String imageString = "data:image/png;base64," + convertImageToURI(imagePath);
		String htmlScript = "<script>$(document).ready(function(){$( \"#" + randomPopUpId
				+ "\" ).dialog({ autoOpen: false });$( \"#" + randomPopUpId
				+ "\" ).dialog({width:1000},{height:700});$( \"#" + randomButtonId + "\" ).click(function() {$( \"#"
				+ randomPopUpId + "\" ).dialog( \"open\" );});});</script></br><img id=\"" + randomButtonId
				+ "\" src=\"" + imageString
				+ "\" style=\"border: 4px solid #f6f7fa;width: 150px;cursor: zoom-in;display: block;margin-top: 15px;\"/></br><div style=\"width: 50%; margin: 0 auto;\" id=\""
				+ randomPopUpId + "\" > <a href=\"#" + randomPopUpId
				+ "\"  class=\"ui-btn ui-corner-all ui-shadow ui-btn-a ui-icon-delete ui-btn-icon-notext ui-btn-right\"></a><img style=\"width:800px;height:600;\"  src=\""
				+ imageString + "\"/></div>";
		return htmlScript;
	}

	public static String getNowTime(String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = new Date();
		return dateFormat.format(date);
	}

	public static String getRandomString(String prefix) {
		return prefix.concat(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
	}

	public static void writeTextToFile(String fileName, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getRandomNumberFromRange(int min, int max) {
		Random ran = new Random();
		return (Math.abs(ran.nextInt(max - min + 1) + min));
	}

	public static String randomDOB() {
		return String.format("%02d/%02d/%04d", LocalDate.now().getDayOfMonth(), LocalDate.now().getMonthValue(),
				LocalDate.now().getYear() - (new Random().nextInt(61) + 10));
	}

	public static String splitStringWithRegex(String input, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		if (matcher.find())
			return matcher.group();
		return null;
	}

	public static String changeDateFormat(String date, String oldFormat, String newFormat) {

		String oldDateString = date;

		SimpleDateFormat sdf = new SimpleDateFormat(oldFormat);
		Date d = null;
		try {
			d = sdf.parse(oldDateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		sdf.applyPattern(newFormat);
		return sdf.format(d);
	}

	public static boolean checkRegexFormat(String text, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(text);
		return matcher.matches();
	}

	@SuppressWarnings("unchecked")
	public static String updateJsonByKey(String key, String value, String path) {
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();

		try {
			// Get the data from Json file following the path "path"
			Object obj = parser.parse(new FileReader(path));
			jsonObject = (JSONObject) obj;
			// If the input "key" is one of "httpPortNumber" or "httpsPortNumber" or
			// "ipv6Prefix", then the input "value" should force to Integer
			switch (key) {
			case "httpPortNumber":
			case "httpsPortNumber":
			case "ipv6Prefix":
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

	// This method used to convert a String (in JSON format) to an JSON object
	public static JSONObject convertStringToJsonObject(String jsonText) {
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(jsonText);
			jsonObject = (JSONObject) obj;
		}

		catch (Exception e) {
		}
		return jsonObject;
	}

	// This method used to save a JSON data in "jsonObject" to a file "filePath"
	public static void saveToJsonFile(String filePath, String jsonString) {

		JSONObject jsonObject = convertStringToJsonObject(jsonString);
		try (FileWriter file = new FileWriter(filePath)) {
			file.write(jsonObject.toJSONString());
			System.out.println("Successfully Copied JSON Object to File");

		} catch (Exception e) {
			System.out.println("UNsuccessfully Copied JSON Object to File");
		}
	}

	public static void splitJsonFileToJsonArray(String path) {
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();

		try {
			// Get the data from Json file following the path "path"
			Object obj = parser.parse(new FileReader(path));
			jsonObject = (JSONObject) obj;
			System.out.println("View Json file first:" + jsonObject.toString());
		}

		catch (Exception e) {
		}

		System.out.println("View Json file:" + jsonObject.toString());
		JSONArray array = new JSONArray(jsonObject.toString());
		for (int i = 0; i < array.length(); i++) {
			System.out.println(array.get(i));
		}

	}

	public static void splitJsonStringToJsonArray(String jsonString) {
		int firstPosition = jsonString.indexOf("[");
		int lastPosition = jsonString.indexOf("]");
		String tempString = jsonString.substring(firstPosition + 1, lastPosition);
		System.out.println("tempString: " + tempString);

		String regex1 = "\\}";
		Pattern p = Pattern.compile(regex1);
		String[] tempText = p.split(tempString);

		System.out.println("Print String before slpitting: " + tempString);
		for (String s : tempText) {
			System.out.println("Print result: " + s);
		}

	}

	// Quoc Anh - May 17 2019
	public static String prettyPrintJsonString(String jsonString) {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonParser jp = new JsonParser();
		JsonElement je = jp.parse(jsonString);
		String prettyJsonString = gson.toJson(je);

		return prettyJsonString;

	}
	/*
	 * public static void splitJsonStringToArray(String jsonString) {
	 * 
	 * int firstPosition = jsonString.indexOf("\\["); int lastPosition =
	 * jsonString.indexOf("\\]"); String tempString =
	 * jsonString.substring(firstPosition, lastPosition);
	 * System.out.println("tempString: " + tempString);
	 * 
	 * String[] parsedString1 = tempString.split("\\{");
	 * 
	 * for (int i=0; i<parsedString1.length; i++) {
	 * System.out.println("Print subsentence of jsonString of array["+ i+"]: "+
	 * parsedString1[i]); }
	 * 
	 * 
	 * for (String s: parsedString1 ) {
	 * System.out.println("Print subsentence of jsonString: "+ s); }
	 * 
	 * 
	 * 
	 * }
	 */

}
