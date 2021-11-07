package seedu.address.logic.commands.vendor;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.vendor.VendorBuilder;

public class AddVendorCommandTest {

    @Test
    public void constructor_nullVendor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddVendorCommand(null));
    }

    @Test
    public void execute_vendorAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingVendorAdded modelStub = new ModelStubAcceptingVendorAdded();
        Vendor validVendor = new VendorBuilder().build();

        CommandResult commandResult = new AddVendorCommand(validVendor).execute(modelStub);

        assertEquals(String.format(AddVendorCommand.MESSAGE_SUCCESS, validVendor), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validVendor), modelStub.vendorsAdded);
    }

    @Test
    public void execute_duplicateVendor_throwsCommandException() {
        Vendor validVendor = new VendorBuilder().build();
        AddVendorCommand addVendorCommand = new AddVendorCommand(validVendor);
        ModelStub modelStub = new ModelStubWithVendor(validVendor);

        assertThrows(CommandException.class, AddVendorCommand.MESSAGE_DUPLICATE_VENDOR, (
        ) -> addVendorCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Vendor alice = new VendorBuilder().withName("Alice").build();
        Vendor bob = new VendorBuilder().withName("Bob").build();
        AddVendorCommand addAliceCommand = new AddVendorCommand(alice);
        AddVendorCommand addBobCommand = new AddVendorCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddVendorCommand addAliceCommandCopy = new AddVendorCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different vendor -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A Model stub that contains a single vendor.
     */
    private class ModelStubWithVendor extends ModelStub {
        private final Vendor vendor;

        ModelStubWithVendor(Vendor vendor) {
            requireNonNull(vendor);
            this.vendor = vendor;
        }

        @Override
        public boolean hasVendor(Vendor vendor) {
            requireNonNull(vendor);
            return this.vendor.isSame(vendor);
        }
    }

    /**
     * A Model stub that always accept the vendor being added.
     */
    private class ModelStubAcceptingVendorAdded extends ModelStub {
        final ArrayList<Vendor> vendorsAdded = new ArrayList<>();

        @Override
        public boolean hasVendor(Vendor vendor) {
            requireNonNull(vendor);
            return vendorsAdded.stream().anyMatch(vendor::isSame);
        }

        @Override
        public void addVendor(Vendor vendor) {
            requireNonNull(vendor);
            vendorsAdded.add(vendor);
        }

        @Override
        public ReadOnlyVendorBook getVendorBook() {
            return new VendorBook();
        }
    }

}
