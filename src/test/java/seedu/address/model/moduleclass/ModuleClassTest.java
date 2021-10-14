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
        ModuleClass editedCS2100Lab = new ModuleClassBuilder(CS2100_LAB1).withRemark("hello").build();
        assertTrue(CS2100_LAB1.isSameModuleClass(editedCS2100Lab));

        // name differs in case, all other attributes same -> returns false
        editedCS2100Lab = new ModuleClassBuilder(CS2100_LAB1).withModuleCode("cs2100").build();
        assertFalse(CS2100_LAB1.isSameModuleClass(editedCS2100Lab));
    }

    @Test
    public void equals() {
        ModuleClass cs2103Tut1Copy = new ModuleClassBuilder(CS2103_TUT1).build();
        assertEquals(CS2103_TUT1, cs2103Tut1Copy);

        assertEquals(CS2100_TUT1, CS2100_TUT1);

        assertNotEquals(CS2100_TUT1, null);

        assertNotEquals(CS2100_TUT1, 5);

        assertNotEquals(CS2103_TUT1, CS2100_TUT1);

    }

    @Test
    public void toStringTest() {
        assertEquals(CS2100_LAB1.toString(), "Module: [CS2100]; Day: Tuesday; Time: 15:00; Remark: COM1 0113");

        assertEquals(CS2100_TUT1.toString(), "Module: [CS2100]; Day: Wednesday; Time: 17:00; Remark: COM1 01-20");
    }


}
