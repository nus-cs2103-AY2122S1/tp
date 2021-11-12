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
            .withDescription("introduces six dominant modes of questioning").withTags("GE").build();

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

    public static final Module MA1101R = new ModuleBuilder().withCode("MA1101R")
            .withTitle("Linear Algebra")
            .withDescription("introduction to linear algebra")
            .withTags("math and science")
            .withAcademicCalendar(2, 1).build();

    public static final Module CS2102 = new ModuleBuilder().withCode("CS2102")
            .withTitle("Database Systems")
            .withDescription("introduction to relational algebra, database design")
            .withTags("breadth and depth")
            .withAcademicCalendar(2, 1).build();

    public static final Module IS1103 = new ModuleBuilder().withCode("IS1103")
            .withTitle("Ethics in Computing")
            .withDescription("introduction to ethics in the computing domain")
            .withTags("it professionalism")
            .withAcademicCalendar(2, 1).build();

    public static final Module GEA1000 = new ModuleBuilder().withCode("GEA1000")
            .withTitle("Quantitative Reasoning with Data")
            .withDescription("essential data literacy skills to analyse data")
            .withTags("ge")
            .withAcademicCalendar(2, 1).build();

    public static final Module CS2040S = new ModuleBuilder().withCode("CS2040S")
            .withTitle("Data Structures and Algorithms")
            .withDescription("introduces students to the design and implementation of fundamental data structures "
                    + "and algorithms")
            .withTags("foundation")
            .withAcademicCalendar(1, 3).build();

    public static final Module CP2106 = new ModuleBuilder().withCode("CP2106")
            .withTitle("Orbital")
            .withDescription("platform for students to gain hands-on industrial experience for computing technologies")
            .withTags("ue")
            .withAcademicCalendar(1, 3).build();

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
        return new ArrayList<>(Arrays.asList(CS2103T, CS2101, GEQ1000, MA1521, CS1231S, CS2030S, MA1101R,
                CS2102, IS1103, GEA1000, CS2040S, CP2106));
    }
}
