package utils;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class BaseComponent {
	
	@BeforeClass
	public void setup() {
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		//RestAssured.baseURI = "https://keytrcrud.herokuapp.com/";

	}
	
	public static Response doPostRequest(String path, String body, int statusCode) {
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					post(path).
				then().
					statusCode(statusCode).
					extract().response();	
		return result;
	}
	
	public static Response doPutRequest(String path, String body, int statusCode) {
		Response result = 
				given().
					contentType(ContentType.JSON).
					body(body).
				when().
					put(path).
				then().
				    statusCode(statusCode).
				    extract().response();	
		return result;
	}
	
	public static Response doGetRequest(String path, int statusCode) {
		Response result =  
				given().
					contentType(ContentType.JSON).
					header("Content-Type", "application/json").
				when().
					get(path).
				then().
					statusCode(statusCode).
					extract().response();			
		return result;
	}
	
	public static Response doDeleteRequest(String path, int statusCode) {
		Response result = 
				given().
					contentType(ContentType.JSON).
				when().
					delete(path).
				then().
					statusCode(statusCode).
					extract().response();
		return result;		
	}

}
