package seedu.smartnus.model.question;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_IMPORTANCE_2;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_QUESTION_4;
import static seedu.smartnus.logic.commands.CommandTestUtil.VALID_TAG_3;
import static seedu.smartnus.testutil.Assert.assertThrows;
import static seedu.smartnus.testutil.TypicalQuestions.MCQ_QUESTION_1;
import static seedu.smartnus.testutil.TypicalQuestions.STORAGE_QUESTION_2;

import org.junit.jupiter.api.Test;

import seedu.smartnus.model.choice.Choice;
import seedu.smartnus.model.tag.Tag;
import seedu.smartnus.testutil.QuestionBuilder;

public class QuestionTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Question question = new QuestionBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> question.getTags().remove(0));
    }

    @Test
    public void isSameQuestion() {
        // same object -> returns true
        assertTrue(MCQ_QUESTION_1.isSameQuestion(MCQ_QUESTION_1));

        // null -> returns false
        assertFalse(MCQ_QUESTION_1.isSameQuestion(null));

        // same name, all other attributes different -> returns true
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withImportance(VALID_IMPORTANCE_2)
                .withTags(VALID_TAG_3).build();
        assertTrue(MCQ_QUESTION_1.isSameQuestion(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withName(VALID_QUESTION_4).build();
        assertFalse(MCQ_QUESTION_1.isSameQuestion(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Question editedBob = new QuestionBuilder(STORAGE_QUESTION_2).withName(VALID_QUESTION_4.toLowerCase()).build();
        assertFalse(STORAGE_QUESTION_2.isSameQuestion(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_QUESTION_4 + " ";
        editedBob = new QuestionBuilder(STORAGE_QUESTION_2).withName(nameWithTrailingSpaces).build();
        assertFalse(STORAGE_QUESTION_2.isSameQuestion(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Question aliceCopy = new QuestionBuilder(MCQ_QUESTION_1).build();
        assertTrue(MCQ_QUESTION_1.equals(aliceCopy));

        // same object -> returns true
        assertTrue(MCQ_QUESTION_1.equals(MCQ_QUESTION_1));

        // null -> returns false
        assertFalse(MCQ_QUESTION_1.equals(null));

        // different type -> returns false
        assertFalse(MCQ_QUESTION_1.equals(5));
        // added to test code coverage
        assertFalse(MCQ_QUESTION_1.equals(new Tag("abc")));

        // different question -> returns false
        assertFalse(MCQ_QUESTION_1.equals(STORAGE_QUESTION_2));

        // different name -> returns false
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withName(VALID_QUESTION_4).build();
        assertFalse(MCQ_QUESTION_1.equals(editedAlice));

        // different importance -> returns false
        editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withImportance(VALID_IMPORTANCE_2).build();
        assertFalse(MCQ_QUESTION_1.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3).build();
        assertFalse(MCQ_QUESTION_1.equals(editedAlice));
    }

    @Test
    public void hashCode_test() {
        Question aliceQuestion = new QuestionBuilder(MCQ_QUESTION_1).build();
        Question aliceCopy = new QuestionBuilder(MCQ_QUESTION_1).build();

        assertEquals(aliceQuestion.hashCode(), aliceCopy.hashCode());

        Question bobQuestion = new QuestionBuilder(STORAGE_QUESTION_2).build();
        assertNotEquals(aliceQuestion.hashCode(), bobQuestion.hashCode());

        // different importance -> returns false
        Question editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withImportance(VALID_IMPORTANCE_2).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());

        // different tags -> returns false
        editedAlice = new QuestionBuilder(MCQ_QUESTION_1).withTags(VALID_TAG_3).build();
        assertNotEquals(aliceQuestion.hashCode(), editedAlice.hashCode());
    }

    @Test
    public void isValidQuestion_lessThanThreeIncorrectOptionsMcq_false() {
        Question invalidMcq = new QuestionBuilder().withChoices(
                new Choice("answer", true), new Choice("option 1", false)
        ).build();
        assertFalse(invalidMcq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_noCorrectOption_false() {
        Question invalidMcq = new QuestionBuilder().withChoices(
                new Choice("option 2", false), new Choice("option 1", false),
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();
        assertFalse(invalidMcq.isValidQuestion());
    }

    @Test
    public void isValidQuestion_validMcq_true() {
        Question validMcq = new QuestionBuilder().withChoices(
                new Choice("option 2", true), new Choice("option 1", false),
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();
        assertTrue(validMcq.isValidQuestion());
    }

    @Test
    public void question_statistic_test() {

        Choice correctChoice = new Choice("option 1", true);
        Choice wrongChoice = new Choice("option 2", false);

        Question validMcq = new QuestionBuilder().withChoices(
                correctChoice, wrongChoice,
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();

        // test attempt count
        assertEquals(0, validMcq.getStatistic().getAttemptCount());
        // test correct count
        assertEquals(0, validMcq.getStatistic().getCorrectCount());

        // test give correct choice and update statistic
        validMcq.attemptAndCheckAnswer(correctChoice);
        assertEquals(1, validMcq.getStatistic().getAttemptCount());
        assertEquals(1, validMcq.getStatistic().getCorrectCount());

        // test give wrong choice and update statistic
        validMcq.attemptAndCheckAnswer(wrongChoice);
        assertEquals(2, validMcq.getStatistic().getAttemptCount());
        assertEquals(1, validMcq.getStatistic().getCorrectCount());

    }

    @Test
    public void question_findChoiceByTitle() {
        Choice expectedChoice = new Choice("option 1", true);

        Question validMcq = new QuestionBuilder().withChoices(
                expectedChoice, new Choice("option 2", false),
                new Choice("option 3", false), new Choice("option 4", false)
        ).build();

        // finds correct choice
        Choice actualChoice = validMcq.findChoiceByTitle("option 1");
        assertEquals(expectedChoice, actualChoice);

        // does not find choice and returns null
        Choice notFoundChoice = validMcq.findChoiceByTitle(" ");
        assertNull(notFoundChoice);
    }
}
