package seedu.tracker.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


class McTest {

    @Test
    void isValidMc() {
        // invalid Mc
        assertFalse(Mc.isValidMc(-3)); // negative value

        // valid Mc
        assertTrue(Mc.isValidMc(0)); // zero
        assertTrue(Mc.isValidMc(2));
        assertTrue(Mc.isValidMc(3));
        assertTrue(Mc.isValidMc(8));
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}
