package api.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.apiImplementation.UserAPI;
import api.payload.User;
import api.utility.ReadProperty;
import api.utility.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userPayload;

	@BeforeClass
	public void setUpData() {
		faker = new Faker();
		userPayload = new User();

		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());

	}

	@Test(priority = 1)
	public void testPostUser() {
		Response response = UserAPI.createUser(userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 2)
	public void testGetUser() {
		Response respones = UserAPI.getUser(this.userPayload.getUsername());
		respones.then().log().all();
		Assert.assertEquals(respones.getStatusCode(), 200);

	}

	@Test(priority = 3)
	public void testUpdateUser() {
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		Response response = UserAPI.updateUser(this.userPayload.getUsername(), this.userPayload);
		response.then().log().all();

		Assert.assertEquals(response.getStatusCode(), 200);

	}

	@Test(priority = 4)
	public void testDeleteUser() {
		Response respones = UserAPI.deleteUser(this.userPayload.getUsername());
		respones.then().log().all();
		Assert.assertEquals(respones.getStatusCode(), 200);
	}

	@Test(priority = 5, description = "Create user with List of users")
	public void createUserWithListOfUserObj() throws IOException {
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setId(faker.idNumber().hashCode());
		user1.setUsername(faker.name().username());
		user1.setFirstName(faker.name().firstName());
		user1.setLastName(faker.name().lastName());
		user1.setEmail(faker.internet().safeEmailAddress());
		user1.setPassword(faker.internet().password(5, 10));
		user1.setPhone(faker.phoneNumber().cellPhone());
		users.add(user1);
		Response response=UserAPI.createUserWithList(users);
		Assert.assertEquals(response.getStatusCode(), Status.CODE_200.statusCode);
		JsonPath js=new JsonPath(response.asString());
		Assert.assertEquals(js.getString("message"), "ok");
	}
	
	@Test(priority = 6, description = "Create user with Array of users")
	public void createUserWithArrayOfUserObj() throws IOException {
		List<User> users = new ArrayList<User>();
		User user1 = new User();
		user1.setId(faker.idNumber().hashCode());
		user1.setUsername(faker.name().username());
		user1.setFirstName(faker.name().firstName());
		user1.setLastName(faker.name().lastName());
		user1.setEmail(faker.internet().safeEmailAddress());
		user1.setPassword(faker.internet().password(5, 10));
		user1.setPhone(faker.phoneNumber().cellPhone());
		users.add(user1);
		Response response=UserAPI.createUserWithArray(users);
		Assert.assertEquals(response.getStatusCode(), Status.CODE_200.statusCode);
		JsonPath js=new JsonPath(response.asString());
		Assert.assertEquals(js.getString("message"), "ok");
	}
	
	@Test(priority=7, description="Get user logins to the system")
	public void verifyUserLoginIntoSyatem() throws IOException
	{
		Response res=UserAPI.loginUserIntoSystem(ReadProperty.getPropertDetails("username"), ReadProperty.getPropertDetails("password"));
		Assert.assertEquals(res.getStatusCode(), Status.CODE_200.statusCode);
		JSONObject jsonObject=new JSONObject(res.asString());
		Assert.assertTrue(jsonObject.getString("message").contains("logged in user session:"));
	}
	
	@Test(priority=8,description="Do user logout action")
	public void doUserLogout() throws IOException
	{
		Response res=UserAPI.userLogOut();
		Assert.assertEquals(res.getStatusCode(), Status.CODE_200.statusCode);
		JSONObject jsonObject=new JSONObject(res.asString());
		Assert.assertEquals(jsonObject.getString("message"), "ok");
	}

}
