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
import seedu.address.model.vendor.VendorBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteGuestCommand}.
 */
public class DeleteGuestCommandTest {

    private Model model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());

    @Test
    public void execute_validPassportNumber_success() {
        Guest guestToDelete = model.getFilteredGuestList()
                .stream()
                .filter(g -> g.getPassportNumber().equals(PASSPORT_NUMBER_FIRST_PERSON))
                .findAny()
                .orElse(null);
        DeleteGuestCommand deleteGuestCommand = new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON);

        String expectedMessage = String.format(DeleteGuestCommand.MESSAGE_DELETE_SUCCESSFUL, guestToDelete);

        ModelManager expectedModel =
                new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel.deleteGuest(guestToDelete);

        assertCommandSuccess(deleteGuestCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPassportNumber_throwsCommandException() {
        DeleteGuestCommand deleteGuestCommand = new DeleteGuestCommand(PASSPORT_NUMBER_UNUSED);

        assertCommandFailure(deleteGuestCommand, model, Messages.MESSAGE_INVALID_GUEST_PASSPORT_NUMBER);
    }

    @Test
    public void equals() {
        DeleteGuestCommand deleteFirstCommand = new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON);
        DeleteGuestCommand deleteSecondCommand = new DeleteGuestCommand(PASSPORT_NUMBER_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteGuestCommand deleteFirstCommandCopy = new DeleteGuestCommand(PASSPORT_NUMBER_FIRST_PERSON);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }
}
