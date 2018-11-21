package upsd;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import spark.Spark;
import upsd.acceptance.AT_GetAllProgrammers;
import upsd.acceptance.AT_GetProgrammerById;
import upsd.controllers.ProgrammerController;
import upsd.domain.Programmer;
import upsd.helpers.Helper;
import upsd.repositories.TestableProgrammerRepository;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AT_GetProgrammerById.class,
        AT_GetAllProgrammers.class
})
public class TestSuite {

    public static Helper helper;

    @BeforeClass
    public static void setUp() {
        List<Programmer> testData = new ArrayList<>(asList(new Programmer(1, "dummy user")));

        TestableProgrammerRepository programmerRepository = new TestableProgrammerRepository(testData);
        helper = new Helper(programmerRepository);

        new Server(new ProgrammerController(programmerRepository)).startOn(8080);
    }

    @AfterClass
    public static void tearDown() {
        Spark.stop();
    }
}
