package nustracker.logic.commands;

import static nustracker.commons.core.Messages.MESSAGE_STUDENT_LIST_NOT_SHOWN;
import static nustracker.logic.commands.CommandTestUtil.assertCommandFailureShownEventList;
import static nustracker.logic.commands.CommandTestUtil.assertCommandFailureShownStudentList;
import static nustracker.logic.commands.CommandTestUtil.assertCommandSuccess;
import static nustracker.logic.commands.DeleteFilteredStudentsCommand.MESSAGE_AT_LEAST_ONE_STUDENT_REQ_FOR_DELFILTER;
import static nustracker.testutil.TypicalAddressBook.getTypicalAddressBook;
import static nustracker.testutil.TypicalEvents.MATH_OLYMPIAD;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import nustracker.model.Model;
import nustracker.model.ModelManager;
import nustracker.model.UserPrefs;
import nustracker.model.student.EnrolledEventsContainsKeywordsPredicate;
import nustracker.model.student.Student;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteFilteredStudentsCommand}.
 */
public class DeleteFilteredStudentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_unfilteredList_success() {
        model.updateFilteredStudentList(currStudent -> true);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredStudentList(currStudent -> true);

        ObservableList<Student> refList = expectedModel.getFilteredStudentList();
        ArrayList<Student> newList = new ArrayList<>();

        for (Student currStudent : refList) {
            newList.add(currStudent);
        }


        for (Student currStudent : newList) {
            expectedModel.deleteStudent(currStudent);
        }

        DeleteFilteredStudentsCommand deleteFilteredStudentsCommand = new DeleteFilteredStudentsCommand();

        assertCommandSuccess(deleteFilteredStudentsCommand, model,
                DeleteFilteredStudentsCommand.MESSAGE_DELETE_ALL_FILTERED_SUCCESS, expectedModel);

    }

    @Test
    public void execute_filteredList_success() {
        // Filter by MATH_OLYMPIAD since TypicalStudents has a few student enrolled in it
        EnrolledEventsContainsKeywordsPredicate mathOlympPred =
                new EnrolledEventsContainsKeywordsPredicate(MATH_OLYMPIAD.getName());

        model.updateFilteredStudentList(mathOlympPred);
        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updateFilteredStudentList(currStudent -> true);

        ObservableList<Student> refList = expectedModel.getFilteredStudentList();
        ArrayList<Student> newList = new ArrayList<>();

        for (Student currStudent : refList) {
            if (currStudent.getEvents().isEnrolledInEvent(MATH_OLYMPIAD.getName())) {
                newList.add(currStudent);
            }
        }


        for (Student currStudent : newList) {
            expectedModel.deleteStudent(currStudent);
        }

        DeleteFilteredStudentsCommand deleteFilteredStudentsCommand = new DeleteFilteredStudentsCommand();

        assertCommandSuccess(deleteFilteredStudentsCommand, model,
                DeleteFilteredStudentsCommand.MESSAGE_DELETE_ALL_FILTERED_SUCCESS, expectedModel);

    }

    @Test
    public void execute_unfilteredListNoStudents_throwsCommandException() {
        ModelManager currModel = new ModelManager();


        DeleteFilteredStudentsCommand deleteFilteredStudentsCommand = new DeleteFilteredStudentsCommand();

        assertCommandFailureShownStudentList(deleteFilteredStudentsCommand, currModel,
                MESSAGE_AT_LEAST_ONE_STUDENT_REQ_FOR_DELFILTER);

    }

    @Test
    public void execute_originalListHasStudentsFilteredListNoStudents_throwsCommandException() {
        model.updateFilteredStudentList(currStudent -> false);

        DeleteFilteredStudentsCommand deleteFilteredStudentsCommand = new DeleteFilteredStudentsCommand();

        assertCommandFailureShownStudentList(deleteFilteredStudentsCommand, model,
                MESSAGE_AT_LEAST_ONE_STUDENT_REQ_FOR_DELFILTER);
    }

    @Test
    public void execute_unfilteredListWhenEventsShown_throwsCommandException() {
        DeleteFilteredStudentsCommand cmd = new DeleteFilteredStudentsCommand();

        assertCommandFailureShownEventList(cmd, model, MESSAGE_STUDENT_LIST_NOT_SHOWN);
    }




    @Test
    public void equals() {
        DeleteFilteredStudentsCommand cmd1 = new DeleteFilteredStudentsCommand();
        DeleteFilteredStudentsCommand cmd2 = new DeleteFilteredStudentsCommand();

        // same object -> returns true
        assertTrue(cmd1.equals(cmd1));

        // same command different object -> returns true
        assertTrue(cmd1.equals(cmd2));

        // different types -> returns false
        assertFalse(cmd1.equals(1));

        // null -> returns false
        assertFalse(cmd1.equals(null));

    }








}
