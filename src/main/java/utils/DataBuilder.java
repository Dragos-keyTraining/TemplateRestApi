package utils;

import org.json.simple.JSONObject;

import com.github.javafaker.Faker;

/**
 * Example class for building the json objects to be used as test data inside the calls
 *
 */
public class DataBuilder {

	@SuppressWarnings("unchecked")
	public static JSONObject buildTodo() {
		
		JSONObject bodyBuilder = new JSONObject();
		Faker faker =  new Faker();
		
		bodyBuilder.put("title", faker.book().title());
		bodyBuilder.put("body", faker.address().fullAddress());
		
		return bodyBuilder;
		
	}

	
	
}
