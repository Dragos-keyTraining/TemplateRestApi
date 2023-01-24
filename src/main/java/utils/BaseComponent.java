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

import static io.restassured.RestAssured.given;

public class BaseComponent {
	
	public static RequestSpecification requestSpec;
	public static ResponseSpecification responseSpec;
	
	@Parameters({"baseURL"})
	@BeforeClass
	public static void createRequestSpecification(String baseUrl) {
		
		requestSpec =  new RequestSpecBuilder()
				.setBaseUri(baseUrl)
				.setBasePath("api/")
				.setContentType(ContentType.JSON)
				.addHeader("accept", "application/json")
				.build();
	}
	
	@BeforeClass
	public static void createResponseSpecification() {
		
		responseSpec =  new ResponseSpecBuilder()
				.expectStatusCode(anyOf(is(200),is(201),is(204)))
				.build();	
	}
	
public static Response doPostRequest(String path,String todo) {
		
		Response result = 
				given().
					spec(requestSpec).
					body(todo).
				when().
					post().
				then().
					spec(responseSpec).
					extract().response();		
		return result;
		
	}
	
	public static Response doPutRequest(String path, String body) {
		
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
	
	
	public static Response doGetRequest(String path) {
		
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
	
	public static Response doGetAllRequest() {
		
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
	
	
	public static Response doDeleteRequest(String path) {
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
