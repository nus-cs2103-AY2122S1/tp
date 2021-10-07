package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;


/**
 * Represents a Supplier in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Supplier extends Person {

    private SupplyType supplyType;
    private DeliveryDetails deliveryDetails;

    /**
     * Constructor of the Supplier class.
     */
    public Supplier(Name name, Phone phone, Email email, Address address, Set<Tag> tags, SupplyType supplyType,
                    DeliveryDetails deliveryDetails) {
        super(name, phone, email, address, tags);
        this.supplyType = supplyType;
    }
}
