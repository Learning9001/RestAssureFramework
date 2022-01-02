package stepDefinations;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlaceResponse;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.*;


public class StepDefinations extends Utils {
	RequestSpecification  requestSpec;
	ResponseSpecification responseSpec;
	RequestSpecification req;
	Response res;
	static String place_id;
	

	AddPlaceResponse response;  //This is for pojo class response
	TestDataBuild data;         //Building body of the data
	


	@Given("Add Place Payload {string} {string}")
	public void add_place_payload(String name, String website)		
			throws IOException {
	    
		this.data = new TestDataBuild();
		
		req= 	given()
				.spec(requestSpecification())
				.body(this.data.addPlacePayload(name, website));
			
	}

	@When("user calls {string} API with {string} http request")
	public void user_calls_api_with_http_request(String resource, String method) {
	    
		APIResources resAPI = APIResources.valueOf(resource);
		
		if (method.equalsIgnoreCase("POST"))
			res =  req.when().post(resAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			res =  req.when().get(resAPI.getResource());
		else if (method.equalsIgnoreCase("DELETE"))
			res =  req.when().delete(resAPI.getResource());
	}

	
	@Then("API Call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer expectedStatusCode) { 
		
		
		assertEquals( expectedStatusCode.intValue(), res.getStatusCode());
			
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedValue) {
	    	
		assertEquals( expectedValue, getJsonPathValue(res, key));
		
	}

	@Then("verify that {string} mapped with {string}")
	public void verify_that_mapped_with(String expectedName, String getPlaceAPI) throws IOException {
	    // Create the specification
		req = 	given()
				.spec(requestSpecification())
				.queryParam("place_id", getJsonPathValue(res, "place_id"));
		place_id = getJsonPathValue(res, "place_id");
		user_calls_api_with_http_request(getPlaceAPI, "GET");
		api_call_got_success_with_status_code(200);
		assertEquals( expectedName, getJsonPathValue(res, "name"));
		
	}
	
	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		
		req = given()
		.spec(requestSpecification())
		.body("{\r\n"
				+ "    \"place_id\":\"" +place_id +"\"\r\n"
				+ "}");
	 
	}
	
	
}
