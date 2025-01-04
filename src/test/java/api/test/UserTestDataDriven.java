package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.datprovider.TestDataDatProvider;
import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTestDataDriven {
	
	Faker faker;
	public User userPayload=new User();
//	
//	@BeforeClass
//	public void setUp()
//	{
//		faker=new Faker();
//	    userPayload=new User();
//		
//	}
	
	@Test(priority=1,dataProvider = "TestData",dataProviderClass = TestDataDatProvider.class)
	public void testPostUser(String UserID,String UserName, String FirstName,String LastName,String Email, String password, String Phone)
	{
	  userPayload.setId(Integer.parseInt(UserID));
	  userPayload.setUsername(UserName);
	  userPayload.setFirstName(FirstName);
	  userPayload.setLastName(LastName);
	  userPayload.setEmail(Email);
	  userPayload.setPassword(password);
	  userPayload.setPhone(Phone);
	  
	  Response response= UserEndPoints.createUser(userPayload);
	  response.then().log().all();
	  
	  Assert.assertEquals(response.getStatusCode(), 200);
	  
	}
	
//	@Test(priority=2)
//	public void testGetUser()
//	{
//		Response respones=UserEndPoints.getUser(this.userPayload.getUsername());
//		respones.then().log().all();
//		Assert.assertEquals(respones.getStatusCode(), 200);
//		
//	}
//	
//	@Test(priority=3)
//	public void testUpdateUser()
//	{
//		userPayload.setFirstName(faker.name().firstName());
//	    userPayload.setLastName(faker.name().lastName());
//	    userPayload.setEmail(faker.internet().safeEmailAddress());
//	  Response response= UserEndPoints.updateUser(this.userPayload.getUsername(), this.userPayload);
//	  response.then().log().all();
//	  
//	  Assert.assertEquals(response.getStatusCode(), 200);
//	  
//	}
//	
//	@Test(priority=4)
//	public void testDeleteUser()
//	{
//		Response respones=UserEndPoints.deleteUser(this.userPayload.getUsername());
//		respones.then().log().all();
//		Assert.assertEquals(respones.getStatusCode(), 200);
//		
//	}
//	

}
