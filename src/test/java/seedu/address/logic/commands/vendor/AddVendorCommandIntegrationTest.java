package seedu.address.logic.commands.vendor;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.vendor.TypicalVendors.getTypicalVendorBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.guest.Archive;
import seedu.address.model.guest.GuestBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.testutil.vendor.VendorBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddVendorCommand}.
 */
public class AddVendorCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new GuestBook(), getTypicalVendorBook(), new UserPrefs(), new Archive());
    }

    @Test
    public void execute_newVendor_success() {
        Vendor validVendor = new VendorBuilder().build();
        Model expectedModel = new ModelManager(new GuestBook(), model.getVendorBook(), new UserPrefs(), new Archive());
        expectedModel.addVendor(validVendor);
        assertCommandSuccess(new AddVendorCommand(validVendor), model,
                String.format(AddVendorCommand.MESSAGE_SUCCESS, validVendor), expectedModel);
    }

    @Test
    public void execute_duplicateVendor_throwsCommandException() {
        Vendor vendorInList = model.getVendorBook().getVendorList().get(0);
        assertCommandFailure(new AddVendorCommand(vendorInList), model, AddVendorCommand.MESSAGE_DUPLICATE_VENDOR);
    }

}
