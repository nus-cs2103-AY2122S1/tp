package seedu.address.testutil.vendor;

import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;

/**
 * A utility class to help with building VendorBook objects.
 * Example usage: <br>
 *     {@code VendorBook vb = new VendorBookBuilder().withVendor("John", "Doe").build();}
 */
public class VendorBookBuilder {

    private VendorBook vendorBook;

    public VendorBookBuilder() {
        vendorBook = new VendorBook();
    }

    public VendorBookBuilder(VendorBook vendorBook) {
        this.vendorBook = vendorBook;
    }

    /**
     * Adds a new {@code Vendor} to the {@code VendorBook} that we are building.
     */
    public VendorBookBuilder withVendor(Vendor vendor) {
        vendorBook.addVendor(vendor);
        return this;
    }

    public VendorBook build() {
        return vendorBook;
    }
}
