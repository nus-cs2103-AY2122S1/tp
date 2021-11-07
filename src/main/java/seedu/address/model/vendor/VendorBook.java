package seedu.address.model.vendor;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import javafx.collections.ObservableList;

public class VendorBook implements ReadOnlyVendorBook {

    private final UniqueVendorList vendors;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        vendors = new UniqueVendorList();
    }

    public VendorBook() {
    }

    /**
     * Creates a Vendor Manager using the entities in the {@code toBeCopied}
     */
    public VendorBook(ReadOnlyVendorBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the vendor list with {@code vendors}.
     * {@code vendors} must not contain duplicate vendors.
     */
    public void setVendors(List<Vendor> vendors) {
        this.vendors.setItems(vendors);
    }

    /**
     * Resets the existing data of this {@code VendorBook} with {@code newData}.
     */
    public void resetData(ReadOnlyVendorBook newData) {
        requireNonNull(newData);
        setVendors(newData.getVendorList());
    }

    /**
     * Returns true if a vendor with the same identity as {@code vendor} exists in the address book.
     */
    public boolean hasVendor(Vendor vendor) {
        requireNonNull(vendor);
        return vendors.contains(vendor);
    }

    /**
     * Adds a vendor to the address book.
     * The vendor must not already exist in the address book.
     */
    public void addVendor(Vendor p) {
        vendors.add(p);
    }

    /**
     * Replaces the given vendor {@code target} in the list with {@code editedVendor}.
     * {@code target} must exist in the address book.
     * The vendor identity of {@code editedVendor} must not be the same as another existing vendor in the address book.
     */
    public void setVendor(Vendor target, Vendor editedVendor) {
        requireNonNull(editedVendor);
        vendors.setItem(target, editedVendor);
    }

    /**
     * Gets the given vendor in the list with the given vendorId.
     * If Vendor does not exist in the vendor book, then Optional is empty.
     */
    public Optional<Vendor> getVendor(VendorId vendorId) {
        requireNonNull(vendorId);
        return vendors.get(vendorId);
    }

    /**
     * Removes {@code key} from this {@code VendorBook}.
     * {@code key} must exist in the address book.
     */
    public void removeVendor(Vendor key) {
        vendors.remove(key);
    }

    @Override
    public String toString() {
        return vendors.asUnmodifiableObservableList().size() + "vendors";
    }

    @Override
    public ObservableList<Vendor> getVendorList() {
        return vendors.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof VendorBook // instanceof handles nulls
                && vendors.equals(((VendorBook) other).vendors));
    }

    @Override
    public int hashCode() {
        return vendors.hashCode();
    }

}
