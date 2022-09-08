package post;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.mapper.factory.DefaultJackson2ObjectMapperFactory;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojo.SlackPojo;
import utils.PayLoadUtils;

import java.lang.reflect.Type;

public class Slack {

    @Test
    public void sendMessageTest(){
        //https://slack.com/api/chat.postMessage

        RestAssured.baseURI = "https://slack.com";
        RestAssured.basePath = "api/chat.postMessage"; //how to ignore all the fields except the ones you need in pojo
        RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig()
                .jackson2ObjectMapperFactory(new DefaultJackson2ObjectMapperFactory(){
                    @Override public ObjectMapper create (Type cls, String charset){
                        ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();
                        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                        return objectMapper;
                    }
                }));

        Response response = RestAssured.given()
                .accept("application/json")
                .contentType("application/json")
                .header("Authorization", "Bearer xoxb-3031094819335-3760010975238-yA5XSr4KSDmmA2ndxhz2vGLZ")
                .body(PayLoadUtils.getSlackPayLoad("Gladys: Hello world from Java"))
                .when().post()
                .then().statusCode(200).extract().response();


        SlackPojo parsedSlackResp= response.as(SlackPojo.class);
        Assert.assertTrue(parsedSlackResp.isOk());

    }
}
