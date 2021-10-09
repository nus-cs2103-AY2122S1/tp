package seedu.academydirectory.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.academydirectory.model.student.Address;
import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Attendance;
import seedu.academydirectory.model.student.Email;
import seedu.academydirectory.model.student.Name;
import seedu.academydirectory.model.student.Phone;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.Telegram;
import seedu.academydirectory.model.tag.Tag;
import seedu.academydirectory.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEGRAM = "@amy";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final int DEFAULT_NUMBER_OF_STUDIO_SESSIONS = 10;

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private Address address;
    private Set<Tag> tags;
    private Attendance attendance;
    private Assessment assessment;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        attendance = new Attendance(DEFAULT_NUMBER_OF_STUDIO_SESSIONS);
        assessment = new Assessment();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        telegram = studentToCopy.getTelegram();
        address = studentToCopy.getAddress();
        tags = new HashSet<>(studentToCopy.getTags());
        attendance = studentToCopy.getAttendance();
        assessment = studentToCopy.getAssessment();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
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
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Attendance} of the {@code Student} that we are building.
     */
    public StudentBuilder withAttendance(boolean[] boolArr) {
        Attendance newAttendance = new Attendance(this.attendance.getSessionCount());
        newAttendance.setAttendance(boolArr);
        this.attendance = newAttendance;
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
     * Sets the {@code Telegram} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegram(String telegram) {
        this.telegram = new Telegram(telegram);
        return this;
    }

    /**
     * Sets the {@code Assessment} of the {@code Student} that we are building.
     */
    public StudentBuilder withAssessment() {
        this.assessment = new Assessment();
        return this;
    }

    /**
     * Builds the Student object for testing.
     */
    public Student build() {
        Student newStudent = new Student(name, phone, email, telegram, address, tags);
        newStudent.setAttendance(this.attendance);
        return newStudent;
    }

}
