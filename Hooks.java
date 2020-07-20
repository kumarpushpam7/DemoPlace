package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException{
    	
    	StepDefination m =  new StepDefination();
    	
    	if(StepDefination.place_Id==null){
    	m.add_Place_Payload_with("Shetty","French","Asia");
    	m.user_call_with_http_request("AddPlaceAPI","Post");
    	m.verify_place_Id_created_maps_to_using("Shetty","getPlaceAPI");
    	}
    	
    	
    }

}
