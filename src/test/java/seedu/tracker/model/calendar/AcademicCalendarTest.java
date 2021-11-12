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
    public void isBeforeTest() {
        AcademicYear year3 = new AcademicYear(3);
        AcademicYear year1 = new AcademicYear(1);

        Semester semester1 = new Semester(1);
        Semester semester2 = new Semester(2);
        AcademicCalendar y3s1 = new AcademicCalendar(year3, semester1);
        AcademicCalendar y3s1Copy = new AcademicCalendar(year3, semester1);

        AcademicCalendar y3s2 = new AcademicCalendar(year3, semester2);
        AcademicCalendar y1s2 = new AcademicCalendar(year1, semester2);

        //same semester -> false
        assertEquals(false, y3s1.isBefore(y3s1Copy));

        //y3s1 before y3s2 -> true
        assertEquals(true, y3s1.isBefore(y3s2));

        //y3s1 not before y1s2 -> false
        assertEquals(false, y3s1.isBefore(y1s2));

        //y1s1 is before y3s1 -> true
        assertEquals(true, y1s2.isBefore(y3s1));
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

    @Test
    public void compareToTest() {
        AcademicCalendar academicCalendarY1S2 = new AcademicCalendar(new AcademicYear(1), new Semester(2));
        AcademicCalendar academicCalendarY1S1 = new AcademicCalendar(new AcademicYear(1), new Semester(1));

        // left < right -> negative
        int result = academicCalendarY1S1.compareTo(academicCalendarY1S2);
        assertEquals(true, result < 0);

        // left > right -> positive
        result = academicCalendarY1S2.compareTo(academicCalendarY1S1);
        assertEquals(true, result > 0);

        // left = right -> 0
        AcademicCalendar anotherAcademicCalendarY1S2 = new AcademicCalendar(new AcademicYear(1), new Semester(2));
        result = anotherAcademicCalendarY1S2.compareTo(academicCalendarY1S2);
        assertEquals(true, result == 0);
    }

}
