package seedu.plannermd.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.plannermd.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.plannermd.logic.commands.ClearCommand;

class SessionTest {

    private final String validTime = "10:00";
    private final String validTime2 = "20:30";
    private final String invalidTime = "30:00";
    private final String invalidTime2 = "abc123";
    private final Duration duration = new Duration(15);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Session(null, null));
        assertThrows(NullPointerException.class, () -> new Session(null, duration));
        assertThrows(NullPointerException.class, () -> new Session(validTime, null));
    }

    @Test
    public void constructor_invalidStartTime_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Session(invalidTime, duration));
    }

    @Test
    public void isValidTime_validStartTime_success() {
        assertTrue(Session.isValidTime(validTime));
        assertTrue(Session.isValidTime(validTime2));
    }

    @Test
    public void isValidTime_invalidStartTime_failure() {
        assertFalse(Session.isValidTime(invalidTime));
        assertFalse(Session.isValidTime(invalidTime2));
    }

    @Test
    public void isValidTime_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Session.isValidTime(null));
    }

    @Test
    public void isClash_clash_returnsTrue() {
        Session s1 = new Session("12:00", duration);
        Session s2 = new Session("12:05", duration);
        Session s3 = new Session("11:00", new Duration(120));
        Session s4 = new Session("12:10", new Duration(1));
        assertTrue(s1.isClash(s2));
        assertTrue(s1.isClash(s3));
        assertTrue(s1.isClash(s4));
    }

    @Test
    public void isClash_noClash_returnsFalse() {
        Session s1 = new Session("12:00", duration);
        Session s2 = new Session("12:20", duration);
        Session s3 = new Session("11:00", duration);
        assertFalse(s1.isClash(s2));
        assertFalse(s1.isClash(s3));
    }

    @Test
    public void getFormattedStartTime() {
        assertEquals("10:00", new Session(validTime, duration).getFormattedStartTime());
    }

    @Test
    public void getFormattedEndTime() {
        assertEquals("10:15", new Session(validTime, duration).getFormattedEndTime());
    }

    @Test
    public void equals() {
        final Session session = new Session(validTime, duration);

        // same values -> returns true
        Session copySession = new Session(validTime, duration);
        assertEquals(session, copySession);

        // same object -> returns true
        assertEquals(session, session);

        // null -> returns false
        assertNotEquals(null, session);

        // different types -> returns false
        assertNotEquals(session, new ClearCommand());

        // different start time -> returns false
        assertNotEquals(session, new Session(validTime2, duration));

        // different duration -> returns false
        assertNotEquals(session, new Session(validTime, new Duration(60)));
    }
}
