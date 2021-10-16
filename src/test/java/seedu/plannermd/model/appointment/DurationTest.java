package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.ClearCommand;

class DurationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Duration(null));
    }

    @Test
    public void constructor_invalidDuration_throwsIllegalArgumentException() {
        Integer invalidDuration = 9999;
        assertThrows(IllegalArgumentException.class, () -> new Duration(invalidDuration));
    }

    @Test
    public void isValidDuration_validDuration_success() {
        // valid duration is 1-120
        assertTrue(Duration.isValidDuration(1));
        assertTrue(Duration.isValidDuration(10));
        assertTrue(Duration.isValidDuration(120));
    }

    @Test
    public void isValidDuration_invalidDuration_failure() {
        assertFalse(Duration.isValidDuration(-120));
        assertFalse(Duration.isValidDuration(0));
        assertFalse(Duration.isValidDuration(121));
    }

    @Test
    public void isValidDuration_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Duration.isValidDuration(null));
    }

    @Test
    public void getDefaultDuration() {
        assertEquals(Duration.DEFAULT_DURATION, Duration.getDefaultDuration().duration);
    }

    @Test
    public void equals() {
        final Duration duration = new Duration(15);

        // same values -> returns true
        Duration copyDuration = new Duration(15);
        assertEquals(duration, copyDuration);

        // same object -> returns true
        assertEquals(duration, duration);

        // null -> returns false
        assertNotEquals(null, duration);

        // different types -> returns false
        assertNotEquals(duration, new ClearCommand());

        // different duration -> returns false
        assertNotEquals(duration, new Duration(30));
    }
}
