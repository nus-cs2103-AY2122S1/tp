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
                    getTagSet("breadth and depth")),
            new Module(new Code("GER1000"), new Title("Quantitative reasoning"),
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
                getTagSet()),
            new Module(new Code("CS5232"), new Title("Formal Specification & Design Techniques"),
                new Description("The objective of this course is to study various formal"
                        + " specification and design techniques for modellings"), new Mc(4),
                getTagSet()),
            new Module(new Code("CS5218"), new Title("Principles and Practice of Program Analysis"),
                new Description("This module enables students to address a broad spectrum "
                        + "of program analysis in a practical way."), new Mc(4),
                getTagSet()),
            new Module(new Code("CS3219"), new Title("Software Engineering Principles and Patterns"),
                new Description("This module provides an in-depth, hands-on experience in key aspects "
                        + "of software engineering that accompany the development of software."), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS4211"), new Title("Formal Methods for Software Engineering"),
                new Description("This module will cover formal specification and verification techniques for"
                        + " accurately capturing and reasoning about requirements, model and code. "), new Mc(4),
                getTagSet("breadth and depth")),
            new Module(new Code("CS3216"), new Title("Software Development on Evolving Platforms"),
                new Description("In this module, students will practice software product engineering "),
                    new Mc(5),
                getTagSet()),
            new Module(new Code("CS3217"), new Title("Software Engineering on Modern Application Platforms"),
                new Description("This module introduces students to the practice of software engineering on modern "
                        + "application platforms such as mobile devices, the Web and cloud systems."), new Mc(5),
                getTagSet()),
            new Module(new Code("CS5219"), new Title("Automatic Software Validation"),
                new Description("This module will focus on automated validation techniques."), new Mc(4),
                getTagSet()),
            new Module(new Code("CS2220"), new Title("Introduction to Computational Biology"),
                new Description("This course aims to develop flexible and logical problem solving skills"),
                    new Mc(4),
                getTagSet()),
            new Module(new Code("CS5233"), new Title("Simulation and Modeling Techniques"),
                new Description("This module aims to provide students with a working knowledge of applying "
                        + "simulation techniques to model, simulate and study complex systems."), new Mc(4),
                getTagSet()),
            new Module(new Code("CS3241"), new Title("Computer Graphics"),
                    new Description("This module teaches some graphics hardware devices, reviews the mathematics "
                            + "related to the understanding, and discusses the fundamental "
                            + "areas of computer graphics."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3242"), new Title("3D Modelling and Animation"),
                    new Description("This module aims to provide fundamental concepts in 3D modeling and "
                            + "animation. It also serves as a bridge to advanced media modules."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3247"), new Title("Game Development"),
                    new Description("The objective of this module is to introduce techniques for electronic game"
                            + " design and programming."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4247"), new Title("Graphics Rendering Techniques"),
                    new Description("This module provides a general treatment of real-time and offline rendering"
                            + " techniques in 3D computer graphics."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4350"), new Title("Game Development Project"),
                    new Description("The objective of this project-based module is to provide an opportunity"
                            + " for the students to work in a group to design and develop a game following "
                            + "the main stages of game development process."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3218"), new Title("Multimodal Processing in Mobile Platforms"),
                    new Description("This module introduces "
                            + "multimodal processing in Mobile Platforms."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS3240"), new Title("Interaction Design"),
                    new Description("This course is intended for students in computing and related disciplines "
                            + "whose work focuses on human-computer interaction issues"
                            + " in the design of computer systems."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS3249"), new Title("User Interface Development"),
                    new Description("This module introduces User Interface Development"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4240"), new Title("Interaction Design for Virtual and Augmented Reality"),
                    new Description("This module aims to expose students to the human-centered principles"
                            + " of designing and building virtual reality (VR) and augmented"
                            + " reality (AR) applications."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4243"), new Title("Computer Vision and Pattern Recognition"),
                    new Description("This module is for undergraduates who are interested in computer"
                            + " vision and its applications."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4249"), new Title("Phenomena and Theories of HCI"),
                    new Description("This module teaches the underlying science of Human-Computer"
                            + " Interaction (HCI) and its application to user interface design."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4351"), new Title("Real-time Graphics"),
                    new Description("This module introduces Real-time Graphics"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5237"), new Title("Computational Geometry and Applications"),
                    new Description("This module introduces Computational"
                            + " Geometry and Applications"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5240"), new Title("Theoretical Foundation of Multimedia"),
                    new Description("The module lays the theoretical foundation for graduate"
                            + " students to do research in multimedia: images, videos, audio,"
                            + " speech, graphics and text documents."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5343"), new Title("Advanced Computer Animation"),
                    new Description("This modules introduces Advanced Computer Animation"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5346"), new Title("Information Visualisation"),
                    new Description("This module aims to bring together individual pedagogies"
                            + " of design, information, and computation, for teaching the analysis"
                            + " and representation of data for visualisation."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS2107"), new Title("Introduction to Information Security"),
                    new Description("This module serves as an introductory module on information"
                            + " security. It illustrates the fundamentals of how systems fail due"
                            + " to malicious activities and how they can be protected. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3235"), new Title("Computer Security"),
                    new Description("The objective of this module is to provide a broad understanding"
                            + " of computer security with some indepth discussions on selected topics in"
                            + " system and network security."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4236"), new Title("Cryptography Theory and Practice"),
                    new Description("This module aims to introduce the foundation, principles and concepts"
                            + " behind cryptology and the design of secure communication systems."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4238"), new Title("Computer Security Practice"),
                    new Description("This is a practice security module with emphasis on hands-on"
                            + " experiences of computer security."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4239"), new Title("Software Security"),
                    new Description("Software engineering processes need to include security"
                            + " considerations in the modern world. This module familiarizes students"
                            + " to security issues in different stages of the software life-cycle."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3221"), new Title("Operating Systems Design and Pragmatics"),
                    new Description("This module introduces Operating Systems "
                            + "Design and Pragmatics"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4257"), new Title("Algorithmic Foundations of Privacy"),
                    new Description("This module introduces Algorithmic Foundations of Privacy"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4276"), new Title("IoT Security"),
                    new Description("This module introduces IoT Security"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5231"), new Title("Systems Security"),
                    new Description("This module introduces fundamental notions and "
                            + "requirements in computer system security and the mechanisms that"
                            + " provide security in various systems and applications."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5250"), new Title("Advanced Operating Systems"),
                    new Description("The module covers a broad range of issues in the design"
                            + " and implementation of modern advanced operating systems."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5321"), new Title("Network Security"),
                    new Description("The objective of this module is to introduce students to"
                            + " the various issues that arise in securing the networks, and study"
                            + " the state-of-the-art techniques for addressing these challenges."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5322"), new Title("Database Security"),
                    new Description("Database security has a great impact on the design of today's"
                            + " information systems."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5331"), new Title("Web Security"),
                    new Description("This module aims to prepare graduate students for understanding"
                            + " the security of the latest web platform and its interplay with operating"
                            + " systems and the cloud infrastructure."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5332"), new Title("Biometric Authentication"),
                    new Description("Biometrics (such as fingerprint, iris images) are commonly"
                            + " used for authentication."), new Mc(4),
                    getTagSet()),
            new Module(new Code("IFS4101"), new Title("Legal Aspects of Information Security"),
                    new Description("This module examines the laws relating to information security."
                            + " The issues and considerations concerning information security"
                            + " have greatly shaped many laws, in particular, the laws relating"
                            + " to cybercrimes, electronic commerce, electronic evidence, document"
                            + " discovery, information management and data protection. These areas"
                            + " of the law have in turn altered the development and practice of"
                            + " information security in the industry."), new Mc(4),
                    getTagSet()),
            new Module(new Code("IFS4102"), new Title("Digital Forensics"),
                    new Description("Digital forensics encompasses the recovery and investigation"
                            + " of material found in digital devices in relation to cyber crime and"
                            + " other crimes where digital evidence is relevant."), new Mc(4),
                    getTagSet()),
            new Module(new Code("IFS4103"), new Title("Penetration Testing Practice"),
                    new Description("This is a practice-oriented and project-based module that provides"
                            + " a hands-on experience of performing penetration testing on a"
                            + " collaborating organisation’s system."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS2102"), new Title("Database Systems"),
                    new Description("The aim of this module is to introduce the fundamental"
                            + " concepts and techniques necessary for the understanding and practice"
                            + " of design and implementation of database applications and of the"
                            + " management of data with relational database management systems. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3223"), new Title("Database Systems Implementation"),
                    new Description("This system-oriented module provides an in-depth study of"
                            + " the concepts and implementation issues related to database"
                            + " management systems."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4221"), new Title("Database Applications Design and Tuning"),
                    new Description("This module addresses the design and performance tuning"
                            + " of database applications."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4224"), new Title("Distributed Databases"),
                    new Description("This module studies the management of data in a"
                            + " distributed environment."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4225"), new Title("Big Data Systems for Data Science"),
                    new Description("Data science incorporates varying elements and builds"
                            + " on techniques and theories from many fields, including statistics,"
                            + " data engineering, data mining, visualization, data warehousing, and"
                            + " high-performance computing systems with the goal of extracting"
                            + " meaning from big data and creating data products."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4220"), new Title("Knowledge Discovery Methods in Bioinformatics"),
                    new Description("This module is introduced to provide students with"
                            + " knowledge of techniques that can be used to analyse biological"
                            + " data to enable them to discover new knowledge."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5226"), new Title("Database Tuning"),
                    new Description("This module introduces Database Tuning"), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5228"), new Title("Knowledge Discovery and Data Mining"),
                    new Description("This course introduces fundamental principles behind"
                            + " data mining and efficient techniques for mining large databases."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS3231"), new Title("Theory of Computation"),
                    new Description("This module examines fundamental aspects of computation that every "
                            + "computer scientist should know. This module introduces techniques for precisely "
                            + "formulating problems and studying their properties using a variety of commonly "
                            + "used reasoning techniques (e.g., model equivalence, non-determinism, "
                            + "digitalisation, simulation and reduction)."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3236"), new Title("Introduction to Information Theory"),
                    new Description("This module introduces the basics of modern information theory. "
                            + "It covers how information can be quantified, and what this quantification "
                            + "tells us about how well we can compress and transmit information without error. "
                            + "It discusses basic error correcting techniques, and information-theoretic "
                            + "cryptography."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4231"), new Title("Parallel and Distributed Algorithms"),
                    new Description("This course will examine some fundamental issues in parallel programming "
                            + "and distributed computing, and the relationships between the two."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4234"), new Title("Optimisation Algorithms"),
                    new Description("This module covers common algorithmic techniques for solving optimisation "
                            + "problems, and introduces students to approaches for finding good-enough "
                            + "solutions to NP-hard problems."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3233"), new Title("Competitve Programming"),
                    new Description("This module aims to prepare students in competitive problem solving. "
                            + "It covers techniques for attacking and solving challenging computational "
                            + "problems."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4261"), new Title("Algorithmic Mechanism Design"),
                    new Description("We will explore foundational topics at the intersection of economics and "
                            + "computation, starting with the foundations of game theory: Nash equilibria, "
                            + "the theory of cooperative games, before proceeding to covering more "
                            + "advanced topics: matching algorithms, allocation of indivisible goods, "
                            + "and mechanism design."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4268"), new Title("Quantum Computing"),
                    new Description("This module will introduce basics of quantum computing and cover various "
                            + "well known algorithms e.g. Deutsch-Jozsa algorithm, Simon’s algorithms, "
                            + "quantum Fourier transform, phase estimation, order finding, Shor’s algorithm and "
                            + "Grover’s algorithm. The module will also cover some basics in quantum information "
                            + "theory, cryptography and error correction."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4269"), new Title("Fundamentals of Logic in Computer Science"),
                    new Description("This module will formally introduce and prove some of the fundamental "
                            + "results in logic to provide students with a rigorous introduction of syntax, "
                            + "semantics, decision procedures, and complexity of propositional and "
                            + "first-order logic."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4330"), new Title("Combinatorial Methods in Bioinformatics"),
                    new Description("After the complete sequencing of a number of genomes, we are in the stage "
                            + "to understand the mystery of our body, that is, we need to understand the "
                            + "information encoded in the genome and its relationship with RNA and protein. "
                            + "This aim of this module is to cover algorithms related to this stage. In the "
                            + "module, we will cover the algorithms related to genome annotation, "
                            + "motif identification, proteomics, population genetics, "
                            + "microarray, etc."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5230"), new Title("Computational Complexity"),
                    new Description("The aim of this module is to study the various measures of difficulty "
                            + "of problem solving in computing, and to introduce some techniques in theoretical "
                            + "computer science such as non-determinism, digitalisation, simulation, padding, "
                            + "reduction, randomisation and interaction."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5234"), new Title("Algorithms at Scale"),
                    new Description("This course presents advanced techniques for the design and analysis of "
                            + "algorithms and data structures, with emphasis on efficiency and scalability. "
                            + "It will cover a variety of algorithmic topics that arise when coping with very "
                            + "large data sets."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5236"), new Title("Advanced Automata Theory"),
                    new Description("This module covers automata theory in depth, describes the Chomsky hierarchy, "
                            + "and introduces various advanced topics including automata structures, automata "
                            + "on infinite words, automata on trees and the learnability of classes of regular "
                            + "languages from queries and positive data."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5330"), new Title("Randomized Algorithms"),
                    new Description("The module will cover basic concepts in the design and analysis of "
                            + "randomized algorithms. It will cover both basic techniques, such as Chernoff "
                            + "bounds, random walks, and the probabilistic method, and a variety of practical "
                            + "algorithmic applications, such as load balancing, hash functions, and graph/network "
                            + "algorithms."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS2109S"), new Title("Introduction to AI and Machine Learning (CFM)"),
                    new Description("This module introduces basic concepts in Artificial Intelligence (AI) "
                            + "and Machine Learning (ML). It adopts the perspective that planning, games, "
                            + "and learning are related types of search problems, and examines the "
                            + "underlying issues, challenges and techniques."), new Mc(4),
                    getTagSet("foundation")),
            new Module(new Code("CS3243"), new Title("Introduction to Artificial Intelligence"),
                    new Description("The module introduces the basic concepts in search and knowledge "
                            + "representation as well as to a number of sub-areas of artificial intelligence. "
                            + "It focuses on covering the essential concepts in AI."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3244"), new Title("Machine Learning"),
                    new Description("This module introduces basic concepts and algorithms in machine learning "
                            + "and neural networks."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4244"), new Title("Knowledge Representation and Reasoning"),
                    new Description("This course will focus on core issues of representation "
                            + "and reasoning of the knowledge in the context of design "
                            + "of intelligent machines."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4246"), new Title("AI Planning and Decision Making"),
                    new Description("This module introduces the major concepts and paradigms in planning and "
                            + "decision making in complex environments."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4248"), new Title("Natural Language Processing"),
                    new Description("This module deals with computer processing of human languages, including "
                            + "the use of neural networks and deep learning in natural language "
                            + "processing."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4277"), new Title("3D Computer Vision"),
                    new Description("This module covers the mathematical concepts and algorithms that allow "
                            + "us to recover the 3D geometry of the camera motions and the structures "
                            + "in its environment."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS4278"), new Title("Intelligent Robots: Algorithms and Systems"),
                    new Description("This module introduces the core algorithms and system architectures of "
                            + "intelligent robots. It examines the main system components for sensing, decision "
                            + "making, and motion control and importantly, their integration for core robot "
                            + "capabilities, such as navigation and manipulation."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5215"), new Title("Constraint Processing"),
                    new Description("This module discusses the basic aspects of constraint programming, "
                            + "focusing on how to model and solve the constraints."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5242"), new Title("Neural Networks and Deep Learning"),
                    new Description("This module provides students with the knowledge of deep neural network "
                            + "and enables them to apply deep learning methods effectively on real world "
                            + "problems."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5260"), new Title("Neural Networks and Deep Learning II"),
                    new Description("This module is a follow-up to CS5242 and covers advanced topics in "
                            + "neural networks and deep learning. This module explores the underlying mechanism "
                            + "of a variety of different types of learning models."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5340"), new Title("Uncertainty Modelling in AI"),
                    new Description("The module covers modelling methods that are suitable for reasoning with "
                            + "uncertainty. The main focus will be on probabilistic models including Bayesian "
                            + "networks and Markov networks."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5339"), new Title("Theory and Algorithms for Machine Learning"),
                    new Description("The module aims to provide a broad theoretical understanding of machine "
                            + "learning and how the theory guides the development of algorithms and "
                            + "applications."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS2108"), new Title("Introduction to Media Computing"),
                    new Description("This module introduces students to (i) the fundamental principles, "
                            + "theory, algorithms, and data structures behind digital representation, "
                            + "compression, synchronization, and processing of image, audio, and video data "
                            + "types, and (ii) challenges and issues in developing media-rich applications, "
                            + "such as media streaming and media retrieval."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3245"), new Title("Information Retrieval"),
                    new Description("This module discusses the basic concepts and methods of information "
                            + "retrieval including capturing, representing, storing, organizing, and "
                            + "retrieving unstructured or loosely structured information."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4242"), new Title("Social Media Computing"),
                    new Description("This module aims to provide students with a good understanding of the "
                            + "social network phenomena and computational skills for analysing the complex "
                            + "social relation networks between users, the contents they shared, and the ways "
                            + "contents and events are perceived and propagated through the social "
                            + "networks."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4347"), new Title("Sound and Music Computing"),
                    new Description("This module introduces the fundamental technologies employed in Sound "
                            + "and Music Computing focusing on three major categories: speech, music, "
                            + "and environmental sound."), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS5246"), new Title("Text Mining"),
                    new Description("Topics include text classification, text clustering, sentiment analysis, "
                            + "text summarization, information extraction (named entity recognition, relation "
                            + "and event extraction), and question answering. The module will emphasize the use "
                            + "of machine learning approaches to text mining."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5241"), new Title("Speech Processing"),
                    new Description("This module exposes the graduate students to the fundamental theory of "
                            + "speech processing, focusing primarily on automatic speech "
                            + "recognition."), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS3103"), new Title("Computer Networks Practice"),
                    new Description("This module aims to provide an opportunity for the students to learn"
                            + " commonly-used network protocols in greater technical depth with their "
                            + "implementation details than a basic networking course. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4222"), new Title("Wireless Networking"),
                    new Description("This module aims to provide solid foundation for students in the area of "
                            + "wireless networks and introduces students to the emerging area of "
                            + "cyber-physical-system/Internet-of-Things. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4226"), new Title("Internet Architecture"),
                    new Description("This module aims to focus on advanced networking concepts pertaining to "
                            + "the modern Internet architecture and applications. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS3237"), new Title("Introduction to Internet of Things"),
                    new Description("The Internet of Things (IoT), where a large number of physical objects"
                            + " embedded with computing power and sensors connect to the network for seamless "
                            + "cooperation between the cyber domain and the physical world, is revolutionizing "
                            + "our lives. "), new Mc(4),
                        getTagSet()),
            new Module(new Code("CS4344"), new Title("Networked and Mobile Gaming"),
                    new Description("This module aims at providing students a deep understanding of various"
                            + " technical issues pertaining to the development of networked games and mobile"
                            + " games. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5223"), new Title("Distributed Systems"),
                    new Description("The topic of Distributed Systems is now garnering increasing importance, "
                            + "especially with the advancement in technology of the Internet and WWW. The aim of"
                            + " this module is to provide students with basic concepts and principles of"
                            + " distributed operating systems, interprocess communications, distributed file"
                            + " systems, shared data, and the middleware approach. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5224"), new Title("Cloud Computing"),
                    new Description("This module aims to provide an overview of the design, management and "
                            + "application of cloud computing. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5229"), new Title("Advanced Computer Networks"),
                    new Description("This course covers advanced fundamental principles of computer networks"
                            + " and techniques for networking. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5248"), new Title("Systems Support for Continuous Media"),
                    new Description("This module is targeted at computer science graduate students and covers"
                            + " the major aspects of building streaming media applications -- from coding to "
                            + "transmission to playback. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS3210"), new Title("Parallel Computing"),
                    new Description("The aim of this module is to provide an introduction to the field of "
                            + "parallel computing with hands-on parallel programming experience on real "
                            + "parallel machines. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS4223"), new Title("Multi-core Architectures"),
                    new Description("The world of parallel computer architecture has gone through a "
                            + "significant transformation in the recent years from high-end supercomputers "
                            + "used only for scientific applications to the multi-cores (multiple processing "
                            + "cores on a single chip) that are ubiquitous in mainstream computing systems "
                            + "including desktops, servers, and embedded systems. "), new Mc(4),
                    getTagSet("breadth and depth")),
            new Module(new Code("CS5222"), new Title("Advanced Computer Architecture"),
                    new Description("The aim of this module is to introduce the state-of-the-art architectural"
                            + " advances underlying the current generation of computing systems. "), new Mc(4),
                    getTagSet()),
            new Module(new Code("CS5239"), new Title("Computer System Performance Analysis"),
                    new Description("The objective of this module is to provide students a working knowledge"
                        + " of computer performance evaluation and capacity planning. "), new Mc(4),
                    getTagSet()),

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
