package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalContacts.getTypicalContacts;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;

public class ViewCommandNameTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validName_success() {
        Contact contactToView = model.getFilteredContactList().get(0);
        Name validName = contactToView.getName();

        ViewCommandName viewCommand = new ViewCommandName(validName);

        String expectedMessage = String.format(ViewCommandName.MESSAGE_VIEW_CONTACT_SUCCESS, contactToView);
        CommandResult expectedCommandResult = new CommandResult(expectedMessage, contactToView);

        assertCommandSuccess(viewCommand, model, expectedCommandResult, model);
    }

    @Test
    public void execute_invalidName_throwsCommandException() {
        Name invalidName = new Name("Invalid name");
        ViewCommandName viewCommand = new ViewCommandName(invalidName);

        assertCommandFailure(viewCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        Contact firstNameInList = getTypicalContacts().get(0);
        Contact secondNameInList = getTypicalContacts().get(1);

        ViewCommandName viewFirstCommand = new ViewCommandName(firstNameInList.getName());
        ViewCommandName viewSecondCommand = new ViewCommandName(secondNameInList.getName());

        // same object -> returns true
        assertTrue(viewFirstCommand.equals(viewFirstCommand));

        // same values -> returns true
        ViewCommandName viewFirstCommandCopy = new ViewCommandName(firstNameInList.getName());
        assertTrue(viewFirstCommand.equals(viewFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewFirstCommand.equals(viewSecondCommand));
    }
}
