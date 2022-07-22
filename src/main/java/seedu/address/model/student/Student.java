package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.Nameable;
import seedu.address.model.tag.Tag;
import seedu.address.model.tuition.ClassName;
import seedu.address.model.tuition.Timeslot;
import seedu.address.model.tuition.TuitionClass;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student implements Nameable {
    /** Most recently view student */
    private static Student mostRecent;

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
    public Student(Name name, Phone phone, Email email, Address address,
                   Remark remark, Set<Tag> tags, Classes classes) {
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
    public Student(Name name) {
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

    public ArrayList<Integer> getClassesArray() {
        return classes.getClasses();
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
     * Removes a tuition class from the student enrolled in the class.
     *
     * @param tuitionClass The tuition class to be removed.
     * @return Copy of student after removing the tuition class.
     */
    public Student removeClass(TuitionClass tuitionClass) {
        for (Integer id : classes.getClasses()) {
            if (tuitionClass.getId() == id) {
                classes.removeClass(id);
                Set<Tag> updatedTags = removeTag(tuitionClass.getName(), tuitionClass.getTimeslot());
                return new Student(name, phone, email, address, remark, updatedTags, classes);
            }
        }
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
     * Adds a new tag to the student.
     *
     * @param tag The tag to be added.
     * @return Copy of the updated set of tags.
     */
    public Set<Tag> addTag(Tag tag) {
        tags.add(tag);
        Set<Tag> updatedTags = new HashSet<>();
        updatedTags.addAll(tags);
        return updatedTags;
    }

    /**
     * Returns the set of tags belonging to the student after removing a tuition class tag.
     *
     * @param name The name of the tuition class.
     * @param slot The timeslot of the tuition class.
     * @return Copy of the updated tags after removing a class tag.
     */
    public Set<Tag> removeTag(ClassName name, Timeslot slot) {
        Set<Tag> updatedTags = new HashSet<Tag>();
        tags.remove(new Tag(String.format("%s | %s", name, slot)));
        updatedTags.addAll(tags);
        return updatedTags;
    }

    /**
     * Updates the name and timeslot of the class tag.
     *
     * @param tuitionClass The original tuition class.
     * @param updatedClass The updated tuition class.
     * @return The student after updating the class.
     */
    public Student updateTag(TuitionClass tuitionClass, TuitionClass updatedClass) {
        Tag tag = new Tag(String.format("%s | %s", tuitionClass.getNameString(), tuitionClass.getTimeslot()));
        tags.remove(tag);
        tags.add(new Tag(String.format("%s | %s", updatedClass.getNameString(), updatedClass.getTimeslot())));
        Set<Tag> updatedTags = new HashSet<Tag>(tags);

        return new Student(this.name, phone, email, address, remark, updatedTags, classes);
    }
    /**
     * Returns true if both students have the same name.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        if (otherStudent == null || !(otherStudent instanceof Student)) {
            return false;
        }

        String thisName = this.getNameString().trim().replaceAll(" +", " ").toLowerCase();
        String otherName = otherStudent.getNameString().trim().replaceAll(" +", " ").toLowerCase();
        return thisName.equals(otherName);
    }

    public Student getSameNameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return this;
        }
        boolean sameName = otherStudent != null && otherStudent.getName().fullName.equals(name.fullName);
        if (sameName) {
            return this;
        }
        return null;
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getTags().equals(getTags());
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
            builder.append("; Classes: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Sets most recently viewed student to a given Student.
     * @param student Student to set as most recently looked at.
     */
    public static void setMostRecentTo(Student student) {
        mostRecent = student;
    }

    /**
     * Returns the most recently viewed student
     * @return most recently viewed student.
     */
    public static Student getMostRecent() {
        return mostRecent;
    }

}
