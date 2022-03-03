package utils.helper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

public class JsonHelperCustom {

	public static <T> T getData(String jsonPath, Type type, String dateFormat) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat(dateFormat);
			Gson gson = gsonBuilder.create();

			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(reader, type);
		} catch (Exception e) {
			throw e;
		}
	}

	public static <T> T getData(String jsonPath, Class<?> clazz, String dateFormat) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat(dateFormat);
			Gson gson = gsonBuilder.create();
			JsonReader reader = getJsonReader(jsonPath);
			return gson.fromJson(reader, clazz);
		} catch (Exception e) {
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Type type, String dateFormat) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat(dateFormat);
			Gson gson = gsonBuilder.create();
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst = new ArrayList<T>();
			lst = gson.fromJson(reader, type);
			return lst;
		} catch (Exception e) {
			throw e;
		}
	}

	public static <T> List<T> getListData(String jsonPath, Class<?> clazz, String dateFormat) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.setDateFormat(dateFormat);
			Gson gson = gsonBuilder.create();
			JsonReader reader = getJsonReader(jsonPath);
			List<T> lst = new ArrayList<T>();
			lst = gson.fromJson(reader, clazz);
			return lst;
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> List<T> getListFromJsonObj(JsonElement jsonElement, Type classType, String dateFormat) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setDateFormat(dateFormat);
		Gson gson = gsonBuilder.create();
		List<T> lst = new ArrayList<T>();
		lst = gson.fromJson(jsonElement, classType);
		return lst;
	}

	private static JsonReader getJsonReader(String jsonPath) {
		try {
			JsonReader reader;
			reader = new JsonReader(new FileReader(jsonPath));
			return reader;
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public static JSONObject readFileJson(String link) {
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = new JSONObject();
		try {

			Object obj = parser.parse(new FileReader(link));

			jsonObject = (JSONObject) obj;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
