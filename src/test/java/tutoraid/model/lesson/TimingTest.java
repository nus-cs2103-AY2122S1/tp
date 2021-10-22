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
        String invalidTiming = "123*";
        Assert.assertThrows(IllegalArgumentException.class, () -> new Timing(invalidTiming));
    }

    @Test
    public void isValidTiming() {
        // null Timing number
        Assert.assertThrows(NullPointerException.class, () -> Timing.isValidTiming(null));

        // invalid Timing numbers
        assertFalse(Timing.isValidTiming("")); // empty string

        // valid Timing numbers
        assertTrue(Timing.isValidTiming("0800-1000")); // exactly 3 numbers
        assertTrue(Timing.isValidTiming("17/2 9-10")); // forward slash
        assertTrue(Timing.isValidTiming("Monday 9am to 11am")); // long Timing numbers
    }
}
