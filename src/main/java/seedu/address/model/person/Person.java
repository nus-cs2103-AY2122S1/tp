package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Nameable;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.TuitionClass;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Nameable {
    /** Most recently view person */
    private static Person mostRecent;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Remark remark;


    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Classes classes;


    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags, Classes classes) {
        requireAllNonNull(name, phone, email, address, tags, classes);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.classes = classes;
        mostRecent = this;
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name) {
        requireAllNonNull(name);
        this.name = name;
        this.phone = null;
        this.email = null;
        this.address = null;
        this.remark = null;
        this.classes = null;
    }

    public Remark getRemark() {
        return remark;
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

    public Classes getClasses() {
        return classes;
    }

    public String getNameString() {
        return name.fullName;
    }

    /**
     * Adds a new tuition class to the student's class list.
     * @param tuitionClass The tuition class to be added.
     * @return the updated Classes object.
     */
    public Classes addClass(TuitionClass tuitionClass) {
        Classes updatedClass = this.getClasses().addClass(tuitionClass.getId());
        return updatedClass;
    }

    /**
     * Removes all traces of a tuition class from the student.
     */
    public Person removeClass(TuitionClass tuitionClass) {
        for (Integer id : classes.getClasses()) {
            if ((tuitionClass.getId()) == id) {
                classes.removeClass(id);
                removeTag(new Tag(tuitionClass.getName().getName()));
                return this;
            }
        }
        return this;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Adds a new tag to the person.
     * @param tag tag to be added
     * @return the updated set of tag
     */
    public Set<Tag> addTag(Tag tag) {
        this.tags.add(tag);
        return this.tags;
    }

    /**
     * Removes tag from the person.
     * @param tag tag to be removed
     */
    public void removeTag(Tag tag) {
        tags.remove(tag);
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

    public Person getSameNamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return this;
        }
        boolean sameName = otherPerson != null
                && otherPerson.getName().equals(getName());
        if (sameName) {
            return this;
        }
        return null;
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
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, classes);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append(" Remark: ")
                .append(getRemark())
                .append("; Address: ")
                .append(getAddress());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        if (classes.getNumofClass() != 0) {
            builder.append("; Classes: ");
            classes.getClasses().forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Sets most recently viewed student to a given Person.
     * @param student Student to set as most recently looked at.
     */
    public static void setMostRecentTo(Person student) {
        mostRecent = student;
    }

    /**
     * Returns the most recently viewed student
     * @return most recently viewed student.
     */
    public static Person getMostRecent() {
        return mostRecent;
    }

}
