package upsd.controllers;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import spark.Request;
import spark.Response;
import upsd.domain.Programmer;
import upsd.repositories.ProgrammerRepository;

import java.util.List;

public class ProgrammerController {

    private final ProgrammerRepository programmerRepository;

    public ProgrammerController(ProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public String getAll(Request req, Response res) {
        res.type("application/json");
        return jsonFrom(programmerRepository.getAll()).toString();
    }

    public String getById(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        res.type("application/json");

        return jsonFrom(programmerRepository.getBy(id).get()).toString();
    }

    private JsonObject jsonFrom(List<Programmer> programmers) {
        JsonArray programmersJson = new JsonArray();

        programmers.stream()
                .map(p -> jsonFrom(p))
                .forEach(p -> programmersJson.add(p));


        return new JsonObject().add("programmers", programmersJson);
    }

    private JsonObject jsonFrom(Programmer p) {
        return new JsonObject()
                .add("id", p.id())
                .add("name", p.name());
    }
}
