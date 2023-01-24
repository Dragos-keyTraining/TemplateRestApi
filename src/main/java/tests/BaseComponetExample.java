package tests;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;


public class BaseComponetExample extends BaseComponent{
	
	String id;
	
	@Test(priority = 1)
	public void postANewTodo() {		
		
		Response resp = doPostRequest("save", DataBuilder.buildTodo().toJSONString());
		id = resp.jsonPath().getString("id");
		String succesMsg = resp.jsonPath().getString("info");
		assertThat(succesMsg, is(equalTo("Todo saved! Nice job!")));
		System.out.println(resp.asPrettyString());
	}
	
	
	@Test(priority = 2)
	public void updateATodo() {
		Response resp =  doPutRequest("todos/"+id, DataBuilder.buildTodo().toJSONString());
		System.out.println(resp.asPrettyString());
	}
	
	@Test(priority = 3)
	public void getATodo() {
		
		Response resp = doGetRequest(id);
		assertThat(id, is(equalTo(resp.jsonPath().getString("_id"))) );
	}
	
	@Test(priority = 4)
	public void deleteTodo() {
		
		Response resp = doDeleteRequest("delete/"+id);
		assertThat(resp.jsonPath().getString("msg"), is(equalTo("Event deleted.")) );

	}
	

}
