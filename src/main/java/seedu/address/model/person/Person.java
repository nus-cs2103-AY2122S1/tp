package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Price price;
    private final Info info;
    private final Status status;

    // Data fields
    private final Address address;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Price price, Info info, Status status, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, price, info, status, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.price = price;
        this.info = info;
        this.status = status;
        this.tags.addAll(tags);
    }

    /**
     * Creates a Person with only a name string.
     * Not supposed to be used by external users.
     */
    private Person(String inputName) {
        name = new Name(inputName);
        phone = null;
        email = null;
        address = null;
        this.price = null;
        this.info = null;
        this.status = null;
    }

    /**
     * Creates a Person with only a name string.
     * Not supposed to be used by external users.
     * @param inputName
     * @return invalid Person object
     */
    public static Person createTempFakePerson(String inputName) {
        return new Person(inputName);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPrice() {
        return price;
    }

    public Info getInfo() {
        return info;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * Returns true if this person's status is same as {@code anotherStatus}
     */
    public boolean hasSameStatus(Status anotherStatus) {
        return status.equals(anotherStatus);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getPrice().equals(getPrice())
                && otherPerson.getInfo().equals(getInfo())
                && otherPerson.getStatus().equals(getStatus())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, price, info, status, tags);
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
                .append(getAddress())
                .append("; Price: ")
                .append(getPrice())
                .append("; Info: ")
                .append(getInfo())
                .append("; Status: ")
                .append(getStatus());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
