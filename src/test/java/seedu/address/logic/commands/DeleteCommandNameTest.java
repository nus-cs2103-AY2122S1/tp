package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.AIRZONE;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalContacts.getTypicalContacts;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.model.contact.Name;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommandName}.
 */
public class DeleteCommandNameTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        DeleteCommandName deleteCommandName = new DeleteCommandName(AIRZONE.getName());

        List<Contact> contactList = model.getFilteredContactList();

        Contact contactToDelete = null;

        for (Contact contact : contactList) {
            String fullName = contact.getName().fullName;
            if (fullName.equals(AIRZONE.getName().fullName.trim())) {
                contactToDelete = contact;
                break;
            }
        }

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);

        assertCommandSuccess(deleteCommandName, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_invalidNameUnfilteredList_throwsCommandException() {
        String nameNotInListString = "RYAN";
        Name nameNotInList = new Name(nameNotInListString);
        DeleteCommandName deleteCommand = new DeleteCommandName(nameNotInList);

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME);
    }

    @Test
    public void execute_validNameFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);
        Name nameInList = getTypicalContacts().get(0).getName();
        Contact contactToDelete = getTypicalContacts().get(0);
        DeleteCommandName deleteCommand = new DeleteCommandName(nameInList);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);
        showNoContact(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact nameNotInFilteredList = getTypicalContacts().get(5);
        // ensures that name is still in addressBook
        assertTrue(getTypicalAddressBook().hasContact(nameNotInFilteredList));

        DeleteCommandName deleteCommand = new DeleteCommandName(nameNotInFilteredList.getName());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        Contact firstNameInList = getTypicalContacts().get(0);
        Contact secondNameInList = getTypicalContacts().get(1);

        DeleteCommandName deleteFirstCommand = new DeleteCommandName(firstNameInList.getName());
        DeleteCommandName deleteSecondCommand = new DeleteCommandName(secondNameInList.getName());

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommandName deleteFirstCommandCopy = new DeleteCommandName(firstNameInList.getName());
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoContact(Model model) {
        model.updateFilteredContactList(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }
}
