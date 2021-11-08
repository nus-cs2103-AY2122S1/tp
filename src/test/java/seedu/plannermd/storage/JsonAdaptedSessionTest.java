package seedu.plannermd.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.plannermd.storage.JsonAdaptedAppointment.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.plannermd.testutil.Assert.assertThrows;
import static seedu.plannermd.testutil.appointment.TypicalAppointments.FIVE_MIN_APPOINTMENT;

import org.junit.jupiter.api.Test;

import seedu.plannermd.commons.exceptions.IllegalValueException;
import seedu.plannermd.model.appointment.Duration;
import seedu.plannermd.model.appointment.Session;

class JsonAdaptedSessionTest {
    private static final String INVALID_START_TIME_1 = "18-00";
    private static final String INVALID_START_TIME_2 = "25:00";
    private static final Integer INVALID_DURATION_1 = 0;
    private static final Integer INVALID_DURATION_2 = 121;

    private static final Session VALID_SESSION = FIVE_MIN_APPOINTMENT.getSession();
    private static final String VALID_START_TIME = VALID_SESSION.toInputStringFormat();
    private static final Integer VALID_DURATION = VALID_SESSION.getMinutes();

    @Test
    public void toModelType_validSessionDetails_returnsSession() throws Exception {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_SESSION);
        assertEquals(VALID_SESSION, session.toModelType());
    }

    @Test
    public void toModelType_invalidStartTime_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(INVALID_START_TIME_1, VALID_DURATION);
        String expectedMessage = Session.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);

        session = new JsonAdaptedSession(INVALID_START_TIME_2, VALID_DURATION);
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(null, VALID_DURATION);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, JsonAdaptedSession.IDENTIFIER_START_TIME);
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_START_TIME, INVALID_DURATION_1);
        String expectedMessage = Duration.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);

        session = new JsonAdaptedSession(VALID_START_TIME, INVALID_DURATION_2);
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedSession session = new JsonAdaptedSession(VALID_START_TIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Duration.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, session::toModelType);
    }
}
