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

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Contact contact = getTypicalContacts().get(0);
    private Contact otherContact = getTypicalContacts().get(1);
    private Contact newContact = new ContactBuilder().build();


    @Test
    public void execute_singleRedo() {
        model.setAddressBook(new AddressBook());
        model.undo();

        expectedModel.setAddressBook(new AddressBook());
        expectedModel.undo();

        String expectedMessage = RedoCommand.MESSAGE_REDO_SUCCESS;
        String failureMessage = Messages.MESSAGE_INVALID_REDO_STATE;

        // single redoable state in model
        expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model, expectedMessage, expectedModel);

        // no redoable state
        assertCommandFailure(new RedoCommand(), model, failureMessage);
    }

    @Test
    public void execute_multipleConsecutiveRedo() {
        model.deleteContact(contact);
        model.deleteContact(otherContact);
        model.addContact(newContact);
        model.undo();
        model.undo();
        model.undo();

        expectedModel.deleteContact(contact);
        expectedModel.deleteContact(otherContact);
        expectedModel.addContact(newContact);
        expectedModel.undo();
        expectedModel.undo();
        expectedModel.undo();
        String successMessage = RedoCommand.MESSAGE_REDO_SUCCESS;
        String failureMessage = Messages.MESSAGE_INVALID_REDO_STATE;

        // multiple redoable states in model
        expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model, successMessage, expectedModel);

        // multiple redoable states in model
        expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model, successMessage, expectedModel);

        // single redoable state in model
        expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model, successMessage, expectedModel);

        // no redoable state
        assertCommandFailure(new RedoCommand(), model, failureMessage);
    }

    @Test
    public void execute_multipleNonConsecutiveRedo() {
        model.deleteContact(contact);
        model.addContact(newContact);
        model.undo();
        model.undo();

        expectedModel.deleteContact(contact);
        expectedModel.addContact(newContact);
        expectedModel.undo();
        expectedModel.undo();

        String successMessage = RedoCommand.MESSAGE_REDO_SUCCESS;
        String failureMessage = Messages.MESSAGE_INVALID_REDO_STATE;

        // multiple redoable states in model
        expectedModel.redo();
        assertCommandSuccess(new RedoCommand(), model, successMessage, expectedModel);

        // overwrite address book history
        model.deleteContact(otherContact);
        expectedModel.deleteContact(otherContact);

        // no readoable state
        assertCommandFailure(new RedoCommand(), model, failureMessage);
    }

    @Test
    public void execute_notRedoableModel_throwsCommandException() {
        String failureMessage = Messages.MESSAGE_INVALID_REDO_STATE;
        assertCommandFailure(new RedoCommand(), model, failureMessage);
    }

    @Test
    public void equals() {

        RedoCommand redoFirstCommand = new RedoCommand();
        RedoCommand redoSecondCommand = new RedoCommand();

        // same object -> returns true
        assertTrue(redoFirstCommand.equals(redoFirstCommand));

        // different types -> returns false
        assertFalse(redoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(redoFirstCommand.equals(null));

        // same -> returns true
        assertTrue(redoFirstCommand.equals(redoSecondCommand));
    }
}
