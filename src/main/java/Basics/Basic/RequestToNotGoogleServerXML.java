package Basics.Basic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static io.restassured.RestAssured.given;

import Files.ReusableMethods;

public class RequestToNotGoogleServerXML {

    Properties properties = new Properties();

    @Before
    public void getData() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("E:\\IdeaProjects\\RestAssured\\application.properties");
        properties.load(fileInputStream);
    }

    @Test
    public void postData() throws IOException {

        String postData = GenerateStringFromResource("src\\main\\java\\Files\\postdata.xml");

        //BaseURL or Host
        RestAssured.baseURI = properties.getProperty("HOST");


        Response response = given().
                queryParam("key", properties.getProperty("KEY")).
                body(postData).
                when().
                post("/maps/api/place/add/xml").then().assertThat().
                statusCode(200).and().contentType(ContentType.XML).
                extract().response();

        XmlPath xmlPath = ReusableMethods.rawToXML(response);
        String placeId = xmlPath.get("place_id");
        System.out.println("place_id: " + placeId);
    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }
}
