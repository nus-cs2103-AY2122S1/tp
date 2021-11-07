package seedu.address.logic.commands.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALICE;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BENSON;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGuestAtPassportNumber;
import static seedu.address.testutil.guest.TypicalGuests.ALICE_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BENSON_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.BOB_ARCHIVED_GUEST;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalArchive;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_UNUSED;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.guest.EditGuestCommand.EditGuestDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Guest;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.guest.EditGuestDescriptorBuilder;
import seedu.address.testutil.guest.GuestBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditGuestCommand.
 */
public class EditGuestCommandTest {

    private Model model =
            new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), getTypicalArchive());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Guest guest = ALICE_GUEST;
        Guest editedGuest = new GuestBuilder().build();
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(editedGuest).build();
        EditGuestCommand editCommand = new EditGuestCommand(guest.getPassportNumber(), descriptor);

        String expectedMessage = String.format(EditGuestCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new VendorBook(), new UserPrefs(),
                getTypicalArchive());
        expectedModel.setGuest(guest, editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Guest guest = ALICE_GUEST;

        GuestBuilder guestBuilder = new GuestBuilder(guest);
        Guest editedGuest = guestBuilder
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .build();

        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder()
                .withName(VALID_NAME_BENSON)
                .withEmail(VALID_EMAIL_BENSON)
                .build();

        EditGuestCommand editCommand = new EditGuestCommand(guest.getPassportNumber(), descriptor);

        String expectedMessage = String.format(EditGuestCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new VendorBook(), new UserPrefs(),
                getTypicalArchive());
        expectedModel.setGuest(guest, editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        // no fields are changed, so the edited guest stays exactly the same
        Guest editedGuest = ALICE_GUEST;

        EditGuestCommand editCommand = new EditGuestCommand(editedGuest.getPassportNumber(), new EditGuestDescriptor());

        String expectedMessage = String.format(EditGuestCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new VendorBook(), new UserPrefs(),
                getTypicalArchive());

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showGuestAtPassportNumber(model, PASSPORT_NUMBER_FIRST_PERSON);

        Guest personInFilteredList = ALICE_GUEST;
        Guest editedGuest = new GuestBuilder(personInFilteredList)
                .withName(VALID_NAME_BENSON)
                .build();
        EditGuestCommand editCommand = new EditGuestCommand(
                personInFilteredList.getPassportNumber(),
                new EditGuestDescriptorBuilder().withName(VALID_NAME_BENSON).build()
        );

        String expectedMessage = String.format(EditGuestCommand.MESSAGE_EDIT_GUEST_SUCCESS, editedGuest);

        Model expectedModel = new ModelManager(new GuestBook(model.getGuestBook()), new VendorBook(), new UserPrefs(),
                getTypicalArchive());
        expectedModel.setGuest(model.getFilteredGuestList().get(0), editedGuest);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateGuestUnfilteredList_failure() {
        Guest guest = ALICE_GUEST;
        EditGuestDescriptor descriptor = new EditGuestDescriptorBuilder(guest).build();
        EditGuestCommand editCommand = new EditGuestCommand(BENSON_GUEST.getPassportNumber(), descriptor);

        assertCommandFailure(editCommand, model, EditGuestCommand.MESSAGE_DUPLICATE_GUEST);
    }

    @Test
    public void execute_duplicateGuestFilteredList_failure() {
        showGuestAtPassportNumber(model, PASSPORT_NUMBER_FIRST_PERSON);

        Guest personInList = BENSON_GUEST;
        EditGuestCommand editCommand = new EditGuestCommand(ALICE_GUEST.getPassportNumber(),
                new EditGuestDescriptorBuilder(personInList).build());

        assertCommandFailure(editCommand, model, EditGuestCommand.MESSAGE_DUPLICATE_GUEST);
    }

    @Test
    public void execute_guestInArchive_failure() {
        Guest archivedGuest = BOB_ARCHIVED_GUEST;
        EditGuestCommand editCommand = new EditGuestCommand(BOB_ARCHIVED_GUEST.getPassportNumber(),
                new EditGuestDescriptorBuilder()
                        .withName(VALID_NAME_BENSON)
                        .withEmail(VALID_EMAIL_BENSON)
                        .build());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_GUEST_IS_IN_ARCHIVE);
    }

    @Test
    public void execute_invalidPassportNumberUnfilteredList_failure() {
        EditGuestCommand editCommand = new EditGuestCommand(PASSPORT_NUMBER_UNUSED, new EditGuestDescriptor());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_GUEST_TO_EDIT_DOES_NOT_EXIST);
    }

    //        @Test
    //        public void execute_invalidPassportNumberFilteredList_failure() {
    //            showGuestAtPassportNumber(model, PASSPORT_NUMBER_FIRST_PERSON);
    //            Index outOfBoundIndex = INDEX_SECOND_PERSON;
    //            // ensures that outOfBoundIndex is still in bounds of address book list
    //            assertTrue(outOfBoundIndex.getZeroBased() < model.getGuestBook().getPersonList().size());
    //
    //            EditGuestCommand editCommand = new EditGuestCommand(outOfBoundIndex,
    //                    new EditPersonDescriptorBuilder().withName(VALID_NAME_BENSON).build());
    //
    //            assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    //        }

    @Test
    public void equals() {
        final EditGuestCommand standardCommand = new EditGuestCommand(PASSPORT_NUMBER_FIRST_PERSON, DESC_ALICE);

        // same values -> returns true
        EditGuestDescriptor copyDescriptor = new EditGuestDescriptor(DESC_ALICE);
        EditGuestCommand commandWithSameValues = new EditGuestCommand(PASSPORT_NUMBER_FIRST_PERSON, copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearGuestCommand()));

        // different passport number -> returns false
        assertFalse(standardCommand.equals(new EditGuestCommand(PASSPORT_NUMBER_SECOND_PERSON, DESC_ALICE)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditGuestCommand(PASSPORT_NUMBER_FIRST_PERSON, DESC_BENSON)));
    }

}
