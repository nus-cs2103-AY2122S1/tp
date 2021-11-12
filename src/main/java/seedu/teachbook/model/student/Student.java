package seedu.teachbook.model.student;

import static seedu.teachbook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.tag.Tag;

/**
 * Represents a Student in the TeachBook.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    private final Name name;
    private final Phone phone;
    private Class studentClass;
    private final Email email;
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();
    private final Attendance attendance;
    private final Grade grade;

    /**
     * Constructs a {@code Student} object without a class.
     * The class of the student can be set later.
     *
     * @param name name of student.
     * @param phone phone number of student.
     * @param email email address of student.
     * @param address address of student.
     * @param remark remark of student.
     * @param tags tags of student.
     * @param attendance attendance of student.
     * @param grade grade of student.
     */
    public Student(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
                   Attendance attendance, Grade grade) {
        this(name, phone, null, email, address, remark, tags, attendance, grade);
    }

    /**
     * Constructs a {@code Student} object.
     *
     * @param name name of student.
     * @param phone phone number of student.
     * @param studentClass Class of student.
     * @param email email address of student.
     * @param address address of student.
     * @param remark remark of student.
     * @param tags tags of student.
     * @param attendance attendance of student.
     * @param grade grade of student.
     */
    public Student(Name name, Phone phone, Class studentClass, Email email, Address address, Remark remark,
                   Set<Tag> tags, Attendance attendance, Grade grade) {
        requireAllNonNull(name, phone, email, address, remark, tags, attendance, grade);
        this.name = name;
        this.phone = phone;
        this.studentClass = studentClass;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.tags.addAll(tags);
        this.attendance = attendance;
        this.grade = grade;
    }

    /**
     * Constructs a {@code Student} object from an existing {@code Student} object.
     *
     * @param toCopy existing {@code Student} object.
     */
    public Student(Student toCopy) {
        this.name = toCopy.getName();
        this.phone = toCopy.getPhone();
        this.studentClass = toCopy.getStudentClass();
        this.email = toCopy.getEmail();
        this.address = toCopy.getAddress();
        this.remark = toCopy.getRemark();
        this.tags.addAll(toCopy.getTags());
        this.attendance = toCopy.getAttendance();
        this.grade = toCopy.getGrade();
        this.studentClass = toCopy.getStudentClass();
    }

    /**
     * Returns the name of the {@code Student} object.
     *
     * @return name of {@code Student} object.
     */
    public Name getName() {
        return name;
    }

    /**
     * Returns the phone number of the {@code Student} object.
     *
     * @return phone number of {@code Student} object.
     */
    public Phone getPhone() {
        return phone;
    }

    /**
     * Returns the class of the {@code Student} object.
     *
     * @return class of {@code Student} object.
     */
    public Class getStudentClass() {
        return studentClass;
    }

    /**
     * Returns the email address of the {@code Student} object.
     *
     * @return email address of {@code Student} object.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Returns the address of the {@code Student} object.
     *
     * @return address of {@code Student} object.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Returns the remark of the {@code Student} object.
     *
     * @return remark of {@code Student} object.
     */
    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException} if modification is attempted.
     *
     * @return tags of {@code Student} object.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns the attendance of the {@code Student} object.
     *
     * @return attendance of {@code Student} object.
     */
    public Attendance getAttendance() {
        return attendance;
    }

    /**
     * Returns the formatted attendance string representation of the {@code Student} object.
     *
     * @return formatted attendance string representation of {@code Student} object.
     */
    public String getAttendanceString() {
        return attendance.asFormattedString();
    }

    /**
     * Returns the grade of the {@code Student} object.
     *
     * @return grade of {@code Student} object.
     */
    public Grade getGrade() {
        return grade;
    }

    /**
     * Sets the class of the {@code Student} object.
     *
     * @param studentClass Class of student.
     */
    public void setStudentClass(Class studentClass) {
        this.studentClass = studentClass;
    }

    /**
     * Checks if the student is present.
     *
     * @return {@code true} if the student is present.
     */
    public boolean isPresent() {
        return attendance.getIsPresent();
    }

    /**
     * Returns true if both students have the same name and are from the same class.
     * This defines a weaker notion of equality between two students.
     *
     * @param otherStudent other student to be checked against.
     * @return {@code true} if both students have the same name and are from the same class.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && otherStudent.getStudentClass().equals(getStudentClass());
    }

    /**
     * Returns the string representation of a {@code Student} object consisting only its non-empty fields.
     *
     * @return string representation of {@code Student} object.
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Class: ")
                .append(getStudentClass());
        if (!phone.value.equals("")) {
            builder.append("; Phone: ").append(getPhone());
        }
        if (!email.value.equals("")) {
            builder.append("; Email: ").append(getEmail());
        }
        if (!address.value.equals("")) {
            builder.append("; Address: ").append(getAddress());
        }
        if (!remark.value.equals("")) {
            builder.append("; Remark: ").append(getRemark());
        }
        if (!grade.value.equals("")) {
            builder.append("; Grade: ").append(getGrade());
        }
        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     *
     * @param other other object to be checked against.
     * @return {@code true} if both students have the same identity and data fields.
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
                // at any certain point of time, a student obj may exist without a class ie. just after being parsed
                // by AddCommandParser
                && ((otherStudent.getStudentClass() == null && getStudentClass() == null)
                || (otherStudent.getStudentClass() != null && getStudentClass() != null
                && otherStudent.getStudentClass().getClassName().equals(getStudentClass().getClassName())))
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getAddress().equals(getAddress())
                && otherStudent.getRemark().equals(getRemark())
                && otherStudent.getTags().equals(getTags())
                // this is to skip pass the checking of lastModified field in an attendance obj as the sensitivity of
                // LocalDateTime.now() is very high eg. 2021-10-27T22:52:40.133105900
                && otherStudent.isPresent() == isPresent()
                && otherStudent.getGrade().equals(getGrade());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, studentClass.getClassName(), email, address, remark, tags, attendance, grade);
    }
}
