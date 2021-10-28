package seedu.address.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.EditModuleCommand.EditModuleDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.module.Module;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.TypicalModules;

import static seedu.address.testutil.TypicalModules.*;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;


public class EditModuleCommandTest {

    private Model model = new ModelManager(TypicalModules.getTypicalBuddy(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        //editing from CS2103 to CS2100
        Module editedModule = MODULE_2;
        ModuleName initialModuleName = new ModuleName(MODULE_NAME_0);
        ModuleName editedModuleName = new ModuleName(MODULE_NAME_1);
        EditModuleDescriptor editModuleDescriptor = new EditModuleDescriptor();
        editModuleDescriptor.setModuleName(editedModuleName);
        EditModuleCommand editCommand = new EditModuleCommand(initialModuleName, editModuleDescriptor);

        String expectedMessage = String.format(Messages.MESSAGE_EDIT_MODULE_SUCCESS, editedModuleName);

        Model expectedModel = new ModelManager();
        expectedModel.addModule(editedModule);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
