package nustracker.model.student;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

import nustracker.commons.util.CollectionUtil;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Year year;
    private final Major major;
    private final StudentId studentId;
    private final EnrolledEvents enrolledEvents;

    /**
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, Year year, Major major,
                   StudentId studentId, EnrolledEvents enrolledEvents) {
        CollectionUtil.requireAllNonNull(name, phone, email, year, major, studentId, enrolledEvents);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.year = year;
        this.major = major;
        this.studentId = studentId;
        this.enrolledEvents = enrolledEvents;
        Major.addStudent(this);
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

    public Year getYear() {
        return year;
    }

    public Major getMajor() {
        return major;
    }

    public StudentId getStudentId() {
        return studentId;
    }

    public EnrolledEvents getEvents() {
        return enrolledEvents;
    }

    /**
     * Returns true if the other student has the same credentials in the fields
     * where having the same ones is not allowed like the student ID, email, & phone.
     */
    public boolean hasDuplicateCredentials(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        boolean notNull = otherStudent != null;
        boolean sameId = notNull && otherStudent.getStudentId().equals(getStudentId());
        boolean sameEmail = notNull && otherStudent.getEmail().equals(getEmail());
        boolean samePhone = notNull && otherStudent.getPhone().equals(getPhone());

        return sameId || sameEmail || samePhone;
    }

    /**
     * Wraps the studentId in a Student for easy re-usability with other methods.
     *
     * @param studentId The studentId
     * @return A Student with the given studentId, and pseudo details.
     */
    public static Student pseudoStudent(StudentId studentId) {
        requireNonNull(studentId);

        String validName = "Pseudo Student";
        String validPhone = "00000000";
        String validEmail = "pseudoStudent@gmail.com";
        String validYear = "1";
        String validMajor = "CS";
        EnrolledEvents validEnrolledEvents = new EnrolledEvents();
        return new Student(new Name(validName),
                new Phone(validPhone),
                new Email(validEmail),
                new Year(validYear),
                new Major(validMajor),
                studentId,
                validEnrolledEvents);
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
                && otherStudent.getYear().equals(getYear())
                && otherStudent.getMajor().equals(getMajor())
                && otherStudent.getStudentId().equals(getStudentId())
                && otherStudent.getEvents().equals(getEvents());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, year, major, studentId);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Major: ")
                .append(getMajor())
                .append("; StudentId: ")
                .append(getStudentId())
                .append("; Year: ")
                .append(getYear())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail());
        if (enrolledEvents.hasEvents()) {
            builder.append("; Events:");
            builder.append(enrolledEvents.getEventNamesString());
        }
        return builder.toString();
    }

}
