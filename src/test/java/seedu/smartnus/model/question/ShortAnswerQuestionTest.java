package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_1;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_SAQ_ANSWER_1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.testutil.QuestionBuilder;


public class ShortAnswerQuestionTest {
    private static ShortAnswerQuestion validSaq;

    @BeforeEach
    void setUp() {
        Set<Choice> validSaqChoices = new HashSet<>();
        validSaqChoices.add(new Choice(VALID_SAQ_ANSWER_1, true, new HashSet<>(List.of("j", "Rowling"))));
        validSaq = new ShortAnswerQuestion(new Name(VALID_QUESTION_1),
                new Importance(VALID_IMPORTANCE_1), new HashSet<>(), validSaqChoices);
    }

    @Test
    public void isValidQuestion_validSaq_true() {
        assertTrue(validSaq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_notExactlyOneAnswer_false() {
        Set<Choice> invalidSaqChoices = new HashSet<>();
        invalidSaqChoices.add(new Choice(VALID_SAQ_ANSWER_1, true, new HashSet<>(List.of("j", "Rowling"))));
        invalidSaqChoices.add(new Choice("answer2", true, new HashSet<>(List.of("answer2"))));
        ShortAnswerQuestion invalidSaq = new ShortAnswerQuestion(new Name(VALID_QUESTION_1),
                new Importance(VALID_IMPORTANCE_1), new HashSet<>(), invalidSaqChoices);
        assertFalse(invalidSaq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_answerHasNoKeywords_false() {
        ShortAnswerQuestion invalidSaq = new QuestionBuilder().withChoices(
                new Choice(VALID_SAQ_ANSWER_1, true, new HashSet<>())).buildSaq();
        assertFalse(invalidSaq.isValidQuestion());
    }

    @Test
    public void attemptAndCheckAnswer_correctAnswer_true() {
        Choice correctAns1 = new Choice("J K Rowling", true);
        Choice correctAns2 = new Choice("abcrowling jdef", true);
        assertTrue(validSaq.attemptAndCheckAnswer(correctAns1));
        assertTrue(validSaq.attemptAndCheckAnswer(correctAns2));
    }

    @Test
    public void attemptAndCheckAnswer_wrongAnswer_false() {
        Choice wrongAns1 = new Choice("J K Rowl", true);
        Choice wrongAns2 = new Choice("J K", true);
        assertFalse(validSaq.attemptAndCheckAnswer(wrongAns1));
        assertFalse(validSaq.attemptAndCheckAnswer(wrongAns2));
    }

    @Test
    public void getKeywordsFormattedString() {
        String expectedString = "Rowling, j";
        assertEquals(validSaq.getKeywordsFormattedString(), expectedString);
    }
}
