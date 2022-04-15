package Basics.Basic;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetToGoogleServerPrintAllNames {
    @Test
    public void Test() {

        //BaseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        Response response = given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("key", "AIzaSyAO-pFagOqAijTw_nVNVN6P4BcGBr0mBQw").log().all().
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Content-Type", "application/json; charset=UTF-8").and().
                header("Server", "scaffolding on HTTPServer2").log().body().
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        int count = jsonPath.get("results.size()");

        for(int i = 0; i <count; i++) {
            System.out.println((String) jsonPath.get("results["+i+"].name"));;
        }

        System.out.println(count);

    }
}
