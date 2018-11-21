package upsd.repositories;

import org.junit.Test;
import upsd.domain.Programmer;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ProgrammerRepositoryShould {

    @Test
    public void get_all_programmers() {
        List<Programmer> expectedProgrammers = asList(new Programmer(4, "some programmer"));
        ProgrammerRepository programmerRepository = new ProgrammerRepository(expectedProgrammers);


        List<Programmer> programmersFound = programmerRepository.getAll();


        assertThat(programmersFound, is(expectedProgrammers));
    }

    @Test
    public void get_programmer_by_id() {
        List<Programmer> expectedProgrammers = asList(
                new Programmer(4, "some programmer"),
                new Programmer(12, "another programmer")
        );
        ProgrammerRepository programmerRepository = new ProgrammerRepository(expectedProgrammers);


        Optional<Programmer> programmerFound = programmerRepository.getBy(12);


        assertThat(programmerFound.get(), is(new Programmer(12, "another programmer")));
    }
}
