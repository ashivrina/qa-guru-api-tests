package com.github.ashivrina.tests;

import com.github.ashivrina.models.User;
import com.github.ashivrina.models.UserData;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;

public class ReqresTests {
    @Test
    void checkSuccessfullLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"cityslicka\"}")
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
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
        given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"eve.holt@reqres.in\"," +
                        "\"password\": \"pistol\"}")
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .body("id", is(4));
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
        String actualName = given()
                        .contentType(ContentType.JSON)
                        .body("{\"name\": \"" + expectedName + "\"," +
                                "\"job\": \"vampire slayer\"}")
                        .post("https://reqres.in/api/users")
                        .then()
                        .statusCode(201)
                        .extract().path("name");

        assertThat(actualName).isEqualTo(expectedName);
    }

    @Test
    void testGetUserById() {
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
    void getExistingUser() {
        given()
                    .contentType(ContentType.JSON)
                    .get("https://reqres.in/api/users/2")
                    .then()
                    .statusCode(200)
                    .body("data.email", is("janet.weaver@reqres.in"))
                    .body("data.first_name", is("Janet"))
                    .body("data.last_name", is("Weaver"));
    }

    @Test
    void getNonexistingUser() {
        get("https://reqres.in/api/users/23")
                .then()
                .statusCode(404);
    }
}
