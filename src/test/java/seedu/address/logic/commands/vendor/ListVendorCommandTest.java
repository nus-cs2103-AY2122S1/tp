package seedu.address.logic.commands.vendor;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showVendorAtVendorId;
import static seedu.address.testutil.vendor.TypicalVendorIds.VENDOR_ID_FIRST_PERSON;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListVendorCommand.
 */
public class ListVendorCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());
        expectedModel = new ModelManager(new GuestBook(), model.getVendorBook(), new UserPrefs(), new Archive());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListVendorCommand(), model, ListVendorCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showVendorAtVendorId(model, VENDOR_ID_FIRST_PERSON);
        assertCommandSuccess(new ListVendorCommand(), model, ListVendorCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
