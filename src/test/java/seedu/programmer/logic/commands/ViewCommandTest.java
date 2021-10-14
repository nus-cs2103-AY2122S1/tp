package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.programmer.commons.core.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.programmer.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.programmer.testutil.TypicalStudents.ALICE;
import static seedu.programmer.testutil.TypicalStudents.BENSON;
import static seedu.programmer.testutil.TypicalStudents.CARL;
import static seedu.programmer.testutil.TypicalStudents.DANIEL;
import static seedu.programmer.testutil.TypicalStudents.ELLE;
import static seedu.programmer.testutil.TypicalStudents.FIONA;
import static seedu.programmer.testutil.TypicalStudents.GEORGE;
import static seedu.programmer.testutil.TypicalStudents.getTypicalProgrammerError;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.programmer.model.Model;
import seedu.programmer.model.ModelManager;
import seedu.programmer.model.UserPrefs;
import seedu.programmer.model.student.QueryStudentDescriptor;
import seedu.programmer.model.student.StudentDetailContainsQueryPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code ViewCommand}.
 */
public class ViewCommandTest {
    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @Test
    public void equals() {
        QueryStudentDescriptor firstQueryFields = new QueryStudentDescriptor("first", null, null);
        QueryStudentDescriptor secondQueryFields = new QueryStudentDescriptor("second", "A123", "B01");
        QueryStudentDescriptor thirdQueryFields = new QueryStudentDescriptor("second", "A123", null);

        // firstPredicate contains query field(s) : name
        StudentDetailContainsQueryPredicate firstPredicate =
                new StudentDetailContainsQueryPredicate(firstQueryFields);
        // secondPredicate contains query field(s) : name, StudentId, ClassID
        StudentDetailContainsQueryPredicate secondPredicate =
                new StudentDetailContainsQueryPredicate(secondQueryFields);
        // thirdPredicate contains query field(s) : name, StudentId (same as that of the secondPredicate)
        StudentDetailContainsQueryPredicate thirdPredicate = new StudentDetailContainsQueryPredicate(thirdQueryFields);


        ViewCommand viewFirstCommand = new ViewCommand(firstPredicate);
        ViewCommand viewSecondCommand = new ViewCommand(secondPredicate);
        ViewCommand viewThirdCommand = new ViewCommand(thirdPredicate);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommand viewFirstCommandCopy = new ViewCommand(firstPredicate);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));

        // different student -> returns false
        assertFalse(viewFirstCommand.equals(viewThirdCommand));

        // different student -> returns false
        assertFalse(viewSecondCommand.equals(viewThirdCommand));
    }

    @Test
    public void execute_zeroQueryArg_allStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 7);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, null, null);
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleNameQueryArg_oneStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        StudentDetailContainsQueryPredicate predicate = preparePredicate("Elle", null, null);
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleCidQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, null, "B01");
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleSidQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, "a021", null);
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleQueryArg_oneStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 1);
        StudentDetailContainsQueryPredicate predicate = preparePredicate("Pauline", "A0212425H", null);
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, "A02", "B0");
        ViewCommand command = new ViewCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredStudentList());
    }

    /**
     * Parses {@code name}, {@code classId} and {@code studentId} into a {@code StudentDetailContainsQueryPredicate}.
     */
    private StudentDetailContainsQueryPredicate preparePredicate(String name, String studentId, String classId) {
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor(name, studentId, classId);
        return new StudentDetailContainsQueryPredicate(queryFields);
    }
}
