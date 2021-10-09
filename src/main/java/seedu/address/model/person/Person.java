package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
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
    private final Language language;

    // Data fields
    private final Address address;
    private final Optional<LastVisit> lastVisit;
    private final Optional<Visit> visit;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Language language, Address address,
                  Optional<LastVisit> lastVisit, Optional<Visit> visit, Set<Tag> tags) {
        requireAllNonNull(name, phone, language, address, tags);
        this.name = name;
        this.phone = phone;
        this.language = language;
        this.address = address;
        this.lastVisit = lastVisit;
        this.visit = visit;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Language getLanguage() {
        return language;
    }

    public Address getAddress() {
        return address;
    }

    public Optional<LastVisit> getLastVisit() {
        return lastVisit;
    }

    public Optional<Visit> getVisit() {
        return visit;
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
     * Returns true if the person has a scheduled visit.
     */
    public boolean hasVisit() {
        return (this.visit.get().hasVisit());
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
                && otherPerson.getLanguage().equals(getLanguage())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getLastVisit().equals(getLastVisit())
                && otherPerson.getVisit().equals(getVisit())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, language, address, visit, lastVisit, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Language: ")
                .append(getLanguage())
                .append("; Address: ")
                .append(getAddress())
                .append("; Last Visit: ")
                .append(getLastVisit())
                .append("; Visit: ")
                .append(getVisit().get());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
