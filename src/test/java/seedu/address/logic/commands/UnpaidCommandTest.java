package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnpaidCommand}.
 */
public class UnpaidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Student studentToEdit = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Student editedStudent = new PersonBuilder(studentToEdit).withPaymentStatus(false).build();

        UnpaidCommand unpaidCommand = new UnpaidCommand(INDEX_SECOND_PERSON);

        String expectedMessage = String.format(UnpaidCommand.MESSAGE_SET_TO_UNPAID_SUCCESS, editedStudent);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(studentToEdit, editedStudent);

        assertCommandSuccess(unpaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnpaidCommand unpaidCommand = new UnpaidCommand(outOfBoundIndex);

        assertCommandFailure(unpaidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnpaidCommand unpaidCommand = new UnpaidCommand(outOfBoundIndex);

        assertCommandFailure(unpaidCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
