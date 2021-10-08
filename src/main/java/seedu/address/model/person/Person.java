package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.lesson.Lesson;
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

    // Data fields
    private final Phone parentPhone;
    private final Email parentEmail;
    private final Address address;
    private final Remark remark;
    private final Fee outstandingFee;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Lesson> lessons = new TreeSet<>();

    /**
     * Every field must be present and not null.
     */

    public Person(Name name, Phone phone, Email email, Phone parentPhone, Email parentEmail,
                  Address address, Fee outstandingFee, Remark remark, Set<Tag> tags, Set<Lesson> lessons) {
        requireAllNonNull(name, phone, email, address, remark, tags, lessons);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.parentPhone = parentPhone;
        this.parentEmail = parentEmail;
        this.address = address;
        this.outstandingFee = outstandingFee;
        this.remark = remark;
        this.tags.addAll(tags);
        this.lessons.addAll(lessons);
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

    public Phone getParentPhone() {
        return parentPhone;
    }

    public Email getParentEmail() {
        return parentEmail;
    }

    public Address getAddress() {
        return address;
    }

    public Fee getFee() {
        return outstandingFee;
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
     * Returns an immutable lesson set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Lesson> getLessons() {
        return Collections.unmodifiableSet(lessons);
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
     * Returns true if {@code Lesson} to check clashes with existing lessons.
     *
     * @param toCheck The lesson to be compared with.
     * @return True if and only if there is at least one clash.
     */
    public boolean hasClashingLessons(Lesson toCheck) {
        if (toCheck == null) {
            return false;
        }
        boolean isClash = false;
        for (Lesson lesson : lessons) {
            if (isClash) {
                break;
            }
            isClash = lesson.isClashing(toCheck);
        }
        return isClash;
    }

    /**
     * Returns true if this person has at least one contact field not empty.
     */
    public boolean hasContactField() {
        return !(phone.value.isEmpty() && email.value.isEmpty()
                && parentPhone.value.isEmpty() && parentEmail.value.isEmpty());
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
                && otherPerson.getParentPhone().equals(getParentPhone())
                && otherPerson.getParentEmail().equals(getParentEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getFee().equals(getFee())
                && otherPerson.getRemark().equals(getRemark())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLessons().equals(getLessons());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Parent Phone: ")
                .append(getParentPhone())
                .append("; Parent Email: ")
                .append(getParentEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Outstanding Fees: ")
                .append(getFee())
                .append("; Remark: ")
                .append(getRemark());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
