package Basics.Jira;

import Files.PayLoad;
import Files.Resourses;
import Files.ReusableMethods;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UpdateComment {

    @Test
    public void updateComment() {

        String sessionID = ReusableMethods.getSessionID();

        //Creating defect
        Response response = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID = "+ sessionID ).
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
                header("Cookie", "JSESSIONID="+sessionID).
                body(PayLoad.getCreateCommentBugBody()).
                when().
                post(Resourses.getCreateBugCommentResourse(bugID)).
                then().assertThat().statusCode(201).and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath_comment = ReusableMethods.rawToJSON(comment_response);
        String comment_id = jsonPath_comment.get("id");
        System.out.println(comment_id);

        Response updateComment_Response = given().
                header("Content-Type", "application/json").
                header("Cookie", "JSESSIONID="+sessionID).
                body(PayLoad.getUpdateCommentBugBody()).
                when().
                put(Resourses.getUpdateCommentResourse(bugID,comment_id)).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                extract().response();

        JsonPath jsonPath_UpdateComment = ReusableMethods.rawToJSON(comment_response);
        String updateComment_id = jsonPath_UpdateComment.get("id");
        System.out.println(updateComment_id);
    }
}
