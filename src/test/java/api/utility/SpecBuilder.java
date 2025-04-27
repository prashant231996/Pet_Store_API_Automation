package api.utility;

import java.io.IOException;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	public static RequestSpecification getRequestSpecifications() throws IOException
	{
		return new RequestSpecBuilder()
				.setBaseUri(ReadProperty.getPropertDetails("baseUrl"))
				.setBasePath(ReadProperty.getPropertDetails("basePath"))
				.addHeader("api_key", ReadProperty.getPropertDetails("apiKeyValue"))
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	public static ResponseSpecification getResponseSpecifications()
	{
		return new ResponseSpecBuilder()
				.log(LogDetail.ALL).build();
	}

}
