package seedu.address.logic.commands.vendor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ELLE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_VENDOR_ID_DANIEL;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showVendorAtVendorId;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_SECOND_PERSON;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_UNUSED;
import static seedu.address.testutil.vendor.TypicalVendors.DANIEL_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.ELLE_VENDOR;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.guest.ClearGuestCommand;
import seedu.address.logic.commands.vendor.EditVendorCommand.EditVendorDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;
import seedu.address.model.vendor.VendorId;
import seedu.address.testutil.vendor.EditVendorDescriptorBuilder;
import seedu.address.testutil.vendor.VendorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditVendorCommand.
 */
public class EditVendorCommandTest {

    private Model model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Vendor vendor = DANIEL_VENDOR;
        Vendor editedVendor = new VendorBuilder().build();
        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder(editedVendor).build();
        EditVendorCommand editCommand = new EditVendorCommand(vendor.getVendorId(), descriptor);

        String expectedMessage = String.format(EditVendorCommand.MESSAGE_EDIT_VENDOR_SUCCESS, editedVendor);

        Model expectedModel = new ModelManager(new GuestBook(), new VendorBook(model.getVendorBook()), new UserPrefs(),
                new Archive());
        expectedModel.setVendor(vendor, editedVendor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Vendor vendor = DANIEL_VENDOR;

        VendorBuilder vendorBuilder = new VendorBuilder(vendor);
        Vendor editedVendor = vendorBuilder
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .build();

        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder()
                .withName(VALID_NAME_DANIEL)
                .withEmail(VALID_EMAIL_DANIEL)
                .build();

        EditVendorCommand editCommand = new EditVendorCommand(vendor.getVendorId(), descriptor);

        String expectedMessage = String.format(EditVendorCommand.MESSAGE_EDIT_VENDOR_SUCCESS, editedVendor);

        Model expectedModel = new ModelManager(new GuestBook(), new VendorBook(model.getVendorBook()), new UserPrefs(),
                new Archive());
        expectedModel.setVendor(vendor, editedVendor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // no fields are changed, so the edited vendor stays exactly the same
        VendorId targetVendorId = new VendorId(VALID_VENDOR_ID_DANIEL);
        EditVendorDescriptor editVendorDescriptor =
                new EditVendorDescriptorBuilder().withVendorId(VALID_VENDOR_ID_DANIEL).build();
        EditVendorCommand editCommand = new EditVendorCommand(targetVendorId, editVendorDescriptor);

        String expectedMessage = String.format(EditVendorCommand.MESSAGE_EDIT_VENDOR_SUCCESS, DANIEL_VENDOR);
        Model expectedModel = new ModelManager(new GuestBook(), new VendorBook(model.getVendorBook()), new UserPrefs(),
                new Archive());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        // Hard-coded to show first Vendor in TypicalGuests list
        showVendorAtVendorId(model, VENDOR_ID_FIRST_PERSON);

        Vendor vendorInFilteredList = DANIEL_VENDOR;
        Vendor editedVendor = new VendorBuilder(vendorInFilteredList)
                .withName(VALID_NAME_DANIEL)
                .build();
        EditVendorCommand editCommand = new EditVendorCommand(
                vendorInFilteredList.getVendorId(),
                new EditVendorDescriptorBuilder().withName(VALID_NAME_DANIEL).build()
        );

        String expectedMessage = String.format(EditVendorCommand.MESSAGE_EDIT_VENDOR_SUCCESS, editedVendor);

        Model expectedModel = new ModelManager(new GuestBook(), new VendorBook(model.getVendorBook()), new UserPrefs(),
                new Archive());
        expectedModel.setVendor(vendorInFilteredList, editedVendor);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateVendorUnfilteredList_failure() {
        Vendor vendor = DANIEL_VENDOR;
        EditVendorDescriptor descriptor = new EditVendorDescriptorBuilder(vendor).build();
        EditVendorCommand editCommand = new EditVendorCommand(ELLE_VENDOR.getVendorId(), descriptor);

        assertCommandFailure(editCommand, model, EditVendorCommand.MESSAGE_DUPLICATE_VENDOR);
    }

    @Test
    public void execute_duplicateVendorFilteredList_failure() {
        // Hard-coded to show first Vendor in TypicalGuests list
        showVendorAtVendorId(model, VENDOR_ID_FIRST_PERSON);

        Vendor personInList = ELLE_VENDOR;
        EditVendorCommand editCommand = new EditVendorCommand(DANIEL_VENDOR.getVendorId(),
                new EditVendorDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditVendorCommand.MESSAGE_DUPLICATE_VENDOR);
    }

    @Test
    public void execute_invalidVendorIdUnfilteredList_failure() {
        EditVendorCommand editCommand = new EditVendorCommand(VENDOR_ID_UNUSED, new EditVendorDescriptor());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_VENDOR_TO_EDIT_DOES_NOT_EXIST);
    }

    //    @Test
    //    public void execute_invalidPassportNumberFilteredList_failure() {
    //        showPersonAtIndex(model, INDEX_FIRST_PERSON);
    //        Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //        // ensures that outOfBoundIndex is still in bounds of address book list
    //        assertTrue(outOfBoundIndex.getZeroBased() < model.getVendorBook().getPersonList().size());
    //
    //        EditVendorCommand editCommand = new EditVendorCommand(outOfBoundIndex,
    //                new EditPersonDescriptorBuilder().withName(VALID_NAME_DANIEL).build());
    //
    //        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //    }

    @Test
    public void equals() {
        // Something needs to be done about DESC_ELLE?? Change to something else??
        final EditVendorCommand standardCommand = new EditVendorCommand(VENDOR_ID_FIRST_PERSON, DESC_ELLE);

        // same values -> returns true
        EditVendorDescriptor copyDescriptor = new EditVendorDescriptor(DESC_ELLE);
        EditVendorCommand commandWithSameValues = new EditVendorCommand(VENDOR_ID_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearGuestCommand()));

        // different vendor id -> returns false
        assertFalse(standardCommand.equals(new EditVendorCommand(VENDOR_ID_SECOND_PERSON, DESC_ELLE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditVendorCommand(VENDOR_ID_FIRST_PERSON, DESC_DANIEL)));
    }

}
