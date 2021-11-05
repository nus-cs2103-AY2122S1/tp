package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.model.person.PersonTestUtil.createPeriod;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Period;

public class JsonAdaptedPeriodTest {
    private static final Period DEFAULT_PERIOD = createPeriod(1, 10);
    private static final String INVALID_PERIOD = "15-15-15 80-04-02";
    private static final String INVALID_PERIOD_EMPTY = "";

    @Test
    public void toModelType_validPeriodDetails() throws Exception {
        JsonAdaptedPeriod toTest = new JsonAdaptedPeriod(DEFAULT_PERIOD);
        assertEquals(DEFAULT_PERIOD, toTest.toModelType());
    }

    @Test
    public void toModelType_invalidPeriodDetails() {
        JsonAdaptedPeriod toTest = new JsonAdaptedPeriod(INVALID_PERIOD);
        assertThrows(IllegalArgumentException.class, toTest::toModelType);

        toTest = new JsonAdaptedPeriod(INVALID_PERIOD_EMPTY);
        assertThrows(IllegalArgumentException.class, toTest::toModelType);
    }





}
