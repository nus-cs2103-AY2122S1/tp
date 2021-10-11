package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Mod;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final StudentId id;
    private final Phone phone;
    private final Email email;
    private final boolean isFavourite;

    // Data fields
    private final Set<Mod> mods = new HashSet<>();
    private final boolean isMyProfile;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, StudentId id, Phone phone, Email email,
                  boolean isFavourite, Set<Mod> mods, boolean isMyProfile) {
        requireAllNonNull(name, id, phone, email, mods, isMyProfile);
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.isFavourite = isFavourite;
        this.mods.addAll(mods);
        this.isMyProfile = isMyProfile;
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return id;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Mod> getMods() {
        return Collections.unmodifiableSet(mods);
    }

    public boolean getIsMyProfile() {
        return isMyProfile;
    }

    public boolean getIsFavourite() {
        return isFavourite;
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
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

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getMods().equals(getMods())
                && otherPerson.getIsFavourite() == getIsFavourite()
                && otherPerson.getStudentId().equals(getStudentId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, mods);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Student ID: ")
                .append(getStudentId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; isFavourite: ")
                .append(getIsFavourite());

        Set<Mod> mods = getMods();
        if (!mods.isEmpty()) {
            builder.append("; Tags: ");
            mods.forEach(builder::append);
        }
        return builder.toString();
    }

}
