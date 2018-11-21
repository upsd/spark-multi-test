package upsd;

import spark.Spark;
import upsd.controllers.ProgrammerController;

public class Server {

    private final ProgrammerController programmerController;

    public Server(ProgrammerController programmerController) {
        this.programmerController = programmerController;
    }

    public void startOn(int port) {
        Spark.port(port);
        new Routes(programmerController).setup();
    }
}
