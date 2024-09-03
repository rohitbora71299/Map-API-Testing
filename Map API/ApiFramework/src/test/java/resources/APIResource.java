package resources;

// ENUM is a special class in Java which has collection of constants or methods
public enum APIResource {
    AddPlaceAPI("/maps/api/place/add/json"),
    getPlaceAPI("/maps/api/place/get/json"),
    deletePlaceAPI("/maps/api/place/delete/json");

    private String resource;
    APIResource(String resource){
        this.resource =  resource;
    }

    public String getResource(){
        return resource;
    }


}
