package seedu.address.logic.commands.guest;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.Guest;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.guest.GuestBuilder;

public class CheckInReturningGuestCommandIntegrationTest {

    private Model model;
    private Guest validGuest = new GuestBuilder().build();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        model.addArchivedGuest(validGuest);
    }

    @Test
    public void execute_returningGuest_success() {
        Model expectedModel = new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(),
                model.getArchive());
        expectedModel.deleteArchivedGuest(validGuest);
        expectedModel.addGuest(validGuest);
        assertCommandSuccess(new CheckInReturningGuestCommand(validGuest), model,
                String.format(CheckInReturningGuestCommand.MESSAGE_SUCCESS_RETURNING_GUEST, validGuest), expectedModel);
    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest personInList = model.getGuestBook().getGuestList().get(0);
        assertCommandFailure(new CheckInReturningGuestCommand(personInList), model,
                CheckInReturningGuestCommand.MESSAGE_DUPLICATE_GUEST);
    }

}
