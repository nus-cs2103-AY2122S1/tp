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
    private final Email email;
    private final Set<ModuleCode> moduleCodes = new HashSet<>();
    private final TeleHandle teleHandle;
    private final Phone phone;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Set<ModuleCode> moduleCodes,
                  Phone phone, TeleHandle teleHandle, Remark remark, Set<Tag> tags) {
        requireAllNonNull(name, email, moduleCodes, phone, teleHandle, tags);
        this.name = name;
        this.email = email;
        this.remark = remark;
        this.moduleCodes.addAll(moduleCodes);
        this.phone = phone;
        this.teleHandle = teleHandle;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    /**
     * Returns an immutable module codes set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleCode> getModuleCodes() {
        return Collections.unmodifiableSet(moduleCodes);
    }

    public Phone getPhone() {
        return phone;
    }

    public TeleHandle getTeleHandle() {
        return teleHandle;
    }
    public Remark getRemark() {
        return remark;
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getModuleCodes().equals(getModuleCodes())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getTeleHandle().equals(getTeleHandle())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, moduleCodes, phone, teleHandle, remark, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Module : ");

        Set<ModuleCode> moduleCodes = getModuleCodes();
        moduleCodes.forEach(builder::append);

        if (!getPhone().value.isEmpty()) {
            builder.append("; Phone: ");
            builder.append(getPhone());
        }

        if (!getTeleHandle().value.isEmpty()) {
            builder.append("; Telegram: ");
            builder.append(getTeleHandle());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (!remark.toString().trim().isEmpty()) {
            builder.append("; Remark: ");
            builder.append(getRemark());
        }

        return builder.toString();
    }

}
