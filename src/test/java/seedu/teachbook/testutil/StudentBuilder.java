package seedu.teachbook.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.teachbook.model.classobject.Class;
import seedu.teachbook.model.classobject.ClassName;
import seedu.teachbook.model.gradeobject.Grade;
import seedu.teachbook.model.student.Address;
import seedu.teachbook.model.student.Attendance;
import seedu.teachbook.model.student.Email;
import seedu.teachbook.model.student.Name;
import seedu.teachbook.model.student.Phone;
import seedu.teachbook.model.student.Remark;
import seedu.teachbook.model.student.Student;
import seedu.teachbook.model.tag.Tag;
import seedu.teachbook.model.util.SampleDataUtil;

/**
 * A utility class to help with building {@code Student} objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_CLASS = "A";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_REMARK = "Allergic to seafood.";
    public static final String DEFAULT_GRADE = "";

    private Name name;
    private Phone phone;
    private Class studentClass;
    private Email email;
    private Address address;
    private Remark remark;
    private Set<Tag> tags;
    private Attendance attendance;
    private Grade grade;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        studentClass = new Class(new ClassName(DEFAULT_CLASS));
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        remark = new Remark(DEFAULT_REMARK);
        tags = new HashSet<>();
        attendance = Attendance.getDefaultAttendance();
        grade = new Grade(DEFAULT_GRADE);
    }

    /**
     * Initializes the {@code StudentBuilder} with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        studentClass = studentToCopy.getStudentClass();
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        remark = studentToCopy.getRemark();
        tags = new HashSet<>(studentToCopy.getTags());
        attendance = studentToCopy.getAttendance();
        grade = studentToCopy.getGrade();
    }

    /**
     * Initializes the {@code StudentBuilder} with the data of {@code studentToCopy}, but set student class to null.
     */
    public StudentBuilder(Student studentToCopy, boolean isForAddCommandParser) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        studentClass = null;
        email = studentToCopy.getEmail();
        address = studentToCopy.getAddress();
        remark = studentToCopy.getRemark();
        tags = new HashSet<>(studentToCopy.getTags());
        attendance = studentToCopy.getAttendance();
        grade = studentToCopy.getGrade();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Class} of the {@code Student} that we are building.
     */
    public StudentBuilder withClass(String className) {
        if (className != null) {
            this.studentClass = new Class(new ClassName(className));
        } else {
            this.studentClass = null;
        }
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Student} that we are building.
     */
    public StudentBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Remark} of the {@code Student} that we are building.
     */
    public StudentBuilder withRemark(String remark) {
        this.remark = new Remark(remark);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(String attendance) {
        this.attendance = Attendance.fromString(attendance);
        return this;
    }

    /**
     * Sets the {@code Grade} of the {@code Student} that we are building.
     */
    public StudentBuilder withGrade(String grade) {
        this.grade = new Grade(grade);
        return this;
    }

    /**
     * Builds a valid {@code Student} object as per resulted after the execution of {@code AddCommand}.
     */
    public Student build() {
        return new Student(name, phone, studentClass, email, address, remark, tags, attendance, grade);
    }

    /**
     * Builds a valid {@code Student} object to be passed into {@code AddCommandParser}.
     */
    public Student buildToAdd() {
        return new Student(name, phone, null, email, address, new Remark(""), tags,
                Attendance.getDefaultAttendance(), new Grade(""));
    }

}
