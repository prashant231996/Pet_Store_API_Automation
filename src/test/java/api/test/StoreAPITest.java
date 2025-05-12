package api.test;

import java.io.IOException;
import java.util.Date;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.apiImplementation.StoreAPI;
import api.payload.Store;
import api.utility.Status;
import io.restassured.response.Response;

public class StoreAPITest {

	Store storeObj = new Store();

	@BeforeClass
	public void setUp() {
		storeObj.setId(13);
		storeObj.setPetId(13);
		storeObj.setQuantity(13);
		storeObj.setShipDate("2025-05-11T13:27:29.576Z");
		storeObj.setStatus("placed");
		storeObj.setComplete(true);
	}

	@Test(description = "Place pet orders", priority = 0, groups = { "Sanity" })
	public void placePetOrder() throws IOException {
		Store responseObj = StoreAPI.placePetOrder(storeObj);
		Assert.assertEquals(storeObj.getPetId(), responseObj.getPetId());
		Assert.assertTrue(responseObj.isComplete());
	}

	@Test(description = "Get Inventory details", priority = 1, groups = { "sanity" })
	public void getInventoryDetails() throws IOException {
		JSONObject jsonObject = StoreAPI.getInventoryDetails();
		Assert.assertTrue(jsonObject.getInt("sold") > 0);
	}

	@Test(description = "Purchase order by id", priority = 2, dependsOnMethods = { "placePetOrder" }, groups = {
			"Sanity" })
	public void purchaseOrderById() throws IOException {
		Response response = StoreAPI.purchaseOrderById(storeObj.getId());
		System.out.println("Response is " + response.asString());
		if (response.getStatusCode() == 200) {
			JSONObject jsonObject = new JSONObject(response.asString());
			Assert.assertEquals(jsonObject.getInt("id"), storeObj.getId());
		} else {
			JSONObject jsonObject = new JSONObject(response.asString());
			Assert.assertEquals(jsonObject.getInt("code"), Status.CODE_400.statusCode);
			Assert.assertEquals(jsonObject.get("message"), "Order not found");
		}
	}

	@Test(description = "delete purchase order by id", priority = 3, dependsOnMethods = { "placePetOrder" }, groups = {
			"Sanity" })
	public void deletePurchaseOrderById() throws IOException {
		Response response = StoreAPI.deletePurchaseOrderById(storeObj.getId());
		if (response.getStatusCode() == 200) {
			JSONObject jsonObject = new JSONObject(response.asString());
			Assert.assertEquals(jsonObject.getInt("code"), Status.CODE_200.statusCode);
			Assert.assertEquals(jsonObject.getString("message"), String.valueOf(storeObj.getId()));
		} else {
			JSONObject jsonObject = new JSONObject(response.asString());
			Assert.assertEquals(jsonObject.getInt("code"), Status.CODE_400.statusCode);
			Assert.assertEquals(jsonObject.getString("message"), "Order not found");
		}
	}

}
