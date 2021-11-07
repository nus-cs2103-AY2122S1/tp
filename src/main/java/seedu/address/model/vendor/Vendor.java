package seedu.address.model.vendor;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.uniquelist.UniqueListItem;

public class Vendor extends UniqueListItem {

    private final VendorId vendorId;
    private final Name name;
    private final ServiceName serviceName;
    private final Address address;
    private final Email email;
    private final Phone phone;
    private final Cost cost;
    private final OperatingHours operatingHours;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Creates a vendor.
     *
     * @param name
     * @param email
     * @param tags
     * @param vendorId
     * @param phone
     * @param serviceName
     * @param address
     * @param cost
     * @param operatingHours
     */
    public Vendor(Name name, Email email, Set<Tag> tags, VendorId vendorId, Phone phone,
                  ServiceName serviceName, Address address, Cost cost, OperatingHours operatingHours) {
        requireAllNonNull(name, email, tags, vendorId, serviceName, address, cost, operatingHours);
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.vendorId = vendorId;
        this.phone = phone;
        this.serviceName = serviceName;
        this.address = address;
        this.cost = cost;
        this.operatingHours = operatingHours;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public VendorId getVendorId() {
        return vendorId;
    }

    public Phone getPhone() {
        return phone;
    }

    public ServiceName getServiceName() {
        return serviceName;
    }

    public Address getAddress() {
        return address;
    }

    public Cost getCost() {
        return cost;
    }

    public OperatingHours getOperatingHours() {
        return operatingHours;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void setTags(Set<Tag> newTags) {
        tags = newTags;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    @Override
    public boolean isSame(UniqueListItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (otherItem instanceof Vendor) {
            Vendor otherVendor = (Vendor) otherItem;
            return otherVendor.getVendorId().equals(getVendorId());
        }

        return false;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Vendor)) {
            return false;
        }

        Vendor otherVendor = (Vendor) other;
        return otherVendor.getName().equals(getName())
                && otherVendor.getEmail().equals(getEmail())
                && otherVendor.getAddress().equals(getAddress())
                && otherVendor.getVendorId().equals(getVendorId())
                && otherVendor.getServiceName().equals(getServiceName())
                && otherVendor.getPhone().equals(getPhone())
                && otherVendor.getCost().equals(getCost())
                && otherVendor.getOperatingHours().equals(getOperatingHours())
                && otherVendor.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, tags, address, serviceName, cost, operatingHours, vendorId, phone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Vendor ID: ")
                .append(getVendorId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress())
                .append("; Cost: ")
                .append(getCost())
                .append("; Operating Hours: ")
                .append(getOperatingHours())
                .append("; Service provided: ")
                .append(getServiceName());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
