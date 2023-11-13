package tests;


import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static utils.NumberChecker.*;
import static utils.NumberIsPositive.*;

public class HamcrestAssertionExample {
	
	@Test
	public void hamcrestMatchers() {
		
		Response resp = 
				given().
					get("https://swapi.dev/api/planets/1/").
				then().
					extract().
					response();
		System.out.println(resp.asString());
		
		JsonPath jsnPath = resp.jsonPath();
		String name = jsnPath.getString("name");
		System.out.println(name);
		
		//testng assert
		assertEquals(name, "Tatooine");
		
		//Hamcrest assert
		assertThat(name, equalTo("Tatooine"));
		assertThat(name, is("Tatooine"));
		assertThat(name, is(equalTo("Tatooine")));
		
		//testNg assert
		assertNotEquals(name, "Terra");
		//Hamcrest assert
		assertThat(name, is(not("Terra")));
		assertThat(name, is(not(equalTo("Terra"))));
		assertThat(name, is(not(instanceOf(Integer.class))));
		assertThat(name, is(instanceOf(String.class)));
		//starts-with
		assertThat(resp.asString(), startsWith("{\"name\""));
		assertThat(name, startsWith("Tato"));
		assertThat(resp.asString(), startsWithIgnoringCase("{\"NAME\""));
		//ends-with
		assertThat(resp.asString(), endsWith("api/planets/1/\"}"));
		assertThat(resp.asString(), endsWithIgnoringCase("api/plaNeTs/1/\"}"));
		//contains
		assertThat(name, containsStringIgnoringCase("tooi"));
		assertThat(resp.asString(), containsString("desert"));
		assertThat(name, containsString("tooi"));
		//pattern
		assertThat(name, matchesPattern("[A-Za-z]+"));
		name="Tatooine123";
		assertThat(name, matchesPattern("[A-Za-z0-9]+"));
		String diameter = jsnPath.getString("diameter");
		assertThat(diameter, matchesPattern("[0-9]+"));
		List<String> movies = jsnPath.getList("films");
		System.out.println(movies.get(1));
		assertThat(movies, contains(
				"https://swapi.dev/api/films/1/", 
		        "https://swapi.dev/api/films/3/", 
		        "https://swapi.dev/api/films/4/", 
		        "https://swapi.dev/api/films/5/", 
		        "https://swapi.dev/api/films/6/"));
		String[] array = {jsnPath.getString("climate"), jsnPath.getString("terrain"),jsnPath.getString("diameter"),jsnPath.getString("gravity"),jsnPath.getString("name") };
		System.out.println(array[0]);
		assertThat(array, is(not(emptyArray())));
		assertThat(array, is(not(nullValue())));
		System.out.println(Arrays.toString(array));
		assertThat(array, arrayContainingInAnyOrder(
				"Tatooine",
				"arid", 
				"desert", 
				"10465", 
				"1 standard" ));
		assertThat(array, arrayContaining("arid", "desert", "10465", "1 standard","Tatooine"));
		assertThat(resp.asString(), containsStringIgnoringCase("ARID") );
		assertThat(resp.asString(), stringContainsInOrder("name","rotation_period" ));
		//and
		assertThat(resp.asString(),both(containsString("Tatooine")).and(containsString(diameter)));
		//or
		assertThat(name, either(is("Tatooine")).or(is("Tatooine2")).or(is(not("Tatooine5"))));
		String rotation = jsnPath.getString("rotation_period");
		String climate = jsnPath.getString("climate");
		String gravity = jsnPath.getString("gravity");
		System.out.println(rotation);
		System.out.println(climate);
		System.out.println(gravity);
		assertThat(rotation, is(numbersOnly()));
		assertThat(Integer.parseInt(rotation), is(positiveNumber()));
		
	}}

