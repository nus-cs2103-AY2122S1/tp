package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;


/**
 * Represents a Person in the ProgrammerError.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final StudentId studentId;
    private final ClassId classId;
    private final Grade grade;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, StudentId studentId, ClassId classId, Grade grade) {
        requireAllNonNull(name, studentId, classId, grade);
        this.name = name;
        this.studentId = studentId;
        this.classId = classId;
        this.grade = grade;
    }

    public Name getName() {
        return name;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public ClassId getClassId() {
        return classId;
    }

    public Grade getGrade() {
        return grade;
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
                && otherPerson.getStudentId().equals(getStudentId())
                && otherPerson.getClassId().equals(getClassId())
                && otherPerson.getGrade().equals(getGrade());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, studentId, classId, grade);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Student_Id: ")
                .append(getStudentId())
                .append("; Class_Id: ")
                .append(getClassId())
                .append("; Grade: ")
                .append(getGrade());

        return builder.toString();
    }

}
