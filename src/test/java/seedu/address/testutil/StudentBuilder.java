package seedu.address.testutil;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Note;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amy_bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_NOTE = "Weak in environment model.";
    public static final String DEFAULT_GROUP_NAME = "CS2103T";

    private Name name;
    private TelegramHandle telegramHandle;
    private Email email;
    private Note note;
    private GroupName groupName;
    private UniqueAssessmentList assessments;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        email = new Email(DEFAULT_EMAIL);
        note = new Note(DEFAULT_NOTE);
        groupName = new GroupName(DEFAULT_GROUP_NAME);
        assessments = new UniqueAssessmentList();
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        telegramHandle = studentToCopy.getTelegramHandle();
        email = studentToCopy.getEmail();
        note = studentToCopy.getNote();
        groupName = studentToCopy.getGroupName();
        assessments = new UniqueAssessmentList();

        ObservableList<Assessment> assessmentList = studentToCopy.getAssessmentList();
        for (int i = 0; i < assessmentList.size(); i++) {
            assessments.add(assessmentList.get(i));
        }
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code TelegramHandle} of the {@code Student} that we are building.
     */
    public StudentBuilder withTelegramHandle(String telegramHandle) {
        this.telegramHandle = new TelegramHandle(telegramHandle);
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
     * Sets the {@code Note} of the {@code Student} that we are building.
     */
    public StudentBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code GroupName} of the {@code Student} that we are building.
     */
    public StudentBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    /**
     * Add an {@code Assessment} to the {@code UniqueAssessmentList} of the {@code Student} that we are building.
     */
    public StudentBuilder withAssessment(Assessment assessment) {
        this.assessments.add(assessment);
        return this;
    }

    /**
     * Sets the {@code UniqueAssessmentList} of the {@code Student} that we are building.
     */
    public StudentBuilder withAssessments(UniqueAssessmentList assessments) {
        this.assessments = assessments;
        return this;
    }

    /**
     * Builds a student
     * @return built student
     */
    public Student build() {
        Student student = new Student(name, telegramHandle, email, note, groupName);
        ObservableList<Assessment> unmodifiableAssessments = assessments.asUnmodifiableObservableList();
        for (int i = 0; i < unmodifiableAssessments.size(); i++) {
            student.addAssessment(unmodifiableAssessments.get(i));
        }
        return student;
    }

}
