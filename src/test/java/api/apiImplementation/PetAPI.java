package api.apiImplementation;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.JSONObject;

import api.payload.Pet;
import api.utility.ReadProperty;
import api.utility.Routes;
import api.utility.SpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
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
	
	public static Pet getPetDetails(int petId) throws IOException
	{
		Response response=given()
				.spec(SpecBuilder.getRequestSpecifications())
				.pathParam("petId", petId)
				.when()
				.get(Routes.getPetDetails)
				.then().spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
		if(response.getStatusCode()!=200)
		{
			throw new RuntimeException(response.asString());
		}
		return response.as(Pet.class);
	}
	
	public static Pet updatePetDetails(Pet petObj) throws IOException
	{
		Response response=given().spec(SpecBuilder.getRequestSpecifications())
				.body(petObj)
				.when()
				.put(Routes.updatePetDetails)
				.then().extract().response();
		if(response.getStatusCode()!=200)
		{
			throw new RuntimeException(response.asString());
		}
		return response.as(Pet.class);
	}
	
	public static List<Object> getPetByStatus(String status) throws IOException
	{
		Response response=given()
				.spec(SpecBuilder.getRequestSpecifications())
				.queryParam("status", status)
				.when()
				.get(Routes.findByStatus)
				.then().extract().response();
		if(response.getStatusCode()!=200)
		{
			throw new RuntimeException(response.asString());
		}
		return response.as(List.class);
	}
	
	public static Response updatePetDetailsByPetId(int petId,HashMap<String,Object>hmap) throws IOException
	{
		return given()
				.baseUri(ReadProperty.getPropertDetails("baseUrl"))
				.basePath(ReadProperty.getPropertDetails("basePath"))
				.header("api_key", ReadProperty.getPropertDetails("apiKeyValue"))
				.pathParam("petId", petId)
				.formParams(hmap)
				.contentType(ContentType.URLENC)
				.when()
				.post(Routes.updatePetDetailsByid)
				.then()
				.spec(SpecBuilder.getResponseSpecifications())
				.extract().response();
	}
	
	public static Response deletPet(int petId) throws IOException
	{
		return given()
		.spec(SpecBuilder.getRequestSpecifications())
		.pathParam("petId", petId)
		.when()
		.delete(Routes.deletePet)
		.then()
		.spec(SpecBuilder.getResponseSpecifications())
		.extract().response();
	}
	
	
	
}
