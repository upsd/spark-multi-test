package upsd.controllers;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import org.junit.Before;
import org.junit.Test;
import spark.Request;
import spark.Response;
import upsd.domain.Programmer;
import upsd.repositories.ProgrammerRepository;

import java.util.List;
import java.util.Optional;

import static org.codehaus.groovy.runtime.InvokerHelper.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ProgrammerControllerShould {

    private ProgrammerController programmerController;
    private ProgrammerRepository programmerRepository;
    private Request request;
    private Response response;

    @Before
    public void setUp() {
        programmerRepository = mock(ProgrammerRepository.class);
        programmerController = new ProgrammerController(programmerRepository);
        response = mock(Response.class);
        request = mock(Request.class);
    }

    @Test
    public void return_all_programmers() {
        List<Programmer> programmers = asList(new Programmer(1, "bob"));

        given(programmerRepository.getAll()).willReturn(programmers);


        String body = programmerController.getAll(request, response);


        verify(response).type("application/json");
        assertThat(body, is(jsonFrom(programmers).toString()));
    }

    @Test
    public void return_programmer_for_given_id() {
        Programmer programmer = new Programmer(2, "jim");

        given(programmerRepository.getBy(2)).willReturn(Optional.of(programmer));
        given(request.params(":id")).willReturn("2");


        String body = programmerController.getById(request, response);


        verify(response).type("application/json");
        assertThat(body, is(jsonFrom(programmer).toString()));
    }

    private JsonObject jsonFrom(List<Programmer> programmers) {
        JsonArray array = new JsonArray();

        programmers.stream()
                .map(p -> jsonFrom(p))
                .forEach(p -> array.add(p));


        return new JsonObject().add("programmers", array);
    }

    private JsonObject jsonFrom(Programmer p) {
        return new JsonObject()
                .add("id", p.id())
                .add("name", p.name());
    }
}
