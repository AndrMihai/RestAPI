package tests;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import utils.BaseComponent3;
import utils.DataBuilder;

public class BaseComponent3Test extends BaseComponent3{
	String id;
	@Test(priority=1)
	public void createUser() {
		System.out.println("--------POST--------");
		Response resp = doRequest("post", "", DataBuilder.buildUser().toJSONString());
		id = resp.jsonPath().getString("result._id");
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=2)
	public void readUser() {
		Response resp = doRequest("GET",id,"");
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=3)
	public void readALLUser() {
		Response resp = doRequest("GET_ALL",id,"");
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=4)
	public void updateUser() {
		Response resp = doRequest("PUT", id, DataBuilder.buildUser().toJSONString());
		System.out.println(resp.asPrettyString());
	}
	@Test(priority=5)
	public void deleteUser() {
		Response resp = doRequest("Delete", id,"");
		System.out.println(resp.asPrettyString());
	}
}
