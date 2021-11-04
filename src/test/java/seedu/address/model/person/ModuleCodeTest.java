package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class ModuleCodeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null, null));
    }

    @Test
    public void constructor_invalidModuleCode_throwsIllegalArgumentException() {
        String invalidModuleCode = "1521";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidModuleCode, new HashSet<>()));
    }

    @Test
    public void isValidModuleCode() {
        // null module code
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModuleCode(null));

        // invalid module code
        assertFalse(ModuleCode.isValidModuleCode("1101S")); // no prefix
        assertFalse(ModuleCode.isValidModuleCode("CS100")); // 3 digit in module code
        assertFalse(ModuleCode.isValidModuleCode("CS10101")); // 5 digit in module code
        assertFalse(ModuleCode.isValidModuleCode("CS1010XX")); // 2-letter suffix

        // valid module code
        assertTrue(ModuleCode.isValidModuleCode("CS1010")); // 2-letter prefix
        assertTrue(ModuleCode.isValidModuleCode("ACC4000")); // 3-letter prefix
        assertTrue(ModuleCode.isValidModuleCode("CS2103T")); // 1-letter suffix
    }
}
