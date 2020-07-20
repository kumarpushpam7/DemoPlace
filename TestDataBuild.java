package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestDataBuild {
	
	public AddPlace addPlacePayload(String name, String language, String address){
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(address);
		p.setName(name);
		p.setLanguage(language);
		p.setPhone_number("(+91) 983 893 3937");
		p.setWebsite("http://google.com");
		
		List<String> typesList = new ArrayList<String>();
		typesList.add("shoe park");
		typesList.add("shop");
		p.setTypes(typesList); //as "setTypes" is expecting a List; so we created typesList as ArrayList
		
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		
		p.setLocation(l); // as "setLocation" is expecting Object of Location; so we created Object of Location
		
		return p;
	}
	
	public String deletePlacePayload(String placeId){
		
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}\r\n" + 
				"";
	}



}
