package stepDefinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before ("@DeletePlaceAPI")
	public void createAddPlaceData() throws IOException {
		
		StepDefinations m = new StepDefinations();
		if (StepDefinations.place_id == null) {
			m.add_place_payload("From Hooks", "www.fromhookfile.com");
			m.user_calls_api_with_http_request("addPlaceAPI", "POST");
			m.verify_that_mapped_with("From Hooks", "getPlaceAPI");
		}	
		
	}

}
