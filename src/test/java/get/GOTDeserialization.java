package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.GameOfThronesPojo;

public class GOTDeserialization {
    /**
     * GET https://api.got.show/api/show/characters
     * 1. Sort Character name and house
     * 2. Sort Male/Female characters
     */

    @Test
    public void GameOfThrones(){
        Response response = RestAssured.given()
                .accept("application/json")
                .when().get("https://api.got.show/api/show/characters")
                .then().statusCode(200).extract().response();
        GameOfThronesPojo parseGOT= response.as(GameOfThronesPojo.class);

        String parseGender=parseGOT.getGender();
        Assert.assertEquals("female",parseGender);



    }
}
