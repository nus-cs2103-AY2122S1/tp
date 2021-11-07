package seedu.address.model.vendor;

import java.util.Optional;

import seedu.address.model.uniquelist.UniqueList;

public class UniqueVendorList extends UniqueList<Vendor> {

    public Optional<Vendor> get(VendorId vendorId) {
        return super.asModifiableObservableList().stream().filter(
            vendor -> vendor.getVendorId().equals(vendorId)).findFirst();
    }

}
