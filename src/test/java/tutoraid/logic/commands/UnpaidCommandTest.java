package tutoraid.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandFailure;
import static tutoraid.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tutoraid.logic.commands.CommandTestUtil.showPersonAtIndex;
import static tutoraid.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static tutoraid.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static tutoraid.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static tutoraid.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import tutoraid.commons.core.Messages;
import tutoraid.commons.core.index.Index;
import tutoraid.model.StudentBook;
import tutoraid.model.Model;
import tutoraid.model.ModelManager;
import tutoraid.model.UserPrefs;
import tutoraid.model.student.Student;
import tutoraid.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnpaidCommand}.
 */
public class UnpaidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToEdit = model.getFilteredStudentList().get(INDEX_SECOND_PERSON.getZeroBased());
        Student editedStudent = new PersonBuilder(studentToEdit).withPaymentStatus(false).build();

        UnpaidCommand unpaidCommand = new UnpaidCommand(INDEX_SECOND_PERSON);

        String expectedMessage = String.format(UnpaidCommand.MESSAGE_SET_TO_UNPAID_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new StudentBook(model.getStudentBook()), new UserPrefs());
        expectedModel.setStudent(studentToEdit, editedStudent);

        assertCommandSuccess(unpaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        UnpaidCommand unpaidCommand = new UnpaidCommand(outOfBoundIndex);

        assertCommandFailure(unpaidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getStudentBook().getStudentList().size());

        UnpaidCommand unpaidCommand = new UnpaidCommand(outOfBoundIndex);

        assertCommandFailure(unpaidCommand, model, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnpaidCommand unpaidFirstCommand = new UnpaidCommand(INDEX_FIRST_PERSON);
        UnpaidCommand unpaidSecondCommand = new UnpaidCommand(INDEX_THIRD_PERSON);

        // same object -> returns true
        assertTrue(unpaidFirstCommand.equals(unpaidFirstCommand));

        // same values -> returns true
        UnpaidCommand unpaidFirstCommandCopy = new UnpaidCommand(INDEX_FIRST_PERSON);
        assertTrue(unpaidFirstCommand.equals(unpaidFirstCommandCopy));

        // different types -> returns false
        assertFalse(unpaidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unpaidFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(unpaidFirstCommand.equals(unpaidSecondCommand));
    }
}
