package seedu.address.model.participant;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.tag.Tag;

public class NextOfKin {

    // Identity fields
    private final Name name;
    private final Phone phone;

    private final Tag tag;

    /**
     * Constructor for NextOfKin.
     *
     * @param name  name of next of kin.
     * @param phone phone of next of kin.
     * @param tag   tag to describe relation of the next of kin.
     */
    public NextOfKin(Name name, Phone phone, Tag tag) {
        requireAllNonNull(name, phone, tag);
        this.name = name;
        this.phone = phone;
        this.tag = tag;
    }

    public Name getName() {
        return name;
    }

    public String getFullName() {
        return name.toString();
    }

    public Phone getPhone() {
        return phone;
    }

    public String getPhoneValue() {
        return phone.toString();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Tag getTag() {
        return tag;
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

        if (!(other instanceof NextOfKin)) {
            return false;
        }

        NextOfKin otherNextOfKin = (NextOfKin) other;
        return otherNextOfKin.getName().equals(getName())
            && otherNextOfKin.getPhone().equals(getPhone())
            && otherNextOfKin.getTag().equals(getTag());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, tag);
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %s", getName(), getTag(), getPhone());
    }


}
