package api.test;

import java.util.ArrayList;
import java.util.List;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PetAPI;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tag;

public class PetAPITest {
	
    Pet petObj=new Pet();
	Pet responseObj=new Pet();
	Category category=new Category();
	Tag tag=new Tag();
	List<Tag> tags=new ArrayList<Tag>();
	List<Object>photoUrls=new ArrayList<Object>();
	
	
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
	
	
	@Test
	public void addPet()
	{
		responseObj=PetAPI.addPet(petObj);
		Assert.assertEquals(responseObj.getName(), petObj.getName());
	}

}
