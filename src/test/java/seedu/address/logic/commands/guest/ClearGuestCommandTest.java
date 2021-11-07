package seedu.address.logic.commands.guest;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.VendorBook;

public class ClearGuestCommandTest {

    @Test
    public void execute_emptyGuestBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearGuestCommand(), model, ClearGuestCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyGuestBook_success() {
        Model model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        Model expectedModel = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel.setGuestBook(new GuestBook());

        assertCommandSuccess(new ClearGuestCommand(), model, ClearGuestCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
