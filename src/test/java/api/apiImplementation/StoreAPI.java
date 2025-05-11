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

}
