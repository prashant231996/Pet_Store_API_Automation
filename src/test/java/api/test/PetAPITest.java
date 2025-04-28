package api.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.apiImplementation.PetAPI;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PetAPITest {
	
    Pet petObj=new Pet();
	Pet responseObj=new Pet();
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
		responseObj=PetAPI.addPet(petObj);
		Assert.assertEquals(responseObj.getName(), petObj.getName());
		petId=responseObj.getId();
	}
	
	@Test(description="Upload pet image",priority=1,dependsOnMethods = "addPet",groups= {"Regression","Smoke"})
	public void uploadPetImage() throws IOException
	{
		Response response=PetAPI.uploadPetImage(petId, new File("./testData/CAT_IMAGE.jpg"));
		//Verifying response message contains uploaded image name
		JsonPath jsonPath=new JsonPath(response.asString());
		Assert.assertTrue(jsonPath.get("message").toString().contains("CAT_IMAGE.jpg"));
	}
	
	@Test(description="Get pet details",priority=3,dependsOnMethods = "addPet", groups= {"Regression","Smoke"})
	public void getPetDetails() throws IOException
	{
		 responseObj=PetAPI.getPetDetails(petId);
		 Assert.assertEquals(responseObj.getName(), petObj.getName());
	}

}
