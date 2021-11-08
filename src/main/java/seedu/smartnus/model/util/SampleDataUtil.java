package seedu.smartnus.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.smartnus.model.ReadOnlySmartNus;
import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.note.Note;
import seedu.smartnus.model.question.Importance;
import seedu.smartnus.model.question.MultipleChoiceQuestion;
import seedu.smartnus.model.question.Name;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.question.ShortAnswerQuestion;
import seedu.smartnus.model.question.TrueFalseQuestion;
import seedu.smartnus.model.tag.Tag;

/**
 * Contains utility methods for populating {@code SmartNus} with sample data.
 */
public class SampleDataUtil {
    public static final int MCQ_QUESTION_INDEX = 0;
    public static final int TFQ_QUESTION_INDEX = 1;
    public static final int SAQ_QUESTION_INDEX = 2;
    public static Question[] getSampleQuestions() {
        return new Question[] {
            new MultipleChoiceQuestion(
                    new Name("What is the maximum line length according to the CS2103T coding standard?"),
                    new Importance("2"), getTagSet("CS2103T", "Java", "style"),
                    getChoiceSet(new Choice("120", true), new Choice("110", false),
                            new Choice("100", false), new Choice("130", false))
            ),
            new TrueFalseQuestion(
                    new Name("Grey-box test case design is a mixture of specification-based"
                            + " and implementation-based approaches?"),
                    new Importance("2"), getTagSet("CS2103T", "Java", "Testing"),
                    getChoiceSet(new Choice("True", true), new Choice("False", false))
            ),
            new ShortAnswerQuestion(
                    new Name("What is J.K. Rowling's first book about a boy wizard called?"),
                    new Importance("2"), getTagSet("Books", "Literature"),
                        getChoiceSet(new Choice("Harry Potter and the Philosopher's Stone", true,
                                new HashSet<>(List.of("Harry", "Potter"))))
            ),
            new MultipleChoiceQuestion(new Name("Which of the following logic gates is a universal gate?"),
                    new Importance("1"), getTagSet("CS2100"),
                    getChoiceSet(new Choice("NAND", true), new Choice("OR", false),
                            new Choice("XOR", false), new Choice("AND", false))
            ),
            new TrueFalseQuestion(
                    new Name("Data abstraction is removing all data-related information"
                            + " from the view to create a higher level abstraction."),
                    new Importance("2"), getTagSet("CS2103T", "Java", "Design"),
                    getChoiceSet(new Choice("True", false), new Choice("False", true))
            ),
            new ShortAnswerQuestion(
                    new Name("What organelle is commonly known as the powerhouse of the cell?"),
                    new Importance("2"), getTagSet("Biology"),
                    getChoiceSet(new Choice("Mitochondria", true,
                                new HashSet<>(List.of("Mitochondria"))))
            ),
            new MultipleChoiceQuestion(
                    new Name("What are class diagrams that are used to model the problem domain called?"),
                    new Importance("3"), getTagSet("CS2103T"),
                    getChoiceSet(new Choice("Object oriented domain models", true),
                            new Choice("Object diagrams", false), new Choice("Sequence diagrams", false),
                            new Choice("Problem domain diagrams", false))
            )
        };
    }

    public static Note[] getSampleNotes() {
        return new Note[] {
            new Note("CS2103T has PE dry run in Week 11"),
            new Note("CS2100 has assignment 3 coming up"),
            new Note("Remember to not fall behind in CS mods!"),
            new Note("Remember to do the CS2103T quiz over the weekends."),
            new Note("Complete the TP as soon as possible, it can get intense.")
        };
    }

    public static ReadOnlySmartNus getSampleSmartNus() {
        SmartNus sampleSmartNus = new SmartNus();
        for (Question sampleQuestion : getSampleQuestions()) {
            sampleSmartNus.addQuestion(sampleQuestion);
        }
        for (Note sampleNote : getSampleNotes()) {
            sampleSmartNus.addNote(sampleNote);
        }
        return sampleSmartNus;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a choice set containing the list of strings given.
     */
    public static Set<Choice> getChoiceSet(Choice... choices) {
        return Arrays.stream(choices)
                .collect(Collectors.toSet());
    }

}
