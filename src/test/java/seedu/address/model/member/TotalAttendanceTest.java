package seedu.address.model.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TotalAttendanceTest {

    @Test
    void decrementAttendance() {
        TotalAttendance testTotalAttendance = new TotalAttendance(1);

        //1 -> 0
        testTotalAttendance.decrementAttendance();
        assertEquals(0, testTotalAttendance.getAttendance());

        //0 -> 0
        testTotalAttendance.decrementAttendance();
        assertEquals(0, testTotalAttendance.getAttendance());
    }

    @Test
    void toStringTest() {
        TotalAttendance testTotalAttendance = new TotalAttendance(1);
        String expectedString = "Total attendance: 1";
        assertEquals(expectedString, testTotalAttendance.toString());
    }

    @Test
    void equals() {
        TotalAttendance firstTotalAttendance = new TotalAttendance(1);
        TotalAttendance secondTotalAttendance = new TotalAttendance(2);

        assertTrue(firstTotalAttendance.equals(firstTotalAttendance));

        TotalAttendance firstTotalAttendanceCopy = new TotalAttendance(1);
        assertTrue(firstTotalAttendance.equals(firstTotalAttendanceCopy));

        assertFalse(firstTotalAttendance.equals(secondTotalAttendance));

        assertFalse(firstTotalAttendance.equals(null));

        assertFalse(firstTotalAttendance.equals("1"));
    }
}
