package tests;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import org.hamcrest.core.Is;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent;
import utils.DataBuilder;
import static org.hamcrest.Matchers.*;

public class AnoherBaseComponentExample extends BaseComponent{
	String id;
	String email;
	
	@Test
	public void createANewUser() {
		Response resp =  doPostRequest("api/users/", DataBuilder.buildUser().toJSONString(), 201);
		System.out.println(resp.asPrettyString());
		System.out.println(resp.jsonPath().getString("msg"));
		email = resp.jsonPath().getString("result.email");
		id= resp.jsonPath().getString("result._id");
		assertThat(resp.jsonPath().getString("success"), is(not(equalTo("false"))));
	}
	
	@Test
	public void readUser() {
		Response resp = doGetRequest("api/users/"+id, 200);
		assertThat(resp.jsonPath().getString("result.email"), is(email));
		assertThat(resp.jsonPath().getString("result.email"), equalTo(email));
		assertThat(resp.jsonPath().getString("result.email"), is(equalTo(email)));
		assertEquals(resp.jsonPath().getString("result.email"), email);
	}

}
