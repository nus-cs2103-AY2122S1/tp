package seedu.academydirectory.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.academydirectory.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AttendanceTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Attendance(null));
    }

    @Test
    public void constructor_invalidAttendance_throwsNegativeArraySizeException() {
        Integer invalidAttendance = -1;
        assertThrows(NegativeArraySizeException.class, () -> new Attendance(invalidAttendance));
    }

    @Test
    public void isValidAttendance() {
        final String zero = "0";
        final String one = "1";

        assertTrue(Attendance.isValidAttendance(zero));
        assertTrue(Attendance.isValidAttendance(one));

        assertFalse(Attendance.isValidAttendance("-1"));
        assertFalse(Attendance.isValidAttendance("a"));
        assertFalse(Attendance.isValidAttendance(" "));
        assertFalse(Attendance.isValidAttendance(""));
        assertFalse(Attendance.isValidAttendance("."));
    }

    @Test
    public void getSessionCount() {
        Attendance attendanceFive = new Attendance(5);
        assertTrue(attendanceFive.getSessionCount() == 5);

        Attendance attendanceHundred = new Attendance(100);
        assertTrue(attendanceHundred.getSessionCount() == 100);
    }

    @Test
    public void equals() {

        Attendance attendance = new Attendance(4);

        assertTrue(attendance.equals(attendance));

        Attendance attendanceCopy = new Attendance(4);
        Attendance attendanceDiff = new Attendance(5);

        assertTrue(attendance.equals(attendanceCopy));
        assertFalse(attendance.equals(attendanceDiff));

    }
}
