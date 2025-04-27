package api.apiImplementation;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;

import api.payload.Pet;
import api.utility.ReadProperty;
import api.utility.Routes;
import api.utility.SpecBuilder;
import io.restassured.response.Response;

public class PetAPI {
	
	
	public static Pet addPet(Pet petObject) throws IOException
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
	
	public static Response uploadPetImage(int petId,File petImageFile) throws IOException
	{
		Response response=null;
		try {
			response = given()
					.baseUri(ReadProperty.getPropertDetails("baseUrl"))
					.basePath(ReadProperty.getPropertDetails("basePath"))
					.header("api_key", ReadProperty.getPropertDetails("apiKeyValue"))
					.pathParam("petId", petId)
					.contentType("multipart/form-data")
					.multiPart("file",petImageFile)
					.when()
					.post(Routes.uploadPetImage)
					.then().spec(SpecBuilder.getResponseSpecifications())
					.extract().response();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(response.getStatusCode()!=200)
			throw new RuntimeException();
		return response;
	}
	
	
	
}
