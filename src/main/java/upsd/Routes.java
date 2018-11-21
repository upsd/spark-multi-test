package upsd;

import upsd.controllers.ProgrammerController;

import static spark.Spark.get;

public class Routes {

    private final ProgrammerController programmerController;

    public Routes(ProgrammerController programmerController) {
        this.programmerController = programmerController;
    }

    public void setup() {
        get("/programmers", (req, res) -> programmerController.getAll(req, res));
        get("/programmers/:id", (req, res) -> programmerController.getById(req, res));
    }
}
