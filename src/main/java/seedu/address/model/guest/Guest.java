package seedu.address.model.guest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.chargeable.Chargeable;
import seedu.address.model.chargeable.Quantity;
import seedu.address.model.commonattributes.Email;
import seedu.address.model.commonattributes.Name;
import seedu.address.model.tag.Tag;
import seedu.address.model.uniquelist.UniqueListItem;
import seedu.address.model.vendor.Vendor;

public class Guest extends UniqueListItem {

    private final Name name;
    private final Email email;
    private final RoomNumber roomNumber;
    private final PassportNumber passportNumber;
    private List<Chargeable> chargeablesUsed;
    private Set<Tag> tags = new HashSet<>();

    /**
     * Every field other than vendorsHired must be present and not null.
     *
     * @param name
     * @param email
     * @param tags
     * @param roomNumber
     * @param passportNumber
     */
    public Guest(Name name, Email email, Set<Tag> tags, RoomNumber roomNumber, PassportNumber passportNumber) {
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
        this.chargeablesUsed = new ArrayList<>();
    }

    /**
     * Alternate constructor for guests.
     *
     * @param name
     * @param email
     * @param tags
     * @param roomNumber
     * @param passportNumber
     * @param chargeablesUsed
     */
    public Guest(Name name, Email email, Set<Tag> tags, RoomNumber roomNumber, PassportNumber passportNumber,
                 List<Chargeable> chargeablesUsed) {
        this.name = name;
        this.email = email;
        this.tags.addAll(tags);
        this.roomNumber = roomNumber;
        this.passportNumber = passportNumber;
        this.chargeablesUsed = chargeablesUsed;
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public RoomNumber getRoomNumber() {
        return roomNumber;
    }

    public PassportNumber getPassportNumber() {
        return passportNumber;
    }

    public List<Chargeable> getChargeableUsed() {
        return chargeablesUsed;
    }

    public boolean hasChargeables() {
        return chargeablesUsed.size() > 0;
    }

    /**
     * Charges a guest to a vendor hired.
     *
     * @param vendor The vendor hired.
     */
    public void charge(Vendor vendor) {
        Chargeable newCharge =
                new Chargeable(vendor.getVendorId(), vendor.getName(), vendor.getServiceName(), vendor.getCost(),
                        new Quantity(1));
        if (getChargeableUsed().contains(newCharge)) {
            for (Chargeable currCharge : getChargeableUsed()) {
                if (currCharge.equals(newCharge)) {
                    currCharge.incrementQuantity();
                }
            }
        } else {
            this.chargeablesUsed.add(newCharge);
        }
    }

    /**
     * Is there a better way to implement this?
     */
    public void clearChargeables() {
        this.chargeablesUsed = new ArrayList<>();
    }

    @Override
    public boolean isSame(UniqueListItem otherItem) {
        if (otherItem == this) {
            return true;
        }

        if (otherItem instanceof Guest) {
            Guest otherGuest = (Guest) otherItem;
            return otherGuest.getPassportNumber().equals(getPassportNumber());
        }

        return false;
    }

    /**
     * Returns true if both guests have the same identity and data fields.
     * This defines a stronger notion of equality between two guests.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Guest)) {
            return false;
        }

        Guest otherGuest = (Guest) other;
        return otherGuest.getName().equals(getName())
                && otherGuest.getRoomNumber().equals(getRoomNumber())
                && otherGuest.getEmail().equals(getEmail())
                && otherGuest.getPassportNumber().equals(getPassportNumber())
                && otherGuest.getTags().equals(getTags())
                && otherGuest.getChargeableUsed().equals(getChargeableUsed());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email, roomNumber, passportNumber, tags, chargeablesUsed);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; RoomNumber: ")
                .append(getRoomNumber())
                .append("; PassportNumber: ")
                .append(getPassportNumber());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
