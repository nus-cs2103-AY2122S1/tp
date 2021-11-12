package seedu.tracker.model.calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tracker.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class SemesterTest {

    @Test
    public void constructor_invalidSemester_throwsIllegalArgumentException() {
        int invalidSemesterZero = 0;
        assertThrows(IllegalArgumentException.class, () -> new Semester(invalidSemesterZero));
    }

    @Test
    public void isValidSemester() {
        // null tag name
        int invalidSemesterZero = 0;
        int invalidSemesterFive = 5;
        int validSemesterOne = 4;
        int validSemesterFour = 4;
        assertEquals(false, Semester.isValidSemester(invalidSemesterZero));
        assertEquals(false, Semester.isValidSemester(invalidSemesterFive));
        assertEquals(true, Semester.isValidSemester(validSemesterOne));
        assertEquals(true, Semester.isValidSemester(validSemesterFour));
    }

    @Test
    public void testToString() {
        assertEquals("1", new Semester(1).toString());
    }

    @Test
    public void equalsTest() {
        //same value, different object -> true
        Semester test1 = new Semester(1);
        Semester test2 = new Semester(1);
        assertEquals(true, test1.equals(test2));

        //same object -> true
        assertEquals(true, test1.equals(test1));

        //null -> false
        assertEquals(false, test1.equals(null));

        //different value -> false
        test2 = new Semester(2);
        assertEquals(false, test1.equals(test2));
    }

    @Test
    public void equals_differentSemester_false() {
        Semester test1 = new Semester(1);
        Semester test2 = new Semester(2);
        assertEquals(false, test1.equals(test2));
    }

    @Test
    public void compareToTest() {
        Semester semester1 = new Semester(1);
        Semester semester4 = new Semester(4);

        // left < right -> negative
        int result = semester1.compareTo(semester4);
        assertEquals(true, result < 0);

        // left > right -> positive
        result = semester4.compareTo(semester1);
        assertEquals(true, result > 0);

        // left = right -> 0
        result = semester1.compareTo(semester1);
        assertEquals(true, result == 0);
    }

}
