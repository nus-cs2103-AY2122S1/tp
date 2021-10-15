package seedu.tracker.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.module.Module;

/**
 * A utility class containing a list of {@code Module} objects to be used in tests.
 */
public class TypicalModules {
    public static final Module CS2103T = new ModuleBuilder().withCode("CS2103T")
            .withTitle("Software Engineering").withMc(4)
            .withDescription("Covers the main areas of software development")
            .build();
    public static final Module CS2101 = new ModuleBuilder().withCode("CS2101")
            .withTitle("Effective Communication for Computing Professionals")
            .withDescription("equip students with the skills needed to communicate technical information")
            .build();
    public static final Module GEQ1000 = new ModuleBuilder().withCode("GEQ1000")
            .withTitle("Asking Questions")
            .withDescription("introduces six dominant modes of questioning").build();

    public static final Module MA1521 = new ModuleBuilder().withCode("MA1521")
            .withTitle("Calculus")
            .withDescription("introduces calculus")
            .withAcademicCalendar(1, 1).build();

    public static final Module CS1231S = new ModuleBuilder().withCode("CS1231S")
            .withTitle("Discrete math")
            .withDescription("introduces discrete math")
            .withAcademicCalendar(1, 1).build();

    public static final Module CS2030S = new ModuleBuilder().withCode("CS2030S")
            .withTitle("OOP")
            .withDescription("OOP")
            .withAcademicCalendar(1, 2).build();

    private TypicalModules() {} // prevents instantiation

    /**
     * Returns an {@code ModuleTracker} with all the typical persons.
     */
    public static ModuleTracker getTypicalModuleTracker() {
        ModuleTracker mt = new ModuleTracker();
        for (Module module : getTypicalModules()) {
            mt.addModule(module);
        }
        return mt;
    }

    public static List<Module> getTypicalModules() {
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, GEQ1000, MA1521, CS1231S, CS2030S));
    }
}
