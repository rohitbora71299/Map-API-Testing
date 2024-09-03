package resources;

import org.example.pojo.AddPlace;
import org.example.pojo.Location;
import java.util.Arrays;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name,String language,String location){

        AddPlace placeJson = new AddPlace();
        Location loc = new Location();
        loc.setLat(-38.383494);
        loc.setLng(33.427362);

        String[] types = {"shoe park","shop"};
        placeJson.setAccuracy(50);
        placeJson.setAddress(location);
        placeJson.setLanguage(language);
        placeJson.setPhoneNumber("(+91) 983 893 3937");
        placeJson.setWebsite("http://google.com");
        placeJson.setName(name);
        placeJson.setLocation(loc);
        placeJson.setTypes(Arrays.asList(types));
        return placeJson;
    }

//    public String deletePlacePayload(String placeId){
//        return "{\"placeId\": "+placeId+"}";
//    }
    public String deletePlacePayload(String placeId) {
        return "{\"place_id\": \"" + placeId + "\"}";
    }


}
