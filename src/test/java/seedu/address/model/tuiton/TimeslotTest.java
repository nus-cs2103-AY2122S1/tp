package seedu.address.model.tuiton;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import seedu.address.model.tuition.Timeslot;

public class TimeslotTest {
    @Test
    public void isConflictTime() {
        Timeslot timeslot = new Timeslot("Mon 14:00-16:00");

        assertTrue(timeslot.compareTime("Mon 14:00-16:00"));//exact the same
        assertTrue(timeslot.compareTime("Mon 15:00-16:00"));//overlap on time slot
        assertFalse(timeslot.compareTime("Mon 16:00-17:00"));//no overlap
        assertFalse(timeslot.compareTime("Tue 16:00-17:00"));//different day
    }

    @Test
    public void isFormatCorrect() {
        assertTrue(new Timeslot("Mon 14:00-16:00").isFormatCorrect());//correct format
        assertFalse(new Timeslot("mon 14:00-16:00").isFormatCorrect());//M in "mon" is in lower case
        assertFalse(new Timeslot("Mon 2pm-4pm").isFormatCorrect());//use "pm" instead of hh:mm-hh:mm
        assertFalse(new Timeslot("Monday 14:00-16:00").isFormatCorrect());//week should be in shorten format
        assertFalse(new Timeslot("Monday 17:00-16:00").isFormatCorrect());//the sequence of time is incorrect
    }


    @Test
    public void isTimeFormatCorrect() {
        Timeslot timeslot = new Timeslot("Mon 14:00-16:00");
        assertTrue(timeslot.isTimeFormatCorrect("14:00-16:00"));//correct format
        assertFalse(timeslot.isTimeFormatCorrect("17:00-16:00"));//the sequence of time is incorrect
        assertFalse(timeslot.isTimeFormatCorrect("4pm-6pm"));//use "pm" instead of hh:mm-hh:mm


    }
}
