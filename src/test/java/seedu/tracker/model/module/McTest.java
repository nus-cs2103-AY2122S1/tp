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
    void isValidModuleMc() {
        // invalid Module Mc
        assertFalse(Mc.isValidModuleMc(-1)); // negative value
        assertFalse(Mc.isValidModuleMc(21)); // too high

        // valid Module Mc
        assertTrue(Mc.isValidModuleMc(0)); // zero
        assertTrue(Mc.isValidModuleMc(1));
    }

    @Test
    void isValidMcGoal() {
        // invalid Mc Goal
        assertFalse(Mc.isValidMcGoal(-3)); // negative value
        assertFalse(Mc.isValidMcGoal(0)); // zero
        assertFalse(Mc.isValidMcGoal(1000)); //too high

        // valid Mc Goal
        assertTrue(Mc.isValidMc(1)); // minimum goal
        assertTrue(Mc.isValidMc(999)); // maximum goal
        assertTrue(Mc.isValidMc(8));
    }

    @Test
    void testToString() {
    }

    @Test
    void testEquals() {
    }
}
