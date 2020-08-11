package com.epam.demo.test.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import com.epam.demo.TestBase;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SpotifyTest extends TestBase {
    private RequestSpecification requestSpecification;

    @BeforeClass
    void beforeClass() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://api.spotify.com")
                .setBasePath("/v1/")
                .setAuth(RestAssured.oauth2(spotifyAuthHelper.getAccessToken()))
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    void queryForEdSheeranShouldReturnEdSheeranAsTheFirstItem() {
        given().spec(requestSpecification)
                .queryParam("q", "Ed Sheeran")
                .queryParam("type", "artist")
                .when()
                .get("search")
                .then()
                .log()
                .body()
                .statusCode(200)
                .body("artists.items.name[0]", equalTo("Ed Sheeran"));
    }

    @Test
    void artistShouldBeEdSheeranForId() {
        given().spec(requestSpecification)
                .pathParam("id", "6eUKZXaKkcviH0Ku9w2n3V")
                .when()
                .get("artists/{id}")
                .then()
                .log()
                .body()
                .statusCode(200)
                .body("name", equalTo("Ed Sheeran"));
    }
}
