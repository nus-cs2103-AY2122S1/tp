package seedu.address.model.moduleclass;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModuleClasses.CS2100_LAB1;
import static seedu.address.testutil.TypicalModuleClasses.CS2100_TUT1;
import static seedu.address.testutil.TypicalModuleClasses.CS2103_TUT1;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.ModuleClassBuilder;

public class ModuleClassTest {

    @Test
    public void isSameModuleClass() {
        assertTrue(CS2100_LAB1.isSameModuleClass(CS2100_LAB1));

        assertFalse(CS2100_TUT1.isSameModuleClass(null));

        // same module code, all other attributes same -> return true
        ModuleClass editedCS2100_LAB1 = new ModuleClassBuilder(CS2100_LAB1).withRemark("hello").build();
        assertTrue(CS2100_LAB1.isSameModuleClass(editedCS2100_LAB1));

        // name differs in case, all other attributes same -> returns false
        editedCS2100_LAB1 = new ModuleClassBuilder(CS2100_LAB1).withModuleCode("cs2100").build();
        assertFalse(CS2100_LAB1.isSameModuleClass(editedCS2100_LAB1));
    }

    @Test
    public void equals() {
        ModuleClass CS2103_TUT1_COPY = new ModuleClassBuilder(CS2103_TUT1).build();
        assertEquals(CS2103_TUT1, CS2103_TUT1_COPY);

        assertEquals(CS2100_TUT1, CS2100_TUT1);

        assertNotEquals(CS2100_TUT1, null);

        assertNotEquals(CS2100_TUT1, 5);

        assertNotEquals(CS2103_TUT1, CS2100_TUT1);

    }

}
