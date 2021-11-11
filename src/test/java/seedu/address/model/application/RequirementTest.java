package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RequirementTest {
    @Test
    public void isValidRequirement() {
        // null requirement
        assertThrows(NullPointerException.class, () -> Requirement.isValidRequirement(null));

        // invalid requirement
        assertFalse(Requirement.isValidRequirement("")); // empty string
        assertFalse(Requirement.isValidRequirement(" ")); // spaces only
        assertFalse(Requirement.isValidRequirement(" invalid requirement")); // begins with whitespace
        assertFalse(Requirement.isValidRequirement("very long requirement that is over 20 chars")); // too long

        // valid requirement
        assertTrue(Requirement.isValidRequirement("resume")); // alphabets only
        assertTrue(Requirement.isValidRequirement("100")); // numbers only
        assertTrue(Requirement.isValidRequirement("resume100")); // alphanumeric characters
        assertTrue(Requirement.isValidRequirement("CV")); // with capital letters
        assertTrue(Requirement.isValidRequirement("long requirement")); // multiple words
    }

    @Test
    public void equals() {
        Requirement r = new Requirement("CV");

        // same object -> returns true
        assertTrue(r.equals(r));

        // different types -> returns false
        assertFalse(r.equals(1));

        // null -> returns false
        assertFalse(r.equals(null));

        // same value -> returns true
        assertTrue(r.equals(new Requirement("CV")));

        // different value -> returns false
        assertFalse(r.equals(new Requirement("hackathon")));
    }
}
