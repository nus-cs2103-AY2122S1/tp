package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.module.ModuleName;

public class EditModuleDescriptorTest {

    @Test
    public void equals() {
        ModuleName standardModuleName = new ModuleName(MODULE_NAME_0);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(standardModuleName);

        //same object -> returns true
        assertTrue(editModuleDescriptor.equals(editModuleDescriptor));

        //same values -> returns true
        EditModuleDescriptor editModuleDescriptorCopy = new EditModuleDescriptor(editModuleDescriptor);
        assertTrue(editModuleDescriptor.equals(editModuleDescriptorCopy));

        //different types -> returns false
        assertFalse(editModuleDescriptor.equals(1));

        //null -> returns false
        assertFalse(editModuleDescriptor.equals(null));

        //different module name -> returns false
        EditModuleDescriptor differentModuleName = new EditModuleDescriptor();
        differentModuleName.setModuleName(new ModuleName(MODULE_NAME_1));
        assertFalse(editModuleDescriptor.equals(differentModuleName));
    }
}
