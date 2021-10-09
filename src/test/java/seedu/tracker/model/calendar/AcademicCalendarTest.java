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
    public void equals_academicCalendarWithSameYearSemester_true() {
        AcademicYear academicYear = new AcademicYear(2);
        Semester semester = new Semester(1);
        AcademicCalendar academicCalendar1 = new AcademicCalendar(academicYear, semester);
        AcademicCalendar academicCalendar2 = new AcademicCalendar(academicYear, semester);
        assertEquals(true, academicCalendar1.equals(academicCalendar2));
    }

    @Test
    public void equals_sameValueDifferentAddress_true() {
        AcademicYear academicYear1 = new AcademicYear(2);
        Semester semester1 = new Semester(1);
        AcademicYear academicYear2 = new AcademicYear(2);
        Semester semester2 = new Semester(1);
        AcademicCalendar academicCalendar1 = new AcademicCalendar(academicYear1, semester1);
        AcademicCalendar academicCalendar2 = new AcademicCalendar(academicYear2, semester2);
        assertEquals(true, academicCalendar1.equals(academicCalendar2));
    }

    @Test
    public void equals_differentValue_false() {
        AcademicYear academicYear1 = new AcademicYear(2);
        Semester semester1 = new Semester(1);
        AcademicYear academicYear2 = new AcademicYear(3);
        Semester semester2 = new Semester(2);
        AcademicCalendar academicCalendar1 = new AcademicCalendar(academicYear1, semester1);
        AcademicCalendar academicCalendar2 = new AcademicCalendar(academicYear2, semester2);
        assertEquals(false, academicCalendar1.equals(academicCalendar2));
    }

}
