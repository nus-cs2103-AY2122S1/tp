package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Module(null));
    }

    @Test
    public void constructor_invalidModule_throwsIllegalArgumentException() {
        String invalidModule = "";
        assertThrows(IllegalArgumentException.class, () -> new Module(invalidModule));
    }

    @Test
    public void isValidModule() {
        // null Module
        assertThrows(NullPointerException.class, () -> Module.isValidModule(null));

        // invalid Modules
        assertFalse(Module.isValidModule("")); // empty string
        assertFalse(Module.isValidModule(" ")); // spaces only

        // valid Modules
        assertTrue(Module.isValidModule("GER1000")); // 3 letter prefix
        assertTrue(Module.isValidModule("CS1231")); // 2 letter prefix
        assertTrue(Module.isValidModule("CS2103T")); // 1 postfix
        assertTrue(Module.isValidModule("ACC2232T")); // 3 letter prefix + 1 postfix
    }
}
