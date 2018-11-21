package upsd.acceptance;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import upsd.domain.Programmer;
import upsd.helpers.Helper;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;

public class AT_GetAllProgrammers {

    private Programmer PROGRAMMER = new Programmer(7, "dummy programmer");

    @Before
    public void setUp() {
        Helper.clearData();
        Helper.add(PROGRAMMER);
    }

    @Test
    public void return_200_and_all_programmers_found() {
        String expectedResponse = jsonFrom(PROGRAMMER).toString();

        get("/programmers").then()
                .statusCode(200)
                .contentType("application/json")
                .body(is(expectedResponse));
    }

    private JsonObject jsonFrom(Programmer programmer) {
        JsonObject programmerJson = new JsonObject()
                .add("id", programmer.id())
                .add("name", programmer.name());

        return new JsonObject()
                .add("programmers", new JsonArray().add(programmerJson));
    }
}
