package seedu.academydirectory.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.academydirectory.model.student.Assessment;
import seedu.academydirectory.model.student.Attendance;
import seedu.academydirectory.model.student.Email;
import seedu.academydirectory.model.student.Name;
import seedu.academydirectory.model.student.Participation;
import seedu.academydirectory.model.student.Phone;
import seedu.academydirectory.model.student.Student;
import seedu.academydirectory.model.student.StudioRecord;
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
    public static final int DEFAULT_NUMBER_OF_STUDIO_SESSIONS = 12;

    private Name name;
    private Phone phone;
    private Email email;
    private Telegram telegram;
    private Set<Tag> tags;
    private StudioRecord studioRecord;
    private Assessment assessment;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        telegram = new Telegram(DEFAULT_TELEGRAM);
        tags = new HashSet<>();
        studioRecord = new StudioRecord(DEFAULT_NUMBER_OF_STUDIO_SESSIONS);
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
        tags = new HashSet<>(studentToCopy.getTags());
        studioRecord = studentToCopy.getStudioRecord();
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
        Integer sessionCount = studioRecord.getAttendance().getSessionCount();
        Attendance newAttendance = new Attendance(sessionCount);
        newAttendance.setAttendance(boolArr);
        this.studioRecord = new StudioRecord(newAttendance, studioRecord.getParticipation());
        return this;
    }

    /**
     * Sets the {@code Participation} of the {@code Student} that we are building.
     */
    public StudentBuilder withParticipation(int[] intArr) {
        Integer sessionCount = studioRecord.getAttendance().getSessionCount();
        Participation newParticipation = new Participation(sessionCount);
        newParticipation.setParticipation(intArr);
        this.studioRecord = new StudioRecord(studioRecord.getAttendance(), newParticipation);
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

        Student newStudent = new Student(name, phone, email, telegram, tags);
        newStudent.setAttendance(studioRecord.getAttendance());
        return newStudent;
    }

}
