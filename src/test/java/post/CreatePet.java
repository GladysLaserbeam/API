package post;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;
import utils.PayLoadUtils;

public class CreatePet {
    @Test
    public void createPetTest(){
        // https://petstore.swagger.io/v2/pet
        RestAssured.baseURI = "https://petstore.swagger.io";
        RestAssured.basePath = "v2/pet";

        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .body(PayLoadUtils.getPetPayLoad()).when().post().then().statusCode(200).extract().response();

        PetPojo parsedPet = response.as(PetPojo.class);

        Assert.assertEquals("in transit from java code", parsedPet.getStatus());
    }
}
