package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedApplication.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalApplications.AMAZON;
import static seedu.address.testutil.TypicalApplications.BYTEDANCE;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.application.Company;
import seedu.address.model.application.Completion;
import seedu.address.model.application.Deadline;
import seedu.address.model.application.Position;
import seedu.address.model.application.Priority;
import seedu.address.model.application.Status;

public class JsonAdaptedApplicationTest {

    private static final String INVALID_COMPANY = "433#$%";
    private static final String INVALID_POSITION = " ";
    private static final String INVALID_DEADLINE = "243114";
    private static final String INVALID_COMPLETION = "done";
    private static final String INVALID_STATUS = "maybe";
    private static final String INVALID_PRIORITY = "prioritised";

    private static final String VALID_COMPANY = AMAZON.getCompany().toString();
    private static final String VALID_POSITION = AMAZON.getPosition().toString();
    private static final String VALID_DEADLINE = AMAZON.getDeadline().toString();
    private static final String VALID_STATUS = AMAZON.getStatus().toString();
    private static final String VALID_COMPLETION = AMAZON.getCompletion().toString();
    private static final String VALID_PRIORITY = AMAZON.getPriority().toString();
    private static final List<JsonAdaptedRequirement> VALID_REQUIREMENTS = AMAZON.getRequirements().stream()
            .map(JsonAdaptedRequirement::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedInterviewDateAndTime> VALID_INTERVIEW_DATE_AND_TIME =
            AMAZON.getInterviewDateAndTime().stream()
            .map(JsonAdaptedInterviewDateAndTime::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validApplication_success() throws Exception {
        JsonAdaptedApplication application = new JsonAdaptedApplication(BYTEDANCE);
        assertEquals(BYTEDANCE, application.toModelType());
    }

    @Test
    public void toModelType_invalidCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(INVALID_COMPANY, VALID_POSITION, VALID_DEADLINE, VALID_COMPLETION,
                        VALID_STATUS, VALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Company.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompany_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(null, VALID_POSITION,
                VALID_DEADLINE, VALID_COMPLETION, VALID_STATUS, VALID_PRIORITY,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Company.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, INVALID_POSITION, VALID_DEADLINE, VALID_COMPLETION,
                        VALID_STATUS, VALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Position.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullPosition_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, null,
                VALID_DEADLINE, VALID_COMPLETION, VALID_STATUS, VALID_PRIORITY,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION, INVALID_DEADLINE, VALID_COMPLETION,
                        VALID_STATUS, VALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Deadline.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullDeadline_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION,
                null, VALID_COMPLETION, VALID_STATUS, VALID_PRIORITY,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Deadline.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidCompletion_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION, VALID_DEADLINE, INVALID_COMPLETION,
                        VALID_STATUS, VALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Completion.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullCompletion_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION,
                VALID_DEADLINE, null, VALID_STATUS, VALID_PRIORITY,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Completion.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidStatus_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION, VALID_DEADLINE, VALID_COMPLETION,
                        INVALID_STATUS, VALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Status.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION,
                VALID_DEADLINE, VALID_COMPLETION, null, VALID_PRIORITY,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Status.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_invalidPriority_throwsIllegalValueException() {
        JsonAdaptedApplication application =
                new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION, VALID_DEADLINE, VALID_COMPLETION,
                        VALID_STATUS, INVALID_PRIORITY, VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = Priority.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }

    @Test
    public void toModelType_nullPriority_throwsIllegalValueException() {
        JsonAdaptedApplication application = new JsonAdaptedApplication(VALID_COMPANY, VALID_POSITION,
                VALID_DEADLINE, VALID_COMPLETION, VALID_STATUS, null,
                VALID_REQUIREMENTS, VALID_INTERVIEW_DATE_AND_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Priority.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, application::toModelType);
    }


}
