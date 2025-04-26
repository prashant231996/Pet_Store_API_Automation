package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserAPI;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	
	Faker faker;
	User userPayload;
	
	@BeforeClass
	public void setUpData()
	{
		faker=new Faker();
	    userPayload=new User();
	    
	    userPayload.setId(faker.idNumber().hashCode());
	    userPayload.setUsername(faker.name().username());
	    userPayload.setFirstName(faker.name().firstName());
	    userPayload.setLastName(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	    userPayload.setPassword(faker.internet().password(5,10));
	    userPayload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority=1)
	public void testPostUser()
	{
	  Response response= UserAPI.createUser(userPayload);
	  response.then().log().all();
	  
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	}
	
	@Test(priority=2)
	public void testGetUser()
	{
		Response respones=UserAPI.getUser(this.userPayload.getUsername());
		respones.then().log().all();
		Assert.assertEquals(respones.getStatusCode(), 200);
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		userPayload.setFirstName(faker.name().firstName());
	    userPayload.setLastName(faker.name().lastName());
	    userPayload.setEmail(faker.internet().safeEmailAddress());
	  Response response= UserAPI.updateUser(this.userPayload.getUsername(), this.userPayload);
	  response.then().log().all();
	  
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	}
	
	@Test(priority=4)
	public void testDeleteUser()
	{
		Response respones=UserAPI.deleteUser(this.userPayload.getUsername());
		respones.then().log().all();
		Assert.assertEquals(respones.getStatusCode(), 200);
		
	}
	

}
