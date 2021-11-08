package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeSlotTest {

    @Test
    public void constructor_startTimeNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeSlot(null, "1500"));
    }

    @Test
    public void constructor_endTimeNull_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeSlot("1500", null));
    }

    @Test
    public void constructor_invalidStartTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(invalidTime, "1500"));
    }

    @Test
    public void constructor_invalidEndTime_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot("1500", invalidTime));
    }

    @Test
    public void constructor_endTimeBeforeStartTime_throwsIllegalArgumentException() {
        String startTime = "1300";
        String endTime = "1200";
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(startTime, endTime));
    }

    @Test
    public void constructor_endTimeSameAsStartTime_throwsIllegalArgumentException() {
        String startTime = "1300";
        String endTime = "1300";
        assertThrows(IllegalArgumentException.class, () -> new TimeSlot(startTime, endTime));
    }

    @Test
    public void isValidTimeSlot() {
        // null startTime
        assertThrows(NullPointerException.class, () -> TimeSlot.isValidTimeSlot(null, "1300"));
        // null endTime
        assertThrows(NullPointerException.class, () -> TimeSlot.isValidTimeSlot("1300", null));

        // blank startTime
        assertFalse(TimeSlot.isValidTimeSlot("", "1300")); // empty string
        assertFalse(TimeSlot.isValidTimeSlot(" ", "1300")); // spaces only
        // blank endTime
        assertFalse(TimeSlot.isValidTimeSlot("1300", "")); // empty string
        assertFalse(TimeSlot.isValidTimeSlot("1300", " ")); // spaces only

        // invalid startTime
        assertFalse(TimeSlot.isValidTimeSlot("15", "1600")); // missing minutes
        assertFalse(TimeSlot.isValidTimeSlot("04:30", "0500")); // ':' not allowed in time

        // invalid endTime
        assertFalse(TimeSlot.isValidTimeSlot("1400", "15")); // missing minutes
        assertFalse(TimeSlot.isValidTimeSlot("0400", "04:30")); // ':' not allowed in time

        // invalid startTime endTime combo
        assertFalse(TimeSlot.isValidTimeSlot("1300", "1200")); // endTime comes before startTime
        assertFalse(TimeSlot.isValidTimeSlot("1300", "1300")); // startTime same as endTime

        // valid TimeSlot
        assertTrue(TimeSlot.isValidTimeSlot("2030", "2130"));
        assertTrue(TimeSlot.isValidTimeSlot("0715", "0815"));

    }

    @Test
    public void compareTo() {
        TimeSlot earlierSlot = new TimeSlot("1300", "1400");
        TimeSlot sameAsEarlierSlot = new TimeSlot("1300", "1400");
        TimeSlot laterSlot = new TimeSlot("1400", "1500");
        assertTrue(earlierSlot.compareTo(laterSlot) < 0);
        assertEquals(0, earlierSlot.compareTo(sameAsEarlierSlot));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TimeSlot timeSlotA = new TimeSlot("0800", "0900");
        TimeSlot timeSlotACopy = new TimeSlot("0800", "0900");
        TimeSlot timeSlotB = new TimeSlot("1000", "1100");
        assertTrue(timeSlotA.equals(timeSlotACopy));

        // same object -> returns true
        assertTrue(timeSlotA.equals(timeSlotA));

        // null -> returns false
        assertFalse(timeSlotA.equals(null));

        // different type -> returns false
        assertFalse(timeSlotA.equals(5));

        // different timeslot -> returns false
        assertFalse(timeSlotA.equals(timeSlotB));

        // different startTime -> returns false
        TimeSlot editedTimeSlotA = new TimeSlot("0830", "0900");
        assertFalse(timeSlotA.equals(editedTimeSlotA));

        // different endTime -> returns false
        editedTimeSlotA = new TimeSlot("0800", "0830");
        assertFalse(timeSlotA.equals(editedTimeSlotA));
    }

    @Test
    public void isOverlappingWith() {
        TimeSlot toBeComparedSlot = new TimeSlot("1300", "1500");
        TimeSlot notOverlappingSlot1 = new TimeSlot("1200", "1300");
        TimeSlot notOverlappingSlot2 = new TimeSlot("1500", "1600");
        TimeSlot overlappingSlot1 = new TimeSlot("1400", "1600");
        TimeSlot overlappingSlot2 = new TimeSlot("1200", "1400");
        TimeSlot overlappingSlot3 = new TimeSlot("1330", "1400");
        TimeSlot overlappingSlot4 = new TimeSlot("1200", "1600");

        assertFalse(toBeComparedSlot.isOverlappingWith(notOverlappingSlot1));
        assertFalse(toBeComparedSlot.isOverlappingWith(notOverlappingSlot2));
        assertTrue(toBeComparedSlot.isOverlappingWith(overlappingSlot1));
        assertTrue(toBeComparedSlot.isOverlappingWith(overlappingSlot2));
        assertTrue(toBeComparedSlot.isOverlappingWith(overlappingSlot3));
        assertTrue(toBeComparedSlot.isOverlappingWith(overlappingSlot4));
    }
}
