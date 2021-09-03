import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
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

    //test for create user - use smth like that
    /*
    @Test
    void checkTotal20WithAssertJ() {
        Integer response =
                get("https://selenoid.autotests.cloud/status")
                        .then()
                        .extract().path("total");

        System.out.println(response);

        assertThat(response).isEqualTo(20);
    }
     */

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
}
