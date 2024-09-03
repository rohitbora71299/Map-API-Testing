package stepDefinations;

//import org.junit.Before;
import io.cucumber.java.Before;
import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinations m = new StepDefinations();
        if(StepDefinations.place_id==null){
            m.add_place_payload_with("Rohit","German","Germany");
            m.user_calls_with_http_request("AddPlaceAPI","POST");
            m.verify_place_id_created_maps_to_using("Rohit","getPlaceAPI");
        }
}
    }
