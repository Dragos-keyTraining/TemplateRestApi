package utils;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseComponent {
	
	public  RequestSpecification requestSpec;
	public  ResponseSpecification responseSpec;
	
	/**
	 * Pre and post condition method for the HTTP calls.
	 * Sets generic configuration to be run before and after each call
	 * @param baseUrl (provided from testng.xml)
	 */
	@Parameters({"baseUrl"})
	@BeforeClass
	public  void createRequestSpecification(String baseUrl) {
		
		requestSpec =  new RequestSpecBuilder()
				.setBaseUri(baseUrl)
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
		
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201),is(204)))
				.build();	
	}

	/**
	 * Generic method that uses POST as http method
	 * Enhanced with rquest and response specification
	 * @param path (specific concatenation that needs to be done over the baseURI and basePath)
	 * @param body (body as String object)
	 * @return the call response 
	 */
	public Response doPostRequest(String path,String todo) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(todo).
				when().
					post(path).
				then().
					spec(responseSpec).
					extract().response();		
		return result;
		
	}
	/**
	 * Generic method that uses PUT as http method
	 * Enhanced with rquest and response specification
	 * @param path (specific concatenation that needs to be done over the baseURI and basePath)
	 * @param body (body as String object)
	 * @return the call response 
	 */
	public Response doPutRequest(String path, String body) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(body).
				when().
					put(path).
				then().
					spec(responseSpec).
					extract().response();		
		return result;
		
	}
	
	/**
	 * Generic method that uses GET as http method
	 * @param path (specific concatenation that needs to be done over the baseURI and basePath)
	 * @return the call response 
	 */
	public Response doGetRequest(String path) {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get(path).
				then().
					spec(responseSpec).
					extract().response();		
		return result;
	}
	
	/**
	 * Generic method that uses GET as http method with no specified path
	 * @return the call response 
	 */
	public Response doGetAllRequest() {
		
		Response result = 
				given().
					spec(requestSpec).
				when().
					get().
				then().
					spec(responseSpec).
					extract().response();		
		return result;
	}
	
	/**
	 * Generic method that uses DELETE as http method
	 * @param path (specific concatenation that needs to be done over the baseURI and basePath)
	 * @return the call response 
	 */
	public Response doDeleteRequest(String path) {
		Response result = 
				given().
					spec(requestSpec).
				when().
					delete(path).
				then().
					spec(responseSpec).
					extract().response();		
		return result;
	}
	

}
