package seedu.tracker.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class AcademicCalendarTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AcademicCalendar(null, null));
    }

    @Test
    public void equalsTest() {
        AcademicYear academicYear = new AcademicYear(1);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar1 = new AcademicCalendar(academicYear, semester);

        //same object -> true
        assertEquals(true, academicCalendar1.equals(academicCalendar1));

        //other type -> false
        assertEquals(false, academicCalendar1.equals(1));

        //same value, different object -> true
        AcademicCalendar academicCalendar2 = new AcademicCalendar(academicYear, semester);
        assertEquals(true, academicCalendar1.equals(academicCalendar2));

        //same value, different year and semester object -> true
        AcademicYear academicYear2 = new AcademicYear(1);
        Semester semester2 = new Semester(1);
        academicCalendar2 = new AcademicCalendar(academicYear2, semester2);
        assertEquals(true, academicCalendar1.equals(academicCalendar2));

        //different value -> false
        academicYear2 = new AcademicYear(4);
        semester2 = new Semester(3);
        academicCalendar2 = new AcademicCalendar(academicYear2, semester2);
        assertEquals(false, academicCalendar1.equals(academicCalendar2));

    }

    @Test
    public void hashCode_sameObject_sameResult() {
        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar = new AcademicCalendar(academicYear, semester);
        assertEquals(true, academicCalendar.hashCode() == academicCalendar.hashCode());
    }

    @Test
    public void hashCode_differentObject_differentResult() {
        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar1 = new AcademicCalendar(academicYear, semester);
        AcademicCalendar academicCalendar2 = new AcademicCalendar(academicYear, semester);
        assertEquals(true, academicCalendar1.hashCode() == academicCalendar2.hashCode());
    }

}
