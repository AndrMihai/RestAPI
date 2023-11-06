package tests;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import java.util.List;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseComponent;

public class JsonPathExample extends BaseComponent{
	
	
	@Test
	public void jsonPathExamples() {
		
		Response response  = given()
								.get("https://keytrcrud.herokuapp.com/api/users/")
								.then().extract().response();
		System.out.println(response.asString());
		
		JsonPath jsonPath = response.jsonPath();
		// accesez obiect pe baza de index
		System.out.println(jsonPath.getString("[6]"));
		System.out.println(jsonPath.getString("[6].name"));
		System.out.println(jsonPath.getString("[6]._id"));
		System.out.println(jsonPath.getString("[6].age"));
		//accesez direct attribut fara index
		System.out.println(jsonPath.getString("name"));
		System.out.println(jsonPath.getString("gender"));
		System.out.println("-----------------------------------------------------------");
		
		System.out.println(jsonPath.getList("findAll{it.gender == 'm'}.name"));
		List<String> allMales = jsonPath.getList("findAll{it.gender == 'm'}.name");
		System.out.println(allMales);
		List<String> allFemales = jsonPath.getList("findAll{it.gender == 'f'}.name");
		System.out.println(allFemales);
		
		List<String> allElla = jsonPath.getList("findAll{it.name == 'Ella'}.age");
		System.out.println(allElla);
		
		/*
		 *     {
	        "_id": "65428792f7bcea00133e77bb",
	        "name": "Claudia",
	        "email": "abc@claudia.com",
	        "age": 45,
	        "gender": "f",
	        "__v": 0
    },
		 * 
		 * 
		 */
		
		List<String> allOldElla = jsonPath.getList("findAll{it.name == 'Ella' && it.age >=60}");
		System.out.println(allOldElla);

		List<String> allOldEllaIDs = jsonPath.getList("findAll{it.name == 'Ella' && it.age >=60}._id");
		System.out.println(allOldEllaIDs);
		
		List<String> allOldEllaEmail = jsonPath.getList("findAll{it.name == 'Ella' && it.age >=60}.email");
		System.out.println(allOldEllaEmail);

		String oldestElla = jsonPath.getString("find{it.name == 'Ella' && it.age > 80}");
		System.out.println(oldestElla);
		
		List<String> allEllaOrClaudia = jsonPath.getList("findAll{it.name == 'Ella' | it.name == 'Claudia'}");
		System.out.println(allEllaOrClaudia);
		
		
		
	}
	

}
