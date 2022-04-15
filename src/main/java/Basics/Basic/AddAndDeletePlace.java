package Basics.Basic;

import Files.PayLoad;
import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import Files.Resourses;

public class AddAndDeletePlace {

    Properties properties = new Properties();

    @Before
    public void getData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\IdeaProjects\\RestAssured\\application.properties");
        properties.load(fileInputStream);

    }

    @Test
    //create place (=placeid from response) and then delete it
    public void AddAndDeletePlace() {


        //BaseURL or Host
        RestAssured.baseURI = properties.getProperty("HOST");

        //Grab the Response
        Response response = given().
                queryParam("key", properties.getProperty("KEY")).
                body(PayLoad.getPostData()).
                when().
                post(Resourses.placePostData()).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK")).
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);

        //Grab the place_id from response
        String placeid = jsonPath.get("place_id");
        System.out.println("Placeid: " + placeid);

        //Place place_id in Delete Request
        given().
                queryParam("key", "qaclick123").
                body("{\n" +
                        "    \"place_id\":\"" + placeid + "\"\n" +
                        "}\n").
                when().
                post("/maps/api/place/delete/json").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));


    }
}
