package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.testutil.TypicalLessons.getTypicalLessonBook;
import static tutoraid.testutil.TypicalStudents.BENSON;
import static tutoraid.testutil.TypicalStudents.CARL;
import static tutoraid.testutil.TypicalStudents.DANIEL;
import static tutoraid.testutil.TypicalStudents.ELLE;
import static tutoraid.testutil.TypicalStudents.FIONA;
import static tutoraid.testutil.TypicalStudents.getTypicalStudentBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.student.NameContainsSubstringsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindStudentCommandTest {
    private Model model = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStudentBook(), getTypicalLessonBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsSubstringsPredicate firstPredicate =
                new NameContainsSubstringsPredicate(Collections.singletonList("first"));
        NameContainsSubstringsPredicate secondPredicate =
                new NameContainsSubstringsPredicate(Collections.singletonList("second"));

        FindStudentCommand findFirstStudentCommand = new FindStudentCommand(firstPredicate);
        FindStudentCommand findSecondStudentCommand = new FindStudentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstStudentCommand.equals(findFirstStudentCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindStudentCommand(firstPredicate);
        assertTrue(findFirstStudentCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstStudentCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstStudentCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstStudentCommand.equals(findSecondStudentCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsSubstringsPredicate predicate = preparePredicate(" ");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_noMatch_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        NameContainsSubstringsPredicate predicate = preparePredicate("roy");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_firstName_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsSubstringsPredicate predicate = preparePredicate("Fiona");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_secondName_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsSubstringsPredicate predicate = preparePredicate("Kurz");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredStudentList());
    }

    @Test
    public void execute_keyword_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        NameContainsSubstringsPredicate predicate = preparePredicate("Meier");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_partialMatch_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        NameContainsSubstringsPredicate predicate = preparePredicate("Ku");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_partialMatchAcrossNames_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsSubstringsPredicate predicate = preparePredicate("lle Mey");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_matchOnlyFirstKeyword_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsSubstringsPredicate predicate = preparePredicate("Elle Ut");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_caseInsensitiveMatch_studentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        NameContainsSubstringsPredicate predicate = preparePredicate("elle");
        FindStudentCommand command = new FindStudentCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsSubstringsPredicate preparePredicate(String userInput) {
        return new NameContainsSubstringsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
