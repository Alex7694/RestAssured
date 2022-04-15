package Basics.Basic;

import Files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import static io.restassured.RestAssured.given;


public class StaticJSONFromFile {

    @Test()
    public void addBook() throws IOException {

        //BaseURL or Host
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =  given().
                header("Content-Type", "application/json").
                body(GenerateStringFromResource("E:\\IdeaProjects\\RestAssured\\src\\main\\java\\Files\\file.json")).
                when().
                post("/Library/Addbook.php").then().assertThat().
                statusCode(200).
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("ID");
        System.out.println("ID: " + id);
    }

    public static String GenerateStringFromResource(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)));
    }

}
