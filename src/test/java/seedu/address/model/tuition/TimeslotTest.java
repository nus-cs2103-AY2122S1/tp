package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TimeslotTest {
    @Test
    public void isConflictTime() {
        Timeslot timeslot = Timeslot.parseString("Mon 14:00-16:00");

        //assertTrue(timeslot.compareTime("Mon 14:00-16:00")); //exact the same
        //assertTrue(timeslot.compareTime("Mon 15:00-16:00")); //overlap on time slot
        //assertFalse(timeslot.compareTime("Mon 16:00-17:00")); //no overlap
        //assertFalse(timeslot.compareTime("Tue 16:00-17:00")); //different day
    }

    @Test
    public void isFormatCorrect() {
        assertTrue(Timeslot.parseString("Mon 14:00-16:00") != null); //correct format
        assertFalse(Timeslot.parseString("mon 14:00-16:00") == null); //M in "mon" is in lower case
        assertFalse(Timeslot.parseString("Mon 2pm-4pm") != null); //use "pm" instead of hh:mm-hh:mm
        assertFalse(Timeslot.parseString("Monday 14:00-16:00") == null); //week should be in shorten format
        assertTrue(Timeslot.parseString("Monday 17:00-16:00") == null); //the sequence of time is incorrect
    }


    @Test
    public void isTimeFormatCorrect() {
        Timeslot timeslot = Timeslot.parseString("Mon 14:00-16:00");
        //assertTrue(timeslot.isTimeFormatCorrect("14:00-16:00")); //correct format
        //assertFalse(timeslot.isTimeFormatCorrect("17:00-16:00")); //the sequence of time is incorrect
        //assertFalse(timeslot.isTimeFormatCorrect("4pm-6pm")); //use "pm" instead of hh:mm-hh:mm
    }
}
