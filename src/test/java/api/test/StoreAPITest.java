package api.test;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.apiImplementation.StoreAPI;
import api.payload.Store;

public class StoreAPITest {
	
	Store storeObj=new Store();
	
	@BeforeClass
	public void setUp()
	{
		storeObj.setId(13);
		storeObj.setPetId(13);
		storeObj.setQuantity(13);
		storeObj.setShipDate("2025-05-11T13:27:29.576Z");
		storeObj.setStatus("placed");
		storeObj.setComplete(true);
	}
	
	@Test(description="Place pet orders", priority=0,groups= {"Sanity"})
	public void placePetOrder() throws IOException
	{
		Store responseObj=StoreAPI.placePetOrder(storeObj);
		Assert.assertEquals(storeObj.getPetId(), responseObj.getPetId());
		Assert.assertTrue(responseObj.isComplete());
	}
	
	@Test(description="Get Inventory details", priority=1, groups= {"sanity"})
	public void getInventoryDetails() throws IOException
	{
		JSONObject jsonObject=StoreAPI.getInventoryDetails();
		Assert.assertTrue(jsonObject.getInt("sold")>0);
	}

}
