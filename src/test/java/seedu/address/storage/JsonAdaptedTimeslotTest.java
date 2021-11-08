package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedTimeslot.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.module.ModBookTime;
import seedu.address.model.module.Timeslot;
import seedu.address.testutil.builders.TimeslotBuilder;

class JsonAdaptedTimeslotTest {
    private static final JsonAdaptedModBookTime START_TIME = new JsonAdaptedModBookTime("10:00");
    private static final JsonAdaptedModBookTime END_TIME = new JsonAdaptedModBookTime("11:30");
    private static final Timeslot VALID_TIMESLOT = new TimeslotBuilder().build();

    @Test
    public void toModelType_validTime_returnsTimeslot() throws Exception {
        JsonAdaptedTimeslot timeslot = new JsonAdaptedTimeslot(VALID_TIMESLOT);
        assertEquals(VALID_TIMESLOT, timeslot.toModelType());
    }

    @Test
    public void toModelType_invalidStartEndTime_throwsIllegalValueException() {
        JsonAdaptedTimeslot timeslot = new JsonAdaptedTimeslot(END_TIME, START_TIME);
        String expectedMessage = Timeslot.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, timeslot::toModelType);
    }

    @Test
    public void toModelType_nullStartTime_throwsIllegalValueException() {
        JsonAdaptedTimeslot timeslot = new JsonAdaptedTimeslot(null, END_TIME);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModBookTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, timeslot::toModelType);
    }

    @Test
    public void toModelType_nullEndTime_throwsIllegalValueException() {
        JsonAdaptedTimeslot timeslot = new JsonAdaptedTimeslot(START_TIME, null);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, ModBookTime.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, timeslot::toModelType);
    }
}
