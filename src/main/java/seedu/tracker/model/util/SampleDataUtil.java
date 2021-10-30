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
                            + "computational problem solving, and is the first and foremost"
                            + " introductory module to computing."),
                    new Mc(4),
                    getTagSet("foundation")),
            new Module(new Code("CS1231S"), new Title("Discrete Structures"),
                    new Description("This module introduces mathematical tools required in the"
                            + " study of computer science."),
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
                            + "and links it with contemporary operating systems"
                            + " (eg. Unix/Linux and Windows)."), new Mc(4),
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
                            + "representation as well as to a number of sub-areas of"
                            + " artificial intelligence."), new Mc(4),
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
                            + "analysis of assumptions and assertions on issues"
                            + " facing the Information Age."), new Mc(4),
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
                getTagSet("math and science")),
            new Module(new Code("CS2104"), new Title("Programming Language Concepts"),
                new Description("This module introduces the concepts that serve as a basis"
                    + "for hundreds of programming languages."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3211"), new Title("Parallel and Concurrent Programming"),
                new Description("This module introduces the design, development and debugging"
                    + " of parallel programs."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS4212"), new Title("Compiler Design"),
                new Description("The objective of this module is to introduce the principal"
                        + " ideas behind program compilation."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS4215"), new Title("Programming Language Implementation"),
                new Description("This module provides the students with theoretical knowledge"
                        + " and practical skill in the implementation of programming languages."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3234"), new Title("Logic for Proofs and Programs"),
                new Description("This module introduces logic as a means for specifying and "
                        + "solving computational problems."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS5232"), new Title("Formal Specification & Design Techniques"),
                new Description("The objective of this course is to study various formal"
                        + " specification and design techniques for modellings"), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS5218"), new Title("Principles and Practice of Program Analysis"),
                new Description("This module enables students to address a broad spectrum "
                        + "of program analysis in a practical way."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3219"), new Title("Software Engineering Principles and Patterns"),
                new Description("This module provides an in-depth, hands-on experience in key aspects "
                        + "of software engineering that accompany the development of software."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS4211"), new Title("Formal Methods for Software Engineering"),
                new Description("This module will cover formal specification and verification techniques for"
                        + " accurately capturing and reasoning about requirements, model and code. "), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3216"), new Title("Software Development on Evolving Platforms"),
                new Description("In this module, students will practice software product engineering ")
                    , new Mc(5),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3217"), new Title("Software Engineering on Modern Application Platforms"),
                new Description("This module introduces students to the practice of software engineering on modern "
                        + "application platforms such as mobile devices, the Web and cloud systems."), new Mc(5),
                getTagSet("breadth and depth")),
            new Module(new Code("CS5219"), new Title("Automatic Software Validation"),
                new Description("This module will focus on automated validation techniques.")
                    , new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS2220"), new Title("Introduction to Computational Biology"),
                new Description("This course aims to develop flexible and logical problem solving skills")
                    , new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS5233"), new Title("Simulation and Modeling Techniques"),
                new Description("This module aims to provide students with a working knowledge of applying "
                        +"simulation techniques to model, simulate and study complex systems."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3241"), new Title("Computer Graphics"),
                    new Description("This module teaches some graphics hardware devices, reviews the mathematics "
                            + "related to the understanding, and discusses the fundamental "
                            + "areas of computer graphics."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3242"), new Title("3D Modelling and Animation"),
                    new Description("This module aims to provide fundamental concepts in 3D modeling and "
                            + "animation. It also serves as a bridge to advanced media modules."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3247"), new Title("Game Development"),
                    new Description("The objective of this module is to introduce techniques for electronic game"
                            + " design and programming."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4247"), new Title("Graphics Rendering Techniques"),
                    new Description("This module provides a general treatment of real-time and offline rendering"
                            + " techniques in 3D computer graphics."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4350"), new Title("Game Development Project"),
                    new Description("The objective of this project-based module is to provide an opportunity"
                            + " for the students to work in a group to design and develop a game following "
                            + "the main stages of game development process."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3218"), new Title("Multimodal Processing in Mobile Platforms"),
                    new Description("This module introduces "
                            + "multimodal processing in Mobile Platforms."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3240"), new Title("Interaction Design"),
                    new Description("This course is intended for students in computing and related disciplines "
                            + "whose work focuses on human-computer interaction issues"
                            + " in the design of computer systems."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3249"), new Title("User Interface Development"),
                    new Description("This module introduces User Interface Development"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4240"), new Title("Interaction Design for Virtual and Augmented Reality"),
                    new Description("This module aims to expose students to the human-centered principles"
                            + " of designing and building virtual reality (VR) and augmented"
                            + " reality (AR) applications."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4243"), new Title("Computer Vision and Pattern Recognition"),
                    new Description("This module is for undergraduates who are interested in computer"
                            + " vision and its applications."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4249"), new Title("Phenomena and Theories of HCI"),
                    new Description("This module teaches the underlying science of Human-Computer"
                            + " Interaction (HCI) and its application to user interface design."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4351"), new Title("Real-time Graphics"),
                    new Description("This module introduces Real-time Graphics"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5237"), new Title("Computational Geometry and Applications"),
                    new Description("This module introduces Computational"
                            + " Geometry and Applications"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5240"), new Title("Theoretical Foundation of Multimedia"),
                    new Description("The module lays the theoretical foundation for graduate"
                            + " students to do research in multimedia: images, videos, audio,"
                            + " speech, graphics and text documents."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5343"), new Title("Advanced Computer Animation"),
                    new Description("This modules introduces Advanced Computer Animation"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5346"), new Title("Information Visualisation"),
                    new Description("This module aims to bring together individual pedagogies"
                            + " of design, information, and computation, for teaching the analysis"
                            + " and representation of data for visualisation."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS2107"), new Title("Introduction to Information Security"),
                    new Description("This module serves as an introductory module on information"
                            + " security. It illustrates the fundamentals of how systems fail due"
                            + " to malicious activities and how they can be protected. "), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3235"), new Title("Computer Security"),
                    new Description("The objective of this module is to provide a broad understanding"
                            + " of computer security with some indepth discussions on selected topics in"
                            + " system and network security."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4236"), new Title("Cryptography Theory and Practice"),
                    new Description("This module aims to introduce the foundation, principles and concepts"
                            + " behind cryptology and the design of secure communication systems."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4238"), new Title("Computer Security Practice"),
                    new Description("This is a practice security module with emphasis on hands-on"
                            + " experiences of computer security."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4239"), new Title("Software Security"),
                    new Description("Software engineering processes need to include security"
                            + " considerations in the modern world. This module familiarizes students"
                            + " to security issues in different stages of the software life-cycle."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3221"), new Title("Operating Systems Design and Pragmatics"),
                    new Description("This module introduces Operating Systems "
                            + "Design and Pragmatics"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4257"), new Title("Algorithmic Foundations of Privacy"),
                    new Description("This module introduces Algorithmic Foundations of Privacy"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4276"), new Title("IoT Security"),
                    new Description("This module introduces IoT Security"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5231"), new Title("Systems Security"),
                    new Description("This module introduces fundamental notions and "
                            + "requirements in computer system security and the mechanisms that"
                            + " provide security in various systems and applications."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5250"), new Title("Advanced Operating Systems"),
                    new Description("The module covers a broad range of issues in the design"
                            + " and implementation of modern advanced operating systems."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5321"), new Title("Network Security"),
                    new Description("The objective of this module is to introduce students to"
                            + " the various issues that arise in securing the networks, and study"
                            + " the state-of-the-art techniques for addressing these challenges."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5322"), new Title("Database Security"),
                    new Description("Database security has a great impact on the design of today's"
                            + " information systems."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5331"), new Title("Web Security"),
                    new Description("This module aims to prepare graduate students for understanding"
                            + " the security of the latest web platform and its interplay with operating"
                            + " systems and the cloud infrastructure."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5332"), new Title("Biometric Authentication"),
                    new Description("Biometrics (such as fingerprint, iris images) are commonly"
                            + " used for authentication."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("IFS4101"), new Title("Legal Aspects of Information Security"),
                    new Description("This module examines the laws relating to information security."
                            + " The issues and considerations concerning information security"
                            + " have greatly shaped many laws, in particular, the laws relating"
                            + " to cybercrimes, electronic commerce, electronic evidence, document"
                            + " discovery, information management and data protection. These areas"
                            + " of the law have in turn altered the development and practice of"
                            + " information security in the industry."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("IFS4102"), new Title("Digital Forensics"),
                    new Description("Digital forensics encompasses the recovery and investigation"
                            + " of material found in digital devices in relation to cyber crime and"
                            + " other crimes where digital evidence is relevant."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("IFS4103"), new Title("Penetration Testing Practice"),
                    new Description("This is a practice-oriented and project-based module that provides"
                            + " a hands-on experience of performing penetration testing on a"
                            + " collaborating organisation’s system."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS2102"), new Title("Database Systems"),
                    new Description("The aim of this module is to introduce the fundamental"
                            + " concepts and techniques necessary for the understanding and practice"
                            + " of design and implementation of database applications and of the"
                            + " management of data with relational database management systems. "), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS3223"), new Title("Database Systems Implementation"),
                    new Description("This system-oriented module provides an in-depth study of"
                            + " the concepts and implementation issues related to database"
                            + " management systems."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4221"), new Title("Database Applications Design and Tuning"),
                    new Description("This module addresses the design and performance tuning"
                            + " of database applications."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4224"), new Title("Distributed Databases"),
                    new Description("This module studies the management of data in a"
                            + " distributed environment."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4225"), new Title("Big Data Systems for Data Science"),
                    new Description("Data science incorporates varying elements and builds"
                            + " on techniques and theories from many fields, including statistics,"
                            + " data engineering, data mining, visualization, data warehousing, and"
                            + " high-performance computing systems with the goal of extracting"
                            + " meaning from big data and creating data products."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS4220"), new Title("Knowledge Discovery Methods in Bioinformatics"),
                    new Description("This module is introduced to provide students with"
                            + " knowledge of techniques that can be used to analyse biological"
                            + " data to enable them to discover new knowledge."), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5226"), new Title("Database Tuning"),
                    new Description("This module introduces Database Tuning"), new Mc(4),
                    getTagSet("breath and depth")),
            new Module(new Code("CS5228"), new Title("Knowledge Discovery and Data Mining"),
                    new Description("This course introduces fundamental principles behind"
                            + " data mining and efficient techniques for mining large databases."), new Mc(4),
                    getTagSet("breath and depth"))
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
