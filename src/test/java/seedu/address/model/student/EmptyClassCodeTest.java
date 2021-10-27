package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class EmptyClassCodeTest {
    @Test
    public void equals() {
        EmptyClassCode emptyClassCode = new EmptyClassCode();
        assertTrue(emptyClassCode.equals(emptyClassCode));

        assertTrue(emptyClassCode.equals(new EmptyClassCode()));

        assertTrue(emptyClassCode.equals(new ClassCode("G00")));

        assertFalse(emptyClassCode.equals(new ClassCode("G01")));
    }
}
