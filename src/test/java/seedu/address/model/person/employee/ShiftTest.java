package seedu.address.model.person.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ShiftTest {

    public static final String INVALID_MONTH = "2021-13-20 0700";
    public static final String INVALID_DAY = "2021-12-40 0800";
    public static final String INVALID_TIME = "2021-12-12 2800";
    public static final String VALID_SHIFT_1 = "2021-12-08 0800";
    public static final String VALID_SHIFT_2 = "2021-11-11 1700";
    public static final String VALID_SHIFT_3 = "2021-12-12 12:00";
    public static final String VALID_SHIFT_4 = "12-12-2021 12:00";
    public static final String INVALID_SHIFT_1 = "2021-12-12 12:00 am";
    public static final String INVALID_SHIFT_2 = "12-12-2021 2:00 pm";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Shift(null));
    }

    @Test
    public void constructor_invalidShiftName_throwsIllegalArgumentException() {
        String invalidShiftName = "";
        assertThrows(IllegalArgumentException.class, () -> new Shift(invalidShiftName));
    }

    @Test
    public void isValidShift_null() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Shift.isValidShift(null));
    }

    @Test
    public void isValidShiftFormats_success() {
        // valid shift using regex
        assertTrue(Shift.isValidShift(VALID_SHIFT_1));
        assertTrue(Shift.isValidShift(VALID_SHIFT_2));
        assertTrue(Shift.isValidShift(VALID_SHIFT_3));
        assertTrue(Shift.isValidShift(VALID_SHIFT_4));
    }

    @Test
    public void isValidShiftInvalidMonth_failure() {
        // invalid month
        assertFalse(Shift.isValidShift(INVALID_MONTH));
    }

    @Test
    public void isValidShiftInvalidDay_failure() {
        // invalid day
        assertFalse(Shift.isValidShift(INVALID_DAY));
    }

    @Test
    public void isValidShiftInvalidTime_failure() {
        // invalid time
        assertFalse(Shift.isValidShift(INVALID_TIME));
    }

    @Test
    public void isValidShiftInvalidFormat_failure() {
        // HH:mm instead of HHmm
        assertFalse(Shift.isValidShift(INVALID_SHIFT_1));
        assertFalse(Shift.isValidShift(INVALID_SHIFT_2));
    }

}
