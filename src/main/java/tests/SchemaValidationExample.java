package tests;

import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationExample {
	@Test
	public void validateSchema() {
		
		Response result = given().
				get("https://keytrcrud.herokuapp.com/api/users/6555c5e8c37a15001340b04a").
				then().extract().response();
		
		System.out.println(result.asPrettyString());
		assertThat(result.asString(), matchesJsonSchemaInClasspath("schema.json"));
	}

}
