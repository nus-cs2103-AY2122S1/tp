package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Name;
import seedu.address.model.contact.Contact;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteCommandName}.
 */
public class DeleteCommandNameTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        DeleteCommandName deleteCommandName = new DeleteCommandName(ALICE.getName());

        List<Contact> contactList = model.getFilteredContactList();

        Contact contactToDelete = null;

        for (Contact contact : contactList) {
            String fullName = contact.getName().fullName;
            if (fullName.equals(ALICE.getName().fullName.trim())) {
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
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Name nameInList = getTypicalPersons().get(0).getName();
        Contact contactToDelete = getTypicalPersons().get(0);
        DeleteCommandName deleteCommand = new DeleteCommandName(nameInList);

        String expectedMessage = String.format(DeleteCommand.MESSAGE_DELETE_CONTACT_SUCCESS, contactToDelete);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteContact(contactToDelete);
        showNoPerson(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidNameFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Contact nameNotInFilteredList = getTypicalPersons().get(5);
        // ensures that name is still in addressBook
        assertTrue(getTypicalAddressBook().hasContact(nameNotInFilteredList));

        DeleteCommandName deleteCommand = new DeleteCommandName(nameNotInFilteredList.getName());

        assertCommandFailure(deleteCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_NAME);
    }

    @Test
    public void equals() {
        Contact firstNameInList = getTypicalPersons().get(0);
        Contact secondNameInList = getTypicalPersons().get(1);

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

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPerson(Model model) {
        model.updateFilteredContactList(p -> false);

        assertTrue(model.getFilteredContactList().isEmpty());
    }
}
