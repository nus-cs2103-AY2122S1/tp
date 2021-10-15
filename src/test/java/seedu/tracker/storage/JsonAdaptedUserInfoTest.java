package seedu.tracker.storage;

import org.junit.jupiter.api.Test;
import seedu.tracker.commons.exceptions.IllegalValueException;
import seedu.tracker.model.UserInfo;
import seedu.tracker.model.calendar.AcademicCalendar;
import seedu.tracker.model.calendar.AcademicYear;
import seedu.tracker.model.calendar.Semester;
import seedu.tracker.model.module.Mc;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.tracker.storage.JsonAdaptedUserInfo.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.tracker.testutil.Assert.assertThrows;

class JsonAdaptedUserInfoTest {
    private static final JsonAdaptedCalendar validSemester = new JsonAdaptedCalendar(1,1);

    private static final int validMcGoal = 120;
    private static final int invalidMcGoal = -120;

    private static final UserInfo validUserInfo = new UserInfo(
            new AcademicCalendar(new AcademicYear(1), new Semester(1)),
            new Mc(120)
    );


    @Test
    public void toModelType_validUserInfo_returnsUserInfo() throws Exception {
        JsonAdaptedUserInfo userInfo = new JsonAdaptedUserInfo(validUserInfo);
        assertEquals(validUserInfo, userInfo.toModelType());
    }

    @Test
    public void toModelType_invalidMc_throwsIllegalValueException() {
        JsonAdaptedUserInfo userInfo = new JsonAdaptedUserInfo(validSemester, invalidMcGoal);
        String expectedMessage = Mc.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, userInfo::toModelType);
    }

    @Test
    public void toModelType_nullSemester_throwsIllegalValueException() {
        JsonAdaptedUserInfo userInfo = new JsonAdaptedUserInfo(null, validMcGoal);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AcademicCalendar.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, userInfo::toModelType);
    }

}