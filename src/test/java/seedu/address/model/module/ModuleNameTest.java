package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.*;

import org.junit.jupiter.api.Test;

class ModuleNameTest {
    private final ModuleName moduleName1 = new ModuleName(MODULE_NAME_0);
    private final ModuleName moduleName2 = new ModuleName(MODULE_NAME_1);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidName = " ";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidName));
    }

    @Test
    void isValidName() {
        // name follows the NUS module code format -> returns true
        assertTrue(ModuleName.isValidName(MODULE_NAME_0));

        // blank -> returns false
        assertFalse(ModuleName.isValidName(" "));

        // starts with a space -> false
        assertFalse(ModuleName.isValidName(" CS2103"));

        // contains other symbols -> returns false
        assertFalse(ModuleName.isValidName("CS2103#"));

        // does not start with 2-4 letters -> returns false
        assertFalse(ModuleName.isValidName("A2103"));

        // does not 4 digits -> returns false
        assertFalse(ModuleName.isValidName("CS210"));

        // does not start with 0-4 letters -> returns false
        assertFalse(ModuleName.isValidName("CS2103AAAAA"));
    }

    @Test
    void testEquals() {
    }
}
