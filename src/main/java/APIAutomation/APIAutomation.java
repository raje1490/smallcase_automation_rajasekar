package APIAutomation;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyData;
import io.restassured.response.ResponseOptions;
import io.restassured.response.ValidatableResponse;
import org.json.simple.JSONObject;

import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;


public class APIAutomation{
public static void getResponseBody() throws IOException{
	
	@SuppressWarnings("deprecation")
	String expectedJson = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/TestData/JsonScehma.json"));
    
	given().when().get("https://bookstore.toolsqa.com/BookStore/v1/Books").then().assertThat()
	.statusCode(200).body(JsonSchemaValidator.matchesJsonSchema(expectedJson));

	}


}