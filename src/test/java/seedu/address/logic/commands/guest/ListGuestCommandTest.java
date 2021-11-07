package seedu.address.logic.commands.guest;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showGuestAtPassportNumber;
import static seedu.address.testutil.guest.TypicalGuests.getTypicalGuestBook;
import static seedu.address.testutil.guest.TypicalPassportNumbers.PASSPORT_NUMBER_FIRST_PERSON;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.vendor.VendorBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListGuestCommand.
 */
public class ListGuestCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
        expectedModel = new ModelManager(model.getGuestBook(), new VendorBook(), new UserPrefs(), new Archive());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListGuestCommand(), model, ListGuestCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showGuestAtPassportNumber(model, PASSPORT_NUMBER_FIRST_PERSON);
        assertCommandSuccess(new ListGuestCommand(), model, ListGuestCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
