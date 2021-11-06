package seedu.edrecord.model.module;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.edrecord.testutil.TypicalModules.CS2103;
import static seedu.edrecord.testutil.TypicalModules.CS3230;

import org.junit.jupiter.api.Test;

import seedu.edrecord.model.group.Group;


public class ModuleSetTest {
    @Test
    public void isValidModuleAndGroup() {
        ModuleSet moduleSet = new ModuleSet();
        moduleSet.addToSet(CS2103);
        Module validModule = new Module("CS2103"); // does not contain classes or assignments
        Group validGroupInValidModule = new Group("T03");

        assertTrue(moduleSet.containsModule(validModule));
        assertTrue(moduleSet.containsGroupInModule(validModule, validGroupInValidModule));
    }

    @Test
    public void isInvalidModuleAndGroup() {
        ModuleSet moduleSet = new ModuleSet();
        moduleSet.addToSet(CS2103);
        Module invalidModule = new Module("CS2105");
        Group invalidGroup = new Group("T15");
        invalidModule.addGroup(invalidGroup);

        assertFalse(moduleSet.containsModule(invalidModule));
        assertFalse(moduleSet.containsGroupInModule(invalidModule, invalidGroup));
    }

    @Test
    public void isValidAddAll() {
        ModuleSet moduleSet = new ModuleSet();
        moduleSet.addToSet(CS2103);
        moduleSet.addToSet(CS3230);
        ModuleSet cloneSet = new ModuleSet();
        cloneSet.addAll(moduleSet);
        assertEquals(moduleSet, cloneSet);

        cloneSet.addAll(moduleSet);
        assertEquals(moduleSet, cloneSet); // still contains the same modules.
    }
    @Test
    public void isInvalidEquals() {
        ModuleSet moduleSet = new ModuleSet();
        moduleSet.addToSet(CS2103);
        ModuleSet cloneSet = new ModuleSet();
        cloneSet.addToSet(new Module("CS2103"));
        assertNotEquals(moduleSet, cloneSet);
    }

    @Test
    public void isInvalidRemovalOfGroup() {
        ModuleSet moduleSet = new ModuleSet();
        moduleSet.addToSet(CS2103); // Contains groups T07 and T03
        Module moduleWithNoGroup = new Module("CS2105");
        moduleSet.addToSet(moduleWithNoGroup);
        moduleSet.removeGroup(moduleWithNoGroup, new Group("T07"));
        assertTrue(moduleSet.containsGroupInModule(CS2103, new Group("T07")));
    }
}
