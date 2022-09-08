package put;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.PetPojo;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.junit.Assert.assertEquals;

public class UpdatePet {

    @Test
    public void updatePetTest(){
        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";

        PetPojo pet = new PetPojo();
        pet.setId(485756);
        pet.setName("jeremy");
        pet.setStatus("noisy pet");

        Response response = given()
                .accept("application/json")
                .contentType(JSON)
                .body(pet)
                .when().put()
                .then().statusCode(200).extract().response();
        //deserialization
        Map<String, Object> parsedPetResponse = response.as(new TypeRef<Map<String, Object>>() {
        });
        //DRY - Do not repeat yourself
       int actualId= (int)parsedPetResponse.get("id"); //we are parsing to int b/c the map is <String,Object> line 36
       String actualName = parsedPetResponse.get("name").toString();
       String actualStatus = parsedPetResponse.get("status").toString();

        assertEquals(485756,actualId);
        assertEquals("jeremy",actualName);
        assertEquals("noisy pet",actualStatus);


    }
    @Test
    public void allCallsTest(){
        //Create a pet

        RestAssured.baseURI = "https://petstore.swagger.io/v2";
        RestAssured.basePath = "pet";

        String expectedName= "Snoopy";
        int expectedId= 847565;
        String expectedStatus = "gone fishing";

        PetPojo pet = new PetPojo();
        pet.setName(expectedName);
        pet.setId(expectedId);
        pet.setStatus(expectedStatus);

        //deserializing using pojo class

        Response response = given().contentType(JSON).accept(JSON).body(pet)
                .when().post().then().statusCode(200).extract().response();
        //logAll is for debugging purposes and checking data
       PetPojo parsedPetResponse =  response.as(PetPojo.class);
       assertEquals(pet.getName(),parsedPetResponse.getName());
        assertEquals(pet.getId(),parsedPetResponse.getId());
        assertEquals(pet.getStatus(),parsedPetResponse.getStatus());

        //GET request
        Response getResponse = given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(200).extract().response();


        PetPojo parsedResponse = getResponse.as(PetPojo.class);
        assertEquals(pet.getName(),parsedPetResponse.getName());
        assertEquals(pet.getId(),parsedPetResponse.getId());
        assertEquals(pet.getStatus(),parsedPetResponse.getStatus());

        //PUT request
        pet.setName("Buzzy");
        Response putResponse = given().accept(JSON).contentType(JSON).body(pet).when().put()
                .then().statusCode(200).body("name", Matchers.equalTo(pet.getName())).log().all().extract().response();

        //GET request
        getResponse = given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(200).log().all().extract().response();

        PetPojo parsedGetResponse = getResponse.as(PetPojo.class);
        assertEquals(pet.getName(), parsedGetResponse.getName());
        assertEquals(pet.getId(), parsedGetResponse.getId());
        assertEquals(pet.getStatus(), parsedGetResponse.getStatus());


        //DELETE request
        Response deleteResponse =  given().accept(JSON).when().delete(String.valueOf(pet.getId()))
                .then().statusCode(200).body("message", Matchers.is(String.valueOf(pet.getId()))).extract().response();

        PetPojo parsedDeleteResponse = deleteResponse.as(PetPojo.class);
        assertEquals(String.valueOf(pet.getId()), parsedDeleteResponse.getMessage());

        //GET request
        given().accept(JSON).when().get(String.valueOf(pet.getId()))
                .then().statusCode(404).log().all();

    }


}
