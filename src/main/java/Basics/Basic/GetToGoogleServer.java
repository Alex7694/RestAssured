package Basics.Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetToGoogleServer {

    @Test
    public void Test() {

        //BaseURL or Host
        RestAssured.baseURI = "https://maps.googleapis.com";

        given().
                param("location", "-33.8670522,151.1957362").
                param("radius", "1500").
                param("key", "AIzaSyAO-pFagOqAijTw_nVNVN6P4BcGBr0mBQw").log().all().
                when().
                get("/maps/api/place/nearbysearch/json").
                then().assertThat().
                statusCode(200).and().contentType(ContentType.JSON).and().
                body("results[0].place_id", equalTo("ChIJP3Sa8ziYEmsRUKgyFmh9AQM")).and().
                header("Content-Type", "application/json; charset=UTF-8").and().
                header("Server", "scaffolding on HTTPServer2");


    }
}
