package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;

public class ViewCommandIndexTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Contact contactToView = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        ViewCommandIndex viewCommand = new ViewCommandIndex(INDEX_FIRST_CONTACT);

        String expectedMessage = String.format(ViewCommandIndex.MESSAGE_VIEW_CONTACT_SUCCESS, contactToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, contactToView);

        Model expectedModel = model;

        assertCommandSuccess(viewCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index invalidIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        ViewCommandIndex viewCommand = new ViewCommandIndex(invalidIndex);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewCommandIndex viewFirstCommand = new ViewCommandIndex(INDEX_FIRST_CONTACT);
        ViewCommandIndex viewSecondCommand = new ViewCommandIndex(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommandIndex viewFirstCommandCopy = new ViewCommandIndex(INDEX_FIRST_CONTACT);
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }

}
