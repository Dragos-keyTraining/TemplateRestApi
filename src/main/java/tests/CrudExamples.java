package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.json.simple.JSONObject;

public class CrudExamples {
	
	String id;
	JSONObject body;
	
	@BeforeClass
	public void setup() {
		
		RestAssured.baseURI = "https://keytodorestapi.herokuapp.com/";
		body = new JSONObject();
		
		Faker faker =  new Faker();
		body.put("title", faker.lordOfTheRings().character());
		body.put("body", faker.chuckNorris().fact());
		
	}
	
	@Test(priority = 1)
	public void createTodo() {		
		Response resp = 
				given().
					contentType(ContentType.JSON).
					body(body.toJSONString()).
					post("api/save").
				then()
					.statusCode(200)
					.body("info", is(equalTo("Todo saved! Nice job!")))
					.body("id",is(anything()))
					.log().all()
					.extract().response();	
		
		id= resp.jsonPath().getString("id");
	}
	
	@Test(priority =2)
	public void getAllTodos() {
		Response resp = 
				given().
					contentType(ContentType.JSON).
					get("api").
				then().
					statusCode(200).
					extract().response();
	System.out.println("----------------------");	
	System.out.println(resp.asPrettyString());
	System.out.println("----------------------");	
	System.out.println(resp.asString());

	}
	
	@Test(priority = 3)
	public void getSingleTodo() {
		Response resp = 
				given().
					contentType(ContentType.JSON).
					get("api/"+id).
				then().
					statusCode(200).
					extract().
					response();		
		System.out.println(resp.asPrettyString());
		assertEquals(id, resp.jsonPath().getString("_id"));			
		assertThat(id, is(equalTo(resp.jsonPath().getString("_id"))) );
	}
	
	@Test(priority = 4)
	public void updateTodo() {
		JSONObject body =  new JSONObject();
		body.put("title", "updatedTitle");
		body.put("body", "updatedBody");
		
		Response resp = 
				given().
					contentType(ContentType.JSON).
					body(body.toJSONString()).
					put("api/todos/"+id).
				then().
					statusCode(201).
					extract().response();
		System.out.println("---- Inside update method");		
		System.out.println(resp.asPrettyString());		
	}
	
	@Test(priority = 5)
	public void deleteTodo() {
		System.out.println("---- Inside delete method");		

		given().
			contentType(ContentType.JSON).
		when().
			delete("api/delete/"+id).
		then().
			statusCode(200).
			body("msg", is(equalTo("Event deleted."))).
			log().all();
		
	//assertThat("Event deleted.", is(equalTo(resp.jsonPath().getString("msg"))) );

		
	}

}
