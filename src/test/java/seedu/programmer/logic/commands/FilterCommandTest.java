package seedu.programmer.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.programmer.commons.core.Messages.MESSAGE_STUDENTS_FILTERED;
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
 * Contains integration tests (interaction with the Model) for {@code FilterCommand}.
 */
public class FilterCommandTest {
    private Model model = new ModelManager(getTypicalProgrammerError(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalProgrammerError(), new UserPrefs());

    @Test
    public void test_equals_returnsCorrectly() {
        QueryStudentDescriptor firstQueryFields = new QueryStudentDescriptor("first", null, null, null);
        QueryStudentDescriptor secondQueryFields = new QueryStudentDescriptor("second", "A123", "B01", null);
        QueryStudentDescriptor thirdQueryFields = new QueryStudentDescriptor("second", "A123", null, null);

        // firstPredicate contains query field(s) : name
        StudentDetailContainsQueryPredicate firstPredicate =
                new StudentDetailContainsQueryPredicate(firstQueryFields);
        // secondPredicate contains query field(s) : name, StudentId, ClassID
        StudentDetailContainsQueryPredicate secondPredicate =
                new StudentDetailContainsQueryPredicate(secondQueryFields);
        // thirdPredicate contains query field(s) : name, StudentId (same as that of the secondPredicate)
        StudentDetailContainsQueryPredicate thirdPredicate = new StudentDetailContainsQueryPredicate(thirdQueryFields);


        FilterCommand filterFirstCommand = new FilterCommand(firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondPredicate);
        FilterCommand filterThirdCommand = new FilterCommand(thirdPredicate);

        // same object -> returns true
        assertEquals(filterFirstCommand, filterFirstCommand);

        // same values -> returns true
        FilterCommand filterFirstCommandCopy = new FilterCommand(firstPredicate);
        assertEquals(filterFirstCommand, filterFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, filterFirstCommand);

        // null -> returns false
        assertNotEquals(null, filterFirstCommand);

        // different student -> returns false
        assertNotEquals(filterFirstCommand, filterSecondCommand);

        // different student -> returns false
        assertNotEquals(filterFirstCommand, filterThirdCommand);

        // different student -> returns false
        assertNotEquals(filterSecondCommand, filterThirdCommand);
    }

    @Test
    public void execute_zeroQueryArg_allStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 7);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, null, null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE, BENSON, DANIEL, CARL, FIONA, GEORGE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleNameQueryArg_oneStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 1);
        StudentDetailContainsQueryPredicate predicate = preparePredicate("Elle", null, null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleCidQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, null, "B01", null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, ELLE), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleSidQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(null, "a021", null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleQueryArg_oneStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 1);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(
                "Pauline", "A0212425H", null, null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleQueryArg_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_FILTERED, 2);
        StudentDetailContainsQueryPredicate predicate = preparePredicate(
                null, "A02", "B0", null);
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredStudentList());
    }

    /**
     * Parses {@code name}, {@code classId} and {@code studentId} into a {@code StudentDetailContainsQueryPredicate}.
     */
    private StudentDetailContainsQueryPredicate preparePredicate(
            String name, String studentId, String classId, String email) {
        QueryStudentDescriptor queryFields = new QueryStudentDescriptor(name, studentId, classId, email);
        return new StudentDetailContainsQueryPredicate(queryFields);
    }
}
