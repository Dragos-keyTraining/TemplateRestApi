package tests;

import org.testng.annotations.Test;


import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;


public class BaseComponetExample extends BaseComponent{
	
	String id;
	
	@Test(priority = 1)
	public void postANewTodo() {		
		
		Response resp = doPostRequest("/api/save", DataBuilder.buildTodo().toJSONString());
		id = resp.jsonPath().getString("id");
		String succesMsg = resp.jsonPath().getString("info");
		assertThat(succesMsg, is(equalTo("Todo saved! Nice job!")));
		System.out.println(resp.asPrettyString());
	}
	
	
	@Test(priority = 2)
	public void updateATodo() {
		Response resp =  doPutRequest("/api/todos/"+id, DataBuilder.buildTodo().toJSONString());
		System.out.println(resp.asPrettyString());
		assertThat(resp.jsonPath().getString("msg"), is(equalTo("Item updated")));
	}
	
	@Test(priority = 3)
	public void getATodo() {
		
		Response resp = doGetRequest("/api/"+id);
		assertThat(id, is(equalTo(resp.jsonPath().getString("_id"))) );
	}
	
	@Test(priority = 4)
	public void deleteTodo() {
		
		Response resp = doDeleteRequest("/api/delete/"+id);
		assertThat(resp.jsonPath().getString("msg"), is(equalTo("Event deleted.")) );

	}
	

}
