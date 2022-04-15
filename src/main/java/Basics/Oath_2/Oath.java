package Basics.Oath_2;

import Basics.POJO.API;
import Basics.POJO.GetCourse;
import Basics.POJO.Webautomation;
import io.restassured.path.json.JsonPath;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

import static io.restassured.RestAssured.given;


public class Oath {
    public static void main(String[] args) {

        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.29.1-win64\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php");

        driver.findElement(By.xpath("//input[@type = 'email']")).sendKeys("atoropov2@gmail.com");
        driver.findElement(By.xpath("//span[contains(text(), 'Далее')]")).click();


        String acessTokenResponse = given().
                queryParams("code", "")
                .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .queryParam("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                .queryParam("grant_type", "authorization_code")
                .when().log().all()
                .post("https://www.googleapis.com/oauth2/v4/token").asString();

        JsonPath jsonPath = new JsonPath(acessTokenResponse);
        String acessToken = jsonPath.getString("access_token");


        String response = given().
                queryParam("access_token", acessToken).
                when().get("https://rahulshettyacademy.com/getCourse.php").asString();

        System.out.println(response);


        GetCourse getCourse = new GetCourse();
        System.out.println(getCourse.getCourses().getApi().get(1).getCourseTitle());

        List<API>  apiCourses = getCourse.getCourses().getApi();

        for(int i = 0; i <apiCourses.size(); i++) {
            if (apiCourses.get(i).getCourseTitle().equals("SoapUI Webservices testing")) {
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        List<Webautomation> webautomations = getCourse.getCourses().getWebAutomation();
        for(int i = 0; i < webautomations.size(); i++) {
            System.out.println(webautomations.get(i).getCourseTitle());
        }
    }
}
