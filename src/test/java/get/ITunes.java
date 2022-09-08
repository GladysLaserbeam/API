package get;

import io.restassured.RestAssured;
import org.junit.Test;

public class ITunes {

    @Test
    public void ITunesTest1(){
        /**
         * 1. Define/Find a valid/correct URL to send an http request to get a response
         * 2.define a proper HTTP METHOD
         * 3.Define query string parameters (if needed)
         * 4.Define header parameters
         * 5.Send/execute a request
         * //http:
         */
        RestAssured.baseURI ="http://itunes.apple.com/search";

        RestAssured.given().header("Accept","application/json") //prerequisite
                .queryParam("term","michael+jackson")
                .queryParam("limit","1")
                .when().get() //action
                .then().statusCode(200); //validation
    }
}
