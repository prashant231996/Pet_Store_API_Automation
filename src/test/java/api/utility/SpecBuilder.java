package api.utility;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	public static RequestSpecification getRequestSpecifications()
	{
		return new RequestSpecBuilder()
				.setBaseUri("https://petstore.swagger.io")
				.setBasePath("/v2")
				.addHeader("api_key", "special-key")
				.setContentType(ContentType.JSON)
				.log(LogDetail.ALL).build();
	}
	
	public static ResponseSpecification getResponseSpecifications()
	{
		return new ResponseSpecBuilder()
				.log(LogDetail.ALL).build();
	}

}
