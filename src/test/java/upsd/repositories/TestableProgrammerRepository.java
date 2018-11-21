package upsd.repositories;

import upsd.domain.Programmer;

import java.util.List;
import java.util.Optional;

public class TestableProgrammerRepository extends ProgrammerRepository {

    private List<Programmer> programmers;

    public TestableProgrammerRepository(List<Programmer> testData) {
        this.programmers = testData;
    }

    @Override
    public List<Programmer> getAll() {
        return programmers;
    }

    @Override
    public Optional<Programmer> getBy(int id) {
        return programmers.stream().filter(p -> p.id() == id).findFirst();
    }

    public void clear() {
        programmers.clear();
    }

    public void add(Programmer programmer) {
        programmers.add(programmer);
    }
}
