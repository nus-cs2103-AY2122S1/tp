package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalModules.MODULE_1;
import static seedu.address.testutil.TypicalModules.MODULE_2;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.TeachingAssistantBuddy;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleName;
import seedu.address.testutil.TypicalModules;

public class AddModuleCommandTest {
    private TeachingAssistantBuddy testBuddy = TypicalModules.getTypicalBuddy();
    private Model model = new ModelManager(testBuddy, new UserPrefs());

    @Test
    public void constructor_nullModule_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddModuleCommand(null));
    }

    @Test
    public void execute_validModuleName_addSuccessful() throws CommandException {
        ModuleName validModuleName = new ModuleName("CS2105");
        Module moduleToAdd = new Module(validModuleName);
        Model expectedModel = new ModelManager();

        for (Module module : model.getFilteredModuleList()) {
            expectedModel.addModule(module);
        }

        CommandResult commandResult = new AddModuleCommand(moduleToAdd).execute(expectedModel);
        assertEquals(String.format(AddModuleCommand.MESSAGE_SUCCESS, moduleToAdd),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(MODULE_1, MODULE_2, moduleToAdd),
                expectedModel.getFilteredModuleList());
    }

    @Test
    public void execute_duplicateModule_throwsCommandException() {
        AddModuleCommand addModuleCommand = new AddModuleCommand(MODULE_1);

        assertThrows(CommandException.class, AddModuleCommand.MESSAGE_DUPLICATE_MODULE, () ->
                addModuleCommand.execute(model));
    }

    @Test
    public void equals() {
        Module module1 = MODULE_1;
        Module module2 = MODULE_2;
        AddModuleCommand addCS2103ModuleCommand = new AddModuleCommand(module1);
        AddModuleCommand addCS2100ModuleCommand = new AddModuleCommand(module2);

        //same object -> returns true
        assertTrue(addCS2100ModuleCommand.equals(addCS2100ModuleCommand));

        //same values -> returns true
        AddModuleCommand addCS2100ModuleCommandCopy = new AddModuleCommand(module2);
        assertTrue(addCS2100ModuleCommand.equals(addCS2100ModuleCommandCopy));

        //different types -> returns false
        assertFalse(addCS2100ModuleCommand.equals(1));

        //null -> returns false
        assertFalse(addCS2100ModuleCommand.equals(null));

        //different modules -> returns false
        assertFalse(addCS2100ModuleCommand.equals(addCS2103ModuleCommand));
    }
}
