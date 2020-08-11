package com.epam.demo.helper;

import static io.restassured.RestAssured.given;
import com.epam.demo.helper.config.SpotifyAuthConfig;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotifyAuthHelper {
    @Autowired
    private SpotifyAuthConfig spotifyAuthConfig;
    private String accessToken;

    public String getAccessToken() {
        // TODO: token should time out and generate a new one
        if (accessToken == null) {
            accessToken = generateAccessToken(getAuthToken(spotifyAuthConfig.getClientId(), spotifyAuthConfig.getClientSecret()));
        }
        return accessToken;
    }

    private String getAuthToken(String clientid, String clientSecret) {
        return Stream.of(clientid + ":" + clientSecret)
                .map(String::getBytes)
                .map(Base64::encodeBase64)
                .map(String::new)
                .collect(Collectors.joining());
    }

    private String generateAccessToken(String authToken) {
        return given()
                .baseUri("https://accounts.spotify.com/api")
                .header("Authorization", "Basic " + authToken)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type", "client_credentials")
                .log().all()
                .when()
                .post("token")
                .jsonPath()
                .get("access_token");
    }
}
