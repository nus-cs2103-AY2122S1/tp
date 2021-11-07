package seedu.address.model.tuition;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ClassLimitTest {

    @Test
    public void isValidLimit() {
        // invalid limit numbers
        assertFalse(ClassLimit.isValid(0)); // less than 1
        assertFalse(ClassLimit.isValid(-10)); // less than 1

        // valid limit numbers
        assertTrue(ClassLimit.isValid(10)); // larger than 0
        assertTrue(ClassLimit.isValid(20)); // larger than 0
    }
}
