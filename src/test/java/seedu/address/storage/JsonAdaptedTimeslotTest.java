package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTimeslots.TEN_TO_TWELVE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.lesson.Timeslot;

class JsonAdaptedTimeslotTest {

    public static final String TEN_AM = "10:00";
    public static final String TWELVE_PM = "12:00";

    @Test
    void toModelType_validTimings_returnsTimeslot() throws Exception {
        JsonAdaptedTimeslot timeslot = new JsonAdaptedTimeslot(TEN_TO_TWELVE);
        assertEquals(TEN_TO_TWELVE, timeslot.toModelType());
    }

    @Test
    void toModelType_invalidTimings_throwsIllegalValueException() {
        JsonAdaptedTimeslot jsonTimeslot = new JsonAdaptedTimeslot(TWELVE_PM, TEN_AM);
        assertThrows(IllegalValueException.class, Timeslot.MESSAGE_CONSTRAINTS, jsonTimeslot::toModelType);
    }

    @Test
    void toModelType_nullTiming_throwsIllegalValueException() {
        JsonAdaptedTimeslot jsonTimeslot = new JsonAdaptedTimeslot(null, null);
        String expected = String.format(MISSING_FIELD_MESSAGE_FORMAT, Timeslot.class.getSimpleName());
        assertThrows(IllegalValueException.class, expected, jsonTimeslot::toModelType);
    }
}
