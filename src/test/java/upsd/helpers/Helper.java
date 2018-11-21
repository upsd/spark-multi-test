package upsd.helpers;

import upsd.domain.Programmer;
import upsd.repositories.TestableProgrammerRepository;

public class Helper {

    private static TestableProgrammerRepository programmerRepository;

    public Helper(TestableProgrammerRepository programmerRepository) {
        this.programmerRepository = programmerRepository;
    }

    public static void add(Programmer programmer) {
        programmerRepository.add(programmer);
    }

    public static void clearData() {
        programmerRepository.clear();
    }
}
