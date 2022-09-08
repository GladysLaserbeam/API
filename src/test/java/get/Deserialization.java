package get;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;

public class Deserialization {

    @Test
    public void deserializationTest(){

        Response response = RestAssured.given().header("Accept","application/json")
                .when().get("https://petstore.swagger.io/v2/pet/10567")
                .then().statusCode(200).extract().response();

        Map<String, Object> deserializationResponse= response.as(new TypeRef<Map<String, Object>>() {
        });

        System.out.println(deserializationResponse); //prints out the whole map with their key and values in java type not json
        //validating petname
        Assert.assertEquals(10567,deserializationResponse.get("id"));
        //validating name
        String actualPetName= String.valueOf(deserializationResponse.get("name"));
        Assert.assertEquals("hatiko",actualPetName);

        Map<String,Object> categoryMap=(Map<String, Object>) deserializationResponse.get("category");
        System.out.println("category map: " + categoryMap); //this will print out the key and values of "category"
    }

    @Test
    public void advDeserializationTest() {
        //1. TypeRef
        //2. POJO
        //3. Json Traversing
        //4. JsonPath

        //https://swapi.dev/api/people

        baseURI = "https://swapi.dev/api";
        basePath = "people";

        Response response = given().accept(JSON)
                .when().get()
                .then().statusCode(200).extract().response();

        JsonPath deserializedResponse = response.jsonPath();

        String nextValue = deserializedResponse.getString("next");
        System.out.println(nextValue);
        Assert.assertEquals(baseURI + "/" + basePath + "/?page=2", nextValue);

        List<Object> resultValue = deserializedResponse.getList("results");
//        System.out.println(resultValue);

        List<Map<String, Object>> result = deserializedResponse.getList("results");
        System.out.println(result);

        Map<String, Object> firstObjectFromList =
                deserializedResponse.getMap("results[0]");
        System.out.println(firstObjectFromList);


    }

}
