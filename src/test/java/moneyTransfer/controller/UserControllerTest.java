package moneyTransfer.controller;

import static com.jayway.restassured.RestAssured.expect;

import org.junit.Before;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;

public class UserControllerTest {

	@Before
	public void setUp() {
		RestAssured.basePath = "http://localhost:8080/moneyTransfer";
	}

	@Test
	public void testGetUsers() {
		expect().statusCode(200).contentType(ContentType.JSON).when().get("/users");
	}

	
}
