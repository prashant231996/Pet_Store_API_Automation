package api.endpoints;

import static io.restassured.RestAssured.*;

import api.payload.Pet;
import api.utility.Routes;
import api.utility.SpecBuilder;
import io.restassured.response.Response;

public class PetAPI {
	
	
	public static Pet addPet(Pet petObject)
	{
	    Response response=given()
	    		.spec(SpecBuilder.getRequestSpecifications())
	    		.body(petObject)
	    		.when()
	    		.post(Routes.addPet)
	    		.then()
	    		.spec(SpecBuilder.getResponseSpecifications()).extract().response();
	    if(response.getStatusCode()!=200)
	    	throw new RuntimeException();
	    return response.as(Pet.class);
	}
	
}
