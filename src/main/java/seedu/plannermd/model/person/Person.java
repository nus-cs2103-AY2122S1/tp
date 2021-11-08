package seedu.plannermd.model.person;

import static seedu.plannermd.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.plannermd.model.tag.Tag;

/**
 * Represents a Person in the plannermd. Guarantees: details are present and not
 * null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final BirthDate birthDate;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, BirthDate birthDate, Remark remark,
            Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags, birthDate);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.birthDate = birthDate;
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

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws
     * {@code UnsupportedOperationException} if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name, phone and email. This
     * defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone()) && otherPerson.getEmail().equals(getEmail());
    }

    /**
     * Returns true if both persons have the same identity and data fields. This
     * defines a stronger notion of equality between two persons.
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
        return otherPerson.getName().equals(getName()) && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail()) && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBirthDate().equals(getBirthDate()) && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, birthDate);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName()).append("; Phone: ").append(getPhone()).append("; Email: ").append(getEmail())
                .append("; Address: ").append(getAddress()).append("; Date of Birth: ").append(getBirthDate());

        if (!remark.isEmpty()) {
            builder.append("; Remark: ").append(getRemark());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            // print tags in lexicographical order
            tags.stream().sorted(Comparator.comparing(tag -> tag.tagName)).forEach(builder::append);
        }
        return builder.toString();
    }

}
