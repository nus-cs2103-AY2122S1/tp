package tutoraid.model.lesson;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import tutoraid.testutil.Assert;

public class TimingTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        Assert.assertThrows(NullPointerException.class, () -> new Timing(null));
    }

    @Test
    public void constructor_invalidTiming_throwsIllegalArgumentException() {
        String invalidTiming = " ";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Timing(invalidTiming));
    }

    @Test
    public void isValidTiming() {
        // null Timing input
        Assert.assertThrows(NullPointerException.class, () -> Timing.isValidTiming(null));

        // invalid Timing input
        assertFalse(Timing.isValidTiming("")); // empty string

        // valid Timing inputs
        assertTrue(Timing.isValidTiming("Monday 9am to 11am")); // alphanumeric characters
        assertTrue(Timing.isValidTiming("17/2 0800-1000")); // contains non-alphanumeric characters
    }
}
