package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_ANSWER_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.model.choice.Choice.FALSE_CHOICE_TITLE;
import static seedu.smartnus.model.choice.Choice.TRUE_CHOICE_TITLE;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.choice.Choice;

class TrueFalseQuestionTest {

    private static TrueFalseQuestion validTfQuestion = null;

    @BeforeEach
    void setUp() {
        Set<Choice> validTfqChoices = new HashSet<>();
        validTfqChoices.add(new Choice(TRUE_CHOICE_TITLE, false));
        validTfqChoices.add(new Choice(FALSE_CHOICE_TITLE, true));

        validTfQuestion = new TrueFalseQuestion(new Name(VALID_QUESTION_1),
                new Importance(VALID_IMPORTANCE_1), new HashSet<>(), validTfqChoices);
    }

    @Test
    void isValidQuestion_validAnswers_true() {
        assertTrue(validTfQuestion.isValidQuestion());
    }

    @Test
    void isValidTrueFalseChoice_validChoice_true() {
        TrueFalseQuestion.isValidTrueFalseChoice(new Choice(TRUE_CHOICE_TITLE, false));
        TrueFalseQuestion.isValidTrueFalseChoice(new Choice(FALSE_CHOICE_TITLE, true));
    }

    @Test
    void isValidTrueFalseChoice_invalidChoice_false() {
        TrueFalseQuestion.isValidTrueFalseChoice(new Choice(VALID_ANSWER_1, false));
    }
}
