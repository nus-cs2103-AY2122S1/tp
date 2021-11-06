package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.friend.exceptions.InvalidDayTimeException;

/**
 * Unreachable instruction in DayTimeUtilTest - class creation.
 */
class DayTimeUtilTest {

    @Test
    void getTimeFromIndex() throws InvalidDayTimeException {
        // Valid index
        assertEquals("0000", DayTimeUtil.getTimeFromIndex(0));
        assertEquals("0900", DayTimeUtil.getTimeFromIndex(9));
        assertEquals("2300", DayTimeUtil.getTimeFromIndex(23));

        // Invalid index
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getTimeFromIndex(-1));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getTimeFromIndex(25));
    }

    @Test
    void getIndexFromTime() throws InvalidDayTimeException {
        // Valid time string
        assertEquals(0, DayTimeUtil.getIndexFromTime("0"));
        assertEquals(9, DayTimeUtil.getIndexFromTime("9"));
        assertEquals(23, DayTimeUtil.getIndexFromTime("23"));

        // Invalid time string
        assertThrows(NullPointerException.class, () -> DayTimeUtil.getIndexFromTime(null));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("   "));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("-1"));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("25"));
    }
}
