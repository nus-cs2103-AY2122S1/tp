package seedu.times.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.times.ui.TabName;

class ViewCommandTest {

    @Test
    void testEquals() {
        ViewCommand viewTimetable = new ViewCommand(TabName.TIMETABLE);
        ViewCommand viewStudents = new ViewCommand(TabName.STUDENTS);
        ViewCommand viewClasses = new ViewCommand(TabName.CLASSES);

        //same object
        assertTrue(viewClasses.equals(viewClasses));
        assertTrue(viewTimetable.equals(viewTimetable));
        assertTrue(viewStudents.equals(viewStudents));

        //same values
        assertTrue(new ViewCommand(TabName.TIMETABLE).equals(viewTimetable));
        assertTrue(new ViewCommand(TabName.STUDENTS).equals(viewStudents));
        assertTrue(new ViewCommand(TabName.CLASSES).equals(viewClasses));

        //different types
        assertFalse(viewClasses.equals(1));

        //null
        assertFalse(viewClasses.equals(null));

        //different values
        assertFalse(viewClasses.equals(viewStudents));

    }
}
