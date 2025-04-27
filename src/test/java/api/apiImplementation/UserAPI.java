package api.apiImplementation;

import static io.restassured.RestAssured.given;

import api.payload.User;
import api.utility.Routes;
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
	
	
}
