package seedu.address.storage.vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.vendor.ReadOnlyVendorBook;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.vendor.VendorBook;

/**
 * An Immutable VendorBook() that is serializable to JSON format.
 */
@JsonRootName(value = "vendorBook")
public class JsonSerializableVendorBook {

    public static final String MESSAGE_DUPLICATE_VENDOR = "Vendor list contains duplicate vendor(s).";

    private final List<JsonAdaptedVendor> vendors = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableVendorBook} with the given vendors.
     * The json property here reads from the vendors.json for the header staff
     */
    @JsonCreator
    public JsonSerializableVendorBook(@JsonProperty("vendors") List<JsonAdaptedVendor> vendors) {
        this.vendors.addAll(vendors);
    }

    /**
     * Converts a given {@code ReadOnlyVendorBook} into this class for Jackson use.
     * This code saves the data in this format
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableVendorBook}.
     */
    public JsonSerializableVendorBook(ReadOnlyVendorBook source) {
        vendors.addAll(source.getVendorList().stream().map(
                JsonAdaptedVendor::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code VendorBook} object.
     * This code loads the data into the system.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public VendorBook toModelType() throws IllegalValueException {
        VendorBook vendorManager = new VendorBook();
        for (JsonAdaptedVendor jsonAdaptedVendor : vendors) {
            Vendor vendor = jsonAdaptedVendor.toModelType();
            if (vendorManager.hasVendor(vendor)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_VENDOR);
            }
            vendorManager.addVendor(vendor);
        }
        return vendorManager;
    }

}
