package seedu.address.model.person.supplier;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;


/**
 * Represents a Supplier in RHRH.
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

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Supplier)) {
            return false;
        }

        Supplier otherSupplier = (Supplier) other;
        return otherSupplier.getName().equals(getName())
                && otherSupplier.getPhone().equals(getPhone())
                && otherSupplier.getEmail().equals(getEmail())
                && otherSupplier.getAddress().equals(getAddress())
                && otherSupplier.getTags().equals(getTags())
                && otherSupplier.getSupplyType().equals(getSupplyType())
                && otherSupplier.getDeliveryDetails().equals(getDeliveryDetails());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPhone(), getEmail(), getAddress(), getTags(), supplyType, deliveryDetails);
    }
}
