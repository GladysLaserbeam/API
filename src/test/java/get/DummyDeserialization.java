package get;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import pojo.dummyPojo;

public class DummyDeserialization {

    @Test
    public void DummyTest(){
        Response response = RestAssured.given().header("Accept", "application/json")
                .when().get("https://dummy.restapiexample.com/api/v1/employees")
                .then().statusCode(200).extract().response();
        dummyPojo parsedDummy=response.as(dummyPojo.class);

        Assert.assertEquals("success",parsedDummy.getStatus());

    }

    @Test
    public void DummyTest2(){
        RestAssured.baseURI = "https://dummy.restapiexample.com";
        RestAssured.basePath = "api/v1/employees";
        RestAssured.given().accept("application/json")
                .then().statusCode(200).body("data.employee_name", Matchers.is("Ashton Cox"))
                .body("employee_salary",Matchers.equalTo(86000));
    }

        



    }

