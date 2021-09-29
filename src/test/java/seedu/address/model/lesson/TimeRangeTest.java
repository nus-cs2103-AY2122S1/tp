package seedu.address.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeRangeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TimeRange(null, null));
    }

    @Test
    public void constructor_invalidTimeRange_throwsIllegalArgumentException() {
        Time validStartTime = new Time("12:00");
        Time validEndTime = new Time("10:00");
        assertThrows(IllegalArgumentException.class, () ->
            new TimeRange(validStartTime, validEndTime));
    }

    @Test
    public void isValidTimeRange() {
        // null date
        assertThrows(NullPointerException.class, () -> TimeRange.isValidTimeRange(null, null));

        // invalid time range
        assertFalse(TimeRange.isValidTimeRange(new Time("23:00"),
            new Time("19:00"))); // end time earlier than start

        // valid time
        assertTrue(TimeRange.isValidTimeRange(new Time("13:00"),
            new Time("14:30")));
    }

    @Test
    public void isClashing_timeRange() {
        TimeRange timeRange = new TimeRange(new Time("09:00"), new Time("12:00"));
        TimeRange clashingTimeRange = new TimeRange(new Time("09:00"), new Time("10:00"));
        TimeRange nonClashingTimeRange = new TimeRange(new Time("13:00"), new Time("15:00"));

        // time ranges not clashing
        assertFalse(timeRange.isClashing(nonClashingTimeRange));

        // time ranges clashing
        assertTrue(timeRange.isClashing(clashingTimeRange));
    }
}
