package seedu.address.storage;

import static seedu.address.storage.JsonAdaptedInterview.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;
import static seedu.address.testutil.TypicalPositions.ADMIN_ASSISTANT;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Interview.InterviewStatus;
import seedu.address.model.position.Position;
import seedu.address.model.position.Title;

class JsonAdaptedInterviewTest {
    private static final String INVALID_TITLE = "Acc-Manager";
    private static final String INVALID_DATE_WRONG_ORDER = "2021/10/15";
    private static final String INVALID_DATE_NOT_A_DATE = "32/13/2100";
    private static final String INVALID_TIME_HAS_COLON = "18:00";
    private static final String INVALID_TIME_NOT_A_TIME = "2600";
    private static final String INVALID_DURATION_NEGATIVE = "-10";
    private static final String INVALID_DURATION_NOT_NUMBER = "Twenty";
    private static final String VALID_DATE = "15/10/2021";
    private static final String VALID_TIME = "1200";
    private static final String VALID_DURATION = "120";
    private static final String VALID_POSITION = ADMIN_ASSISTANT.getTitle().fullTitle;
    private static final InterviewStatus VALID_INTERVIEW_STATUS_PENDING = InterviewStatus.PENDING;
    private static final Set<String> VALID_CANDIDATE_ID_SET = getTypicalPersons().stream()
            .map(p -> String.valueOf(p.hashCode())).collect(Collectors.toSet());

    @Test
    public void toModelType_invalidPositionName_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(INVALID_TITLE, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Title.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullPositionName_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(null, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Position.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidDateFormat_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        INVALID_DATE_WRONG_ORDER, VALID_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        INVALID_DATE_NOT_A_DATE, VALID_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_DATE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        null, VALID_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "date");
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidTimeFormat_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, INVALID_TIME_HAS_COLON, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, INVALID_TIME_NOT_A_TIME, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_TIME_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullTime_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, null, VALID_DURATION, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "time");
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidDurationFormat_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, INVALID_DURATION_NOT_NUMBER, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_DURATION_CONSTRAINTS_NOT_A_NUMBER;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, INVALID_DURATION_NEGATIVE, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = Interview.MESSAGE_DURATION_CONSTRAINTS_INVALID_NUMBER;
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, null, VALID_INTERVIEW_STATUS_PENDING);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, "duration");
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }

    @Test
    public void toModelType_nullStatus_throwsIllegalValueException() {
        JsonAdaptedInterview interview =
                new JsonAdaptedInterview(VALID_POSITION, VALID_CANDIDATE_ID_SET,
                        VALID_DATE, VALID_TIME, VALID_DURATION, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, InterviewStatus.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, interview::toModelType);
    }
}
