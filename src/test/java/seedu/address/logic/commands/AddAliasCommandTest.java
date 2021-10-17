package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class AddAliasCommandTest {

    @Test
    public void constructor_null_exceptionThrown() {
        assertThrows(NullPointerException.class, () -> new AddAliasCommand(null, null));
    }

    @Test
    public void execute_addAlias_success() {
        ModelManager model = new ModelManager();
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addAlias("lf", "listf");
        String expectedMessage = String.format(AddAliasCommand.MESSAGE_SUCCESS, "listf", "lf");
        assertCommandSuccess(new AddAliasCommand("lf", "listf"),
                model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidCommand_throwsCommandException() {
        ModelManager model = new ModelManager();
        assertCommandFailure(new AddAliasCommand("listf", "random"), model,
                AddAliasCommand.MESSAGE_INVALID_COMMAND_WORD);
    }

    @Test
    public void equals() {
        AddAliasCommand command1 = new AddAliasCommand("lf", "listf");
        AddAliasCommand command2 = new AddAliasCommand("lyf", "listf");
        AddAliasCommand command1Copy = new AddAliasCommand("lf", "listf");

        assertTrue(command1.equals(command1));
        assertTrue(command1.equals(command1Copy));
        assertFalse(command1.equals(command2));
        assertFalse(command1.equals(null));
        assertFalse(command1.equals("10"));
    }

}
