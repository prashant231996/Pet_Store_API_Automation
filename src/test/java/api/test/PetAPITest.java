package api.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;

import api.apiImplementation.PetAPI;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import api.utility.Status;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetAPITest {
	
    Pet petObj=new Pet();
	Pet responsePetObj=new Pet();
	Category category=new Category();
	Tag tag=new Tag();
	List<Tag> tags=new ArrayList<Tag>();
	List<Object>photoUrls=new ArrayList<Object>();
	static int petId;
	
	
	@BeforeClass
	public void setUp()
	{
		tag.setId(11);
		tag.setName("Prashant Pet");
		tags.add(tag);
		category.setId(12);
		category.setName("Cat");
		petObj.setId(13);
		petObj.setName("Mini Mau");
		petObj.setStatus("ALive");
		petObj.setCategory(category);
		petObj.setTags(tags);
		petObj.setPhotoUrls(photoUrls);
	}
	
	
	@Test(description="Adding new pet in pet store",priority=0,groups= {"Regression","Smoke"})
	public void addPet() throws IOException
	{
		responsePetObj=PetAPI.addPet(petObj);
		Assert.assertEquals(responsePetObj.getName(), petObj.getName());
		petId=responsePetObj.getId();
	}
	
	@Test(description="Upload pet image",priority=1,dependsOnMethods = "addPet",groups= {"Regression","Smoke"})
	public void uploadPetImage() throws IOException
	{
		Response response=PetAPI.uploadPetImage(petId, new File("./testData/CAT_IMAGE.jpg"));
		//Verifying response message contains uploaded image name
		JsonPath jsonPath=new JsonPath(response.asString());
		Assert.assertTrue(jsonPath.get("message").toString().contains("CAT_IMAGE.jpg"));
	}
	
	@Test(description="Get pet details",priority=2,dependsOnMethods = "addPet", groups= {"Regression","Smoke"})
	public void getPetDetails() throws IOException
	{
		 Pet responsePetObj=PetAPI.getPetDetails(petId);
		 Assert.assertEquals(responsePetObj.getName(), petObj.getName());
	}
	
	@Test(description = "Update Pet details",priority=3,dependsOnMethods = "addPet", groups = {"Regression","Smoke","Sanity"})
	public void updatePetDetails() throws IOException
	{
		responsePetObj.setName("Updated Pet Name");
		responsePetObj=PetAPI.updatePetDetails(responsePetObj);
		Assert.assertEquals(responsePetObj.getName(), "Updated Pet Name");	
	}
	
	@Test(description="Get pet details by it's status",priority=4,dependsOnMethods = "addPet", groups= {"Regression","Smoke"})
	public void getPetDetailsByStatus() throws IOException
	{
		List<Object>responseLis=PetAPI.getPetByStatus("available");
		Map<String,Object>pet1=(Map<String,Object>)responseLis.get(0);
		Assert.assertEquals(pet1.get("status"), "available");
	}
	
	@Test(description="Update pet details by pet id",priority=5,dependsOnMethods = "addPet", groups= {"Regression","Smoke"})
	public void updatePetDetailsByPetId() throws IOException
	{
		HashMap<String, Object>hmap=new HashMap<String, Object>();
		hmap.put("name", "MyPetName");
		hmap.put("status", "available");
		Response response=PetAPI.updatePetDetailsByPetId(petId, hmap);
		Assert.assertEquals(response.getStatusCode(), Status.CODE_200.statusCode);
		JsonPath jsonPath=new JsonPath(response.asString());
		Assert.assertEquals(jsonPath.getInt("message"), petId);
	}
	
	@Test(description="Delete Pet Details",priority=6,dependsOnMethods = {"addPet"},groups= {"Smoke"})
	public void deletePetDetail() throws IOException
	{
		Response response=PetAPI.deletPet(petId);
		Assert.assertEquals(response.getStatusCode(), Status.CODE_200.statusCode);
		JsonPath jsonPath=new JsonPath(response.asString());
		Assert.assertEquals(jsonPath.getInt("message"), petId);
	}

}
