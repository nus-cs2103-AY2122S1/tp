package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.id.HasUniqueId;
import seedu.address.model.id.UniqueId;
import seedu.address.model.lesson.NoOverlapLessonList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements HasUniqueId {

    // Identity fields
    private final Name name;
    private final UniqueId id;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final NoOverlapLessonList lessonsList;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  NoOverlapLessonList lessonsList) {
        this.id = UniqueId.generateId(this);
        requireAllNonNull(name, phone, email, address, tags, id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.lessonsList = lessonsList == null ? new NoOverlapLessonList() : lessonsList;
    }

    /**
     * Every field must be present and not null.
     */
    public Person(UniqueId uniqueId, Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  NoOverlapLessonList lessonsList) {
        requireAllNonNull(name, phone, email, address, tags, uniqueId);
        this.id = uniqueId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.lessonsList = lessonsList == null ? new NoOverlapLessonList() : lessonsList;
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

    public UniqueId getId() {
        return id;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public NoOverlapLessonList getLessonsList() {
        return lessonsList;
    }

    /**
     * Immutable way of updating the lessons list. Note that the id will be re-generated.
     *
     * @param newLessonsList to change to
     * @return new Person instance with the updated lessons list
     */
    public Person updateLessonsList(NoOverlapLessonList newLessonsList) {
        return new Person(name, phone, email, address, tags, newLessonsList);
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
     * Returns true if both persons have the same id.
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
        return otherPerson.getId().equals(getId());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, id, phone, email, address, tags, lessonsList);
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
                .append("; Lessons: ")
                .append(getLessonsList());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}
