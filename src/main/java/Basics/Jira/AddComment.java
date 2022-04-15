package Basics.Jira;

import Files.PayLoad;
import Files.Resourses;
import Files.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;


import static io.restassured.RestAssured.given;

public class AddComment {

    @Test
    public void addCommentToBug() {

        String sessionId = ReusableMethods.getSessionID();

        //Creating defect
        Response response = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + sessionId ).
                body(PayLoad.getCreateBugBody()).
                when().
                post(Resourses.getCreateBugResourse()).
                then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String bugID = jsonPath.get("id");
        System.out.println(bugID);

        Response comment_response = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID=" + sessionId).
                body(PayLoad.getCreateCommentBugBody()).
                when().
                post(Resourses.getCreateBugCommentResourse(bugID)).
                then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath_comment = ReusableMethods.rawToJSON(comment_response);
        String comment_id = jsonPath_comment.get("id");
        System.out.println(comment_id);

    }
}
