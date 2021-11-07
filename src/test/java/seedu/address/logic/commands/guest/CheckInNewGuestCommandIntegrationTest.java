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

/**
 * Contains integration tests (interaction with the Model) for {@code CheckInCommand}.
 */
public class CheckInNewGuestCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
    }

    @Test
    public void execute_newGuest_success() {
        Guest validGuest = new GuestBuilder().build();
        Model expectedModel = new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel.addGuest(validGuest);
        assertCommandSuccess(new CheckInNewGuestCommand(validGuest), model,
                String.format(CheckInNewGuestCommand.MESSAGE_SUCCESS, validGuest), expectedModel);
    }

    @Test
    public void execute_duplicateGuest_throwsCommandException() {
        Guest personInList = model.getGuestBook().getGuestList().get(0);
        assertCommandFailure(new CheckInNewGuestCommand(personInList), model,
                CheckInNewGuestCommand.MESSAGE_DUPLICATE_GUEST);
    }

}
