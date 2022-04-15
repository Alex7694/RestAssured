package Basics.Basic;
import Files.PayLoad;
import Files.ReusableMethods;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.io.IOException;



public class DynamicJSON {

    @Test(dataProvider= "BooksData")
    public void addBook(String isbn,String aisle) throws IOException  {

        //BaseURL or Host
        RestAssured.baseURI = "http://216.10.245.166";

        Response response =  given().
                header("Content-Type", "application/json").
                body(PayLoad.Addbook(isbn, aisle)).
                when().
                post("/Library/Addbook.php").then().assertThat().
                statusCode(200).
                extract().response();

        JsonPath jsonPath = ReusableMethods.rawToJSON(response);
        String id = jsonPath.get("ID");
        System.out.println("ID: " + id);
    }

    @DataProvider(name="BooksData")
    public Object[][] ArraysOfBooks() {
        return new Object[][] {{"ojfwty","888"},{"cwetee","999"}, {"okmfet","111"} };
    }

}





