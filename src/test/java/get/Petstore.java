package get;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.Test;

public class Petstore {
    //Create a new @Test method

    //Send a GET request to https://petstore.swagger.io/v2/pet/10567
    //Validate response status code equals to 200

    @Test
    public void getPetTest(){
        RestAssured.baseURI ="https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/10567";

        RestAssured.given()
                .header("Accept","application/json").log().all()
                .when()
                .get()
                .then()
                .statusCode(200).log().all(); //log print out all related result
    }
    @Test
    public void getPetTest2(){
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet/10567";
        RestAssured.given()
                .header("Accept","application/json")
                .then().statusCode(200)
                .body("name", Matchers.is("hatiko"))
                .body("category.id",Matchers.equalTo(0));

    }
}
