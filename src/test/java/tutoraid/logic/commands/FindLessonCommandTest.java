package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.commons.core.Messages.MESSAGE_LESSONS_LISTED_OVERVIEW;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.testutil.TypicalLessons.MATHS_ONE;
import static tutoraid.testutil.TypicalLessons.SCIENCE_ONE;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.lesson.LessonNameContainsSubstringsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindLessonCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());

    @Test
    public void equals() {
        LessonNameContainsSubstringsPredicate firstPredicate =
                new LessonNameContainsSubstringsPredicate(Collections.singletonList("first"));
        LessonNameContainsSubstringsPredicate secondPredicate =
                new LessonNameContainsSubstringsPredicate(Collections.singletonList("second"));

        FindLessonCommand findFirstStudentCommand = new FindLessonCommand(firstPredicate);
        FindLessonCommand findSecondStudentCommand = new FindLessonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstStudentCommand.equals(findFirstStudentCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindLessonCommand(firstPredicate);
        assertTrue(findFirstStudentCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstStudentCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstStudentCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstStudentCommand.equals(findSecondStudentCommand));
    }

    @Test
    public void execute_zeroKeywords_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        LessonNameContainsSubstringsPredicate predicate = preparePredicate(" ");
        FindLessonCommand command = new FindLessonCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLessonList());
    }

    @Test
    public void execute_noMatch_noLessonFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 0);
        LessonNameContainsSubstringsPredicate predicate = preparePredicate("Biology");
        FindLessonCommand command = new FindLessonCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredLessonList());
    }

    @Test
    public void execute_partialMatch_multipleLessonsFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 2);
        LessonNameContainsSubstringsPredicate predicate = preparePredicate("1");
        FindLessonCommand command = new FindLessonCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MATHS_ONE, SCIENCE_ONE), model.getFilteredLessonList());
    }

    @Test
    public void execute_matchOnlyFirstKeyword_studentFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        LessonNameContainsSubstringsPredicate predicate = preparePredicate("Math dk");
        FindLessonCommand command = new FindLessonCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MATHS_ONE), model.getFilteredLessonList());
    }

    @Test
    public void execute_caseInsensitiveMatch_studentFound() {
        String expectedMessage = String.format(MESSAGE_LESSONS_LISTED_OVERVIEW, 1);
        LessonNameContainsSubstringsPredicate predicate = preparePredicate("math");
        FindLessonCommand command = new FindLessonCommand(predicate);
        expectedModel.updateFilteredLessonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MATHS_ONE), model.getFilteredLessonList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private LessonNameContainsSubstringsPredicate preparePredicate(String userInput) {
        return new LessonNameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

