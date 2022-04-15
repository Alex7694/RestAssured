package Basics.Jira;

import Files.PayLoad;
import Files.Resourses;
import Files.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public  class CreateBug {

    static String sessionID = ReusableMethods.getSessionID();

    public static String getSessionID() {
        return sessionID;
    }


    @Test
    public  void JiraAPI() {

        //Creating defect
        Response response = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID = "+ ReusableMethods.getSessionID() +" ").
                body(PayLoad.getCreateBugBody()).
                when().
                post(Resourses.getCreateBugResourse()).
                then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String bugID = jsonPath.get("id");
        System.out.println(bugID);

    }
}
