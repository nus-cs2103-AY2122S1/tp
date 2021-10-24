package seedu.address.testutil;

import javafx.collections.ObservableList;
import seedu.address.model.assessment.Assessment;
import seedu.address.model.assessment.AssessmentName;
import seedu.address.model.assessment.Score;
import seedu.address.model.assessment.UniqueAssessmentList;
import seedu.address.model.group.GroupName;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.student.TelegramHandle;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TELEGRAM_HANDLE = "@amy_bee";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_GROUP_NAME = "CS2103T";
    public static final String DEFAULT_ASSESSMENT_NAME = "Midterms";
    public static final int DEFAULT_ACTUAL_SCORE = 60;
    public static final int DEFAULT_TOTAL_SCORE = 100;

    private Name name;
    private TelegramHandle telegramHandle;
    private Email email;
    private GroupName groupName;
    private UniqueAssessmentList assessments;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        telegramHandle = new TelegramHandle(DEFAULT_TELEGRAM_HANDLE);
        email = new Email(DEFAULT_EMAIL);
        groupName = new GroupName(DEFAULT_GROUP_NAME);

        assessments = new UniqueAssessmentList();
        Assessment assessment = new Assessment(new AssessmentName(DEFAULT_ASSESSMENT_NAME),
                new Score(DEFAULT_ACTUAL_SCORE, DEFAULT_TOTAL_SCORE));
        assessments.add(assessment);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        telegramHandle = studentToCopy.getTelegramHandle();
        email = studentToCopy.getEmail();
        groupName = studentToCopy.getGroupName();
        assessments = new UniqueAssessmentList();

        ObservableList<Assessment> assessmentList = studentToCopy.getAssessmentList();
        for (int i = 0; i < assessmentList.size(); i++) {
            assessmentList.add(assessmentList.get(i));
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
     * Sets the {@code GroupName} of the {@code Student} that we are building.
     */
    public StudentBuilder withGroupName(String groupName) {
        this.groupName = new GroupName(groupName);
        return this;
    }

    /**
     * Sets the {@code GroupName} of the {@code Student} that we are building.
     */
    public StudentBuilder withGroupName(GroupName groupName) {
        this.groupName = new GroupName(groupName.toString());
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
        return new Student(name, telegramHandle, email, groupName);
    }

}
