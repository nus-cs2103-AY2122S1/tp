package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalContacts.getTypicalContacts;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.testutil.ContactBuilder;

public class UndoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Contact contact = getTypicalContacts().get(0);
    private Contact otherContact = getTypicalContacts().get(1);
    private Contact newContact = new ContactBuilder().build();

    @Test
    public void execute_singleUndo() {
        model.setAddressBook(new AddressBook());
        expectedModel.setAddressBook(new AddressBook());
        String expectedMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        String failureMessage = Messages.MESSAGE_INVALID_UNDO_STATE;

        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, expectedMessage, expectedModel);

        // no undoable state
        assertCommandFailure(new UndoCommand(), model, failureMessage);
    }

    @Test
    public void execute_multipleUndo() {
        model.deleteContact(contact);
        model.deleteContact(otherContact);
        expectedModel.deleteContact(contact);
        expectedModel.deleteContact(otherContact);

        String successMessage = UndoCommand.MESSAGE_UNDO_SUCCESS;
        String failureMessage = Messages.MESSAGE_INVALID_UNDO_STATE;

        // multiple undoable states in model
        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, successMessage, expectedModel);

        // single undoable state in model
        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, successMessage, expectedModel);

        // no undoable state
        assertCommandFailure(new UndoCommand(), model, failureMessage);

        // overwrite address book history
        model.addContact(newContact);
        expectedModel.addContact(newContact);

        // single undoable state after overwriting address book history
        expectedModel.undo();
        assertCommandSuccess(new UndoCommand(), model, successMessage, expectedModel);

        assertCommandFailure(new UndoCommand(), model, failureMessage);
    }


    @Test
    public void execute_notUndoableModel_throwsCommandException() {
        String failureMessage = Messages.MESSAGE_INVALID_UNDO_STATE;
        assertCommandFailure(new UndoCommand(), model, failureMessage);
    }


    @Test
    public void equals() {

        UndoCommand undoFirstCommand = new UndoCommand();
        UndoCommand undoSecondCommand = new UndoCommand();

        // same object -> returns true
        assertTrue(undoFirstCommand.equals(undoFirstCommand));

        // different types -> returns false
        assertFalse(undoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(undoFirstCommand.equals(null));

        // same -> returns true
        assertTrue(undoFirstCommand.equals(undoSecondCommand));
    }

}
