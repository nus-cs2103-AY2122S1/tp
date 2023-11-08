package seedu.address.model.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TodayAttendanceTest {

    @Test
    void getAttendanceStatusIcon() {
        TodayAttendance testTodayAttendance1 = new TodayAttendance(true);
        assertEquals("✓", testTodayAttendance1.getAttendanceStatusIcon());

        TodayAttendance testTodayAttendance2 = new TodayAttendance(false);
        assertEquals("X", testTodayAttendance2.getAttendanceStatusIcon());
    }

    @Test
    void toStringTest() {
        TodayAttendance testTodayAttendance = new TodayAttendance(true);
        assertEquals("Today's attendance: ✓", testTodayAttendance.toString());
    }

    @Test
    void equals() {
        TodayAttendance firstTodayAttendance = new TodayAttendance(true);
        TodayAttendance secondTodayAttendance = new TodayAttendance(false);

        assertTrue(firstTodayAttendance.equals(firstTodayAttendance));

        TodayAttendance firstTodayAttendanceCopy = new TodayAttendance(true);
        assertTrue(firstTodayAttendance.equals(firstTodayAttendanceCopy));

        assertFalse(firstTodayAttendance.equals(secondTodayAttendance));

        assertFalse(firstTodayAttendance.equals(null));

        assertFalse(firstTodayAttendance.equals("1"));
    }
}
