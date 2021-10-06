package seedu.plannermd.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class FlagTest {

    private static final String FLAG_DELETE = "-d";
    private final Flag flag = new Flag(FLAG_DELETE);

    @Test
    void getFlag() {
        assertEquals(FLAG_DELETE, flag.getFlag());
    }

    @Test
    void testToString() {
        assertEquals(FLAG_DELETE, flag.toString());
    }

    @Test
    void equals() {
        // same values -> returns true
        Flag copyFlag = new Flag(FLAG_DELETE);
        assertEquals(flag, copyFlag);

        // different values -> returns false
        assertNotEquals(flag, new Flag("-a"));

        // same object -> returns true
        assertEquals(flag, flag);

        // null -> returns false
        assertNotEquals(null, flag);
    }
}
