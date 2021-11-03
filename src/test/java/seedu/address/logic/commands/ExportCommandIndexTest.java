package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showContactAtIndex;
import static seedu.address.testutil.TypicalContacts.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CONTACT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CONTACT;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.Contact;
import seedu.address.storage.ExportStorage;

public class ExportCommandIndexTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    private UserPrefs userPrefs = new UserPrefs();

    {
        try {
            new ExportStorage(userPrefs.getExportFilePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Contact contactToExport = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        ExportCommandIndex exportCommand = new ExportCommandIndex(INDEX_FIRST_CONTACT);

        String expectedMessage = ExportCommand.MESSAGE_EXPORT_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.exportContact(contactToExport);

        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredContactList().size() + 1);
        ExportCommandIndex exportCommand = new ExportCommandIndex(outOfBoundIndex);

        assertCommandFailure(exportCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Contact contactToExport = model.getFilteredContactList().get(INDEX_FIRST_CONTACT.getZeroBased());
        ExportCommandIndex exportCommand = new ExportCommandIndex(INDEX_FIRST_CONTACT);

        String expectedMessage = ExportCommand.MESSAGE_EXPORT_SUCCESS;

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.exportContact(contactToExport);
        showContactAtIndex(expectedModel, INDEX_FIRST_CONTACT);

        assertCommandSuccess(exportCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showContactAtIndex(model, INDEX_FIRST_CONTACT);

        Index outOfBoundIndex = INDEX_SECOND_CONTACT;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getContactList().size());

        ExportCommandIndex exportCommand = new ExportCommandIndex(outOfBoundIndex);

        assertCommandFailure(exportCommand, model, Messages.MESSAGE_INVALID_CONTACT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ExportCommandIndex exportFirstCommand = new ExportCommandIndex(INDEX_FIRST_CONTACT);
        ExportCommandIndex exportSecondCommand = new ExportCommandIndex(INDEX_SECOND_CONTACT);

        // same object -> returns true
        assertTrue(exportFirstCommand.equals(exportFirstCommand));

        // same values -> returns true
        ExportCommand exportFirstCommandCopy = new ExportCommandIndex(INDEX_FIRST_CONTACT);
        assertTrue(exportFirstCommand.equals(exportFirstCommandCopy));

        // different types -> returns false
        assertFalse(exportFirstCommand.equals(1));

        // null -> returns false
        assertFalse(exportFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(exportFirstCommand.equals(exportSecondCommand));
    }

}
