package upsd.repositories;

import upsd.domain.Programmer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProgrammerRepository {

    private final List<Programmer> programmers;

    public ProgrammerRepository(List<Programmer> programmers) {
        this.programmers = programmers;
    }

    public ProgrammerRepository() {
        this.programmers = new ArrayList<>();
    }

    public List<Programmer> getAll() {
        return programmers;
    }

    public Optional<Programmer> getBy(int id) {
        return programmers.stream()
                .filter(p -> p.id() == id)
                .findFirst();
    }
}
