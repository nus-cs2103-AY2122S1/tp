package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class VendorId {

    public static final String MESSAGE_CONSTRAINTS = "VendorID must be alphanumeric and non-empty.";
    public static final String VALIDATION_REGEX = "^[a-zA-Z0-9_]+[a-zA-Z0-9_ ]*$";

    public final String value;

    /**
     * Constructs an {@code VendorId}.
     *
     * @param vendorId A valid vendorId
     */
    public VendorId(String vendorId) {
        requireNonNull(vendorId);
        checkArgument(isValidVendorId(vendorId), MESSAGE_CONSTRAINTS);
        this.value = vendorId;
    }

    /**
     * Returns true if a given string is a valid vendor ID.
     */
    public static boolean isValidVendorId(String vendorId) {
        return vendorId.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorId // instanceof handles nulls
                && value.equals(((VendorId) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
