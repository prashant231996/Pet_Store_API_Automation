package api.apiImplementation;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.payload.User;
import api.utility.Routes;
import api.utility.SpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


//Created for performing CRUD Operations, CREATE, READ, UPDATE, DELETE
public class UserAPI {

	
	public static Response createUser(User userPayload)
	{
		Response response=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(userPayload)
		.when()
		  .post(Routes.post_url);
		
		return  response;
		
	}
	
	public static Response getUser(String userName)
	{
		Response response=given()
		   .pathParam("username", userName)
		.when()
		  .get(Routes.get_url);
		
		return  response;
		
	}
	
	public static Response updateUser(String userName,User userPayload)
	{
		Response response=given()
		  .contentType(ContentType.JSON)
		  .accept(ContentType.JSON)
		  .body(userPayload)
		  .pathParam("username", userName)
		.when()
		  .put(Routes.update_url);
		
		return  response;
		
	}
	
	public static Response deleteUser(String userName)
	{
		Response response=given()
		   .pathParam("username", userName)
		.when()
		  .delete(Routes.delete_url);
		
		return  response;	
	}
	
	public static Response createUserWithList(List<User>users) throws IOException
	{
		return given()
				.spec(SpecBuilder.getRequestSpecifications())
				.body(users)
				.when()
				.post(Routes.createUserWithList)
				.then()
				.spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}
	
	public static Response createUserWithArray(List<User>users) throws IOException
	{
		return given()
				.spec(SpecBuilder.getRequestSpecifications())
				.body(users)
				.when()
				.post(Routes.createUserWithArray)
				.then()
				.spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}
	
	public static Response loginUserIntoSystem(String username,String password) throws IOException
	{
		Map<String,String>credMap=new HashMap<String,String>();
		credMap.put("username", username);
		credMap.put("password", password);
		return given()
				.spec(SpecBuilder.getRequestSpecifications())
				.queryParams(credMap)
				.when()
				.get(Routes.loginUserIntoSystem)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}
	
	public static Response userLogOut() throws IOException
	{
		return given()
				.spec(SpecBuilder.getRequestSpecifications())
				.when()
				.get(Routes.userLogout)
				.then()
				.spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}
	
	
}
