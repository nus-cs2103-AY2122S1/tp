package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.RecurrencePeriod;
import seedu.address.model.person.Period;
import seedu.address.model.person.Slot;

public class JsonAdaptedRecurrencePeriodTest {
    private static final Period DEFAULT_TEST_PERIOD = createPeriod(1, 10);
    private static final RecurrencePeriod DEFAULT_TEST_MORNING =
            new RecurrencePeriod(DEFAULT_TEST_PERIOD, Slot.MORNING);
    private static final RecurrencePeriod DEFAULT_TEST_AFTERNOON =
            new RecurrencePeriod(DEFAULT_TEST_PERIOD, Slot.AFTERNOON);
    private static final String INVALID_TIME = "25:00";
    private static final String INVALID_TIME_EMPTY = "";
    private static final String VALID_TIME_AFTERNOON = "16:00";
    private static final String VALID_TIME_AFTERNOON_LATE = "18:00";
    private static final String VALID_TIME_MORNING = "10:00";

    private JsonAdaptedRecurrencePeriod createRePeriod(String start, String end) {
        return new JsonAdaptedRecurrencePeriod(new JsonAdaptedPeriod(DEFAULT_TEST_PERIOD),
                start, end);
    }

    @Test
    public void toModelType_validRecPeriod() throws Exception {
        JsonAdaptedRecurrencePeriod toTest = new JsonAdaptedRecurrencePeriod(DEFAULT_TEST_MORNING);
        assertEquals(DEFAULT_TEST_MORNING, toTest.toModelType());

        toTest = new JsonAdaptedRecurrencePeriod((DEFAULT_TEST_AFTERNOON));
        assertEquals(DEFAULT_TEST_AFTERNOON, toTest.toModelType());
    }

    private void testTwoStringError(String start, String end) {
        JsonAdaptedRecurrencePeriod toTest = createRePeriod(start, end);
        assertThrows(IllegalValueException.class, toTest::toModelType);
    }

    @Test
    public void toModelType_inValidTime() {

        testTwoStringError(VALID_TIME_AFTERNOON, INVALID_TIME);

        testTwoStringError(VALID_TIME_AFTERNOON, INVALID_TIME_EMPTY);

        testTwoStringError(INVALID_TIME_EMPTY, VALID_TIME_MORNING);

        testTwoStringError(INVALID_TIME, VALID_TIME_MORNING);

    }


}
