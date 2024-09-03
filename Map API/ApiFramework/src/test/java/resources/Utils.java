package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.io.*;
import java.util.Properties;

public class Utils {
    public static RequestSpecification req;
    public RequestSpecification requestSpecification() throws IOException {
        if(req == null){
            PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
            req = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
                    .addQueryParam("key","qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON).build();
        }

        return req;
    }
    public String getGlobalValue(String key) throws IOException {
        Properties prop = new Properties();
        String path = "D:/Coding/Testing/Cucumber/ApiFramework/src/test/java/resources/global.properties";
        FileInputStream file = new FileInputStream(path);
        prop.load(file);
        return prop.getProperty(key);
    }

    public String getJsonPath(Response response , String keyValue){
        String res = response.asString();
        JsonPath js = new JsonPath(res);
        return js.get(keyValue).toString();
    }
}
