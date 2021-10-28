package seedu.tracker.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.tracker.model.ModuleTracker;
import seedu.tracker.model.ReadOnlyModuleTracker;
import seedu.tracker.model.module.Code;
import seedu.tracker.model.module.Description;
import seedu.tracker.model.module.Mc;
import seedu.tracker.model.module.Module;
import seedu.tracker.model.module.Title;
import seedu.tracker.model.tag.Tag;

/**
 * Contains utility methods for populating {@code ModuleTracker} with sample data.
 */
public class SampleDataUtil {
    public static Module[] getSampleModules() {
        return new Module[]{
            new Module(new Code("CS1101S"), new Title("Programming Methodology"),
                new Description("This module introduces the concepts of programming and "
                    + "computational problem solving, and is the first and foremost introductory module to computing."),
                new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS1231S"), new Title("Discrete Structures"),
                new Description("This module introduces mathematical tools required in the study of computer science."),
                new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2030S"), new Title("Programming Methodology II"),
                new Description("This module is a follow up to CS1010. It explores two modern "
                    + "programming paradigms, object-oriented programming and functional programming."),
                new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2040S"), new Title("Data Structures and Algorithms"),
                new Description("This module introduces students to the design and implementation"
                    + " of fundamental data structures and algorithms."), new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2100"), new Title("Computer Organisation"),
                new Description("The objective of this module is to familiarise students"
                    + " with the fundamentals of computing devices."), new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2103T"), new Title("Software Engineering"),
                new Description("This module introduces the necessary conceptual and analytical"
                    + " tools for systematic and rigorous development of software systems."), new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2106"), new Title("Introduction to Operating Systems"),
                new Description("This module introduces the basic concepts in operating systems "
                    + "and links it with contemporary operating systems (eg. Unix/Linux and Windows)."), new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS3230"), new Title("Design and Analysis of Algorithms"),
                new Description("This module introduces different techniques of designing and"
                    + " analysing algorithms."), new Mc(4),
                getTagSet("foundation")),
            new Module(new Code("CS2105"), new Title("Introduction to Computer Networks"),
                new Description("This module aims to provide a broad introduction to computer "
                    + "networks and network application programming."), new Mc(4),
                getTagSet("ue")),
            new Module(new Code("CS3243"), new Title("Introduction to Artificial Intelligence"),
                new Description("The module introduces the basic concepts in search and knowledge "
                    + "representation as well as to a number of sub-areas of artificial intelligence."), new Mc(4),
                getTagSet("ue")),
            new Module(new Code("GER1000"), new Title("quantitative reasoning"),
                new Description("learn quantitative reasoning"), new Mc(4),
                getTagSet("ge")),
            new Module(new Code("GEQ1000"), new Title("Asking Questions"),
                new Description("This module introduces six dominant modes of questioning "
                    + "from the perspective of computational thinking, design thinking, engineering,"
                    + " philosophy, science, and social sciences."), new Mc(4),
                getTagSet("ge")),
            new Module(new Code("CS3213"), new Title("Foundations of Software Engineering"),
                new Description("This module will provide the students with foundational knowledge"
                    + " and understanding of different aspects of software engineering including"
                    + " requirements, modelling, architecture, behavior, environment, software analysis"
                    + ", validation and verification."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS4218"), new Title("Software Testing"),
                new Description("This module covers the concepts and practice of software testing"
                    + " including unit testing, integration testing, and regression testing."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS2101"), new Title("Effective Communication for Computing Professionals"),
                new Description("Equip students with the skills needed to communicate "
                    + "technical information"), new Mc(4),
                getTagSet("it professionalism")),
            new Module(new Code("IS1103"), new Title("Ethics in Computing"),
                new Description("Students will learn about the importance of Ethics in"
                    + " Computing policy-making and be able to make judgements and decisions "
                    + "based on established ethical frameworks."), new Mc(4),
                getTagSet("it professionalism")),
            new Module(new Code("ES2660"), new Title("Communicating in the Information Age"),
                new Description("Students will learn to question and articulate their "
                    + "analysis of assumptions and assertions on issues facing the Information Age."), new Mc(4),
                getTagSet("it professionalism")),
            new Module(new Code("MA1521"), new Title("Calculus for Computing"),
                new Description("This module provides a basic foundation for calculus"
                    + " and its related subjects required by computing students."), new Mc(4),
                getTagSet("math and science")),
            new Module(new Code("MA1101R"), new Title("Linear Algebra I"),
                new Description("Students are expected to acquire computational facilities and"
                    + " geometric intuition with regard to vectors and matrices."), new Mc(4),
                getTagSet("math and science")),
            new Module(new Code("ST2334"), new Title("Probability and Statistics"),
                new Description("This module introduces students to basic probability theory "
                    + "and statistical inference."), new Mc(4),
                getTagSet("math and science"))
        };
    }

    public static ReadOnlyModuleTracker getSampleModuleTracker() {
        ModuleTracker sampleMt = new ModuleTracker();
        for (Module sampleModule : getSampleModules()) {
            sampleMt.addModule(sampleModule);
        }
        return sampleMt;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
