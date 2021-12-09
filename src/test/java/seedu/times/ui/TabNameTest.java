package seedu.times.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class TabNameTest {

    @Test
    void getIndex() {
        assertEquals(0, TabName.STUDENTS.getIndex());
        assertEquals(1, TabName.CLASSES.getIndex());
        assertEquals(2, TabName.TIMETABLE.getIndex());

        assertNotEquals(1, TabName.STUDENTS.getIndex());
        assertNotEquals(2, TabName.STUDENTS.getIndex());

        assertNotEquals(0, TabName.CLASSES.getIndex());
        assertNotEquals(2, TabName.CLASSES.getIndex());

        assertNotEquals(0, TabName.TIMETABLE.getIndex());
        assertNotEquals(1, TabName.TIMETABLE.getIndex());
    }
}
