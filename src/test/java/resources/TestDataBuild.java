package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String website) {
		AddPlace addPlace = new AddPlace();
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		List<String> types = new ArrayList<String>();
		types.add("shoe park");
		types.add("shop");

		addPlace.setAccuracy(50);
		addPlace.setAddress("29, side layout, cohen 09");
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setWebsite(website);
		addPlace.setLanguage("French-IN");
		addPlace.setLocation(location);
		addPlace.setTypes(types);
		
		return addPlace;
	}


}
