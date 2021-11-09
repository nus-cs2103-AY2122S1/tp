package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_2;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.TypicalModules;

public class EditModuleCommandTest {

    private Model model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        //editing from CS2103 to CS2105
        ModuleName initialModuleName = new ModuleName(MODULE_NAME_0);
        ModuleName editedModuleName = new ModuleName("CS2105");
        Module editedModule = new Module(editedModuleName);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(editedModuleName);
        EditModuleCommand editCommand = new EditModuleCommand(initialModuleName, editModuleDescriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_MODULE_SUCCESS, initialModuleName);

        Model expectedModel = new ModelManager();
        expectedModel.addModule(editedModule);
        expectedModel.addModule(MODULE_2);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_moduleNotFound_failure() {
        ModuleName initialModuleName = new ModuleName("CS2105");
        ModuleName editedModuleName = new ModuleName("CS2102");
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(editedModuleName);
        EditModuleCommand editCommand = new EditModuleCommand(initialModuleName, editModuleDescriptor);
        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_MODULE_NAME_NOT_FOUND, "CS2105"));
    }

    @Test
    public void execute_duplicateModule_failure() {
        ModuleName initialModuleName = new ModuleName(MODULE_NAME_0);
        ModuleName editedModuleName = new ModuleName(MODULE_NAME_1);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(editedModuleName);
        EditModuleCommand editCommand = new EditModuleCommand(initialModuleName, editModuleDescriptor);
        assertCommandFailure(editCommand, model, String.format(Messages.MESSAGE_DUPLICATE_MODULE, MODULE_NAME_1));
    }

    @Test
    public void equals() {
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        ModuleName initialModuleName = new ModuleName(MODULE_NAME_0);
        ModuleName standardModuleName = new ModuleName(MODULE_NAME_1);
        editModuleDescriptor.setModuleName(standardModuleName);
        EditModuleCommand standardCommand = new EditModuleCommand(initialModuleName, editModuleDescriptor);

        //same values -> returns true
        EditModuleDescriptor editModuleDescriptorCopy = new EditModuleDescriptor(editModuleDescriptor);
        EditModuleCommand commandWithSameValues = new EditModuleCommand(initialModuleName, editModuleDescriptorCopy);
        assertTrue(standardCommand.equals(commandWithSameValues));

        //same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        //null -> returns false
        assertFalse(standardCommand.equals(null));

        //different type -> returns false
        assertFalse(standardCommand.equals(1));

        //different editModuleDescriptor -> returns false
        EditModuleDescriptor differentEditModuleDescriptor = new EditModuleDescriptor();
        differentEditModuleDescriptor.setModuleName(initialModuleName);
        assertFalse(standardCommand.equals(new EditModuleCommand(initialModuleName, differentEditModuleDescriptor)));

        //different moduleName -> returns false
        EditModuleCommand differentModuleName = new EditModuleCommand(standardModuleName, editModuleDescriptor);
        assertFalse(standardCommand.equals(differentModuleName));
    }
}
