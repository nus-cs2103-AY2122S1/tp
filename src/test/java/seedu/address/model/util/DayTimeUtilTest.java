package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.friend.exceptions.InvalidDayTimeException;

class DayTimeUtilTest {

    @Test
    void getTimeFromIndex() throws InvalidDayTimeException {
        // Valid index
        assertEquals("0000", DayTimeUtil.getTimeFromIndex(0));
        assertEquals("0900", DayTimeUtil.getTimeFromIndex(9));
        assertEquals("2300", DayTimeUtil.getTimeFromIndex(23));

        // Invalid index
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getTimeFromIndex(-1));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getTimeFromIndex(24));
    }

    @Test
    void getIndexFromTime() throws InvalidDayTimeException {
        // Valid time string
        assertEquals(0, DayTimeUtil.getIndexFromTime("0000"));
        assertEquals(9, DayTimeUtil.getIndexFromTime("0900"));
        assertEquals(23, DayTimeUtil.getIndexFromTime("2300"));

        // Invalid time string
        assertThrows(NullPointerException.class, () -> DayTimeUtil.getIndexFromTime(null));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime(""));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("20"));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("22000"));
        // Invalid time stamp
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("2400"));
        // Invalid time containing minutes
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("2202"));
        assertThrows(InvalidDayTimeException.class, () -> DayTimeUtil.getIndexFromTime("2220"));
    }
}
