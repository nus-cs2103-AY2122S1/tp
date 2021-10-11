package seedu.address.model.person.supplier;

import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
        this.deliveryDetails = deliveryDetails;
    }

    /**
     * Returns true if both suppliers have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSameSupplier(Supplier otherSupplier) {
        if (otherSupplier == this) {
            return true;
        }

        return otherSupplier != null
                && otherSupplier.getName().equals(getName());
    }

    public SupplyType getSupplyType() {
        return this.supplyType;
    }

    public DeliveryDetails getDeliveryDetails() {
        return this.deliveryDetails;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("; Supply Type: ")
                .append(getSupplyType())
                .append("; Delivery Details: ")
                .append(getDeliveryDetails());

        return builder.toString();
    }
}
