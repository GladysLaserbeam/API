package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.CatPojo;
import pojo.PokemonPojo;

public class CatPojoDeserialization {

    @Test
    public void CatTest() {
        Response response = RestAssured.given()
                .accept("application/json") //shortcut instead of using header
                .when().get("https://catfact.ninja/facts")
                .then().statusCode(200).extract().response();
        CatPojo parsedCatResp = response.as(CatPojo.class);
        int perPage = parsedCatResp.getPer_page();
        int totalFactsPerPage= parsedCatResp.getData().size();

        Assert.assertEquals(perPage,totalFactsPerPage);
    }
}
