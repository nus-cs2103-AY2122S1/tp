package seedu.address.logic.commands.guest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_SECOND_PERSON;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_UNUSED;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.vendor.VendorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteGuestCommand}.
 */
public class CheckOutCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());

    @Test
    public void execute_validPassportNumberAndNoServicesUsed_success() {
        Guest guestToCheckout = model.getFilteredGuestList()
                .stream()
                .filter(g -> g.getPassportNumber().equals(PASSPORT_NUMBER_FIRST_PERSON))
                .findAny()
                .orElse(null);
        CheckOutCommand checkOutCommand = new CheckOutCommand(PASSPORT_NUMBER_FIRST_PERSON);

        String expectedMessage = String.format(CheckOutCommand.MESSAGE_CHECKOUT_NO_INVOICE_SUCCESSFUL, guestToCheckout);

        ModelManager expectedModel =
                new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel.deleteGuest(guestToCheckout);
        expectedModel.addArchivedGuest(guestToCheckout);

        assertCommandSuccess(checkOutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validPassportNumberAndServicesUsed_success() {
        Guest guestToCheckout = model.getFilteredGuestList()
                .stream()
                .filter(g -> g.getPassportNumber().equals(PASSPORT_NUMBER_FIRST_PERSON))
                .findAny()
                .orElse(null);

        Vendor vendor = new VendorBuilder().build();
        guestToCheckout.charge(vendor);

        CheckOutCommand checkOutCommand = new CheckOutCommand(PASSPORT_NUMBER_FIRST_PERSON);


        String expectedMessage = String.format(CheckOutCommand.MESSAGE_CHECKOUT_INVOICE_GENERATED_SUCCESSFUL,
                guestToCheckout);

        ModelManager expectedModel =
                new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel.deleteGuest(guestToCheckout);
        expectedModel.addArchivedGuest(guestToCheckout);

        assertCommandSuccess(checkOutCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPassportNumber_throwsCommandException() {
        CheckOutCommand checkOutCommand = new CheckOutCommand(PASSPORT_NUMBER_UNUSED);

        assertCommandFailure(checkOutCommand, model, Messages.MESSAGE_GUEST_TO_CHECK_OUT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        CheckOutCommand checkOutFirstCommand = new CheckOutCommand(PASSPORT_NUMBER_FIRST_PERSON);
        CheckOutCommand checkOutSecondCommand = new CheckOutCommand(PASSPORT_NUMBER_SECOND_PERSON);

        // same object -> returns true
        assertTrue(checkOutFirstCommand.equals(checkOutFirstCommand));

        // same values -> returns true
        CheckOutCommand checkOutFirstCommandCopy = new CheckOutCommand(PASSPORT_NUMBER_FIRST_PERSON);
        assertTrue(checkOutFirstCommand.equals(checkOutFirstCommandCopy));

        // different types -> returns false
        assertFalse(checkOutFirstCommand.equals(1));

        // null -> returns false
        assertFalse(checkOutFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(checkOutFirstCommand.equals(checkOutSecondCommand));
    }
}

