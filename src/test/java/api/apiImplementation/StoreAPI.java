package api.apiImplementation;
import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.json.JSONObject;

import api.payload.Store;
import api.utility.Routes;
import api.utility.SpecBuilder;
import io.restassured.response.Response;

public class StoreAPI {
	
	public static Store placePetOrder(Store storeObj) throws IOException
	{
		Response response=given()
				.spec(SpecBuilder.getRequestSpecifications())
				.body(storeObj)
				.when()
				.post(Routes.placePetOrder)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
		if(response.getStatusCode()!=200)
			throw new RuntimeException(response.asString());
		return response.as(Store.class);
	}
	
	public static JSONObject getInventoryDetails() throws IOException
	{
		Response response=given()
				.spec(SpecBuilder.getRequestSpecifications())
				.when()
				.get(Routes.getInventoryDetails)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
		if(response.getStatusCode()!=200)
			throw new RuntimeException(response.asString());
		JSONObject jsonObject=new JSONObject(response.asString());
		return jsonObject;
	}
	
	public static Response purchaseOrderById(int orderId) throws IOException
	{
		Response response=given()
				.spec(SpecBuilder.getRequestSpecifications())
				.pathParam("orderId", orderId)
				.when()
				.get(Routes.purchaseOrderById)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
		return response;
	}
	
	public static Response deletePurchaseOrderById(int orderId) throws IOException
	{
		return  given()
				.spec(SpecBuilder.getRequestSpecifications())
				.pathParam("orderId", orderId)
				.when()
				.delete(Routes.deletePurchaseOrderById)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}

}
