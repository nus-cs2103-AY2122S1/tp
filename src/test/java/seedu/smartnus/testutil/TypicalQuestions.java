package seedu.smartnus.testutil;

import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_ANSWER_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_ANSWER_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_OPTION_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_5;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_6;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_7;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import seedu.smartnus.model.SmartNus;
import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.question.Question;
import seedu.smartnus.model.statistic.Statistic;

/**
 * A utility class containing a list of {@code Question} objects to be used in tests.
 */
public class TypicalQuestions {
    public static final int MCQ_ONE_BASED_INDEX = 6;
    public static final int TF_ONE_BASED_INDEX = 7;
    public static final int SAQ_ONE_BASED_INDEX = 8;

    public static final Question MCQ_QUESTION_1 = new QuestionBuilder().withName(VALID_QUESTION_2)
            .withImportance(VALID_IMPORTANCE_1)
            .withChoices(new Choice(VALID_OPTION_1, false), new Choice(VALID_OPTION_3, false),
                    new Choice(VALID_OPTION_4, false), new Choice(VALID_ANSWER_1, true))
            .build();
    public static final Question MCQ_QUESTION_2 = new QuestionBuilder().withName(VALID_QUESTION_3)
            .withChoices(new Choice(VALID_OPTION_1, false), new Choice(VALID_OPTION_3, false),
                    new Choice(VALID_OPTION_4, false), new Choice(VALID_ANSWER_2, true))
            .withImportance(VALID_IMPORTANCE_2)
            .withChoices(new Choice("good guy", true),
                    new Choice("wrong good guy", false),
                    new Choice("wrong 2", false),
                    new Choice("wrong 3", false)).build();
    public static final Question MCQ_QUESTION_3 = new QuestionBuilder().withName(VALID_QUESTION_1)
            .withImportance(VALID_IMPORTANCE_1)
            .withChoices(new Choice(VALID_ANSWER_1, true),
                    new Choice(VALID_OPTION_1, false), new Choice(VALID_OPTION_4, false),
                    new Choice(VALID_OPTION_3, false))
            .withTags(VALID_TAG_1)
            .withStatistic(new Statistic(4, 2)).build();
    public static final Question TF_QUESTION_1 = new QuestionBuilder().withName(VALID_QUESTION_5)
            .withImportance(VALID_IMPORTANCE_2)
            .withChoices(new Choice(Choice.TRUE_CHOICE_TITLE, true),
                    new Choice(Choice.FALSE_CHOICE_TITLE, false))
            .withTags(VALID_TAG_2)
            .withStatistic(new Statistic(1, 1)).buildTrueFalse();
    public static final Question TF_QUESTION_2 = new QuestionBuilder().withName(VALID_QUESTION_6)
            .withChoices(new Choice(Choice.TRUE_CHOICE_TITLE, false),
                    new Choice(Choice.FALSE_CHOICE_TITLE, true))
            .withImportance(VALID_IMPORTANCE_2)
            .buildTrueFalse();
    public static final Question TF_QUESTION_3 = new QuestionBuilder().withName("Is MIPS CISC?")
            .withChoices(new Choice(Choice.TRUE_CHOICE_TITLE, false),
                    new Choice(Choice.FALSE_CHOICE_TITLE, true))
            .withImportance(VALID_IMPORTANCE_1)
            .buildTrueFalse();
    public static final Question SAQ_QUESTION_1 = new QuestionBuilder()
            .withName("Harry, you're a wizard. Where is this quote from?")
            .withImportance(VALID_IMPORTANCE_2)
            .withChoices(new Choice("Harry Potter and the Philosopher's Stone",
                    true, new HashSet<>(List.of("harry", "potter")))).buildSaq();
    public static final Question SAQ_QUESTION_2 = new QuestionBuilder().withName("Who is harry potter's godfather?")
            .withImportance(VALID_IMPORTANCE_2)
            .withChoices(new Choice("Sirius Black",
                    true, new HashSet<>(List.of("sirius", "black"))))
            .buildSaq();
    public static final Question SAQ_QUESTION_3 = new QuestionBuilder()
            .withName(VALID_QUESTION_7)
            .withImportance(VALID_IMPORTANCE_2)
            .withChoices(new Choice("Cedric#12 #!@Diggory(,)><",
                    true, new HashSet<>(List.of("cedric", "diggory"))))
            .buildSaq();
    public static final Question SAQ_QUESTION_4 = new QuestionBuilder()
            .withName(VALID_QUESTION_1)
            .withImportance(VALID_IMPORTANCE_1)
            .withChoices(new Choice("J. K. Rowling", true, new HashSet<>(List.of("rowling"))))
            .buildSaq();

    // Manually added - Question's details found in {@code CommandTestUtil}
    public static final Question STORAGE_QUESTION_1 = new QuestionBuilder().withName(VALID_QUESTION_3)
            .withImportance(VALID_IMPORTANCE_1)
            .withChoices(
                    new Choice(VALID_ANSWER_1, true),
                    new Choice(VALID_OPTION_1, false),
                    new Choice(VALID_OPTION_3, false),
                    new Choice(VALID_OPTION_4, false)).build();
    public static final Question STORAGE_QUESTION_2 = new QuestionBuilder().withName(VALID_QUESTION_4)
            .withImportance(VALID_IMPORTANCE_2)
            .withTags(VALID_TAG_3, VALID_TAG_4)
            .withChoices(new Choice("Lily Potter",
                    true, new HashSet<>(List.of("lily", "potter"))))
            .buildSaq();

    private TypicalQuestions() {} // prevents instantiation

    /**
     * Returns an {@code SmartNus} with all the typical questions.
     */
    public static SmartNus getTypicalSmartNusQuestions() {
        SmartNus ab = new SmartNus();
        for (Question question : getTypicalQuestions()) {
            ab.addQuestion(question);
        }
        return ab;
    }

    public static List<Question> getTypicalQuestions() {
        return new ArrayList<>(Arrays.asList(MCQ_QUESTION_1, MCQ_QUESTION_2, TF_QUESTION_2, TF_QUESTION_3,
                SAQ_QUESTION_2, MCQ_QUESTION_3, TF_QUESTION_1, SAQ_QUESTION_1));
    }
}
