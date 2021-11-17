package socialite.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import socialite.commons.exceptions.IllegalValueException;
import socialite.model.person.Date;
import socialite.testutil.Assert;

public class JsonAdaptedDateTest {
    private static final String INVALID_NAME = "";
    private static final String INVALID_RECURRENCE = "mon";

    private static final Date VALID_DATE = new Date("date 1:2020-01-01:yearly");
    private static final String VALID_NAME = "date 1";
    private static final LocalDate VALID_LOCALDATE = LocalDate.of(2020, 1, 1);
    private static final String VALID_RECURRENCE = "yearly";

    @Test
    public void toModelType_validDate_returnsDate() throws Exception {
        JsonAdaptedDate date = new JsonAdaptedDate(VALID_DATE);
        assertEquals(VALID_DATE, date.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedDate date = new JsonAdaptedDate(INVALID_NAME, VALID_LOCALDATE, VALID_RECURRENCE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedDate date = new JsonAdaptedDate(null, VALID_LOCALDATE, VALID_RECURRENCE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }

    @Test
    public void toModelType_invalidRecurrence_throwsIllegalValueException() {
        JsonAdaptedDate date = new JsonAdaptedDate(VALID_NAME, VALID_LOCALDATE, INVALID_RECURRENCE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }

    @Test
    public void toModelType_nullRecurrence_returnsDate() throws IllegalValueException {
        JsonAdaptedDate date = new JsonAdaptedDate(VALID_NAME, VALID_LOCALDATE, null);
        assertEquals(new Date("date 1:2020-01-01"), date.toModelType());
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedDate date = new JsonAdaptedDate(INVALID_NAME, null, VALID_RECURRENCE);
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        Assert.assertThrows(IllegalValueException.class, expectedMessage, date::toModelType);
    }
}
