package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_0;
import static seedu.address.testutil.TypicalModules.MODULE_NAME_1;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.TypicalModules;

class DeleteModuleCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void execute_validNameUnfilteredList_success() {
        Module moduleToDelete = model.getFilteredModuleList().get(0);

        DeleteCommand deleteCommand = new DeleteModuleCommand(new ModuleName(MODULE_NAME_0));

        String expectedMessage = String.format(DeleteModuleCommand.MESSAGE_DELETE_MODULE_SUCCESS, moduleToDelete);

        ModelManager expectedModel = new ModelManager(model.getBuddy(), new UserPrefs());
        expectedModel.deleteModule(moduleToDelete);

        assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteCommand deleteFirstCommand = new DeleteModuleCommand(new ModuleName(MODULE_NAME_0));
        DeleteCommand deleteSecondCommand = new DeleteModuleCommand(new ModuleName(MODULE_NAME_1));

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteCommand deleteFirstCommandCopy = new DeleteModuleCommand(new ModuleName(MODULE_NAME_0));
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different module -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

}
