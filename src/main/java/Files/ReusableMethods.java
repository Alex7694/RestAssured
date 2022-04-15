package Files;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ReusableMethods {

    public static XmlPath rawToXML(Response response) {
        String responseAsString = response.asString();
        System.out.println(responseAsString);
        XmlPath xmlPath = new XmlPath(responseAsString);
        return xmlPath;
    }

    public static JsonPath rawToJSON(Response response) {
        String responseAsString = response.asString();
        System.out.println(responseAsString);
        JsonPath jsonPath = new JsonPath(responseAsString);
        return jsonPath;
    }

    public static String  getSessionID() {

        // Creating a session
        RestAssured.baseURI = "http://localhost:8080";

        //Grab Response
        Response response = given().
                header("Content-Type", "application/json").
                body(PayLoad.getJiraAuthBody()).
                when().
                post(Resourses.getJiraResourse()).then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);

        //Grab JSESSIONID from Response
        String JSESSIONID = jsonPath.get("session.value");
        System.out.println("JSESSIONID: " + JSESSIONID);

        return  JSESSIONID;

    }
}
