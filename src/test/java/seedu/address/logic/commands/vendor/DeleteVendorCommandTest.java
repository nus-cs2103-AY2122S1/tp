package seedu.address.logic.commands.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_SECOND_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_UNUSED;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.Vendor;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteVendorCommand}.
 */
public class DeleteVendorCommandTest {

    private Model model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());

    @Test
    public void execute_validVendorId_success() {
        Vendor vendorToDelete = model.getFilteredVendorList()
                .stream()
                .filter(v -> v.getVendorId().equals(VENDOR_ID_FIRST_PERSON))
                .findAny()
                .orElse(null);
        DeleteVendorCommand deleteVendorCommand = new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON);

        String expectedMessage = String.format(DeleteVendorCommand.MESSAGE_DELETE_SUCCESSFUL, vendorToDelete);

        ModelManager expectedModel =
                new ModelManager(new GuestBook(), model.getVendorBook(), new UserPrefs(), new Archive());
        expectedModel.deleteVendor(vendorToDelete);

        assertCommandSuccess(deleteVendorCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidVendorId_throwsCommandException() {
        DeleteVendorCommand deleteVendorCommand = new DeleteVendorCommand(VENDOR_ID_UNUSED);

        assertCommandFailure(deleteVendorCommand, model, Messages.MESSAGE_INVALID_VENDORID);
    }

    @Test
    public void equals() {
        DeleteVendorCommand deleteFirstCommand = new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON);
        DeleteVendorCommand deleteSecondCommand = new DeleteVendorCommand(VENDOR_ID_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteVendorCommand deleteFirstCommandCopy = new DeleteVendorCommand(VENDOR_ID_FIRST_PERSON);
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
    private void showNoVendor(Model model) {
        model.updateFilteredVendorList(p -> false);

        assertTrue(model.getFilteredVendorList().isEmpty());
    }
}
