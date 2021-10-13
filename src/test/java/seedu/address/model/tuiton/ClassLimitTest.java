package seedu.address.model.tuiton;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tuition.ClassLimit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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
