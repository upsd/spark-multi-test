package upsd.acceptance;

import org.junit.Before;
import org.junit.Test;
import upsd.domain.Programmer;
import upsd.helpers.Helper;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class AT_GetProgrammerById {

    @Before
    public void setUp() {
        Helper.clearData();
        Helper.add(new Programmer(1, "dummy programmer"));
    }

    @Test
    public void return_200_and_user_found_for_specified_id() {
        get("/programmers/1").then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(1))
                .body("name", is("dummy programmer"));
    }
}
