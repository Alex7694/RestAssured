package Basics.Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostJsonNotGoogleSever {

    @Test
public void postData() {

        //BaseURL or Host
        RestAssured.baseURI = "http://216.10.245.166";

        given().
              queryParam("key", "qaclick123").
              body("{\n" +
                      "\n" +
                      "    \"location\":{\n" +
                      "\n" +
                      "        \"lat\" : -38.383494,\n" +
                      "\n" +
                      "        \"lng\" : 33.427362\n" +
                      "\n" +
                      "    },\n" +
                      "\n" +
                      "    \"accuracy\":50,\n" +
                      "\n" +
                      "    \"name\":\"Frontline house\",\n" +
                      "\n" +
                      "    \"phone_number\":\"(+91) 983 893 3937\",\n" +
                      "\n" +
                      "    \"address\" : \"29, side layout, cohen 09\",\n" +
                      "\n" +
                      "    \"types\": [\"shoe park\",\"shop\"],\n" +
                      "\n" +
                      "    \"website\" : \"http://google.com\",\n" +
                      "\n" +
                      "    \"language\" : \"French-IN\"\n" +
                      "\n" +
                      "}").
                          when().
                post("/maps/api/place/add/json").then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON).and().
                body("status", equalTo("OK"));




    }
 }
