package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import resources.APIResources;
import resources.TestDataBuild;
import resources.Utils;

public class StepDefination extends Utils {
	
	RequestSpecification res;
	ResponseSpecification resSpec;
	Response response;
	static String place_Id;
	
	TestDataBuild data = new TestDataBuild();
	
	@Given("Add Place Payload with {string} {string} {string}")
	public void add_Place_Payload_with(String name, String language, String address) throws IOException {
	    
	
		
		 res = given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
	}





@When("user call {string} with {string} http request")
public void user_call_with_http_request(String resource, String method){
		
		APIResources resourceApi = APIResources.valueOf(resource);
		
		 resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	
		 if(method.equalsIgnoreCase("Post")){
	  response = res.when().post(resourceApi.getResource());
		 }
		 else if(method.equalsIgnoreCase("Get")){
			 response = res.when().get(resourceApi.getResource());
		 }
				

	}

	@Then("the API call got success with status code {int}")
	public void the_API_call_got_success_with_status_code(Integer int1) {
	   
		assertEquals(response.getStatusCode(),200);
		
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
	    
		assertEquals(getJsonPath(response,keyValue).toString(), expectedValue);
	}
	

     @Then("verify place_Id created maps to {string} using {string}")
     public void verify_place_Id_created_maps_to_using(String expectedName, String resource) throws IOException {
    	 
    	place_Id = getJsonPath(response,"place_id");
    
    	 res = given().spec(requestSpecification()).queryParam("place_id", place_Id);
    	 user_call_with_http_request(resource, "Get");
    	 String actualName = getJsonPath(response,"name");
    	 
    	 assertEquals(actualName, expectedName);
    } 
     
     @Given("DeletPlace payload")
     public void deletplace_payload() throws IOException {
         
    	res = given().spec(requestSpecification()).body(data.deletePlacePayload(place_Id));
    	 
     }

}
