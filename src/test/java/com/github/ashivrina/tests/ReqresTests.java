package com.github.ashivrina.tests;

import com.github.ashivrina.models.registration.RegistrationLoginRequest;
import com.github.ashivrina.models.registration.RegistrationLoginResponse;
import com.github.ashivrina.models.users.NewUser;
import com.github.ashivrina.models.users.User;
import com.github.ashivrina.models.users.UserData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @Test
    void checkSuccessfullLogin() {
        String expectedEmail = "eve.holt@reqres.in";
        String password = "cityslicka";
        RegistrationLoginRequest request = new RegistrationLoginRequest(expectedEmail, password);
        RegistrationLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .extract()
                .as(RegistrationLoginResponse.class);
        String token = "QpwL5tke4Pnpja7X4";
        assertThat(response.getToken())
                .as("Check that token is " + token)
                .isEqualTo(token);
    }

    @Test
    void checkUnsuccessfullLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void checkSuccessfullRegistration() {
        String expectedEmail = "eve.holt@reqres.in";
        String password = "pistol";
        RegistrationLoginRequest request = new RegistrationLoginRequest(expectedEmail, password);
        RegistrationLoginResponse response = given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                    .post("https://reqres.in/api/register")
                .then()
                    .statusCode(200)
                    .extract()
                    .as(RegistrationLoginResponse.class);
        String token = "QpwL5tke4Pnpja7X4";
        Integer id = 4;
        assertThat(response.getToken())
                .as("Check that token is " + token)
                .isEqualTo(token);
        assertThat(response.getId())
                .as("Check that id is " + id)
                .isEqualTo(id);
    }

    @Test
    void checkUnsuccessfullRegistration() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"sydney@fife\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(400)
                .body("error", is("Missing password"));
    }

    @Test
    void createNewUser() {
        String expectedName = "Buffy";
        String expectedJob = "vampire slayer";
        NewUser newUser = new NewUser(expectedName, expectedJob);
        NewUser response = given()
                                .contentType(ContentType.JSON)
                                .body(newUser)
                                .post("https://reqres.in/api/users")
                            .then()
                                .statusCode(201)
                                .extract()
                                .as(NewUser.class);
        assertThat(response.getName())
                .as("Check that name is " + expectedName)
                .isEqualTo(expectedName);
        assertThat(response.getJob())
                .as("Check that job is " + expectedJob)
                .isEqualTo(expectedJob);
        assertThat(response.getId())
                .as("Check that id is not negative")
                .isNotNegative();
    }

    @Test
    void getExistingUser() {
        String firstName = "Emma";
        String lastName = "Wong";
        String email = "emma.wong@reqres.in";

        UserData data = given()
               .when()
                    .get("https://reqres.in/api/users/{id}", 3)
               .then()
                    .extract()
                    .as(UserData.class);

        User user = data.getData();

        assertThat(user.getFirstName())
                .as("Check first name is " + firstName)
                .isEqualTo(firstName);
        assertThat(user.getLastName())
                .as("Check last name is " + lastName)
                .isEqualTo(lastName);
        assertThat(user.getEmail())
                .as("Check email is " + email)
                .isEqualTo(email);
    }

    @Test
    void getNonexistingUser() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }
}
