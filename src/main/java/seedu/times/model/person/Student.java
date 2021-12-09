package seedu.times.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.times.model.tag.Tag;
import seedu.times.model.tuitionclass.ClassTiming;
import seedu.times.model.tuitionclass.Location;
import seedu.times.model.tuitionclass.Rate;

/**
 * Represents a Student in the Timestable.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student extends Person {

    /* Each Student has 0..1 Nok */
    private Nok nok;

    private final Set<Tag> tags = new HashSet<>();

    /**
     * Student constructor
     */
    public Student(
            Name name, Phone phone, Email email, Address address, Nok nok, Set<Tag> tags) {
        super(name, phone, email, address);

        this.nok = nok;
        this.tags.addAll(tags);
    }

    public void setNok(Nok nok) {
        this.nok = nok;
    }

    public Nok getNok() {
        return nok;
    }

    public ClassTiming getClassTiming() {
        // TODO: Placeholder
        return null;
    }

    public Location getLocation() {
        // TODO: Placeholder
        return null;
    }

    /**
     * Returns tuition {@code Rate} of this student
     */
    public Rate getRate() {
        // Placeholder
        return null;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if any {@code Tag} belonging to this student matches {@code keyword} in name ignoring case.
     */
    public boolean isAnyTagsMatching(String keyword) {
        requireNonNull(keyword);
        return tags.stream()
                .anyMatch(tag -> tag.isNameMatchingIgnoreCase(keyword));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     * We check if their tags are equal as well.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student o = (Student) other;
        return super.equals(other)
                && o.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // Hashes the hashcode to produce a new hash
        return Objects.hash(super.hashCode(), tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(super.toString());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        builder.append("\nNext-of-Kin: ");
        builder.append(nok.toString());


        return builder.toString();
    }

}
